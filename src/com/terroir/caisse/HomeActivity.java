package com.terroir.caisse;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.terroir.caisse.adapter.HomeAdapter;
import com.terroir.caisse.data.Producer;
import com.terroir.caisse.helper.OpenDataXmlParser;

public class HomeActivity extends Activity {
	
	String TAG = HomeActivity.class.getSimpleName();
	protected HomeAdapter adapter;
	protected ListView list;
	protected Context context;
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);    
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_home);
        context = this;
        list = (ListView) HomeActivity.this.findViewById(R.id.lstProducers);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView l, View v, int position, long id) {
				Producer producer = (Producer) adapter.getItem(position);    			   
				Intent wake = new Intent(HomeActivity.this, CardActivity.class);				
			    wake.putExtra("name", producer.raison_social);
			    startActivity(wake);			    			   
			}
		});
        /*
        ArrayList<Producer> producers = new ArrayList<Producer>();
        producers.add(new Producer ("orange","fruit rond", "merde1", "merde1"));
        producers.add(new Producer ("Lait","Il faut le mettre au frigo", "merde2", "merde2"));
        producers.add(new Producer ("Tomate","C'est rouge", "merde3", "merde3"));
        producers.add(new Producer ("La soupe","Cela fait grandir !", "merde4", "merde4"));
        producers.add(new Producer ("orange","fruit rond", "merde1", "merde1"));
        producers.add(new Producer ("Lait","Il faut le mettre au frigo", "merde2", "merde2"));
        producers.add(new Producer ("Tomate","C'est rouge", "merde3", "merde3"));
        producers.add(new Producer ("La soupe","Cela fait grandir !", "merde4", "merde4"));
        producers.add(new Producer ("orange","fruit rond", "merde1", "merde1"));
        producers.add(new Producer ("Lait","Il faut le mettre au frigo", "merde2", "merde2"));
        producers.add(new Producer ("Tomate","C'est rouge", "merde3", "merde3"));
        producers.add(new Producer ("La soupe","Cela fait grandir !", "merde4", "merde4"));
        producers.add(new Producer ("orange","fruit rond", "merde1", "merde1"));
        producers.add(new Producer ("Lait","Il faut le mettre au frigo", "merde2", "merde2"));
        producers.add(new Producer ("Tomate","C'est rouge", "merde3", "merde3"));
        producers.add(new Producer ("La soupe","Cela fait grandir !", "merde4", "merde4"));
        */        
        Button btnMap = (Button) findViewById(R.id.btnHomeMap);
        btnMap.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				Intent wake = new Intent(HomeActivity.this, HomeMapActivity.class);
				startActivity(wake);
			}
		});
        String ws_url = "http://dataprovence.cloudapp.net:8080/v1/dataprovencetourisme/ProducteursDuTerroir/";
        new ProgressTask().execute(ws_url);
    }
    
    protected void load(String url) throws IOException, XmlPullParserException {
    	OpenDataXmlParser parser = new OpenDataXmlParser();
		InputStream stream = parser.downloadUrl(url);
		Log.i(TAG, "parsing xml stream "+url);    		
		List<Producer> producers = parser.parse(stream);      
		adapter = new HomeAdapter(context, producers);		
		HomeActivity.this.runOnUiThread(new Runnable(){  		    
			@Override  
			public void run() {
				list.setAdapter(adapter);
		    }  		    
		});    
		
    }
   
    
    
    private class ProgressTask extends AsyncTask<String, Void, Boolean> {
        private ProgressDialog dialog;
    
        public ProgressTask() {           
            dialog = new ProgressDialog(HomeActivity.this);
        }

        protected void onPreExecute() {
        	this.dialog.setTitle("La Poste");
        	//this.dialog.setIcon(R.drawable.icon_mini);
        	this.dialog.setMessage("Chargement en cours...");
        	//this.dialog.setProgressStyle(R.style.CustomDialogTheme);
        	this.dialog.setCancelable(false);
        	this.dialog.show();
        }

        @Override
        protected void onPostExecute(final Boolean success) {                    
        	//setListAdapter(listAdapter);             
            if (dialog.isShowing()) {
            	dialog.dismiss();
            	dialog = null;
        }

        if (success) {
            //Toast.makeText(context, "OK", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(HomeActivity.this, "Erreur de connexion", Toast.LENGTH_LONG).show();
        }
    }

    protected Boolean doInBackground(final String... url) {
        try{        	
            load(url[0]);
            return true;
         } catch (Exception e){
            e.printStackTrace();
            return false;
         }
      }
    }
    

}