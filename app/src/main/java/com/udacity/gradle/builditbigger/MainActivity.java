package com.udacity.gradle.builditbigger;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.aerifiu.jokedisplaylib.JokeMainActivity;

public class MainActivity extends ActionBarActivity implements RequestCallback, InterstitialCallback {

	private EndpointsAsyncTask asyncTask;
	ProgressDialog dialog;
	private Flavor flavor;
	private String result;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		flavor = new FlavorFlav();
		flavor.initInterstitial(this, this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
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

	public void tellJoke(View view) {
		if (asyncTask == null || asyncTask.getStatus() == AsyncTask.Status.FINISHED) {

			// show dialog
			dialog = new ProgressDialog(this);
			dialog.setMessage(getString(R.string.please_wait));
			dialog.setCancelable(false);
			dialog.show();

			// start task
			asyncTask = new EndpointsAsyncTask(this);
			asyncTask.execute();
		}
	}

	@Override
	public void onLoadingFinished(String result) {
		if (dialog != null) {
			dialog.dismiss();
		}
		try {
			if (!TextUtils.isEmpty(result)) {
				this.result = result;
				flavor.showInterstitial(this, this);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onAdClosed() {
		if (!TextUtils.isEmpty(result)) {
			Intent i = new Intent(this, JokeMainActivity.class);
			i.putExtra(JokeMainActivity.EXTRA_JOKE_DATA, result);
			startActivity(i);
		}
	}
}
