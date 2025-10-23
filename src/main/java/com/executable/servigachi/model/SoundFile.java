package com.executable.servigachi.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@DiscriminatorValue("AUDIO")
public class SoundFile extends FileEntity{
	//technicals/core
	private int bit_rate;

	//metadatas

	public SoundFile() {}
	public SoundFile(String filename) { super(filename); }
}
