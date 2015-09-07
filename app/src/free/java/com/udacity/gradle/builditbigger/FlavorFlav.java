package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.view.View;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

// As overriding java files with flavors is not possible the only solution is
// an interface :( Pointed out by Jake Wharton and Xavier in:
// https://groups.google.com/forum/?hl=fr#!topic/adt-dev/f2I9LQIogOM
//
// (Except stripping library code with proguard and using a flavored bool.xml
// but I think this solution might not be the endorsed one so I went with the interface)

public class FlavorFlav implements Flavor {

	InterstitialAd interstitialAd;

	@Override
	public void buildMainBannerAd(View root) {
		try {
			AdView mAdView = (AdView) root.findViewById(R.id.adView);
			AdRequest adRequest = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();
			mAdView.loadAd(adRequest);
			mAdView.setVisibility(View.VISIBLE);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initInterstitial(Context context, final InterstitialCallback callback) {
		//from: https://developers.google.com/admob/android/interstitial?hl=en

		interstitialAd = new InterstitialAd(context);
		interstitialAd.setAdUnitId(context.getString(R.string.banner_ad_unit_id));
		interstitialAd.setAdListener(new AdListener() {
			@Override
			public void onAdClosed() {
				requestNewInterstitial();
				callback.onAdClosed();
			}
		});

		requestNewInterstitial();
	}

	@Override
	public void showInterstitial(Context context, final InterstitialCallback callback) {

		if (interstitialAd != null && interstitialAd.isLoaded()) {
			interstitialAd.show();

		} else {
			callback.onAdClosed();
		}
	}

	private void requestNewInterstitial() {
		AdRequest adRequest = new AdRequest.Builder()
				.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
				.build();

		interstitialAd.loadAd(adRequest);
	}
}
