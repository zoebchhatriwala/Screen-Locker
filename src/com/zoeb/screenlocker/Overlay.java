package com.zoeb.screenlocker;


import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.ImageButton;

public class Overlay extends Service {

	private WindowManager wm;
	ImageButton overlayedButton;
	
	@SuppressLint("RtlHardcoded")
	@SuppressWarnings("deprecation")
	@Override
	public void onCreate()
	{
		super.onCreate();
		wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
		overlayedButton = new ImageButton(this);
		overlayedButton.setBackgroundResource(R.color.trans);
		overlayedButton.setAlpha(0);
		WindowManager.LayoutParams params = new WindowManager.LayoutParams(WindowManager.LayoutParams.FILL_PARENT, WindowManager.LayoutParams.FILL_PARENT, WindowManager.LayoutParams.TYPE_SYSTEM_ALERT, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL, PixelFormat.TRANSLUCENT);
		params.gravity = Gravity.CENTER | Gravity.LEFT;
		params.x = 0;
		params.y = 0;
		wm.addView(overlayedButton, params);
	}
	
	@Override
	public IBinder onBind(Intent intent)
	{
		return null;
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		if (overlayedButton != null) {
		    wm.removeView(overlayedButton);
		    overlayedButton = null;
		}
	    }
	

}
