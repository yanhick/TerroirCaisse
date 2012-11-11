package com.terroir.caisse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.terroir.caisse.adapter.CategoryAdapter;

public class CategoryActivity extends Activity {
	
	public CategoryAdapter adapter;
	public String TAG = CategoryActivity.class.getSimpleName();
	
	public static final String[] __CATEGORIES__ = new String[] {
		"MIEL ET CONFITURE", "PRODUITS LAITIERS – FROMAGE", "OLIVES – HUILES D'OLIVES", "BOULANGERIE – PATISSERIE", "FRUITS ET LEGUMES", 
		"VIANDE", "AROME ET PARFUMS", "GOURMANDISES", "VOLAILLES", "PLANTES ET EPICES", "SEL", "SPIRITUEUX", "JUS DE FRUIT ET AUTRES BOISSONS"};		
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "CategoryActivity lauched!");
/*
        DBAdapter db = new DBAdapter(this);
        db.open();
        try {
        	TextView textview = new TextView(this);
            textview.setText("This is Windows mobile tab");
            setContentView(R.layout.activity_category);
            ListView list = (ListView) findViewById(R.id.lstCategories);            
                                          
            //Map<String, Integer> map = db.categories();                                   
            List<String> categories = new ArrayList<String>();
            List<Integer> counts = new ArrayList<Integer>();
            for(String category: CATEGORIES) {
            	int count = db.count("sous_type", category);
            	//map.put(category, count);        	       
            	categories.add(category);
            	counts.add(count);
            	Log.i(TAG, "Number of producers for category "+category+" is "+count);     
            }            
            CategoryAdapter adapter = new CategoryAdapter(this, categories, counts); 
            list.setAdapter(adapter);
        }catch(Exception e) {
        	e.printStackTrace();
        } finally {
        	db.close();
        }
*/
        setContentView(R.layout.activity_category);
        ListView list = (ListView) findViewById(R.id.lstCategories); 
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("MIEL",0); 
        map.put("LAITIER", 0); 
        map.put("OLIVE", 0); 
        map.put("BOULANGERIE", 0); 
        map.put("FRUIT",0); 
        map.put("VIANDE",0); 
        map.put("GOURMANDISE",0); 
        map.put("VOLAILLE",0); 
        map.put("PLANTE",0); 
        map.put("SEL",0); 
        map.put("SPIRITUEUX",0); 
        map.put("JUS",0);
        for(String category: HomeActivity.categories.keySet()) {
        	for(String root: map.keySet()) {
        		if(category!=null && category.toUpperCase().contains(root)) {
        			int count = HomeActivity.categories.get(category).size();  
        			map.put(root, map.get(root) + count);        			
        		}
        	}
        }
        List<String> categories = new ArrayList<String>();
        List<Integer> counts = new ArrayList<Integer>();
        for(String root: map.keySet()) {
        	int count = map.get(root);        	        	      
        	categories.add(root);
        	counts.add(count);        	    
        }
        
        adapter = new CategoryAdapter(this, categories, counts); 
        list.setAdapter(adapter);
        list.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView l, View v, int position, long id) {				
				Intent wake = new Intent(CategoryActivity.this, FilteredProducersActivity.class);
				wake.putExtra("sous_type", (String) adapter.getItem(position));
				startActivity(wake);
			}
		});
    }
}