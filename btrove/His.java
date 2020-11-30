package com.arzin.btrove;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.Browser;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class His extends Activity {
	ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.his);
		// Get ListView object from xml
		listView = (ListView) findViewById(R.id.his_list);
		List<String> myList = new ArrayList<String>();

		List<String> myList2 = new ArrayList<String>();

		// TextView view = (TextView) findViewById(R.id.his_tv);
		String[] projection = new String[] { Browser.BookmarkColumns.TITLE, Browser.BookmarkColumns.URL };
		String sel = Browser.BookmarkColumns.BOOKMARK + " = 0"; // 0 = history,
																// 1 = bookmark
		//@SuppressWarnings("deprecation")
		Cursor mCur = managedQuery(android.provider.Browser.BOOKMARKS_URI, projection, sel, null, null);
		mCur.moveToFirst();
		int titleIdx = mCur.getColumnIndex(Browser.BookmarkColumns.TITLE);
		final int urlIdx = mCur.getColumnIndex(Browser.BookmarkColumns.URL);
		int count = 0;

		if (mCur.moveToFirst() && mCur.getCount() > 0) {
			boolean cont = true;
			while (mCur.isAfterLast() == false && cont) {

				// view.append(mCur.getString(titleIdx)+"\n-----------------------------------\n\n");
				// view.append(mCur.getString(urlIdx)+"\n-----------------------------------\n\n");
				count++;
				myList.add(count + ". " + (mCur.getString(titleIdx)));
				// url_list2
				myList2.add(mCur.getString(urlIdx));

				mCur.moveToNext();
			}
		}
		String[] arr = myList.toArray(new String[myList.size()]);
		final String[] arr2 = myList2.toArray(new String[myList2.size()]);
		ArrayAdapter<String> ArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arr);
		listView.setAdapter(ArrayAdapter);
		// ListView Item Click Listener
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// ListView Clicked item index
				@SuppressWarnings("unused")
				int itemPosition = position;
				// ListView Clicked item value
				@SuppressWarnings("unused")
				String itemValue = (String) listView.getItemAtPosition(position);
				String url = (arr2[position]);
				String theUrl = "https://"
						+ (url.replaceAll(" ", "").replaceAll("http://", "").replaceAll("https://", ""));
				GoOnline.loadUrl(theUrl);
				// Intent browserIntent = new Intent(Intent.,
				// Uri.parse(theUrl));
				// startActivity(browserIntent);
				// GoOnline.loadUrl(theUrl);
				// Show Alert
				// Toast.makeText(getApplicationContext(),
				// "Position :"+itemPosition+" ListItem : " +itemValue ,
				// Toast.LENGTH_LONG)
				// .show();
				//
				Toast.makeText(getApplicationContext(), "Loading Page Now", Toast.LENGTH_LONG).show();
			}
		});
		// setListAdapter(new
		// ArrayAdapter<String>(His.this,android.R.layout.simple_list_item_1,arr));
	}
	//
	// public void getBrowserHist() {
	// @SuppressWarnings("deprecation")
	// Cursor mCur = managedQuery(Browser.BOOKMARKS_URI,
	// Browser.HISTORY_PROJECTION, null, null, null);
	// mCur.moveToFirst();
	// if (mCur.moveToFirst() && mCur.getCount() > 0) {
	// while (mCur.isAfterLast() == false) {
	// Log.v("titleIdx", mCur
	// .getString(Browser.HISTORY_PROJECTION_TITLE_INDEX));
	// Log.v("urlIdx", mCur
	// .getString(Browser.HISTORY_PROJECTION_URL_INDEX));
	// mCur.moveToNext();
	// }
	// }
	// }
}
