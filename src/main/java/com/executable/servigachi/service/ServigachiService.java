package com.executable.servigachi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.executable.servigachi.model.*;
import com.executable.servigachi.repository.*;

@Service
public class ServigachiService {
	private final SoundFileRepository repo;
	
	public ServigachiService(SoundFileRepository repo){
		this.repo = repo;
	}

	public Optional<SoundFile> getFileById(Long id){
		return repo.findById(id);
	}

	public ArrayList<SoundFile> getFileList(){
		return new ArrayList<SoundFile>();
	}

	public SoundFile saveFile(SoundFile file){
		return repo.save(file);
	}

	public void deleteFileById(Long id){
		repo.deleteById(id);
	}

}


