package com.arzin.btrove;
//import com.arzin.btrove.Menu;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class grid_view_bookmarks extends BaseAdapter{
	
	private Context context_1; 
	private final String[] gridValues_1;

	//Constructor to initialize values
	public grid_view_bookmarks(Context context_1, String[] gridValues_1) {
		this.context_1 = context_1;
		this.gridValues_1 = gridValues_1;
	}
	public void onClick(View v,int position) {
		// TODO Auto-generated method stub

		
	}
	@Override
	public int getCount() {
		
		// Number of times getView method call depends upon gridValues.length
		return gridValues_1.length;
	}

	@Override
	public Object getItem(int position) {
		
		return null;
	}

	@Override
	public long getItemId(int position) {
		
		return 0;
	}
	
	
    // Number of times getView method call depends upon gridValues.length
	
	@SuppressLint("InflateParams")
	public View getView(int position, View convertView, ViewGroup parent) {

		//LayoutInflator to call external grid_item.xml file
		
		LayoutInflater inflater_1 = (LayoutInflater) context_1
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View gridView_1;

		if (convertView == null) {

			gridView_1 = new View(context_1);

			// get layout from grid_item.xml
			gridView_1 = inflater_1.inflate(R.layout.grid_item_bookmarks, null);

			// set value into textview
			
			TextView textView_1 = (TextView) gridView_1
					.findViewById(R.id.grid_item_label_bm);
			textView_1.setText(gridValues_1[position]);

			// set image based on selected text
			
			ImageView imageView_1 = (ImageView) gridView_1
					.findViewById(R.id.grid_item_image_bm);
//			String mobile_1 = gridValues_1[position];
//
//			if (mobile_1.equals("Go Onlin")) {
//				
//				imageView_1.setImageResource(R.drawable.bookmark_);
//				
////			}  else if (mobile_1.equals("Search NI")) {
////				
//
//				imageView_1.setImageResource(R.drawable.bookmark_);
////			} else if (mobile_1.equals("Local Stor")) {
////				
//
//				imageView_1.setImageResource(R.drawable.bookmark_);
////			}else if (mobile_1.equals("Reference")) {
////				
//
//				imageView_1.setImageResource(R.drawable.bookmark_);
//			} else if (mobile_1.equals("Setting")) {
//				
//
//				imageView_1.setImageResource(R.drawable.bookmark_);
//			} else if (mobile_1.equals("Abou")) {
//				
//
//				imageView_1.setImageResource(R.drawable.bookmark_);
//			}  else if (mobile_1.equals("EXI")) {
//				
//
//				imageView_1.setImageResource(R.drawable.bookmark_);
//				
//			}else {
//				
//
				imageView_1.setImageResource(R.drawable.bookmark_);
//				}
//
		} else {
			gridView_1 = (View) convertView;
		}

		return gridView_1;
	}


}

