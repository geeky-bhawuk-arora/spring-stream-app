package com.stream.app.services.impl;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.stream.app.entities.Video;
import com.stream.app.repositories.VideoRepository; // <-- ADDED IMPORT
import com.stream.app.services.VideoService;

import lombok.RequiredArgsConstructor; // <-- ADDED IMPORT

@Service
@RequiredArgsConstructor // <-- ADDED: Automatically creates a constructor for final fields
public class VideoServiceImpl implements VideoService {

    private final VideoRepository videoRepository; // <-- ADDED: Dependency for persistence

    @Value("${files.video}") // <-- CORRECTED: Key changed from "video.files" to "files.video"
    String DIR;

    @Override
    public Video save(Video video, MultipartFile file) {
        try {
            // 1. Get file metadata and sanitize names
            String fileName = file.getOriginalFilename();
            String contentType = file.getContentType();
            String cleanFileName = StringUtils.cleanPath(fileName);
            String cleanFolder = StringUtils.cleanPath(DIR);
            
            // 2. Define path, ensure directory exists, and save the file
            Path uploadPath = Paths.get(cleanFolder);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            Path filePath = Paths.get(cleanFolder, cleanFileName);
            
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, filePath, java.nio.file.StandardCopyOption.REPLACE_EXISTING);
            }

            // 3. Update Video entity with file info and save metadata
            video.setContentType(contentType);
            video.setFilePath(filePath.toString());
            return videoRepository.save(video);

        } catch (IOException ex) {
            // Throw a runtime exception to be handled by Spring
            throw new RuntimeException("Could not save video file: " + file.getOriginalFilename(), ex);
        }
    }

    @Override
    public Video get(String videoId) {
        return videoRepository.findById(videoId)
            .orElseThrow(() -> new RuntimeException("Video not found with id: " + videoId));
    }

    @Override
    public Video getByTitle(String title) {
        return videoRepository.findByTitle(title)
            .orElseThrow(() -> new RuntimeException("Video not found with title: " + title));
    }

    @Override
    public List<Video> getAll() {
        return videoRepository.findAll();
    }
}