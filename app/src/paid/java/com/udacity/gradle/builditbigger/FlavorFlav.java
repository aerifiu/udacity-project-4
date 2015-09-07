package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.view.View;

public class FlavorFlav implements Flavor {

	@Override
	public void buildMainBannerAd(View root) {

	}

	@Override
	public void initInterstitial(Context context, final InterstitialCallback callback) {

	}

	@Override
	public void showInterstitial(Context context, final InterstitialCallback callback) {
		callback.onAdClosed();
	}
}
