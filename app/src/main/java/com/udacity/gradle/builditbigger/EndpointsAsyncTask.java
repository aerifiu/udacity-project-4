package com.udacity.gradle.builditbigger;

import android.os.AsyncTask;

import com.aerifiu.backend.jokeApi.JokeApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

class EndpointsAsyncTask extends AsyncTask<Void, Void, String> {

	private RequestCallback requestCallback = new CallbackStub();
	private static JokeApi jokeApi = null;

	class CallbackStub implements RequestCallback {

		@Override
		public void onLoadingFinished(String result) {
		}
	}

	public EndpointsAsyncTask(RequestCallback requestCallback) {
		this.requestCallback = requestCallback;
	}

	@Override
	protected String doInBackground(Void... params) {

		// lets wait a little to see the dialog
		if (BuildConfig.DEBUG) {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException ignore) {
			}
		}

		if (jokeApi == null) {  // Only do this once
			JokeApi.Builder builder = new JokeApi.Builder(AndroidHttp.newCompatibleTransport(),
					new AndroidJsonFactory(), null)
					// options for running against local devappserver
					// - 10.0.2.2 is localhost's IP address in Android emulator
					// - turn off compression when running against local devappserver
					.setRootUrl("http://10.0.2.2:8080/_ah/api/")
					.setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
						@Override
						public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
							abstractGoogleClientRequest.setDisableGZipContent(true);
						}
					});

			jokeApi = builder.build();
		}

		try {
			return jokeApi.getJoke().execute().getJoke();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return "";
	}

	@Override
	protected void onPostExecute(String s) {
		requestCallback.onLoadingFinished(s);
	}
}