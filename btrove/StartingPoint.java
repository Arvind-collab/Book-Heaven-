package com.arzin.btrove;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StartingPoint extends Activity {
	int value;
	Button login,register;
	TextView display;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_starting_point);
		value=0;
		login = (Button) findViewById(R.id.btLogin);
		register = (Button) findViewById(R.id.btRegister);
		display = (TextView) findViewById(R.id.introLogin);
		login.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v){
				value=1;
				if(value==1){
				display.setText("Login Successful!");
				Thread timer2 = new Thread(){
					public void run(){
						try{
							sleep(800);
						}
						catch(InterruptedException e){
							e.printStackTrace();
						}
						finally{
							Intent openStartingPoint = new Intent("com.arzin.btrove.MENU");
							startActivity(openStartingPoint);
						}
					}
				};
				timer2.start();
				}
			}
		});
		register.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v){
				value=2;
				if(value==2){
					display.setText("Oops! Some error occurred.. ");
					}
			}
		});
		
	}
		
}
