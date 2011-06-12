package com.rushdevo.sonatina.writer;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ScrollView;

import com.rushdevo.sonatina.lib.Constants;
import com.rushdevo.sonatina.lib.Score;

/**
 * @author jasonrush
 *
 *	The score activity - holds the main score view
 */
public class ScoreContainer extends Activity {
	
	private ScoreView scoreView;
	private ScrollView scrollView;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        scrollView = new ScrollView(this);
        scrollView.setFillViewport(true);
		scoreView = new ScoreView(this, retrieveOrCreateScore());
		scrollView.addView(scoreView);
		setContentView(scrollView);
        scoreView.requestFocus();
	}
	
	private Score retrieveOrCreateScore() {
		Bundle extras = getIntent().getExtras();
        String filename = (extras == null) ? null : extras.getString(Constants.SCORE_FILENAME);
        return new Score(filename);
	}
}
