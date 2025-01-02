package com.dev.restLms.VideoPlayerPage.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.restLms.entity.FileInfo;
import java.util.Optional;


public interface VideoPlayerFileInfoRepository extends JpaRepository<FileInfo, String>{
    Optional<FileInfo> findByFileNo(String fileNo);
}
