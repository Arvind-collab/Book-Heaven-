package com.arzin.btrove;
//import com.arzin.btrove.Menu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class grid_view_class extends BaseAdapter {
	
	private Context context; 
	private final String[] gridValues;

	//Constructor to initialize values
	public grid_view_class(Context context, String[] gridValues) {
		this.context = context;
		this.gridValues = gridValues;
	}
	
	@Override
	public int getCount() {
		
		// Number of times getView method call depends upon gridValues.length
		return gridValues.length;
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
	
	public View getView(int position, View convertView, ViewGroup parent) {

		//LayoutInflator to call external grid_item.xml file
		
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View gridView;

		if (convertView == null) {

			gridView = new View(context);

			// get layout from grid_item.xml
			gridView = inflater.inflate(R.layout.grid_item, null);
			// set value into textview
			TextView textView = (TextView) gridView
					.findViewById(R.id.grid_item_label);
			textView.setText(gridValues[position]);

			// set image based on selected text
			
			ImageView imageView = (ImageView) gridView
					.findViewById(R.id.grid_item_image);

			String mobile = gridValues[position];

			if (mobile.equals("Go Online")) {
				
				imageView.setImageResource(R.drawable.download);
				
			}  else if (mobile.equals("Search NIT")) {
				
				imageView.setImageResource(R.drawable.search_nit);
				
			} else if (mobile.equals("Local Stores")) {
				
				imageView.setImageResource(R.drawable.local_shops);
				
			}else if (mobile.equals("References")) {
				
				imageView.setImageResource(R.drawable.reference);
				
			} else if (mobile.equals("Settings")) {
				
				imageView.setImageResource(R.drawable.settings);
				
			} else if (mobile.equals("About")) {
				
				imageView.setImageResource(R.drawable.info);
				
			}  else if (mobile.equals("EXIT")) {
				
				imageView.setImageResource(R.drawable.exit_board);
				
				
			}else {
				
				imageView.setImageResource(R.drawable.ic_launcher);
			}

		} else {
			gridView = (View) convertView;
		}

		return gridView;
	}
}

