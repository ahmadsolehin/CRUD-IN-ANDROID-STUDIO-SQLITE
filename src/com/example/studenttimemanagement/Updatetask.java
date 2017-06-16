package com.example.studenttimemanagement;

import java.util.Calendar;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Updatetask extends Activity {
	ImageButton imageButton4,imageButton3,imageButton2;
	private static final int DATE_DIALOG = 1;
	private static final int TIME_DIALOG = 2;
	private int day, month, year, hours, mins;
	private TextView textStartDate, textStartTime,text6;
	EditText ayam,kambing;
    String abu,ali,dog,cat,ikan;
    int a;
	ImageButton button1;
	   SQLiteDatabase db;
	
	LoginDataBaseAdapter loginDataBaseAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_updatetask);
		
		db= openOrCreateDatabase("studenttime.db", MODE_PRIVATE, null);
		   //create new table if not already exist
		   db.execSQL("create table if not exists timetable(name varchar, sur_name varchar)");
		   
		
		// get Instance  of Database Adapter
		loginDataBaseAdapter=new LoginDataBaseAdapter(this);
		loginDataBaseAdapter=loginDataBaseAdapter.open();
		

		textStartDate = (TextView) this.findViewById(R.id.textStartDate);
		textStartTime = (TextView) this.findViewById(R.id.textStartTime);
		ayam = (EditText) findViewById(R.id.Edit1);
		kambing = (EditText) findViewById(R.id.Edit2);
		text6 = (TextView) findViewById(R.id.textView6);
		
		final MediaPlayer mp = MediaPlayer.create(this, R.raw.sound);
		imageButton4 =(ImageButton) findViewById(R.id.imageButton5);
		imageButton3 =(ImageButton) findViewById(R.id.imageButton3);
		imageButton2 =(ImageButton) findViewById(R.id.imageButton2);
		button1 =(ImageButton) findViewById(R.id.imageButton1);
		
		
imageButton2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				ayam.setText("");
				kambing.setText("");
				textStartDate.setText("");
				textStartTime.setText("");
				mp.start();
			}
		});
		
		
		imageButton3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				abu = ayam.getText().toString();
				
				loginDataBaseAdapter.deleteEntry(abu);
				mp.start();
				
			    Toast.makeText(getApplicationContext(), "DELETE SUCCESSFUL ", Toast.LENGTH_LONG).show();
			    ayam.setText("");
				kambing.setText("");
				textStartDate.setText("");
				textStartTime.setText("");
			}
		});




		//nie update
		button1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				abu = ayam.getText().toString();
				ali = kambing.getText().toString();
				dog = textStartDate.getText().toString();
				cat = textStartTime.getText().toString();
				ikan = text6.getText().toString();
				a = Integer.parseInt(ikan);
				mp.start();
				
				
				loginDataBaseAdapter.updateEntry(a,dog,cat,abu,ali);
				
			    Toast.makeText(getApplicationContext(), "TASK SUCCESSFULLY UPDATED ", Toast.LENGTH_LONG).show();

			}
		});
		
		
		
		
		
		
		
		
		
		
		imageButton4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent x = new Intent(Updatetask.this,MainActivity.class);
				  mp.start();
             startActivity(x); 
				
			}
		});
		
		setDateToSysdate();
		updateDateDisplay();
		
		signIn();
		
		
		
	}
	
	
	
	
	
	
	
	public void signIn()
	   {
			final Dialog dialog = new Dialog(Updatetask.this);
			dialog.setContentView(R.layout.login);
		    dialog.setTitle("Please enter");
	
		    // get the Refferences of views
		    final  EditText editTextUserName=(EditText)dialog.findViewById(R.id.editTextUserNameToLogin);
		    
			Button btnSignIn=(Button)dialog.findViewById(R.id.buttonSignIn);
				
			// Set On ClickListener
			btnSignIn.setOnClickListener(new View.OnClickListener() {
				
				public void onClick(View v) {
					// get The User name and Password
					String userName=editTextUserName.getText().toString();
					
					// fetch the Password form database for respective user name
					huhu(userName);
					dialog.dismiss();

					
					// check if the Stored password matches with  Password entered by user
					
				}
			});
			
			dialog.show();
	}
	
	
	
	
	
	
	
	
	
	
	public void huhu(String topic)
	{
		   	    try{
		   Cursor c=db.rawQuery("select * from timetable WHERE TOPIC = ? ", new String[] {topic});
	        if(c.getCount()<1) // UserName Not Exist
	        {
	        	c.close();
	        }
		    c.moveToFirst();
		    String id=c.getString(c.getColumnIndex("ID"));
		    String date=c.getString(c.getColumnIndex("DATE"));
		    String time =c.getString(c.getColumnIndex("TIME"));
		    String tok=c.getString(c.getColumnIndex("TOPIC"));
		    String remarks=c.getString(c.getColumnIndex("REMARKS"));

		    textStartDate.setText(date);
		    textStartTime.setText(time);
		    ayam.setText(tok);
		    kambing.setText(remarks);
		    text6.setText(id);
		    
		   	    }catch(Throwable e){
			   	    e.printStackTrace();
		   	    }
		   	    
		   	    
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

}

