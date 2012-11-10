package com.terroir.caisse.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.terroir.caisse.R;
import com.terroir.caisse.data.Producer;

public class HomeAdapter extends BaseAdapter {

	private LayoutInflater myInflater;
	private List<Producer> producers;
	
	public HomeAdapter (Context context, List<Producer> _producers) {
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
		TextView distance;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
	ViewHolder holder;
		
		if (convertView == null) {
			convertView = myInflater.inflate(R.layout.home_list_item, null);
			holder = new ViewHolder();
			holder.text01 = (TextView) convertView.findViewById(R.id.txtNom);
			holder.text02 = (TextView) convertView.findViewById(R.id.txtDetail);
			holder.distance = (TextView) convertView.findViewById(R.id.txtDistance);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		holder.text01.setText(producers.get(position).raison_social);
		holder.text02.setText(producers.get(position).sous_type);
		double distance = producers.get(position).distance / 10.0;
		distance = Math.round(distance) / 100.0;
		holder.distance.setText(String.valueOf(distance)+"km");

		return convertView;	
	}

}
