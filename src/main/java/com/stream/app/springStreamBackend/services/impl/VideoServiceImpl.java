package com.stream.app.springStreamBackend.services.impl;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.stream.app.springStreamBackend.entities.Video;
import com.stream.app.springStreamBackend.services.VideoService;

public class VideoServiceImpl implements VideoService {

    @Override
    public Video save(Video video, MultipartFile file) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
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
