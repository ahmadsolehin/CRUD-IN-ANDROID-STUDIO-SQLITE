package com.example.studenttimemanagement;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.provider.Settings.System;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

public class MyNotificationService extends Service {

	 int Unique_Integer_Number = 0;



	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		Toast.makeText(this, "on create", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Toast.makeText(this, "on destroy", Toast.LENGTH_SHORT).show();
	}
	
	

	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
		Toast.makeText(this, "on start", Toast.LENGTH_SHORT).show();
		Unique_Integer_Number++;
		
		NotificationManager manager = (NotificationManager)this.getSystemService(NOTIFICATION_SERVICE);
        Intent x = new Intent(this,Addtask.class);
		PendingIntent pend = PendingIntent.getBroadcast(this, 0, x, PendingIntent.FLAG_UPDATE_CURRENT);
		
		int icon = R.drawable.ic_launcher;
		String text = "haha";
		long when = SystemClock.uptimeMillis();
		
		String contentTitle = "title";
		String contentText = "ade msg";

		Notification notification = new Notification(icon,text,when);
		notification.setLatestEventInfo(this, contentTitle, contentText, pend);
		manager.notify(Unique_Integer_Number,notification);
	}

}
