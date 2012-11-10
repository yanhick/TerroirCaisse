package com.terroir.caisse;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

public class CardActivity extends Activity {
	
	public static String TAG = CardActivity.class.getSimpleName();

	private static String INSTAGRAM = "https://api.instagram.com/";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_card);
			
		try {
			Intent intent = getIntent();
			String name = intent.getStringExtra("name");
			TextView text = (TextView) findViewById(R.id.txtViewName);
			text.setText(name);
			
			String mail = intent.getStringExtra("mail");
			TextView mailText = (TextView) findViewById(R.id.txtViewMail);
			mailText.setText(mail);
			
			String category = intent.getStringExtra("category");
			TextView categoryText = (TextView) findViewById(R.id.txtViewCategory);
			categoryText.setText(category);

			String website = intent.getStringExtra("website");
			TextView websiteText = (TextView) findViewById(R.id.txtViewWebSite);
			websiteText.setText(website);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		ImageView imgView = (ImageView) findViewById(R.id.imgPhone);
		imgView.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				// listen for phone state changes to restart the app when the initiated call ends
				EndCallListener callListener = new EndCallListener();
				TelephonyManager mTM = (TelephonyManager)CardActivity.this.getSystemService(Context.TELEPHONY_SERVICE);
				mTM.listen(callListener, PhoneStateListener.LISTEN_CALL_STATE);
				String url = "tel:3334444";
			    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse(url));			
			    CardActivity.this.startActivity(intent);
			}
		});	
		
		InstagramLoader loader = (InstagramLoader) new InstagramLoader().execute(INSTAGRAM);
		
	}
	
	private void onInstagramLoaded(String result) {
		Log.i("", result);
	}
	
	private class EndCallListener extends PhoneStateListener {
	    @Override
	    public void onCallStateChanged(int state, String incomingNumber) {
	        if(TelephonyManager.CALL_STATE_RINGING == state) {
	            Log.i(TAG, "RINGING, number: " + incomingNumber);
	        }
	        if(TelephonyManager.CALL_STATE_OFFHOOK == state) {
	            //wait for phone to go offhook (probably set a boolean flag) so you know your app initiated the call.
	            Log.i(TAG, "OFFHOOK");
	        }
	        if(TelephonyManager.CALL_STATE_IDLE == state) {
	            //when this state occurs, and your flag is set, restart your app
	            Log.i(TAG, "IDLE");
	        }
	    }
	}
	
	
	private class InstagramLoader extends AsyncTask<String, Void, String> {
		
		@Override
	    protected String doInBackground(String... url) {
	          
	        // params comes from the execute() call: params[0] is the url.
	        try {
	            return downloadUrl(url[0]);
	        } catch (IOException e) {
	            return "Unable to retrieve web page. URL may be invalid.";
	        }
	    }
	    // onPostExecute displays the results of the AsyncTask.
	    @Override
	    protected void onPostExecute(String result) {
	    	onInstagramLoaded(result);
	   }
		
		// Given a URL, establishes an HttpUrlConnection and retrieves
		// the web page content as a InputStream, which it returns as
		// a string.
		private String downloadUrl(String urlAsString) throws IOException {
		    InputStream is = null;
		    // Only display the first 500 characters of the retrieved
		    // web page content.
		    int len = 500;
		        
		    try {
		    	URL url = new URL(urlAsString);
		        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		        conn.setReadTimeout(10000 /* milliseconds */);
		        conn.setConnectTimeout(15000 /* milliseconds */);
		        conn.setRequestMethod("GET");
		        conn.setDoInput(true);
		        // Starts the query
		        conn.connect();
		        int response = conn.getResponseCode();
		       
		        is = conn.getInputStream();

		        // Convert the InputStream into a string
		        String contentAsString = readIt(is, len);
		        return contentAsString;
		        
		    // Makes sure that the InputStream is closed after the app is
		    // finished using it.
		    } finally {
		        if (is != null) {
		            is.close();
		        } 
		   }
		}
		
		// Reads an InputStream and converts it to a String.
		public String readIt(InputStream stream, int len) throws IOException, UnsupportedEncodingException {
		    Reader reader = null;
		    reader = new InputStreamReader(stream, "UTF-8");        
		    char[] buffer = new char[len];
		    reader.read(buffer);
		    return new String(buffer);
		}

	}

}
