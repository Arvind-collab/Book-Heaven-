package com.arzin.btrove;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;

public class Harbour extends Activity{

	MediaPlayer startClip;
	@Override
	protected void onCreate(Bundle bundleInstance) {
		// TODO Auto-generated method stub
		super.onCreate(bundleInstance);
		setContentView(R.layout.harbour);
		startClip = MediaPlayer.create(Harbour.this, R.raw.redfern);
		
		final SharedPreferences getPrefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		boolean music = getPrefs.getBoolean("checkbox", true);
		final boolean fasten = getPrefs.getBoolean("checkbox4", false);
		if(music==true){
		startClip.start();
		}
	Thread timer = new Thread(){
		public void run(){
			try{
				if(fasten==true){
				sleep(100);
				}else{
				sleep(3800);	
				}
			}
			catch(InterruptedException e){
				e.printStackTrace();
			}
			finally{
				boolean fakeLogin = getPrefs.getBoolean("checkbox2", true);
				if(fakeLogin==true){
				Intent openStartingPoint = new Intent("com.arzin.btrove.STARTINGPOINT");
				startActivity(openStartingPoint);
				}else{
				Intent openStartingPoint = new Intent("com.arzin.btrove.MENU");
				startActivity(openStartingPoint);
				}
			}
		}
	};
		timer.start();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		startClip.release();
		finish();
	}
		
}
