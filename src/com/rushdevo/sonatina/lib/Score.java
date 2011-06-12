package com.rushdevo.sonatina.lib;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jasonrush
 * 
 * Describes a score
 * 
 */
public class Score {
	
	private List<Staff> staves;
	private String filename;
	private String title;
	
	/**
	 * Score constructor
	 * 
	 * @param filename - name of file to read score from, or null to create a new one
	 */
	public Score(String filename) {
		this.staves = new ArrayList<Staff>();
		if (filename == null) {
			// Generate a new default score
			this.staves.add(new Staff(0));
			this.title = "Untitled";
		} else {
			// TODO: Read score from file
			this.filename = filename;
		}
	}
	
	public List<Staff> getStaves() {
		return this.staves;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFilename() {
		return filename;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}
}
