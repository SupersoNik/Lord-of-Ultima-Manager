package com.avalutions.lou.manager.android;

import java.text.NumberFormat;

import com.avalutions.lou.manager.R;
import com.avalutions.lou.manager.android.adapters.CityAdapter;
import com.avalutions.lou.manager.common.City;
import com.avalutions.lou.manager.common.LouSession;
import com.avalutions.lou.manager.common.Player;
import com.avalutions.lou.manager.net.IPollHandler;
import com.avalutions.lou.manager.net.SessionManager;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class CityListing extends ListActivity implements IPollHandler {
    private SessionManager world;
    private LouSession session;
    private final ProgressDialog dialog = new ProgressDialog(CityListing.this);
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.worlddetails);
        
        Intent intent = this.getIntent();
        this.session = (LouSession)intent.getSerializableExtra("session");
        this.world = SessionManager.getInstance(session);

        world.setPollHandler(this);
        world.setContext(this);
        
        if(world.getPlayer().getCities() == null || world.getPlayer().getCities().size() <= 0) {
            dialog.setMessage("Loading cities...");
            dialog.show();
        }
        updateDetails();
    }
    
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        City city = (City)this.getListAdapter().getItem(position);

        this.world.changeCity(city);

        Intent intent = new Intent(getBaseContext(), CityDetails.class);
        intent.putExtra("session", this.session);
        startActivity(intent);
        
    }
    
    private void updateDetails() {
        NumberFormat formatter = NumberFormat.getIntegerInstance();
        Player player = world.getPlayer();
        
        TextView tvPlayer = (TextView)this.findViewById(R.id.txtPlayerName);
        tvPlayer.setText(player.getName());
        TextView tvScore = (TextView)this.findViewById(R.id.txtPlayerScore);
        tvScore.setText(formatter.format(player.getScore()));
        CityAdapter adapter = new CityAdapter(this, player.getCities().toArray(new City[player.getCities().size()]));
        setListAdapter(adapter);
        if(player.getAlliance() != null) {
            TextView tvAlliance = (TextView)this.findViewById(R.id.txtAlliance);
            tvAlliance.setText(player.getAlliance().getName());
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
