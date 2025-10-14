package com.executable.servigachi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.executable.servigachi.model.SoundFile;

public interface SoundFileRepository extends JpaRepository<SoundFile, Long> {

}
