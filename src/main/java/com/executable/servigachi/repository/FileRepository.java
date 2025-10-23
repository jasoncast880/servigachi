package com.executable.servigachi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.executable.servigachi.model.*;

@Repository
public interface FileRepository extends JpaRepository<FileEntity, Long> {
}
