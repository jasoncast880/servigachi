package com.executable.servigachi.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.executable.servigachi.model.SoundFile;

import com.executable.servigachi.service.ServigachiService;

@RestController
@RequestMapping("/api")
public class FileController {

	public ServigachiService s;

	public FileController(ServigachiService s) {
		this.s = s;
	}

	@GetMapping("/")
	ResponseEntity<List<SoundFile>> getFileList() {
		try {
			List<SoundFile> fileList = s.getFileList();
			if(fileList.isEmpty()) {
				return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(fileList, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{id}")
	ResponseEntity<SoundFile> getFileById(@PathVariable("id") long id) {
		Optional<SoundFile> optFile = s.getFileById(id);

		if(optFile.isPresent()) {
			return new ResponseEntity<>(optFile.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/")
	ResponseEntity<SoundFile> postFile(@RequestBody SoundFile file) {
		try {
			SoundFile _file = s.updateFile(file);
			return new ResponseEntity<>(_file, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/{id}")
	ResponseEntity<SoundFile> putFileById(@PathVariable("id") long id, @RequestBody SoundFile file) {
		Optional<SoundFile> optFile = s.getFileById(id);

		if(optFile.isPresent()) {
			SoundFile _file = optFile.get();
			_file.setName(file.getName());

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
		Optional<SoundFile> optFile = s.getFileById(id);

		if(optFile.isPresent()) {
		s.deleteFileById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
}

