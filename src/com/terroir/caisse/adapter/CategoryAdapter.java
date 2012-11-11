package com.terroir.caisse.adapter;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.terroir.caisse.R;
import com.terroir.caisse.data.Category;

public class CategoryAdapter extends BaseAdapter {

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
			Log.i("getView", "category: "+categories.get(position)+" - counts "+counts.get(position));
			holder.category.setText(""+categories.get(position));
			holder.count.setText(String.valueOf(counts.get(position)));		
			int drawable = Category.get(categories.get(position));			
			if(drawable != -1)
				holder.icon.setImageResource(drawable);	
			else 
				holder.icon.setImageResource(R.drawable.divers);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return convertView;	
	}

}
