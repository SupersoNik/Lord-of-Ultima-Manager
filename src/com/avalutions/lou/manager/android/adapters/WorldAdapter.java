package com.avalutions.lou.manager.android.adapters;

import com.avalutions.lou.manager.R;
import com.avalutions.lou.manager.common.LouSession;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class WorldAdapter extends ArrayAdapter<LouSession> {
	private final Activity context;
	private final LouSession[] sessions;

	public WorldAdapter(Activity context, LouSession[] sessions) {
		super(context, R.layout.worlditem, sessions);
		this.context = context;
		this.sessions = sessions;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = context.getLayoutInflater();
		View rowView = inflater.inflate(R.layout.worlditem, null, true);
		TextView textView = (TextView) rowView.findViewById(R.id.txtWorldNumber);
        TextView txtRegion = (TextView) rowView.findViewById(R.id.txtWorldRegion);
		textView.setText(sessions[position].getWorld());
		txtRegion.setText(sessions[position].getRegion());

		return rowView;
	}
}
