package com.executable.servigachi.model;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "d_type" )
public abstract class FileEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String filename;
	private String storagePath;
	private String mimetype;

	public FileEntity() {}
	public FileEntity(String filename) { this.filename = filename; }

	public Long getId() { return id; }
	public void setId(Long id) {  this.id = id; }

	public String getFilename() { return filename; }
	public void setFilename(String filename) { this.filename = filename; }

	public String getStoragePath() { return storagePath; }
	public void setStoragePath(String path) { this.storagePath = path; }
}
