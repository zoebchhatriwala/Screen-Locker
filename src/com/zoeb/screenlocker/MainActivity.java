package com.zoeb.screenlocker;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	protected SharedPreferences preferences;
	Context context;  
	final int NOTIFICATION_ID = 1;
	Notification.Builder builder;
	NotificationManager manager;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		context = MainActivity.this;
		builder = new Notification.Builder(context);  
		final Button Activate = (Button) findViewById(R.id.button1);
		preferences = this.getSharedPreferences("com.zoeb.screenlocker",Context.MODE_PRIVATE);
        builder.setContentText("Lock/Unlock Screen").setContentTitle(context.getString(R.string.app_name));  
        builder.setSmallIcon(R.drawable.ic_launcher);
        Intent secondActivityIntent = new Intent(MainActivity.this, ActivityMngr.class); 
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, secondActivityIntent, 0);  
        builder.setContentIntent(pendingIntent);  
        builder.setAutoCancel(false);
        final Notification notification = builder.build(); 
        notification.flags |= Notification.FLAG_NO_CLEAR;
        manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		Boolean bin = preferences.getBoolean("com.zoeb.screenlocker.ison",false);
		if(bin.booleanValue()==true)
		{ 
			preferences.edit().putBoolean("com.zoeb.screenlocker.ison",true).apply();
			Activate.setText("Deactivate");
	        manager.notify(NOTIFICATION_ID, notification);
		}
		Activate.setOnClickListener(new View.OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				if(preferences.getBoolean("com.zoeb.screenlocker.ison",false)==false)
				{
					preferences.edit().putBoolean("com.zoeb.screenlocker.ison",true).apply();
					Activate.setText("Deactivate");
			        manager.notify(NOTIFICATION_ID, notification);
				}
				else
				{
					preferences.edit().putBoolean("com.zoeb.screenlocker.ison",false).apply();
					Activate.setText("Activate");
					manager.cancel(NOTIFICATION_ID);
				}
			}
		});
	}

	@Override
	protected void onPause()
	{
		super.onPause();
		finish();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
