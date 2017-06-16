package com.example.studenttimemanagement;

import java.util.Calendar;





import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.TextView;
import android.app.Activity;
import android.media.MediaPlayer;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TimePicker;
import android.widget.Toast;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;

public class Addtask extends Activity {
	ImageButton imageButton2, imagebutton1;
	private static final int DATE_DIALOG = 1;
	private static final int TIME_DIALOG = 2;
	private int day, month, year, hours, mins;
	private TextView textStartDate, textStartTime;
    EditText ayam,kambing;
    String abu,ali,dog,cat;

	Context ctx = this;
	LoginDataBaseAdapter loginDataBaseAdapter;
	Intent intent = new Intent();
	final static int RQS_1 = 1;
	 int Unique_Integer_Number = 0;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_addtask);
		
		NotificationManager manager = (NotificationManager)this.getSystemService(NOTIFICATION_SERVICE);
        manager.cancelAll();
        
		// get Instance  of Database Adapter
				loginDataBaseAdapter=new LoginDataBaseAdapter(this);
				loginDataBaseAdapter=loginDataBaseAdapter.open();
				

		ayam = (EditText) findViewById(R.id.Edit1);
		kambing = (EditText) findViewById(R.id.Edit2);
		final MediaPlayer mp = MediaPlayer.create(this, R.raw.sound);
		imageButton2=(ImageButton) findViewById(R.id.imageButton5);
		imagebutton1=(ImageButton) findViewById(R.id.imageButton1);
	
		
		
		
		imageButton2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				Intent x = new Intent(Addtask.this,MainActivity.class);
				mp.start();
               startActivity(x); 
				
			}
		});
		
		textStartDate = (TextView) this.findViewById(R.id.textStartDate);
		textStartTime = (TextView) this.findViewById(R.id.textStartTime);
		
		setDateToSysdate();
		updateDateDisplay();
		
		
		
		
imagebutton1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				abu = ayam.getText().toString();
				ali = kambing.getText().toString();
				dog = textStartDate.getText().toString();
				cat = textStartTime.getText().toString();
				mp.start();
				
				haha(dog,cat,abu,ali);
				 loginDataBaseAdapter.insertEntry(dog, cat,abu,ali);
				    Toast.makeText(getApplicationContext(), "TASK SUCCESSFUL ADDED ", Toast.LENGTH_LONG).show();
					

				    


			}
		});

	}
	
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		
		loginDataBaseAdapter.close();
	}


	
	
	
	
	
	
	
	
	
	
	
	
	private void setDateToSysdate() {
		Calendar c = Calendar.getInstance();
		day = c.get(Calendar.DAY_OF_MONTH);
		month = c.get(Calendar.MONTH);
		year = c.get(Calendar.YEAR);
	}

	public void showDatePicker(View v) {
		showDialog(DATE_DIALOG);
	}
	
	public void showTimePicker(View v) {
		showDialog(TIME_DIALOG);
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		super.onCreateDialog(id);

		switch (id) {
		 case DATE_DIALOG:
			return new DatePickerDialog(this, dateSetListener, year, month, day);
		 case TIME_DIALOG:
			return new TimePickerDialog(this, timeSetListener, hours,mins, false);			
		}
		return null;
	}

	private DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {

		public void onDateSet(DatePicker view, int pYear, int pMonth, int pDay) {
			year = pYear;
			month = pMonth;
			day = pDay;
			updateDateDisplay();
		}
	};
	
	
	private TimePickerDialog.OnTimeSetListener timeSetListener = 
			   new TimePickerDialog.OnTimeSetListener() {

				@Override
				public void onTimeSet(TimePicker arg0, int pHours, int  pMins) {
					  hours = pHours;
					  mins = pMins;
					  updateTimeDisplay();
				}
	 
	};
	
	

	private void updateDateDisplay() {
		// Month is 0 based so add 1
		textStartDate.setText(String.format("%04d-%02d-%02d", year, month + 1,day));
	}
	
	private void updateTimeDisplay() {
		// Month is 0 based so add 1
		textStartTime.setText(String.format("%02d:%02d", hours,mins));
	}
	
public void haha(String a,String b,String c,String d){
		

			Unique_Integer_Number++;
			
			NotificationManager manager = (NotificationManager)this.getSystemService(NOTIFICATION_SERVICE);
	        Intent x = new Intent(this,Addtask.class);
			PendingIntent pend = PendingIntent.getBroadcast(this, 0, x, PendingIntent.FLAG_UPDATE_CURRENT);
			
			int icon = R.drawable.ic_launcher;
			String text = "notification";
			long when = SystemClock.uptimeMillis();
			
			String contentTitle = "Task";
			String contentText = (a+" "+b+" "+c+" "+d);

			Notification notification = new Notification(icon,text,when);
			notification.setLatestEventInfo(this, contentTitle, contentText, pend);
			manager.notify(Unique_Integer_Number,notification);

	}

}


