package com.terroir.caisse.adapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.terroir.caisse.R;

public class CategoryAdapter extends BaseAdapter {

	public static final Map<String, Integer> CATEGORIES = new HashMap<String, Integer>(); 
	static {
		CATEGORIES.put("MIEL", R.drawable.confit); 
		CATEGORIES.put("LAITIERS", R.drawable.lait); 
		CATEGORIES.put("OLIVES", R.drawable.olive); 
		CATEGORIES.put("BOULANGERIE", R.drawable.boulang); 
		CATEGORIES.put("FRUITS", R.drawable.fruit_et_legume); 
		CATEGORIES.put("VIANDE", R.drawable.viande); 
		CATEGORIES.put("AROME", R.drawable.parfum); 
		CATEGORIES.put("GOURMANDISES", R.drawable.bonbons); 
		CATEGORIES.put("VOLAILLES", R.drawable.poussin); 
		CATEGORIES.put("PLANTES", R.drawable.vegetal); 
		CATEGORIES.put("SEL", R.drawable.sel); 
		CATEGORIES.put("SPIRITUEUX", R.drawable.bonbons); 
		CATEGORIES.put("JUS", R.drawable.jus_de_fruit);	
	}
	
	private LayoutInflater myInflater;
	private List<String> categories;
	private List<Integer> counts;
	
	public CategoryAdapter (Context context, List<String> _categories, List<Integer> _counts) {
		this.myInflater = LayoutInflater.from(context);
		this.categories = _categories;
		this.counts = _counts;
	}
	
	@Override
	public int getCount() {
		return this.categories.size();
	}

	@Override
	public Object getItem(int arg0) {
		return this.categories.get(arg0);
	}

	@Override
	public long getItemId(int position) {		
		return position;
	}

	public static class ViewHolder {
		TextView category;
		TextView count;
		ImageView icon;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
	ViewHolder holder;		
		if (convertView == null) {
			convertView = myInflater.inflate(R.layout.category_list_item, null);
			holder = new ViewHolder();
			holder.category = (TextView) convertView.findViewById(R.id.txtCategory);
			holder.count = (TextView) convertView.findViewById(R.id.txtCount);
			holder.icon = (ImageView) convertView.findViewById(R.id.imgCategory);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		try {
			holder.category.setText(""+categories.get(position));
			holder.count.setText(counts.get(position));		
			holder.icon.setImageResource(R.drawable.logo);	
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return convertView;	
	}

}
