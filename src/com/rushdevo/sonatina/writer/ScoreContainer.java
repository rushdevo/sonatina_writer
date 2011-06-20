package com.rushdevo.sonatina.writer;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ScrollView;

import com.rushdevo.sonatina.lib.Constants;
import com.rushdevo.sonatina.lib.data.RecordNotFoundException;
import com.rushdevo.sonatina.lib.data.Score;

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
		try {
			scoreView = new ScoreView(this, retrieveOrCreateScore());
		} catch (RecordNotFoundException e) {
			// TODO: What should happen here? Can't find the score in the database. Display error
		}
		scrollView.addView(scoreView);
		setContentView(scrollView);
        scoreView.requestFocus();
	}
	
	private Score retrieveOrCreateScore() throws RecordNotFoundException {
		Bundle extras = getIntent().getExtras();
        Integer scoreId = (extras != null) ? extras.getInt(Constants.SCORE_ID) : null;
        if (scoreId == null) {
        	return new Score(this);
        } else {
        	return new Score(scoreId, this);
        }
	}
}
