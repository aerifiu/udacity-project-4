package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.view.View;

public interface Flavor {

	void buildMainBannerAd(View root);

	void initInterstitial(Context context, final InterstitialCallback callback);

	void showInterstitial(Context context, final InterstitialCallback callback);
}
