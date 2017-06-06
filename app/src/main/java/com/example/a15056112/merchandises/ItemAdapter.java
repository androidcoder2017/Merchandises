package com.example.a15056112.merchandises;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class ItemAdapter extends ArrayAdapter<Item> {
	
	Context context;
	int layoutResourceId;   
	ArrayList<Item> itemList = null;
	

	public ItemAdapter(Context context, int resource, ArrayList<Item> objects) {
		super(context, resource, objects);
		this.context = context;
		this.layoutResourceId = resource;
		this.itemList = objects;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		ItemHolder holder = null;

		if(row == null)
		{
			LayoutInflater inflater = ((Activity)context).getLayoutInflater();
			row = inflater.inflate(layoutResourceId, parent, false);

			holder = new ItemHolder();
			holder.fullName = (TextView)row.findViewById(R.id.txtItemName);
			holder.quantity = (TextView)row.findViewById(R.id.txtQuantity);
			holder.price = (TextView)row.findViewById(R.id.txtPrice);

			row.setTag(holder);
		}
		else
		{
			holder = (ItemHolder)row.getTag();
		}

		Item item = itemList.get(position);
		holder.fullName.setText(item.getItemName());
		holder.quantity.setText(item.getQuantity());
		holder.price.setText(item.getPrice());
		return row;
	}

	static class ItemHolder
	{
		TextView fullName;
		TextView quantity;
		TextView price;
	}


		
	
}
