package com.dev.restLms.deleteCourse.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.restLms.entity.FileInfo;

public interface DeleteCourseFileinfoRepository extends JpaRepository<FileInfo, String> {
    
}
