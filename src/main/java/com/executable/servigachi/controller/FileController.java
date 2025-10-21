package com.executable.servigachi.controller;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.executable.servigachi.model.*;

import com.executable.servigachi.service.ServigachiService;

@RestController
@RequestMapping("/api")
public class FileController {

	public ServigachiService s;

	public FileController(ServigachiService s) {
		this.s = s;
	}

	@GetMapping("/")
	ResponseEntity<List<FileEntity>> getFileList() {
		try {
			List<FileEntity> fileList = s.getFileList();
			if(fileList.isEmpty()) {
				return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(fileList, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{id}")
	ResponseEntity<? super FileEntity> getFileById(@PathVariable("id") long id) {
		Optional<? super FileEntity> optFile = s.getFileById(id);

		if(optFile.isPresent()) {
			return new ResponseEntity<>(optFile.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/upload/audio")
	ResponseEntity<SoundFile> uploadSoundFile(@RequestParam("file") MultipartFile file) {
		try {
			//upload on disk
			Path uploadDir = Paths.get("uploads/audio");

			Path filepath = uploadDir.resolve(file.getOriginalFilename());
			
			Files.write(filepath, file.getBytes());
			
			//metadata on db
			SoundFile _file = new SoundFile(file.getOriginalFilename());
			s.updateFile(_file);

			return new ResponseEntity<>(_file, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/upload/img")
	ResponseEntity<ImageFile> uploadImageFile(@RequestParam("file") MultipartFile file) {
		try {
			Path uploadDir = Paths.get("uploads/img");

			Path filepath = uploadDir.resolve(file.getOriginalFilename());

			Files.write(filepath, file.getBytes());

			ImageFile _file = new ImageFile(file.getOriginalFilename());
			s.updateFile(_file);

			return new ResponseEntity<>(_file, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	@PostMapping("/")
	ResponseEntity<? extends FileEntity> postFile(@RequestBody SoundFile file) {
		try {
			FileEntity _file = s.updateFile(file);
			return new ResponseEntity<>(_file, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/{id}")
	ResponseEntity<FileEntity> putFileById(@PathVariable("id") long id, @RequestBody SoundFile file) {
		Optional<SoundFile> optFile = s.getAudioFileById(id);
		if(optFile.isPresent()) {
			SoundFile _file = optFile.get();
			_file.setFilename(file.getFilename());

			return new ResponseEntity<>(s.updateFile(_file), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/")
	ResponseEntity<SoundFile> deleteFileList() {
		try {
			s.deleteFileList();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/{id}")
	ResponseEntity<SoundFile> deleteFile(@PathVariable("id") long id) {
		Optional<SoundFile> optFile = s.getAudioFileById(id);

		if(optFile.isPresent()) {
		s.deleteFileById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
}
