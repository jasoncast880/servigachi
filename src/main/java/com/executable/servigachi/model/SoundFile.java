package com.executable.servigachi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "entries")
public class SoundFile {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String filename;

	public SoundFile() {}
	public SoundFile(String filename) { this.filename = filename; }

	public Long getId() { return id; }
	public void setId(Long id) {  this.id = id; }
	public String getName() { return filename; }
	public void setName(String filename) { this.filename = filename; }
}
