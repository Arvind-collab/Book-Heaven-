package com.arzin.btrove;
import com.arzin.btrove.grid_view_class;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
//import android.widget.Toast;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;

public class Menu extends Activity {
	GridView gridView;
	static final String[] GRID_DATA = new String[]{"Go Online","Search NIT" ,"Local Stores" ,"References" ,
			"Settings" ,"About" , "EXIT" };
	

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu_bg);

//Get gridview object from xml file
		gridView = (GridView) findViewById(R.id.gridViewMenu);
// Set custom adapter (GridAdapter) to gridview
		gridView.setAdapter(new grid_view_class(this, GRID_DATA));
		gridView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				
//				Toast.makeText(
//						getApplicationContext(),
//						((TextView) v.findViewById(R.id.grid_item_label))
//								.getText(), Toast.LENGTH_SHORT).show();
		try{
		Class classActivitythis = Class.forName("com.arzin.btrove."+(((String) ((TextView) v.findViewById(R.id.grid_item_label)).getText()).replaceAll(" ", "")));
		Intent Activitythis = new Intent(Menu.this,classActivitythis);
		startActivity(Activitythis);
			} catch(ClassNotFoundException e){
			e.printStackTrace();
		}
	}
		});
	//	setListAdapter(new ArrayAdapter<String>(Menu.this,android.R.layout.simple_list_item_1,classes));
  }
 @Override
  public void onBackPressed() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Exit Application?");
        alertDialogBuilder
        .setMessage("Click Yes to Exit!")
              // .setIcon(R.drawable.exit)
          .setCancelable(false)
          .setPositiveButton("Yes",
       new DialogInterface.OnClickListener(){
       public void onClick(DialogInterface dialog, int id) {
        moveTaskToBack(true);
       android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
        }
      })
       .setNegativeButton("No", new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int id) {
        dialog.cancel();
            }
         });
       AlertDialog alertDialog = alertDialogBuilder.create();
       alertDialog.show();
    }
//
//	@Override
//	protected void onListItemClick(ListView l, View v, int position, long id) {
// TODO Auto-generated method stub
//	super.onListItemClick(l, v, position, id);
//		String localClass = classes [position];
//		try{
//		Class classActivity1 = Class.forName("com.arzin.btrove."+GRID_DATA);
//		Intent Activity1 = new Intent(Menu.this,classActivity1);
//		startActivity(Activity1);
//		} catch(ClassNotFoundException e){
//			e.printStackTrace();
//		}		
//	}

	

}
