package com.stream.app.springStreamBackend.services.impl;

import java.io.InputStream;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.stream.app.springStreamBackend.entities.Video;
import com.stream.app.springStreamBackend.services.VideoService;

import lombok.Value;

@Service
public class VideoServiceImpl implements VideoService {

    String DIR;

    public VideoServiceImpl(@Value("${files.video}") String dir) {
        this.DIR = dir;
    }

    @Override
    public Video save(Video video, MultipartFile file) {

        try {
        //original file name
        String filename = file.getOriginalFilename();
        String contentType = file.getContentType();
        InputStream inputStream = file.getInputStream();

        

        // folder path : create
        String cleanFileName = StringUtils.cleanPath(filename);
        String cleanFolder = StringUtils.cleanPath(DIR);

        Paths.get(cleanFolder, cleanFileName);

        // folder path with file name
        // copy file to folder
        // video meta data
        // save meta data
        } catch(Exception e) {
            e.printStackTrace();
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
