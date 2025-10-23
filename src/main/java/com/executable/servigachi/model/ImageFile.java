package com.executable.servigachi.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@DiscriminatorValue("IMAGE")
public class ImageFile extends FileEntity{
	//assume even/square resolution, avg ppi
	private int resolution = 67*67;
	private int ppi = 67;

	public int getRes() { return resolution; }
	public void setRes(int resolution) { this.resolution = resolution; }

	public int getPPI() { return ppi; }
	public void setPPI(int ppi) { this.ppi = ppi; }

	public ImageFile() {}
	public ImageFile(String filename) { super(filename);	}
}
