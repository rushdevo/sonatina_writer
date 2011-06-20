package com.rushdevo.sonatina.lib;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

import com.rushdevo.sonatina.lib.data.Part;
import com.rushdevo.sonatina.lib.data.Score;
import com.rushdevo.sonatina.writer.R;

public class ScoreDrawer {
	
	private static final int FIRST_PART_OFFSET = 60;
	private static final int PART_OFFSET = 100;
	private static final int STAFF_LINE_OFFSET = 10;
	
	private Score score;
	private Canvas canvas;
	private View view;
	
	private Paint backgroundPaint;
	private Paint titleTextPaint;
	private Paint staffPaint;
	
	/**
	 * ScoreDrawer constructor
	 * 
	 * @param score - The Score object defining the score to draw
	 * @param canvas - The canvas to draw on
	 * @param view - The view containing the canvas
	 */
	public ScoreDrawer(Score score, Canvas canvas, View view) {
		this.score = score;
		this.canvas = canvas;
		this.view = view;
		this.backgroundPaint = new Paint();
		this.backgroundPaint.setColor(view.getResources().getColor(R.color.score_background));
		this.titleTextPaint = new Paint();
		this.titleTextPaint.setColor(view.getResources().getColor(R.color.score_foreground));
		this.titleTextPaint.setTextSize(20);
		this.staffPaint = new Paint();
		this.staffPaint.setColor(view.getResources().getColor(R.color.staff_lines));
	}
	
	/**
	 * Draws a score
	 * 
	 * @param score - The Score object defining the score to draw
	 * @param canvas - The canvas to draw on
	 * @param view - The view containing the canvas
	 */
	public void drawScore() {
		resizeView();
		drawBackground();
		drawTitle();
		for (Part part : score.getParts()) {
			drawPart(part);
		}
	}
	
	/**
	 * Draw the title of the score
	 */
	public void drawTitle() {
		canvas.drawText(score.getWorkTitle(), 30, 30, this.titleTextPaint);
	}
	
	/**
	 * Draw the background of the score
	 */
	public void drawBackground() {
		canvas.drawRect(0, 0, getScoreWidth(), getScoreHeight(), backgroundPaint);
	}
	
	/**
	 * Draw a staff
	 * 
	 * @param part - The part object defining the staff to draw
	 */
	public void drawPart(Part part) {
		int offset = FIRST_PART_OFFSET + (part.getPosition()*PART_OFFSET);
		for (int line=0; line<5; line++) {
			int y = offset+(line*STAFF_LINE_OFFSET);
			this.canvas.drawLine(10, y, 1000, y, this.staffPaint);
		}
	}
	
	/**
	 * Resizes the view containing the canvas based on the length of the score, or a default length of 4 measures
	 */
	public void resizeView() {
		// TODO
	}
	
	/**
	 * @return width of the score
	 */
	public int getScoreWidth() {
		return view.getWidth();
	}
	
	/**
	 * @return height of the score
	 */
	public int getScoreHeight() {
		return view.getHeight();
	}
}
