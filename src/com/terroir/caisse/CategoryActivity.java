package com.terroir.caisse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.terroir.caisse.adapter.CategoryAdapter;
import com.terroir.caisse.data.DBAdapter;

public class CategoryActivity extends Activity {
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
        List<String> categories = new ArrayList<String>();
        List<Integer> counts = new ArrayList<Integer>();
        for(String category: HomeActivity.categories.keySet()) {
        	int count = HomeActivity.categories.get(category).size();        	        	      
        	categories.add(category);
        	counts.add(count);        	    
        }
        for(int i=0; i<categories.size(); i++) {
            Log.i(TAG, "Categories "+categories.get(i)+" = "+counts.get(i));	
            
        }
        CategoryAdapter adapter = new CategoryAdapter(this, categories, counts); 
        list.setAdapter(adapter);
        
    }
}