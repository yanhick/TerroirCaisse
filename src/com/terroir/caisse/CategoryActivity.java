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
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView textview = new TextView(this);
        textview.setText("This is Windows mobile tab");
        setContentView(R.layout.activity_category);
        ListView list = (ListView) findViewById(R.id.lstCategories);
        DBAdapter db = new DBAdapter(this);
        db.open();        
        
        Map<String, Integer> map = new HashMap<String, Integer>(); 
        Cursor cur = db.categories();
        cur.moveToFirst();
        int index = cur.getColumnIndex("sous_type");
        while (cur.isAfterLast() == false) {
        	String category = cur.getString(index);
        	map.put(category, 0);
        	Log.i(TAG, category);
        	cur.moveToNext();
        }  
        cur.close();
        Log.i(TAG, "Number of catergories "+map.size());
        List<String> categories = new ArrayList<String>();
        List<Integer> counts = new ArrayList<Integer>();
        for(String category: map.keySet()) {
        	int count = db.count("sous_type", category);
        	map.put(category, count);        	       
        	categories.add(category);
        	counts.add(count);
        }
        CategoryAdapter adapter = new CategoryAdapter(this, categories, counts); 
        list.setAdapter(adapter);
    }
}