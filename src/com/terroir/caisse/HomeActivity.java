package com.terroir.caisse;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.terroir.caisse.adapter.HomeAdapter;
import com.terroir.caisse.data.Producer;
import com.terroir.caisse.helper.OpenDataXmlParser;

public class HomeActivity extends Activity {
	
	String TAG = HomeActivity.class.getSimpleName();
	HomeAdapter adapter;
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);    
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_home);
        
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
        
        ListView list = (ListView) this.findViewById(R.id.lstProducers);
        adapter = new HomeAdapter(this, producers);
        
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView l, View v, int position, long id) {
				Producer producer = (Producer) adapter.getItem(position);
			    			    
				Intent wake = new Intent(HomeActivity.this, CardActivity.class);				
			    wake.putExtra("name", producer.raison_social);

			    startActivity(wake);			    			   
			}
		});
        Button btnMap = (Button) findViewById(R.id.btnHomeMap);
        btnMap.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				Intent wake = new Intent(HomeActivity.this, HomeMapActivity.class);
				startActivity(wake);
			}
		});
        
        
        try {
        	String ws_url = "http://dataprovence.cloudapp.net:8080/v1/dataprovencetourisme/ProducteursDuTerroir/";
            OpenDataXmlParser parser = new OpenDataXmlParser();
			InputStream stream = parser.downloadUrl(ws_url);
			//List<Entry> entries = parser.parse(stream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
/*    
    private class ProgressTask extends AsyncTask<String, Void, Boolean> {
        private ProgressDialog dialog;
    
        public ProgressTask(ListActivity activity) {
            
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
         
            
         setListAdapter(listAdapter);
             
            if (dialog.isShowing()) {
            dialog.dismiss();
        }

        if (success) {
            //Toast.makeText(context, "OK", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(HomeActivity.this, "Erreur de connexion", Toast.LENGTH_LONG).show();
        }
    }

    protected Boolean doInBackground(final String... args) {
        try{
           
         loadListContent();

            return true;
         } catch (Exception e){
            Log.e("tag", "error", e);
            return false;
         }
      }
    }
    
    protected void loadListContent() {
        List<Map<String, String>> data = new ArrayList<Map<String,String>>();
        try {
        String sender = URLEncoder.encode(Constants.userName, "UTF-8");
        JSONArray json = new JSONArray(HttpHelper.doGet("letter?sender='" + sender + "'"));


   for(int i=0; i < json.length(); i++) {
   JSONObject obj = (JSONObject) json.get(i);
   Log.i(TAG, obj.toString());
   Map<String, String> map = new HashMap<String, String>();

   map.put("identifier", getString(R.string.num)+obj.getString("identifier"));
   map.put("nameReceiver", "A: " + obj.getString("nameReceiver"));
   map.put("addrReceiver", obj.getString("addrReceiver"));
   map.put("type", obj.getString("type"));


   //map.put("state", "Statut: " + obj.getString("state"));

   String identifier = URLEncoder.encode((String)obj.getString("identifier"), "UTF-8");
   JSONArray jsonStateHistory = new JSONArray(HttpHelper.doGet("state_history?id='"+identifier+"'"));

   String stateHistory="";

   for(int j=jsonStateHistory.length()-1; j >=0 ; j--) {

   JSONObject jsonObjStateHistoryElement = (JSONObject) jsonStateHistory.get(j);

   JSONObject historyElementObj = (JSONObject) jsonObjStateHistoryElement;

   stateHistory=stateHistory+"\n"+" Le "+historyElementObj.getString("date")+" "+historyElementObj.getString("state")+"\n";

   if(j==jsonStateHistory.length()-1){
   map.put("state", " Statut: " + "\n Le "+historyElementObj.getString("date")+" "+historyElementObj.getString("state"));
   }


   Log.i(TAG+"stateHistory=", stateHistory);

   }


   map.put("stateHistory", "Historiques:\n" + stateHistory);	
   data.add(map);

   }	
   } catch (Exception e) {
   e.printStackTrace();
   }
     
           listAdapter = new SimpleAdapter(this, data, R.layout.list_item_layout, new String[] {"identifier", "nameReceiver", "state", "type"}, new int[] {R.id.identifier, R.id.name, R.id.state, R.id.type});
                 
       }
*/

}