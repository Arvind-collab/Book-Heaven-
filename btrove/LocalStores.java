package com.arzin.btrove;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LocalStores extends Activity{

TextView tv = (TextView)findViewById(R.id.tv101);
	Fav fav = new Fav();
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.localstores);
		Button btn = (Button) findViewById(R.id.button123); 
		btn.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v){
				 try {String hh=fav.getInfo();
				 	 tv.setText(hh);
					//String[] d=fav.dd(); 
//		        		Toast.makeText(getApplicationContext(), "Working Finally!"+d,
//		        		Toast.LENGTH_LONG).show();
		            } catch (Exception e) {
		                // TODO Auto-generated catch block
		                e.printStackTrace();                    
		            }finally{
		            	
		            }   
		//Toast.makeText(getApplicationContext(), "Working Finally!"+s,
		//Toast.LENGTH_LONG).show();
			}});
	}
}
