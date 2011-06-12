package com.rushdevo.sonatina.lib;

import com.rushdevo.sonatina.writer.R;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

public class ScoreDrawer {
	
	private static final int FIRST_STAFF_OFFSET = 60;
	private static final int STAFF_OFFSET = 100;
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
		for (Staff staff : score.getStaves()) {
			drawStaff(staff);
		}
	}
	
	/**
	 * Draw the title of the score
	 */
	public void drawTitle() {
		canvas.drawText(score.getTitle(), 30, 30, this.titleTextPaint);
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
	 * @param staff - The staff object defining the staff to draw
	 */
	public void drawStaff(Staff staff) {
		int offset = FIRST_STAFF_OFFSET + (staff.getPosition()*STAFF_OFFSET);
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
