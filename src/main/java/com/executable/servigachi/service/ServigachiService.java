package com.executable.servigachi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.executable.servigachi.model.*;
import com.executable.servigachi.repository.FileRepository;

//handles metadata; not actual files.
@Service
public class ServigachiService {
	private final FileRepository repo;
	
	public ServigachiService(FileRepository repo) {
		this.repo = repo;
	}

	public Optional<FileEntity> getFileById(Long id) {
		return repo.findById(id);
	}

	public List<FileEntity> getFileList() {
		List<FileEntity> _fileList = repo.findAll();
		return _fileList;
	}

	public FileEntity updateFile(FileEntity file) {
		return repo.save(file);
	}

	public void deleteFileById(Long id) {
		repo.deleteById(id);
	}

	public void deleteFileList() {
		repo.deleteAll();
	}
}
