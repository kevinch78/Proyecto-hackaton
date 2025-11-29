package com.recruiter.backend.application.services;

import com.recruiter.backend.domain.repository.CandidateRepository;
import com.recruiter.backend.domain.repository.JobRepository;
import com.recruiter.backend.domain.repository.AreaRepository;
import com.recruiter.backend.domain.service.WowRiskAiService;
import com.recruiter.backend.infraestructure.entitys.ApplicationEntity;
import com.recruiter.backend.infraestructure.entitys.CandidateEntity;
import com.recruiter.backend.infraestructure.entitys.JobEntity;
import com.recruiter.backend.infraestructure.entitys.AreaEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WowRiskAiServiceImpl implements WowRiskAiService {

    private final CandidateRepository candidateRepository;
    private final JobRepository jobRepository;
    private final AreaRepository areaRepository;

    @Override
    public ApplicationEntity analyzeApplication(ApplicationEntity application) {
        CandidateEntity candidate = application.getCandidate();
        JobEntity job = application.getJob();
        AreaEntity area = job.getArea();

        // Calculate Match Score
        double matchScore = calculateMatchScore(candidate, job);
        application.setMatchScore(matchScore);

        // Calculate Financial Risk
        double financialRisk = calculateFinancialRisk(candidate, job, area);
        application.setFinancialRisk(financialRisk);

        // Calculate Area Impact
        double areaImpact = calculateAreaImpact(candidate, job, area);
        application.setAreaImpact(areaImpact);

        // Generate AI Analysis and Recommendation (simplified for MVP)
        String aiAnalysis = generateAiAnalysis(application, matchScore, financialRisk, areaImpact);
        application.setAiAnalysis(aiAnalysis);
        application.setAiRecommendation(generateAiRecommendation(application, matchScore, financialRisk, areaImpact));

        return application;
    }

    private double calculateMatchScore(CandidateEntity candidate, JobEntity job) {
        double score = 0.0;

        // Skills match
        Set<String> candidateSkills = candidate.getSkills().stream().map(String::toLowerCase).collect(Collectors.toSet());
        Set<String> requiredSkills = job.getRequiredSkills().stream().map(String::toLowerCase).collect(Collectors.toSet());
        long commonSkills = candidateSkills.stream().filter(requiredSkills::contains).count();
        if (!requiredSkills.isEmpty()) {
            score += (double) commonSkills / requiredSkills.size() * 30; // 30% weight
        }

        // Technologies match
        Set<String> candidateTechs = candidate.getTechnologies().stream().map(String::toLowerCase).collect(Collectors.toSet());
        Set<String> requiredTechs = job.getRequiredTechnologies().stream().map(String::toLowerCase).collect(Collectors.toSet());
        long commonTechs = candidateTechs.stream().filter(requiredTechs::contains).count();
        if (!requiredTechs.isEmpty()) {
            score += (double) commonTechs / requiredTechs.size() * 30; // 30% weight
        }

        // Years of experience
        double experienceDiff = Math.max(0, candidate.getYearsOfExperience() - job.getYearsExperienceRequired());
        score += Math.min(1.0, experienceDiff / 5.0) * 20; // 20% weight, cap at 5 years difference for max score

        // Salary match (closer to requestedSalary between salaryMin/Max, higher score)
        if (candidate.getRequestedSalary() >= job.getSalaryMin() && candidate.getRequestedSalary() <= job.getSalaryMax()) {
            double salaryMidPoint = (job.getSalaryMin() + job.getSalaryMax()) / 2;
            double deviation = Math.abs(candidate.getRequestedSalary() - salaryMidPoint);
            double range = (job.getSalaryMax() - job.getSalaryMin()) / 2;
            if (range > 0) {
                score += (1 - (deviation / range)) * 20; // 20% weight
            } else { // if salaryMin == salaryMax
                score += 20;
            }
        }

        return Math.min(100.0, score); // Cap at 100
    }

    private double calculateFinancialRisk(CandidateEntity candidate, JobEntity job, AreaEntity area) {
        double risk = 0.0;

        // Salary vs Job Budget (higher requested salary, higher risk)
        if (job.getSalaryMax() > 0) {
            risk += (candidate.getRequestedSalary() / job.getSalaryMax()) * 40; // 40% weight
        } else { // No max salary defined, assume high risk if any salary requested
            if (candidate.getRequestedSalary() > 0) risk += 40;
        }

        // Salary vs Area Available Budget
        if (area.getAvailableBudget() < candidate.getRequestedSalary()) {
            risk += 30; // 30% if salary exceeds available budget
        }

        // Candidate's previous salary (not in entity yet, so simplified)
        // For MVP, assume higher requested salary relative to experience might indicate higher risk if not justified
        if (candidate.getYearsOfExperience() < 2 && candidate.getRequestedSalary() > 50000) { // Example threshold
            risk += 15;
        }

        // Impact of turnover rate in area
        risk += area.getTurnoverRate() * 0.5; // Example: 0.5% risk per % of turnover rate, max 10%

        return Math.min(100.0, risk); // Cap at 100
    }

    private double calculateAreaImpact(CandidateEntity candidate, JobEntity job, AreaEntity area) {
        double impact = 0.0;

        // Job level vs Area performance (hiring senior in low performing area might be high impact)
        if (job.getLevel().ordinal() >= JobEntity.JobLevel.SENIOR.ordinal() && area.getPerformanceScore() < 50) {
            impact += 30; // High impact if senior hire in struggling area
        } else if (job.getLevel().ordinal() <= JobEntity.JobLevel.JUNIOR.ordinal() && area.getPerformanceScore() > 70) {
            impact += 10; // Moderate impact if junior hire in high performing area
        }

        // Hiring priority of the area
        switch (area.getHiringPriority()) {
            case URGENT: impact += 25; break;
            case HIGH: impact += 15; break;
            case MEDIUM: impact += 5; break;
            case LOW: impact += 0; break;
        }

        // Number of open positions in the area (more openings, higher impact of filling one)
        List<JobEntity> openJobsInArea = jobRepository.findByAreaId(area.getId()).stream()
                                            .filter(j -> j.getStatus() == JobEntity.JobStatus.OPEN)
                                            .collect(Collectors.toList());
        if (openJobsInArea.size() > 5) { // More than 5 open jobs, higher impact
            impact += 15;
        } else if (openJobsInArea.size() > 2) {
            impact += 5;
        }
        
        // Candidate's ScoreIA from CV processing could influence this.
        impact += candidate.getScoreIa() * 0.1; // Example: 10% of candidate's general score contributes to positive impact.

        return Math.min(100.0, impact); // Cap at 100
    }

    private String generateAiAnalysis(ApplicationEntity application, double matchScore, double financialRisk, double areaImpact) {
        StringBuilder analysis = new StringBuilder();
        analysis.append("AI Analysis for Application (ID: ").append(application.getId()).append(":\n");
        analysis.append("- Match Score: ").append(String.format("%.2f", matchScore)).append("%\n");
        analysis.append("- Financial Risk: ").append(String.format("%.2f", financialRisk)).append("%\n");
        analysis.append("- Area Impact: ").append(String.format("%.2f", areaImpact)).append("%\n");

        if (matchScore >= 70 && financialRisk < 40 && areaImpact >= 50) {
            analysis.append("Conclusion: Highly recommended candidate with strong fit and positive impact potential.\n");
        } else if (matchScore >= 50 && financialRisk < 60) {
            analysis.append("Conclusion: Good candidate, consider for further evaluation.\n");
        } else {
            analysis.append("Conclusion: Lower fit or higher risk, proceed with caution.\n");
        }
        
        // Add more detailed points based on scores
        if (matchScore < 50) analysis.append("   - Low skill/tech match with job requirements.\n");
        if (financialRisk > 60) analysis.append("   - High financial risk due to salary expectations or budget constraints.\n");
        if (areaImpact < 30) analysis.append("   - Limited potential positive impact on the hiring area.\n");

        return analysis.toString();
    }

    private String generateAiRecommendation(ApplicationEntity application, double matchScore, double financialRisk, double areaImpact) {
        StringBuilder recommendation = new StringBuilder();
        if (matchScore >= 70 && financialRisk < 40 && areaImpact >= 50) {
            recommendation.append("Action: Proceed to interview stage immediately. This candidate is an excellent fit.");
        } else if (matchScore >= 50 && financialRisk < 60) {
            recommendation.append("Action: Review CV and consider for initial screening call. Potential good fit.");
        } else if (financialRisk > 70) {
            recommendation.append("Action: High financial risk detected. Re-evaluate salary expectations or consider other candidates.");
        } else if (matchScore < 40) {
            recommendation.append("Action: Candidate's profile does not strongly align with job requirements. Recommend rejection.");
        } else {
            recommendation.append("Action: Further review needed to assess fit and risks.");
        }
        return recommendation.toString();
    }
}
