package com.rushdevo.sonatina.writer;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;

import com.rushdevo.sonatina.lib.Score;
import com.rushdevo.sonatina.lib.ScoreDrawer;

/**
 * @author jasonrush
 * 
 * The score view - holds the main score canvas
 */
public class ScoreView extends View {
	private Score score;
	private ScoreDrawer scoreDrawer;
	
	public ScoreView(Context context, Score score) {
		super(context);
		this.score = score;
		setFocusable(true);
		setFocusableInTouchMode(true);
	}
	
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		scoreDrawer = new ScoreDrawer(getScore(), canvas, this);
		scoreDrawer.drawScore();
	}
	
	public Score getScore() {
		return this.score;
	}
}
