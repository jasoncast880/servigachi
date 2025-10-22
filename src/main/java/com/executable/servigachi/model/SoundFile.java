package com.executable.servigachi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "audio_files")
public class SoundFile extends FileEntity{
	//technicals/core
	private int sample_rate;
	private int bit_rate;
	private int bps;

	private int channels;
	private int duration;

	//metadatas
	private String title, artist, album, genre, date;
	private int track;
	private String albumArt_Path; //idk

	public SoundFile(String filename) { super(filename); }
}
