package com.avalutions.lou.manager.android;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import com.avalutions.lou.manager.R;
import com.avalutions.lou.manager.android.adapters.CityAdapter;
import com.avalutions.lou.manager.models.City;
import com.avalutions.lou.manager.models.Player;
import com.avalutions.lou.manager.net.Session;

import java.text.NumberFormat;

public class CityListing extends ListActivity {
    private ProgressDialog dialog;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.worlddetails);

        dialog = new ProgressDialog(this);
        
        if(Session.getActive().getWorld().getPlayer().getCities() == null || Session.getActive().getWorld().getPlayer().getCities().length <= 0) {
            dialog.setMessage("Loading cities...");
            dialog.show();
        }
        updateDetails();
    }
    
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        City city = (City)this.getListAdapter().getItem(position);
        Session.getActive().getWorld().setCurrentCity(city);

        Intent intent = new Intent(this, CityDetails.class);
        startActivity(intent);
        
    }
    
    private void updateDetails() {
        NumberFormat formatter = NumberFormat.getIntegerInstance();
        Player player = Session.getActive().getWorld().getPlayer();
        
        TextView tvPlayer = (TextView)this.findViewById(R.id.txtPlayerName);
        tvPlayer.setText(player.getName());
        TextView tvScore = (TextView)this.findViewById(R.id.txtPlayerScore);
        tvScore.setText(formatter.format(player.getScore()));
        CityAdapter adapter = new CityAdapter(this, player.getCities());
        setListAdapter(adapter);
        if(Session.getActive().getWorld().getAlliance() != null) {
            TextView tvAlliance = (TextView)this.findViewById(R.id.txtAlliance);
            tvAlliance.setText(Session.getActive().getWorld().getAlliance().getName());
        }
    }

    public void BucketChanged(String bucket) {
        if(bucket.equals(getString(R.string.bucket_player_details))) {
            if (this.dialog.isShowing()) {
                this.dialog.dismiss();
            }
            updateDetails();
        }
        
    }
}
