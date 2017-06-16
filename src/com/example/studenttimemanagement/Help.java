
package com.example.studenttimemanagement;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class Help extends Activity {
	
	Button btnCall, btnEmail, btnUMP;
	ImageButton imageButton5;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_help);
		
		final MediaPlayer mp = MediaPlayer.create(this, R.raw.sound);
		btnCall=(Button) findViewById(R.id.btnCall);
		btnCall.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent itik = new Intent(android.content.Intent.ACTION_CALL,Uri.parse("tel:013-33333333"));
				mp.start();
				startActivity(itik);
			}
		});
		
		btnEmail=(Button) findViewById(R.id.btnEmail);
		btnEmail.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				try{
				Intent i = new Intent(Intent.ACTION_VIEW);
				mp.start();
				i.putExtra("sms_body", "Hello sir..Can u help me ?");
				i.setType("vnd.android-dir/mms-sms");
				startActivity(i);
				}catch(ActivityNotFoundException ee){
					Toast.makeText(Help.this, "Sorry,no email found", Toast.LENGTH_LONG).show();
				}
			}
		});
		
		
		btnUMP=(Button) findViewById(R.id.btnUMP);
		btnUMP.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent eagle = new Intent(android.content.Intent.ACTION_VIEW,Uri.parse("http://www.ump.edu.my/en"));
				mp.start();
				startActivity(eagle);
			}
		});
		
		
		imageButton5=(ImageButton) findViewById(R.id.imageButton5);
		imageButton5.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent x = new Intent (Help.this,MainActivity.class);
				mp.start();
               startActivity(x); 
				
			}
		});
		
		
	}
}