package com.example.studenttimemanagement;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;


public class MainActivity extends Activity {
	ImageButton imageButton1,imageButton2,imageButton3,imageButton4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.sound);
        imageButton1 = (ImageButton) findViewById(R.id.imageButton1);
        
        imageButton1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				  Intent x = new Intent(MainActivity.this,Addtask.class);
				  mp.start();
                 startActivity(x); 
				
			}
		});
        
        
        final MediaPlayer mp2 = MediaPlayer.create(this, R.raw.sound);
        imageButton2 = (ImageButton) findViewById(R.id.imageButton5);
        imageButton2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent x = new Intent(MainActivity.this,Updatetask.class);
				  mp2.start();
               startActivity(x); 
				
			}
		});
        
        
        final MediaPlayer mp3 = MediaPlayer.create(this, R.raw.sound);
        imageButton3 = (ImageButton) findViewById(R.id.imageButton3);
        imageButton3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				Intent x = new Intent(MainActivity.this,Listtask.class);
				  mp3.start();
           startActivity(x); 
				
			}
		});
        
        
        
        final MediaPlayer mp4 = MediaPlayer.create(this, R.raw.sound);
        imageButton2 = (ImageButton) findViewById(R.id.imageButton2);
        imageButton2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				Intent x = new Intent(MainActivity.this,Help.class);
				  mp4.start();
           startActivity(x); 
				
			}
		});
 
        
        
	}
    }


