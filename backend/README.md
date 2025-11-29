# API Endpoints Documentation

This document provides a detailed overview of the REST API endpoints, including how to use them, expected request and response formats, and example `curl` commands for testing.

## Authentication

Most endpoints require authentication. You will need to obtain a JWT token (e.g., by logging in through an authentication endpoint) and include it in the `Authorization` header of your requests, prefixed with `Bearer `.

Example: `Authorization: Bearer YOUR_JWT_TOKEN`

---

## 1. Application Controller

Base Path: `/api/applications`

### 1.1 Create Application

-   **HTTP Method**: `POST`
-   **Path**: `/api/applications`
-   **Description**: Creates a new job application.
-   **Request Body**: `CreateApplicationRequest`
    ```json
    {
      "candidateId": 1,
      "jobId": 1
    }
    ```
    *   `candidateId` (Long, required): The ID of the candidate.
    *   `jobId` (Long, required): The ID of the job.
-   **Response Body**: `ApplicationResponse`
    ```json
    {
      "id": 1,
      "candidateId": 1,
      "jobId": 1,
      "status": "PENDING",
      "matchScore": 0.0,
      "financialRisk": 0.0,
      "areaImpact": 0.0,
      "aiAnalysis": null,
      "aiRecommendation": null,
      "notes": null,
      "appliedAt": "2025-11-29T10:00:00",
      "updatedAt": "2025-11-29T10:00:00"
    }
    ```
    *   `id` (Long): The ID of the application.
    *   `candidateId` (Long): The ID of the candidate.
    *   `jobId` (Long): The ID of the job.
    *   `status` (Enum: PENDING, REVIEWING, SHORTLISTED, INTERVIEWING, OFFER, HIRED, REJECTED): The current status of the application.
    *   `matchScore` (Double): AI-generated match score.
    *   `financialRisk` (Double): AI-generated financial risk.
    *   `areaImpact` (Double): AI-generated area impact.
    *   `aiAnalysis` (String): AI analysis notes.
    *   `aiRecommendation` (String): AI recommendation.
    *   `notes` (String): Additional notes.
    *   `appliedAt` (LocalDateTime): Timestamp when the application was created.
    *   `updatedAt` (LocalDateTime): Timestamp when the application was last updated.
-   **Example `curl` command**:
    ```bash
    curl -X POST "http://localhost:8080/api/applications" \
         -H "Content-Type: application/json" \
         -H "Authorization: Bearer YOUR_JWT_TOKEN" \
         -d '{
               "candidateId": 1,
               "jobId": 1
             }'
    ```

### 1.2 Get Application by ID

-   **HTTP Method**: `GET`
-   **Path**: `/api/applications/{id}`
-   **Description**: Retrieves a single job application by its ID.
-   **Path Parameters**:
    *   `id` (Long, required): The ID of the application.
-   **Response Body**: `ApplicationResponse` (Same as above)
-   **Example `curl` command**:
    ```bash
    curl -X GET "http://localhost:8080/api/applications/1" \
         -H "Authorization: Bearer YOUR_JWT_TOKEN"
    ```

### 1.3 Get Applications by Job ID

-   **HTTP Method**: `GET`
-   **Path**: `/api/applications?jobId={id}`
-   **Description**: Retrieves a list of job applications associated with a specific Job ID.
-   **Query Parameters**:
    *   `jobId` (Long, required): The ID of the job.
-   **Response Body**: List of `ApplicationResponse` (Same as above)
-   **Example `curl` command**:
    ```bash
    curl -X GET "http://localhost:8080/api/applications?jobId=1" \
         -H "Authorization: Bearer YOUR_JWT_TOKEN"
    ```

### 1.4 Get Applications by Candidate ID

-   **HTTP Method**: `GET`
-   **Path**: `/api/applications?candidateId={id}`
-   **Description**: Retrieves a list of job applications associated with a specific Candidate ID.
-   **Query Parameters**:
    *   `candidateId` (Long, required): The ID of the candidate.
-   **Response Body**: List of `ApplicationResponse` (Same as above)
-   **Example `curl` command**:
    ```bash
    curl -X GET "http://localhost:8080/api/applications?candidateId=1" \
         -H "Authorization: Bearer YOUR_JWT_TOKEN"
    ```

### 1.5 Update Application Status

-   **HTTP Method**: `PATCH`
-   **Path**: `/api/applications/{id}/status`
-   **Description**: Updates the status of a job application.
-   **Path Parameters**:
    *   `id` (Long, required): The ID of the application.
-   **Request Body**: `UpdateApplicationStatusRequest`
    ```json
    {
      "status": "SHORTLISTED",
      "notes": "Candidate moved to the next round of interviews."
    }
    ```
    *   `status` (Enum: PENDING, REVIEWING, SHORTLISTED, INTERVIEWING, OFFER, HIRED, REJECTED, required): The new status for the application.
    *   `notes` (String, optional): Optional notes regarding the status update.
-   **Response Body**: `ApplicationResponse` (Same as above)
-   **Example `curl` command**:
    ```bash
    curl -X PATCH "http://localhost:8080/api/applications/1/status" \
         -H "Content-Type: application/json" \
         -H "Authorization: Bearer YOUR_JWT_TOKEN" \
         -d '{
               "status": "SHORTLISTED",
               "notes": "Candidate moved to the next round of interviews."
             }'
    ```

---

## 2. Area Controller

Base Path: `/api/areas`

### 2.1 Create Area

-   **HTTP Method**: `POST`
-   **Path**: `/api/areas`
-   **Description**: Creates a new area within a company.
-   **Request Body**: `CreateAreaRequest`
    ```json
    {
      "name": "Software Development",
      "description": "Responsible for developing and maintaining software applications.",
      "budgetAllocated": 150000.00,
      "maxEmployees": 20,
      "hiringPriority": "HIGH",
      "companyId": 1
    }
    ```
    *   `name` (String, required): The name of the area.
    *   `description` (String, optional): A description of the area.
    *   `budgetAllocated` (Double, required, min 0): The budget allocated for this area.
    *   `maxEmployees` (Integer, required, min 0): The maximum number of employees allowed in this area.
    *   `hiringPriority` (Enum: LOW, MEDIUM, HIGH, URGENT, required): The hiring priority for this area.
    *   `companyId` (Long, required): The ID of the company this area belongs to.
-   **Response Body**: `AreaResponse`
    ```json
    {
      "id": 1,
      "name": "Software Development",
      "description": "Responsible for developing and maintaining software applications.",
      "budgetAllocated": 150000.0,
      "budgetUsed": 0.0,
      "currentEmployees": 0,
      "maxEmployees": 20,
      "performanceScore": 0.0,
      "turnoverRate": 0.0,
      "hiringPriority": "HIGH",
      "companyId": 1,
      "createdAt": "2025-11-29T10:00:00",
      "updatedAt": "2025-11-29T10:00:00"
    }
    ```
    *   `id` (Long): The ID of the area.
    *   `name` (String): The name of the area.
    *   `description` (String): A description of the area.
    *   `budgetAllocated` (Double): The budget allocated for this area.
    *   `budgetUsed` (Double): The budget used by this area.
    *   `currentEmployees` (Integer): The number of current employees in this area.
    *   `maxEmployees` (Integer): The maximum number of employees allowed in this area.
    *   `performanceScore` (Double): The performance score of the area.
    *   `turnoverRate` (Double): The turnover rate of the area.
    *   `hiringPriority` (Enum: LOW, MEDIUM, HIGH, URGENT): The hiring priority for this area.
    *   `companyId` (Long): The ID of the company this area belongs to.
    *   `createdAt` (LocalDateTime): Timestamp when the area was created.
    *   `updatedAt` (LocalDateTime): Timestamp when the area was last updated.
-   **Example `curl` command**:
    ```bash
    curl -X POST "http://localhost:8080/api/areas" \
         -H "Content-Type: application/json" \
         -H "Authorization: Bearer YOUR_JWT_TOKEN" \
         -d '{
               "name": "Software Development",
               "description": "Responsible for developing and maintaining software applications.",
               "budgetAllocated": 150000.00,
               "maxEmployees": 20,
               "hiringPriority": "HIGH",
               "companyId": 1
             }'
    ```

### 2.2 Get Area by ID

-   **HTTP Method**: `GET`
-   **Path**: `/api/areas/{id}`
-   **Description**: Retrieves a single area by its ID.
-   **Path Parameters**:
    *   `id` (Long, required): The ID of the area.
-   **Response Body**: `AreaResponse` (Same as above)
-   **Example `curl` command**:
    ```bash
    curl -X GET "http://localhost:8080/api/areas/1" \
         -H "Authorization: Bearer YOUR_JWT_TOKEN"
    ```

### 2.3 Get Areas by Company ID

-   **HTTP Method**: `GET`
-   **Path**: `/api/areas?companyId={id}`
-   **Description**: Retrieves a list of areas associated with a specific Company ID.
-   **Query Parameters**:
    *   `companyId` (Long, required): The ID of the company.
-   **Response Body**: List of `AreaResponse` (Same as above)
-   **Example `curl` command**:
    ```bash
    curl -X GET "http://localhost:8080/api/areas?companyId=1" \
         -H "Authorization: Bearer YOUR_JWT_TOKEN"
    ```

### 2.4 Update Area

-   **HTTP Method**: `PUT`
-   **Path**: `/api/areas/{id}`
-   **Description**: Updates an existing area.
-   **Path Parameters**:
    *   `id` (Long, required): The ID of the area.
-   **Request Body**: `UpdateAreaRequest`
    ```json
    {
      "name": "Backend Development",
      "description": "Focuses on server-side logic and database management.",
      "budgetAllocated": 160000.00,
      "maxEmployees": 22,
      "hiringPriority": "URGENT"
    }
    ```
    *   `name` (String, optional): The updated name of the area.
    *   `description` (String, optional): The updated description of the area.
    *   `budgetAllocated` (Double, optional, min 0): The updated budget allocated for this area.
    *   `maxEmployees` (Integer, optional, min 0): The updated maximum number of employees allowed.
    *   `hiringPriority` (Enum: LOW, MEDIUM, HIGH, URGENT, optional): The updated hiring priority.
-   **Response Body**: `AreaResponse` (Same as above)
-   **Example `curl` command**:
    ```bash
    curl -X PUT "http://localhost:8080/api/areas/1" \
         -H "Content-Type: application/json" \
         -H "Authorization: Bearer YOUR_JWT_TOKEN" \
         -d '{
               "name": "Backend Development",
               "description": "Focuses on server-side logic and database management.",
               "budgetAllocated": 160000.00,
               "maxEmployees": 22,
               "hiringPriority": "URGENT"
             }'
    ```

### 2.5 Delete Area

-   **HTTP Method**: `DELETE`
-   **Path**: `/api/areas/{id}`
-   **Description**: Deletes an area by its ID.
-   **Path Parameters**:
    *   `id` (Long, required): The ID of the area to delete.
-   **Response Body**: None (HTTP 204 No Content)
-   **Example `curl` command**:
    ```bash
    curl -X DELETE "http://localhost:8080/api/areas/1" \
         -H "Authorization: Bearer YOUR_JWT_TOKEN"
    ```

---

## 3. Auth Controller

Base Path: `/api/auth`

### 3.1 Register User

-   **HTTP Method**: `POST`
-   **Path**: `/api/auth/register`
-   **Description**: Registers a new user with a username, password, and assigned roles.
-   **Request Body**: `RegisterRequest`
    ```json
    {
      "username": "newuser",
      "password": "securepassword",
      "roles": ["RECRUITER"]
    }
    ```
    *   `username` (String, required): The desired username.
    *   `password` (String, required): The user's password.
    *   `roles` (Array of Enum: ADMIN, RECRUITER, MANAGER, required): A list of roles to assign to the user.
-   **Response Body**: `AuthResponse`
    ```json
    {
      "token": "eyJhbGciOiJIUzI1NiJ9..."
    }
    ```
    *   `token` (String): A JWT token for the newly registered and authenticated user.
-   **Example `curl` command**:
    ```bash
    curl -X POST "http://localhost:8080/api/auth/register" \
         -H "Content-Type: application/json" \
         -d '{
               "username": "newuser",
               "password": "securepassword",
               "roles": ["RECRUITER"]
             }'
    ```

### 3.2 Authenticate User (Login)

-   **HTTP Method**: `POST`
-   **Path**: `/api/auth/login`
-   **Description**: Authenticates an existing user with a username and password, returning a JWT token.
-   **Request Body**: `AuthRequest`
    ```json
    {
      "username": "existinguser",
      "password": "userpassword"
    }
    ```
    *   `username` (String, required): The user's username.
    *   `password` (String, required): The user's password.
-   **Response Body**: `AuthResponse`
    ```json
    {
      "token": "eyJhbGciOiJIUzI1NiJ9..."
    }
    ```
    *   `token` (String): A JWT token for the authenticated user. This token should be used in the `Authorization` header for subsequent requests to protected endpoints.
-   **Example `curl` command**:
    ```bash
    curl -X POST "http://localhost:8080/api/auth/login" \
         -H "Content-Type: application/json" \
         -d '{
               "username": "existinguser",
               "password": "userpassword"
             }'
    ```

---

## 4. Candidate Controller

Base Path: `/api/candidates`

### 4.1 Create Candidate

-   **HTTP Method**: `POST`
-   **Path**: `/api/candidates`
-   **Description**: Creates a new candidate profile.
-   **Request Body**: `CreateCandidateRequest`
    ```json
    {
      "name": "Jane Doe",
      "email": "jane.doe@example.com",
      "phone": "+1234567890",
      "requestedSalary": 75000.00,
      "yearsOfExperience": 5,
      "skills": ["Java", "Spring Boot", "REST API"],
      "technologies": ["MySQL", "AWS"]
    }
    ```
    *   `name` (String, required, 2-100 chars): The candidate's full name.
    *   `email` (String, required, valid email format): The candidate's email address.
    *   `phone` (String, required): The candidate's phone number.
    *   `requestedSalary` (Double, required, min 0): The candidate's desired salary.
    *   `yearsOfExperience` (Integer, required, min 0): The candidate's years of professional experience.
    *   `skills` (List of String, optional): A list of skills the candidate possesses.
    *   `technologies` (List of String, optional): A list of technologies the candidate is proficient in.
-   **Response Body**: `CandidateResponse`
    ```json
    {
      "id": 1,
      "name": "Jane Doe",
      "email": "jane.doe@example.com",
      "phone": "+1234567890",
      "scoreIa": 0.0,
      "requestedSalary": 75000.00,
      "skills": ["Java", "Spring Boot", "REST API"],
      "technologies": ["MySQL", "AWS"],
      "yearsOfExperience": 5,
      "status": "ACTIVE",
      "createdAt": "2025-11-29T10:00:00",
      "updatedAt": "2025-11-29T10:00:00"
    }
    ```
    *   `id` (Long): The ID of the candidate.
    *   `name` (String): The candidate's name.
    *   `email` (String): The candidate's email.
    *   `phone` (String): The candidate's phone number.
    *   `scoreIa` (Double): AI-generated score for the candidate.
    *   `requestedSalary` (Double): The candidate's requested salary.
    *   `skills` (List of String): The candidate's skills.
    *   `technologies` (List of String): The candidate's technologies.
    *   `yearsOfExperience` (Integer): The candidate's years of experience.
    *   `status` (Enum: ACTIVE, HIRED, REJECTED, INACTIVE): The current status of the candidate.
    *   `createdAt` (LocalDateTime): Timestamp when the candidate was created.
    *   `updatedAt` (LocalDateTime): Timestamp when the candidate was last updated.
-   **Example `curl` command**:
    ```bash
    curl -X POST "http://localhost:8080/api/candidates" \
         -H "Content-Type: application/json" \
         -H "Authorization: Bearer YOUR_JWT_TOKEN" \
         -d '{
               "name": "Jane Doe",
               "email": "jane.doe@example.com",
               "phone": "+1234567890",
               "requestedSalary": 75000.00,
               "yearsOfExperience": 5,
               "skills": ["Java", "Spring Boot", "REST API"],
               "technologies": ["MySQL", "AWS"]
             }'
    ```

### 4.2 Get Candidate by ID

-   **HTTP Method**: `GET`
-   **Path**: `/api/candidates/{id}`
-   **Description**: Retrieves a single candidate by their ID.
-   **Path Parameters**:
    *   `id` (Long, required): The ID of the candidate.
-   **Response Body**: `CandidateResponse` (Same as above)
-   **Example `curl` command**:
    ```bash
    curl -X GET "http://localhost:8080/api/candidates/1" \
         -H "Authorization: Bearer YOUR_JWT_TOKEN"
    ```

### 4.3 Get All Candidates

-   **HTTP Method**: `GET`
-   **Path**: `/api/candidates`
-   **Description**: Retrieves a list of all candidate profiles.
-   **Response Body**: List of `CandidateResponse` (Same as above)
-   **Example `curl` command**:
    ```bash
    curl -X GET "http://localhost:8080/api/candidates" \
         -H "Authorization: Bearer YOUR_JWT_TOKEN"
    ```

### 4.4 Update Candidate

-   **HTTP Method**: `PUT`
-   **Path**: `/api/candidates/{id}`
-   **Description**: Updates an existing candidate profile.
-   **Path Parameters**:
    *   `id` (Long, required): The ID of the candidate to update.
-   **Request Body**: `UpdateCandidateRequest`
    ```json
    {
      "name": "Jane D.",
      "phone": "+1122334455",
      "status": "HIRED"
    }
    ```
    *   `name` (String, optional, 2-100 chars): The updated name of the candidate.
    *   `email` (String, optional, valid email format): The updated email address.
    *   `phone` (String, optional): The updated phone number.
    *   `requestedSalary` (Double, optional, min 0): The updated desired salary.
    *   `yearsOfExperience` (Integer, optional, min 0): The updated years of professional experience.
    *   `skills` (List of String, optional): The updated list of skills.
    *   `technologies` (List of String, optional): The updated list of technologies.
    *   `status` (Enum: ACTIVE, HIRED, REJECTED, INACTIVE, optional): The updated status of the candidate.
-   **Response Body**: `CandidateResponse` (Same as above)
-   **Example `curl` command**:
    ```bash
    curl -X PUT "http://localhost:8080/api/candidates/1" \
         -H "Content-Type: application/json" \
         -H "Authorization: Bearer YOUR_JWT_TOKEN" \
         -d '{
               "name": "Jane D.",
               "phone": "+1122334455",
               "status": "HIRED"
             }'
    ```

### 4.5 Upload Candidate CV

-   **HTTP Method**: `POST`
-   **Path**: `/api/candidates/{id}/upload-cv`
-   **Description**: Uploads a CV file (e.g., PDF, DOCX) for a specific candidate.
-   **Path Parameters**:
    *   `id` (Long, required): The ID of the candidate.
-   **Request Body**: `MultipartFile`
    *   A file to be uploaded. The `Content-Type` header should be `multipart/form-data`.
-   **Response Body**: `CandidateResponse` (Same as above)
-   **Example `curl` command**:
    ```bash
    curl -X POST "http://localhost:8080/api/candidates/1/upload-cv" \
         -H "Authorization: Bearer YOUR_JWT_TOKEN" \
         -F "file=@/path/to/your/cv.pdf"
    ```
    *   Replace `/path/to/your/cv.pdf` with the actual path to the file you want to upload.

---

## 5. Company Controller

Base Path: `/api/companies`

### 5.1 Create Company

-   **HTTP Method**: `POST`
-   **Path**: `/api/companies`
-   **Description**: Creates a new company profile.
-   **Request Body**: `CreateCompanyRequest`
    ```json
    {
      "name": "Tech Solutions Inc.",
      "sector": "Technology",
      "size": "MEDIUM",
      "totalPersonnelBudget": 500000.00,
      "country": "USA",
      "city": "New York",
      "description": "A leading technology company specializing in software development."
    }
    ```
    *   `name` (String, required): The name of the company.
    *   `sector` (String, required): The industry sector of the company.
    *   `size` (Enum: SMALL, MEDIUM, LARGE, ENTERPRISE, required): The size of the company.
    *   `totalPersonnelBudget` (Double, required, min 0): The total budget allocated for personnel.
    *   `country` (String, optional): The country where the company is located.
    *   `city` (String, optional): The city where the company is located.
    *   `description` (String, optional): A description of the company.
-   **Response Body**: `CompanyResponse`
    ```json
    {
      "id": 1,
      "name": "Tech Solutions Inc.",
      "sector": "Technology",
      "size": "MEDIUM",
      "totalPersonnelBudget": 500000.0,
      "usedPersonnelBudget": 0.0,
      "country": "USA",
      "city": "New York",
      "description": "A leading technology company specializing in software development.",
      "createdAt": "2025-11-29T10:00:00",
      "updatedAt": "2025-11-29T10:00:00"
    }
    ```
    *   `id` (Long): The ID of the company.
    *   `name` (String): The name of the company.
    *   `sector` (String): The industry sector.
    *   `size` (Enum: SMALL, MEDIUM, LARGE, ENTERPRISE): The size of the company.
    *   `totalPersonnelBudget` (Double): The total personnel budget.
    *   `usedPersonnelBudget` (Double): The budget used for personnel.
    *   `country` (String): The country.
    *   `city` (String): The city.
    *   `description` (String): A description of the company.
    *   `createdAt` (LocalDateTime): Timestamp when the company was created.
    *   `updatedAt` (LocalDateTime): Timestamp when the company was last updated.
-   **Example `curl` command**:
    ```bash
    curl -X POST "http://localhost:8080/api/companies" \
         -H "Content-Type: application/json" \
         -H "Authorization: Bearer YOUR_JWT_TOKEN" \
         -d '{
               "name": "Tech Solutions Inc.",
               "sector": "Technology",
               "size": "MEDIUM",
               "totalPersonnelBudget": 500000.00,
               "country": "USA",
               "city": "New York",
               "description": "A leading technology company specializing in software development."
             }'
    ```

### 5.2 Get Company by ID

-   **HTTP Method**: `GET`
-   **Path**: `/api/companies/{id}`
-   **Description**: Retrieves a single company by its ID.
-   **Path Parameters**:
    *   `id` (Long, required): The ID of the company.
-   **Response Body**: `CompanyResponse` (Same as above)
-   **Example `curl` command**:
    ```bash
    curl -X GET "http://localhost:8080/api/companies/1" \
         -H "Authorization: Bearer YOUR_JWT_TOKEN"
    ```

### 5.3 Get All Companies

-   **HTTP Method**: `GET`
-   **Path**: `/api/companies`
-   **Description**: Retrieves a list of all company profiles.
-   **Response Body**: List of `CompanyResponse` (Same as above)
-   **Example `curl` command**:
    ```bash
    curl -X GET "http://localhost:8080/api/companies" \
         -H "Authorization: Bearer YOUR_JWT_TOKEN"
    ```

### 5.4 Update Company

-   **HTTP Method**: `PUT`
-   **Path**: `/api/companies/{id}`
-   **Description**: Updates an existing company profile.
-   **Path Parameters**:
    *   `id` (Long, required): The ID of the company to update.
-   **Request Body**: `UpdateCompanyRequest`
    ```json
    {
      "description": "A leading global technology company specializing in innovative software solutions.",
      "totalPersonnelBudget": 550000.00,
      "city": "San Francisco"
    }
    ```
    *   `name` (String, optional): The updated name of the company.
    *   `sector` (String, optional): The updated industry sector.
    *   `size` (Enum: SMALL, MEDIUM, LARGE, ENTERPRISE, optional): The updated size of the company.
    *   `totalPersonnelBudget` (Double, optional, min 0): The updated total personnel budget.
    *   `usedPersonnelBudget` (Double, optional, min 0): The updated used personnel budget.
    *   `country` (String, optional): The updated country.
    *   `city` (String, optional): The updated city.
    *   `description` (String, optional): The updated description of the company.
-   **Response Body**: `CompanyResponse` (Same as above)
-   **Example `curl` command**:
    ```bash
    curl -X PUT "http://localhost:8080/api/companies/1" \
         -H "Content-Type: application/json" \
         -H "Authorization: Bearer YOUR_JWT_TOKEN" \
         -d '{
               "description": "A leading global technology company specializing in innovative software solutions.",
               "totalPersonnelBudget": 550000.00,
               "city": "San Francisco"
             }'
    ```

### 5.5 Delete Company

-   **HTTP Method**: `DELETE`
-   **Path**: `/api/companies/{id}`
-   **Description**: Deletes a company by its ID.
-   **Path Parameters**:
    *   `id` (Long, required): The ID of the company to delete.
-   **Response Body**: None (HTTP 204 No Content)
-   **Example `curl` command**:
    ```bash
    curl -X DELETE "http://localhost:8080/api/companies/1" \
         -H "Authorization: Bearer YOUR_JWT_TOKEN"
    ```

---

## 6. Job Controller

Base Path: `/api/jobs`

### 6.1 Create Job

-   **HTTP Method**: `POST`
-   **Path**: `/api/jobs`
-   **Description**: Creates a new job posting.
-   **Request Body**: `CreateJobRequest`
    ```json
    {
      "title": "Software Engineer",
      "description": "Develop and maintain software applications.",
      "salaryMin": 80000.00,
      "salaryMax": 120000.00,
      "requiredSkills": ["Java", "Spring Boot"],
      "requiredTechnologies": ["Docker", "Kubernetes"],
      "yearsExperienceRequired": 3,
      "level": "SEMI_SENIOR",
      "type": "FULL_TIME",
      "openings": 2,
      "companyId": 1,
      "areaId": 1
    }
    ```
    *   `title` (String, required, max 255): The title of the job.
    *   `description` (String, required, max 2000): A detailed description of the job.
    *   `salaryMin` (Double, required, min 0): The minimum salary for the job.
    *   `salaryMax` (Double, required, min 0): The maximum salary for the job.
    *   `requiredSkills` (List of String, optional): A list of skills required for the job.
    *   `requiredTechnologies` (List of String, optional): A list of technologies required for the job.
    *   `yearsExperienceRequired` (Integer, required, min 0): The minimum years of experience required.
    *   `level` (Enum: JUNIOR, SEMI_SENIOR, SENIOR, LEAD, MANAGER, required): The experience level for the job.
    *   `type` (Enum: FULL_TIME, PART_TIME, CONTRACT, FREELANCE, required): The type of employment.
    *   `openings` (Integer, required, min 1): The number of available openings.
    *   `companyId` (Long, required): The ID of the company offering the job.
    *   `areaId` (Long, required): The ID of the area/department within the company.
-   **Response Body**: `JobResponse`
    ```json
    {
      "id": 1,
      "title": "Software Engineer",
      "description": "Develop and maintain software applications.",
      "salaryMin": 80000.0,
      "salaryMax": 120000.0,
      "requiredSkills": ["Java", "Spring Boot"],
      "requiredTechnologies": ["Docker", "Kubernetes"],
      "yearsExperienceRequired": 3,
      "level": "SEMI_SENIOR",
      "status": "DRAFT",
      "type": "FULL_TIME",
      "openings": 2,
      "companyId": 1,
      "areaId": 1,
      "createdAt": "2025-11-29T10:00:00",
      "updatedAt": "2025-11-29T10:00:00"
    }
    ```
    *   `id` (Long): The ID of the job.
    *   `title` (String): The title of the job.
    *   `description` (String): The job description.
    *   `salaryMin` (Double): The minimum salary.
    *   `salaryMax` (Double): The maximum salary.
    *   `requiredSkills` (List of String): Required skills.
    *   `requiredTechnologies` (List of String): Required technologies.
    *   `yearsExperienceRequired` (Integer): Years of experience required.
    *   `level` (Enum: JUNIOR, SEMI_SENIOR, SENIOR, LEAD, MANAGER): Experience level.
    *   `status` (Enum: DRAFT, OPEN, PAUSED, CLOSED, FILLED): Current status of the job posting.
    *   `type` (Enum: FULL_TIME, PART_TIME, CONTRACT, FREELANCE): Type of employment.
    *   `openings` (Integer): Number of openings.
    *   `companyId` (Long): The ID of the company.
    *   `areaId` (Long): The ID of the area/department.
    *   `createdAt` (LocalDateTime): Timestamp when the job was created.
    *   `updatedAt` (LocalDateTime): Timestamp when the job was last updated.
-   **Example `curl` command**:
    ```bash
    curl -X POST "http://localhost:8080/api/jobs" \
         -H "Content-Type: application/json" \
         -H "Authorization: Bearer YOUR_JWT_TOKEN" \
         -d '{
               "title": "Software Engineer",
               "description": "Develop and maintain software applications.",
               "salaryMin": 80000.00,
               "salaryMax": 120000.00,
               "requiredSkills": ["Java", "Spring Boot"],
               "requiredTechnologies": ["Docker", "Kubernetes"],
               "yearsExperienceRequired": 3,
               "level": "SEMI_SENIOR",
               "type": "FULL_TIME",
               "openings": 2,
               "companyId": 1,
               "areaId": 1
             }'
    ```

### 6.2 Get Job by ID

-   **HTTP Method**: `GET`
-   **Path**: `/api/jobs/{id}`
-   **Description**: Retrieves a single job posting by its ID.
-   **Path Parameters**:
    *   `id` (Long, required): The ID of the job.
-   **Response Body**: `JobResponse` (Same as above)
-   **Example `curl` command**:
    ```bash
    curl -X GET "http://localhost:8080/api/jobs/1" \
         -H "Authorization: Bearer YOUR_JWT_TOKEN"
    ```

### 6.3 Get Jobs by Company ID

-   **HTTP Method**: `GET`
-   **Path**: `/api/jobs?companyId={id}`
-   **Description**: Retrieves a list of job postings associated with a specific Company ID.
-   **Query Parameters**:
    *   `companyId` (Long, required): The ID of the company.
-   **Response Body**: List of `JobResponse` (Same as above)
-   **Example `curl` command**:
    ```bash
    curl -X GET "http://localhost:8080/api/jobs?companyId=1" \
         -H "Authorization: Bearer YOUR_JWT_TOKEN"
    ```

### 6.4 Get Jobs by Area ID

-   **HTTP Method**: `GET`
-   **Path**: `/api/jobs?areaId={id}`
-   **Description**: Retrieves a list of job postings associated with a specific Area ID.
-   **Query Parameters**:
    *   `areaId` (Long, required): The ID of the area.
-   **Response Body**: List of `JobResponse` (Same as above)
-   **Example `curl` command**:
    ```bash
    curl -X GET "http://localhost:8080/api/jobs?areaId=1" \
         -H "Authorization: Bearer YOUR_JWT_TOKEN"
    ```

### 6.5 Update Job

-   **HTTP Method**: `PUT`
-   **Path**: `/api/jobs/{id}`
-   **Description**: Updates an existing job posting.
-   **Path Parameters**:
    *   `id` (Long, required): The ID of the job to update.
-   **Request Body**: `UpdateJobRequest`
    ```json
    {
      "description": "Lead the development team and architect new solutions.",
      "salaryMax": 150000.00,
      "level": "LEAD",
      "status": "OPEN",
      "openings": 1
    }
    ```
    *   `title` (String, optional, max 255): The updated title of the job.
    *   `description` (String, optional, max 2000): The updated detailed description of the job.
    *   `salaryMin` (Double, optional, min 0): The updated minimum salary.
    *   `salaryMax` (Double, optional, min 0): The updated maximum salary.
    *   `requiredSkills` (List of String, optional): The updated list of required skills.
    *   `requiredTechnologies` (List of String, optional): The updated list of required technologies.
    *   `yearsExperienceRequired` (Integer, optional, min 0): The updated minimum years of experience.
    *   `level` (Enum: JUNIOR, SEMI_SENIOR, SENIOR, LEAD, MANAGER, optional): The updated experience level.
    *   `status` (Enum: DRAFT, OPEN, PAUSED, CLOSED, FILLED, optional): The updated status of the job posting.
    *   `type` (Enum: FULL_TIME, PART_TIME, CONTRACT, FREELANCE, optional): The updated type of employment.
    *   `openings` (Integer, optional, min 1): The updated number of available openings.
-   **Response Body**: `JobResponse` (Same as above)
-   **Example `curl` command**:
    ```bash
    curl -X PUT "http://localhost:8080/api/jobs/1" \
         -H "Content-Type: application/json" \
         -H "Authorization: Bearer YOUR_JWT_TOKEN" \
         -d '{
               "description": "Lead the development team and architect new solutions.",
               "salaryMax": 150000.00,
               "level": "LEAD",
               "status": "OPEN",
               "openings": 1
             }'
    ```

### 6.6 Delete Job

-   **HTTP Method**: `DELETE`
-   **Path**: `/api/jobs/{id}`
-   **Description**: Deletes a job posting by its ID.
-   **Path Parameters**:
    *   `id` (Long, required): The ID of the job to delete.
-   **Response Body**: None (HTTP 204 No Content)
-   **Example `curl` command**:
    ```bash
    curl -X DELETE "http://localhost:8080/api/jobs/1" \
         -H "Authorization: Bearer YOUR_JWT_TOKEN"
    ```
