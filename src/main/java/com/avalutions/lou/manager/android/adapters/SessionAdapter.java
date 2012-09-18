package com.avalutions.lou.manager.android.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.avalutions.lou.manager.R;
import com.avalutions.lou.manager.net.Session;

public class SessionAdapter extends ArrayAdapter<Session> {
    private final Activity context;
    private final Session[] sessions;

    public SessionAdapter(Activity context, Session[] sessions) {
        super(context, R.layout.world_list_item, sessions);
        this.context = context;
        this.sessions = sessions;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        Session session = sessions[position];

        View rowView = inflater.inflate(R.layout.world_list_item, null, true);

        TextView textView = (TextView) rowView.findViewById(R.id.txtWorldNumber);
        TextView txtRegion = (TextView) rowView.findViewById(R.id.txtWorldRegion);
        textView.setText(session.worldId);
        txtRegion.setText(session.region);

        return rowView;
    }
}
