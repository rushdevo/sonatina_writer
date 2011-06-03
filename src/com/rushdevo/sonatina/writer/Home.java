package com.rushdevo.sonatina.writer;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

/**
 * @author jasonrush
 *
 *	The home screen activity
 */
public class Home extends Activity implements OnClickListener {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        View newButton = findViewById(R.id.new_button);
        newButton.setOnClickListener(this);
        View openButton = findViewById(R.id.open_button);
        openButton.setOnClickListener(this);
        View exitButton = findViewById(R.id.exit_button);
        exitButton.setOnClickListener(this);
    }

	@Override
	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.new_button:
    		// TODO
    		break;
		case R.id.open_button:
			// TODO
			break;
		case R.id.exit_button:
			finish();
			break;
		}
	}
}