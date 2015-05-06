package com.zoeb.screenlocker;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

public class ActivityMngr extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		Intent svc = new Intent(this,Overlay.class);
		SharedPreferences preferences = this.getSharedPreferences("com.zoeb.screenlocker",Context.MODE_PRIVATE);
		Boolean bin = preferences.getBoolean("com.zoeb.screenlocker.lockon",false);
		if(bin.booleanValue()==true)
		{
			stopService(svc);
			preferences.edit().putBoolean("com.zoeb.screenlocker.lockon",false).apply();
		}
		else
		{

	        startService(svc);
	        preferences.edit().putBoolean("com.zoeb.screenlocker.lockon",true).apply();
		}
		finish();
	}

}
