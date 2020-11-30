package com.arzin.btrove;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class Favdb{
	public static final String KEY_ROWID="_id";
	public static final String KEY_TITLE="_site_title";
	public static final String KEY_URL="site_url";
	

	public static final String DATABASE_NAME="FAVDB";
	public static final String DATABASE_TABLE="FAVDB_TABLE";
	public static final int DATABASE_VERSION=1;
	
		private static FavdbHelper ourHelper;
		private static Context ourContext;
		private static  SQLiteDatabase ourDatabase;
		
	private static class FavdbHelper extends SQLiteOpenHelper{
		
		public FavdbHelper(Context context) {
			super(context,DATABASE_NAME, null, DATABASE_VERSION);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL(
			"CREATE TABLE "+ DATABASE_TABLE+ " ("+		
			//columns setting up
					KEY_ROWID+ " INTEGER PRIMARY KEY AUTOINCREMENT, "+
					KEY_TITLE+ " TEXT NOT NULL, "+
					KEY_URL+ " TEXT NOT NULL);"
			);
		}
		
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS "+DATABASE_TABLE);
			onCreate(db);
		}
	
	}

	public Favdb (Context c){
		ourContext=c;
	}
	public void close() {
		// TODO Auto-generated method stub

		ourHelper.close();
	}

	public long createEntry(Object title, Object url) {
		// TODO Auto-generated method stub
		ContentValues cv=new ContentValues(); 
		cv.put(KEY_TITLE, (String) title);
		cv.put(KEY_URL, (String) url);
		return ourDatabase.insert(DATABASE_TABLE, null,cv);
	}

	public Favdb open() throws SQLException{
		// TODO Auto-generated method stub

		ourHelper= new FavdbHelper(ourContext);
		ourDatabase= ourHelper.getWritableDatabase();
		return this;
	}
//	public String getData(){
//		String[] columns = new String[] {KEY_ROWID,KEY_TITLE,KEY_URL};
//		Cursor c = ourDatabase.query(DATABASE_TABLE,columns,null,null,null,null,null);
//		String result="";
//		int iRow=c.getColumnIndex(KEY_ROWID);
//		int iTITLE=c.getColumnIndex(KEY_TITLE);
//		int iURL=c.getColumnIndex(KEY_URL);
//
//		for(c.moveToFirst(); !c.isAfterLast();c.moveToNext()){
//		result = result + c.getString(iRow)+" "+c.getString(iTITLE)+" "+c.getString(iURL)+"\n";
//		}
//		return result;
//		}
//
//		public String gettitle(long l){
//			//TODO Auto-generated method stub
//			String[] columns = new String[]{ KEY_ROWID,KEY_TITLE,KEY_URL};
//			Cursor c =ourDatabase.query(DATABASE_TABLE,columns,KEY_ROWID+"="+l,null,null,null,null);
//			if(c!=null){
//			c.moveToFirst();
//			String name = c.getString(1);
//			return name;
//			}
//			
//			return null;
//		}
//
//		public String geturl(long l){
//			//TODO Auto-generated method stub
//			String[] columns = new String[]{ KEY_ROWID,KEY_TITLE,KEY_URL};
//			Cursor c =ourDatabase.query(DATABASE_TABLE,columns,KEY_ROWID+"="+l,null,null,null,null);
//			if(c!=null){
//			c.moveToFirst();
//			String name = c.getString(2);
//			return name;
//			}
//			
//		return null;
//		}

	public String getData(){
		String[] columns = new String[] {KEY_TITLE};
		Cursor c = ourDatabase.query(DATABASE_TABLE,columns,null,null,null,null,null);
		String result="";
		//int iRow=c.getColumnIndex(KEY_ROWID);
		int iTITLE=c.getColumnIndex(KEY_TITLE);
		//int iURL=c.getColumnIndex(KEY_URL);
		for(c.moveToFirst(); !c.isAfterLast();c.moveToNext()){
		result =result+c.getString(iTITLE)+" ";	
		}
		return result;
		}
}  
