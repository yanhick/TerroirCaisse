package com.terroir.caisse;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
}
