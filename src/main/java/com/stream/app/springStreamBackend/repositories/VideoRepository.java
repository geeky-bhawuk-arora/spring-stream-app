package com.stream.app.springStreamBackend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stream.app.springStreamBackend.entities.Video;

@Repository
public interface VideoRepository extends JpaRepository<Video, String> {

    Optional<Video> findByTitle(String title);

    // query methods
    // native
    // criteria api
}
