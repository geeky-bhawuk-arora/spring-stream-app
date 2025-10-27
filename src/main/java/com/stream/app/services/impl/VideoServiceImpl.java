package com.stream.app.services.impl;

import java.io.InputStream;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import com.stream.app.entities.Video;
import com.stream.app.services.VideoService;

import org.springframework.util.StringUtils;
import org.springframework.beans.factory.annotation.Value;


@Service
public class VideoServiceImpl implements VideoService {

    @Value("${video.files}")
    String DIR;

    @Override
    public Video save(Video save, MultipartFile file) {
        try {
            String fileName = file.getOriginalFilename();
            String contentType = file.getContentType();
            InputStream inputStream = file.getInputStream();

            String cleanFileName = StringUtils.cleanPath(fileName);
            String cleanFolder = StringUtils.cleanPath(DIR);
            
            Path path = Paths.get(cleanFolder, cleanFileName);

            System.out.println(path);

        } catch (IOException ex) {
        }
        return null;
    }

    @Override
    public Video get(String videoId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'get'");
    }

    @Override
    public Video getByTitle(String title) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getByTitle'");
    }

    @Override
    public List<Video> getAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }
    
}
