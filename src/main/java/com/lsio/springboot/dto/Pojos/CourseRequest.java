package com.lsio.springboot.dto.Pojos;

import java.util.Set;

import com.lsio.springboot.entities.CourseContents;

public class CourseRequest {

    public int id;

    public String coursename;

    public Set<CourseContents> coursecontents;
    
}
