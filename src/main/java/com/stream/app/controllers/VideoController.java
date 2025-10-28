package com.stream.app.controllers;

import java.util.UUID;
import java.util.Map;
import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.PostMapping;

import com.stream.app.entities.Video;
import com.stream.app.payload.CustomMessage;
import com.stream.app.services.VideoService;


@RestController
@RequestMapping("/api/v1/videos")
public class VideoController {

    VideoService videoService;

    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }


    @PostMapping
    public ResponseEntity<CustomMessage> create (
        @RequestParam("file") MultipartFile file,
        @RequestParam("title") String title,
        @RequestParam("description") String description
        ){
            
            Video video = new Video();
            video.setTitle(title);
            video.setDescription(description);
            video.setVideoId(UUID.randomUUID().toString());

            Video savedVideo = videoService.save(video, file);

            if(savedVideo != null) {
                Map<String, Object> metadata = new HashMap<>();
                metadata.put("videoId", savedVideo.getVideoId());
                metadata.put("title", savedVideo.getTitle());
                metadata.put("description", savedVideo.getDescription());
                metadata.put("originalFilename", file.getOriginalFilename());
                metadata.put("contentType", file.getContentType());
                metadata.put("size", file.getSize());

                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(CustomMessage.builder()
                                .message("video uploaded successfully")
                                .success(true)
                                .metadata(metadata)
                                .build());           
            } else {
                return ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(CustomMessage.builder()
                                .message("video not uploaded !!")
                                .success(false)
                                .build()); 
            }
        }
    
}
