package com.arzin.btrove;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class Fav extends Activity{  
//Constructor to initialize values 
	GridView gridView_1;
	Button btn_bm;
	static final String[] GRID_DATA_1 = new String[]{"Go Onlin","Search NI" ,"Local Stor" ,"Reference" ,
			"Setting" ,"Abou" , "EXI" };
		//static final String[]
	public String[] dd(){
		String[] s=getInfo().split("  ");
	 // boolean yes=true;
		//success dialogue
		 
		return s;
	}
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fav);
		btn_bm = (Button) findViewById(R.id.button_bm);
		btn_bm.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v){
				//to do
				switch(v.getId()){
				case R.id.button_bm:
	            //update details
					boolean success=true;
					try{ 
						//String title= .getText().toString();
						//String url=  .getText().toString();
						String url=GoOnline.getUrls();
						String title=GoOnline.gett();
						//String url="www.this.com";
						//String title="title";
						Favdb entry=new Favdb(Fav.this);
						entry.open(); 
						entry.createEntry(title,url);
						entry.close();
					}catch(Exception e){
						success=false;
					}finally{
						if(success){
							//success dialogue
//							Dialog d = new Dialog(null);
//							d.setTitle("Ooo..! lala...");
//							TextView tv=new TextView(null);
//							tv.setText("Success!");
//							d.setContentView(tv);
//							d.show();
							Toast.makeText(getApplicationContext(), "Link Saved!",
							Toast.LENGTH_LONG).show();
						}
					}
					break;
				}
			}
		});
		gridView_1 = (GridView) findViewById(R.id.gridViewBM);
		gridView_1.setAdapter(new grid_view_bookmarks(this, GRID_DATA_1));
		gridView_1.setOnItemClickListener(new OnItemClickListener() {
		public void onItemClick(AdapterView<?> parent, View v,
		int position, long id) {
			// to do 	

				}
			});
		}
	String getInfo(){
		Favdb info =new Favdb(this);
			info.open();
			String data= info.getData();
			info.close();
			return data;
		}
	}