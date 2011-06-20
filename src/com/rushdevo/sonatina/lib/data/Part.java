package com.rushdevo.sonatina.lib.data;

import java.util.List;

/**
 * @author jasonrush
 * 
 * Describes a staff of music
 * 
 */
public class Part {
	private int position;
	
	public Part(int position) {
		this.position = position;
	}
	
	public int getPosition() {
		return this.position;
	}
	
	/**
	 * 
	 * @param score: The score who's parts you want
	 * @return a list of parts that belong to the score
	 */
	public static List<Part> forScore(Score score) {
		// TODO
		return null;
	}
	
	/**
	 * 
	 * @return part: A default part with a treble clef and the key of C
	 */
	public static Part defaultPart() {
		return new Part(0);
	}
}
