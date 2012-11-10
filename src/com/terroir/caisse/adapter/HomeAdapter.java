package com.terroir.caisse.adapter;

import java.util.ArrayList;

import com.terroir.caisse.R;
import com.terroir.caisse.data.Producer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class HomeAdapter extends BaseAdapter {

	private LayoutInflater myInflater;
	private ArrayList<Producer> producers;
	
	public HomeAdapter (Context context, ArrayList<Producer> _producers) {
		this.myInflater = LayoutInflater.from(context);
		this.producers = _producers;
	}
	
	@Override
	public int getCount() {
		return this.producers.size();
	}

	@Override
	public Object getItem(int arg0) {
		return this.producers.get(arg0);
	}

	@Override
	public long getItemId(int position) {		
		return position;
	}

	public static class ViewHolder {
		TextView text01;
		TextView text02;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
	ViewHolder holder;
		
		if (convertView == null) {
			convertView = myInflater.inflate(R.layout.home_list_item, null);
			holder = new ViewHolder();
			holder.text01 = (TextView) convertView.findViewById(R.id.txtNom);
			holder.text02 = (TextView) convertView.findViewById(R.id.txtDetail);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		holder.text01.setText(producers.get(position).raison_social);
		holder.text02.setText(producers.get(position).sous_type);

		return convertView;	
	}

}
