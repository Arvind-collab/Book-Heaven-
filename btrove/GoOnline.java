package com.arzin.btrove;

import java.io.File;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.DownloadManager;
import android.app.DownloadManager.Request;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.Browser;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

public class GoOnline extends Activity implements OnClickListener{
	WebView goOnline;
	static EditText url;
	public static String thisURL;
	ProgressBar progressBar;
	 private FrameLayout customViewContainer;
	    private WebChromeClient.CustomViewCallback customViewCallback;
	    private View mCustomView;
	    private myWebChromeClient mWebChromeClient;
	    public static String title;
	private static final String TAG= "Online";
	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	@SuppressLint({ "NewApi", "SetJavaScriptEnabled", "CutPasteId" })
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Log.i(TAG,"onCreate");
		setContentView(R.layout.go_online);
		  final SharedPreferences getPrefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		  boolean getbool_js = getPrefs.getBoolean("checkbox_js",true);
		  boolean getbool_data = getPrefs.getBoolean("checkbox_data",true);
		  boolean getbool_cache = getPrefs.getBoolean("checkbox_cache",true);
		  boolean getbool_dt = getPrefs.getBoolean("checkbox_dt",false);
		  boolean getbool_zoom = getPrefs.getBoolean("checkbox_zoom",true);
		  String get_hp = getPrefs.getString("editbox_hp" , "http://www.google.co.in");
		  
		customViewContainer = (FrameLayout) findViewById(R.id.customViewContainer);
        
		goOnline = (WebView) findViewById(R.id.webView1);
		if(getbool_js==true){
		goOnline.getSettings().setJavaScriptEnabled(true);
		}else{

			goOnline.getSettings().setJavaScriptEnabled(false);
		}
		if(getbool_dt==true){
		goOnline.getSettings().setLoadWithOverviewMode(true);
		goOnline.getSettings().setUseWideViewPort(true);
		}else
		{
			goOnline.getSettings().setLoadWithOverviewMode(false);
			goOnline.getSettings().setUseWideViewPort(false);
		}
		if(getbool_cache==true){
        goOnline.getSettings().setAppCacheEnabled(true);
		}else
		{
	        goOnline.getSettings().setAppCacheEnabled(false);	
		}
		if(getbool_zoom==true){
		goOnline.getSettings().setBuiltInZoomControls(true);
		goOnline.getSettings().setSupportZoom(true);
		}else{

			goOnline.getSettings().setBuiltInZoomControls(false);
			goOnline.getSettings().setSupportZoom(false);
		}
		if(getbool_data==true){
		goOnline.getSettings().setSavePassword(true);
		goOnline.getSettings().setSaveFormData(true);
		}else{

			goOnline.getSettings().setSavePassword(false);
			goOnline.getSettings().setSaveFormData(false);
		}
		goOnline.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
		goOnline.setWebViewClient(new ViewClient()); 

        mWebChromeClient = new myWebChromeClient();
        goOnline.setWebChromeClient(mWebChromeClient);
        
		  final File destinationDir = new File (Environment.getExternalStorageDirectory(), getPackageName());
		     if (!destinationDir.exists()) {
		         destinationDir.mkdir(); // Don't forget to make the directory if it's not there
		     }
		goOnline.setDownloadListener(new DownloadListener() {
	        public void onDownloadStart(String url, String userAgent,
	                String contentDisposition, String mimetype,
	                long contentLength) {
	        			Request request = new Request(
	                    Uri.parse(url));
	                    Uri source = Uri.parse(url);
	                    request.allowScanningByMediaScanner();
	                    request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
	           //         request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "download"); 
	                    // Use the same file name for the destination
	                    File destinationFile = new File (destinationDir, source.getLastPathSegment());
	                    request.setDestinationUri(Uri.fromFile(destinationFile));
	                    DownloadManager dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
	                    dm.enqueue(request);  
	                    Toast.makeText(getApplicationContext(), "Download Started", //To notify the Client that the file is being downloaded
	                    Toast.LENGTH_LONG).show();
	        		}
				});
		try{
			if(get_hp=="http://www.google.co.in"){
		goOnline.loadUrl("http://www.google.co.in");
			}else{
				goOnline.loadUrl(String.valueOf(get_hp));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	progressBar = (ProgressBar) findViewById(R.id.progressBar1);
	Button go =(Button) findViewById(R.id.bGo);
	Button prev =(Button) findViewById(R.id.bBack);
	Button next =(Button) findViewById(R.id.bNext);
	Button refresh =(Button) findViewById(R.id.bRefresh);
	Button prefs =(Button) findViewById(R.id.bPrefs);
	Button exit =(Button) findViewById(R.id.bext);
	Button his =(Button) findViewById(R.id.bHistory);
	Button fav =(Button) findViewById(R.id.bBookmarks);
	url=(EditText)findViewById(R.id.autoCompleteTextView1);
	go.setOnClickListener(this);
	prev.setOnClickListener(this);
	next.setOnClickListener(this);
	his.setOnClickListener(this);
	refresh.setOnClickListener(this);
	fav.setOnClickListener(this);
	prefs.setOnClickListener(this);
	exit.setOnClickListener(this);
	}
    public boolean inCustomView() {
        return (mCustomView != null);
    }

    public void hideCustomView() {
        mWebChromeClient.onHideCustomView();
    }
    class myWebChromeClient extends WebChromeClient {
        @SuppressWarnings("unused")
		private Bitmap mDefaultVideoPoster;
        private View mVideoProgressView;
        @Override
        public void onShowCustomView(View view, int requestedOrientation, CustomViewCallback callback) {
           onShowCustomView(view, callback);    //To change body of overridden methods use File | Settings | File Templates.
        }
        @Override
        public void onShowCustomView(View view,CustomViewCallback callback) {

            // if a view already exists then immediately terminate the new one
            if (mCustomView != null) {
                callback.onCustomViewHidden();
                return;
            }
            mCustomView = view;
            goOnline.setVisibility(View.GONE);
            customViewContainer.setVisibility(View.VISIBLE);
            customViewContainer.addView(view);
            customViewCallback = callback;
        }
        @SuppressLint("InflateParams")
		@Override
        public View getVideoLoadingProgressView() {

            if (mVideoProgressView == null) {
                LayoutInflater inflater = LayoutInflater.from(GoOnline.this);
                mVideoProgressView = inflater.inflate(R.layout.video_progress, null);
            }
            return mVideoProgressView;
        }
        @Override
        public void onHideCustomView() {
            super.onHideCustomView();    //To change body of overridden methods use File | Settings | File Templates.
            if (mCustomView == null)
                return;

            goOnline.setVisibility(View.VISIBLE);
            customViewContainer.setVisibility(View.GONE);

            // Hide the custom view.
            mCustomView.setVisibility(View.GONE);

            // Remove the custom view from its container.
            customViewContainer.removeView(mCustomView);
            customViewCallback.onCustomViewHidden();

            mCustomView = null;
        }
    }
	private class ViewClient extends WebViewClient {
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			// TODO Auto-generated method stub
			view.loadUrl(url);
			thisURL=url;
			progressBar.setVisibility(View.VISIBLE);
			return true;
		}
		@Override
		public void onPageFinished(WebView view , String url){
			super.onPageFinished(view,url);
			final EditText editText = (EditText) findViewById(R.id.autoCompleteTextView1);           
		    editText.setText(url);			
			progressBar.setVisibility(View.GONE);
			title=(String)GoOnline.this.getTitle();
			GoOnline.this.setTitle(title); 
			//GoOnline.this.setTitle(view.getTitle());
					}
		@Override
		public void onPageStarted(WebView view, String url, Bitmap favicon) {
			// TODO Auto-generated method stub
			super.onPageStarted(view, url, favicon);
			progressBar.setVisibility(View.VISIBLE);
		}
	}
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Log.i(TAG,"onStart");
	}
	
	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		Log.i(TAG,"onRestart");
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.i(TAG,"onResume");
		   //To change body of overridden methods use File | Settings | File Templates.
        goOnline.onResume();
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Log.i(TAG,"onPause");
		goOnline.onPause();
	}
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Log.i(TAG,"onStop");
		super.onStop();    //To change body of overridden methods use File | Settings | File Templates.
      
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.i(TAG,"onDestroy");
	}
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		goOnline.saveState(outState);
		Log.i(TAG,"onSaveInstanceState");
	}
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onRestoreInstanceState(savedInstanceState);
		goOnline.restoreState(savedInstanceState);
		Log.i(TAG,"onRestoreInstanceState");
	}
	@Override
	public boolean onKeyDown(int KeyCode, KeyEvent event){
		if(KeyCode==KeyEvent.KEYCODE_BACK){
			  if (inCustomView()) {
	                hideCustomView();
	                return true;
	            }
			goOnline.stopLoading();
		}
		return super.onKeyDown(KeyCode, event);
	}
	public void onClick(View v){

		switch(v.getId()){
		case R.id.bGo:
			String theWebsite = "https://"+(url.getText().toString().replaceAll(" ", "").replaceAll("http://", "").replaceAll("https://", ""));
			goOnline.loadUrl(theWebsite);
			InputMethodManager imm =(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.hideSoftInputFromWindow(url.getWindowToken(), 0);
			break;
		case R.id.bBack:
			if(goOnline.canGoBack())
				goOnline.goBack();
			break;
		case R.id.bNext:
			if(goOnline.canGoForward())
				goOnline.goForward();
			break;
		case R.id.bRefresh:
			goOnline.reload();
			break;
		case R.id.bPrefs:
			Intent openPrefs = new Intent("com.arzin.btrove.PREFS");
			startActivity(openPrefs);
			break;
		case R.id.bext:
			finish();
			break;
		case R.id.bBookmarks:
			Intent ActivityFav = new Intent("com.arzin.btrove.FAV");
			startActivity(ActivityFav);
			break;
		case R.id.bHistory:
			Intent ActivityHis = new Intent("com.arzin.btrove.HIS");
			startActivity(ActivityHis);
			break;
		}
	}
	public static void loadUrl(String theUrl) {
		// TODO Auto-generated method stub
		loadUrl(theUrl);
	}
	public static String getUrls() {
		// TODO Auto-generated method stub
		return thisURL;
	}
	public static String gett() {
		// TODO Auto-generated method stub
		return title;
	}
}
