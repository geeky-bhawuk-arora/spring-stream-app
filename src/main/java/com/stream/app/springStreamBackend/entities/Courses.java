package com.stream.app.springStreamBackend.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "yt_courses")
public class Courses {

    @Id
    private String id;

    private String title;

    // @OneToMany(mappedBy = "course")
    // private List<Video> list = new ArrayList<>();

}