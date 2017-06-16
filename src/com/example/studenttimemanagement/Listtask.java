package com.example.studenttimemanagement;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class Listtask extends Activity {
	ImageButton imageButton5;
	LoginDataBaseAdapter loginDataBaseAdapter;
	TextView text5;
	   SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listtask);
		
		final MediaPlayer mp = MediaPlayer.create(this, R.raw.sound);
		imageButton5 = (ImageButton) findViewById(R.id.imageButton5);
		imageButton5.setOnClickListener (new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent x = new Intent (Listtask.this,MainActivity.class);
				mp.start();
	            startActivity(x); 
			}
		});
		
		
		db= openOrCreateDatabase("studenttime.db", MODE_PRIVATE, null);
		   //create new table if not already exist
		   db.execSQL("create table if not exists timetable(name varchar, sur_name varchar)");
		   
		
		// get Instance  of Database Adapter
		loginDataBaseAdapter=new LoginDataBaseAdapter(this);
		loginDataBaseAdapter=loginDataBaseAdapter.open();
		text5 = (TextView) findViewById(R.id.textView5);
	

	    display();
	    
	    
	}
	
	public void display()
	   {
	//use cursor to keep all data
	   //cursor can keep data of any data type
	   Cursor c=db.rawQuery("select * from timetable ", null);
	   //move cursor to first position
	   c.moveToFirst();
	   //fetch all data one by one
	   do
	   {
	    //we can use c.getString(0) here
	    //or we can get data using column index
	    String id=c.getString(c.getColumnIndex("ID"));
	    String date=c.getString(c.getColumnIndex("DATE"));
	    String time =c.getString(c.getColumnIndex("TIME"));
	    String topic=c.getString(c.getColumnIndex("TOPIC"));
	    String remarks=c.getString(c.getColumnIndex("REMARKS"));
	    //display on text view
	    text5.append(id+"  "+date+"  "+time+"   "+topic+"   "+remarks+"\n");
	    //move next position until end of the data
	   }while(c.moveToNext());
	  }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	}
	
	
