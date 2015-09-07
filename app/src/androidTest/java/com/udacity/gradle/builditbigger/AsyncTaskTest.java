package com.udacity.gradle.builditbigger;

import android.os.ConditionVariable;
import android.test.AndroidTestCase;

public class AsyncTaskTest extends AndroidTestCase implements RequestCallback {

	private EndpointsAsyncTask task;
	private ConditionVariable waitForTask;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		task = new EndpointsAsyncTask(this);
		waitForTask = new ConditionVariable();
	}

	public void testNotEmptyString() {
		task.execute();
		waitForTask.block();
	}

	@Override
	public void onLoadingFinished(String result) {
		assertNotNull(result);
		assertNotSame(result, "");
		waitForTask.open();
	}
}
