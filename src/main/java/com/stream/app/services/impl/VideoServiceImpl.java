package com.stream.app.services.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import com.stream.app.entities.Video;
import com.stream.app.repositories.VideoRepository; 
import com.stream.app.services.VideoService;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor; 

@Service
@RequiredArgsConstructor 
public class VideoServiceImpl implements VideoService {

    private final VideoRepository videoRepository;

    @Value("${files.video}") 
    String DIR;


    // ensure directory exists
    @PostConstruct
    public void init() {
        File file = new File(DIR);
        if(!file.exists()) {
            file.mkdir();
            System.out.println("folder created !!");
        } else {
            System.out.println("folder already created !!");
        }
    }

    @Override
    public Video save(Video video, MultipartFile file) {
        try {
            // get file metadata and sanitize names
            String fileName = file.getOriginalFilename();
            String contentType = file.getContentType();
            String cleanFileName = StringUtils.cleanPath(fileName);
            String cleanFolder = StringUtils.cleanPath(DIR);
            
            // define path, ensure directory exists, and save the file
            // Path uploadPath = Paths.get(cleanFolder);
            // if (!Files.exists(uploadPath)) {
            //     Files.createDirectories(uploadPath);
            // }
            Path filePath = Paths.get(cleanFolder, cleanFileName);

            System.out.println(contentType);
            System.out.println(filePath);

            // copy file to folder
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, filePath, java.nio.file.StandardCopyOption.REPLACE_EXISTING);
            }

            // update video entity with file info and save metadata
            video.setContentType(contentType);
            video.setFilePath(filePath.toString());
            return videoRepository.save(video);

        } catch (Exception ex) {
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