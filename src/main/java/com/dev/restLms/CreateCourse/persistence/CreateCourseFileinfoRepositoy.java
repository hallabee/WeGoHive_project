package com.dev.restLms.CreateCourse.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.restLms.entity.FileInfo;

public interface CreateCourseFileinfoRepositoy extends JpaRepository<FileInfo, String>{
    
}
