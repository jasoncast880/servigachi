package com.executable.servigachi.service;

import java.util.List;
import java.util.Optional;

import java.util.stream.*;

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

	//AUDIO
	public Optional<SoundFile> getAudioFileById(Long id) {
		Optional<SoundFile> _file =  repo.findAll().stream()
			.filter(f -> f instanceof SoundFile)
			.map(f -> (SoundFile) f)
			.filter(a -> a.getId().equals(id))
			.findFirst();
		return _file;
	}

	public Optional<SoundFile> getAudioFileByName(String filename) {
		Optional<SoundFile> _file =  repo.findAll().stream()
			.filter(f -> f instanceof SoundFile)
			.map(f -> (SoundFile) f)
			.filter(a -> a.getFilename().equals(filename))
			.findFirst();
		return _file;
	}

	public List<SoundFile> getAudioFileList() {
		List<SoundFile> _fileList = repo.findAll().stream()
			.filter(f -> f instanceof SoundFile)
			.map(f -> (SoundFile) f)
			.toList();
		return _fileList;
	}

	//IMAGE 
	//There should be a way to make get by id, name generic based on polymorphism??
	public Optional<ImageFile> getImageFileById(Long id) {
		Optional<ImageFile> _file =  repo.findAll().stream()
			.filter(f -> f instanceof ImageFile)
			.map(f -> (ImageFile) f)
			.filter(a -> a.getId().equals(id))
			.findFirst();
		return _file;
	}

	public Optional<ImageFile> getImageFileByName(String filename) {
		Optional<ImageFile> _file =  repo.findAll().stream()
			.filter(f -> f instanceof ImageFile)
			.map(f -> (ImageFile) f)
			.filter(a -> a.getFilename().equals(filename))
			.findFirst();
		return _file;
	}

	public List<ImageFile> getImageFileList() {
		List<ImageFile> _fileList = repo.findAll().stream()
			.filter(f -> f instanceof ImageFile)
			.map(f -> (ImageFile) f)
			.toList();
		return _fileList;
	}
}
