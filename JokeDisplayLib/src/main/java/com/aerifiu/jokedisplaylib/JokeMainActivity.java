package com.aerifiu.jokedisplaylib;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class JokeMainActivity extends AppCompatActivity {

	public static final String EXTRA_JOKE_DATA = "extraJokeData";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_joke_main);

		if (savedInstanceState == null) {
			Intent i = getIntent();
			if (i != null) {
				String joke = i.getStringExtra(EXTRA_JOKE_DATA);
				TextView textView = (TextView) findViewById(R.id.activity_joke_main_text);
				textView.setText(joke == null ? "" : joke);
			}
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_joke_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}
}
