package com.rushdevo.sonatina.writer;

import android.app.Activity;
import android.os.Bundle;

/**
 * @author jasonrush
 *
 *	The score setup activity - configure new score or update existing score
 */
public class ScoreSetup extends Activity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.score_setup);
	}
}
