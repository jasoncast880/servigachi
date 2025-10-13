package com.executable.servigachi.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "users")
class SoundFile {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;

	public SoundFile() {}
	public SoundFile(String name) { this.name = name; }

	public Long getId() { return id; }
	public void setId(Long id) {  this.id = id; }
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
}
