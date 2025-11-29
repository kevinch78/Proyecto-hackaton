package com.recruiter.backend.infraestructure.storage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileStorageService {

    @Value("${file.upload-dir:uploads}") // Default to 'uploads' directory if not specified
    private String uploadDir;

    public String storeFile(MultipartFile file) throws IOException {
        Path uploadPath = Paths.get(uploadDir).toAbsolutePath().normalize();
        Files.createDirectories(uploadPath); // Create directory if it doesn't exist

        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        Path targetLocation = uploadPath.resolve(fileName);
        Files.copy(file.getInputStream(), targetLocation);
        return fileName; // Return the generated file name
    }

    public Path loadFile(String fileName) {
        Path uploadPath = Paths.get(uploadDir).toAbsolutePath().normalize();
        return uploadPath.resolve(fileName).normalize();
    }
}
