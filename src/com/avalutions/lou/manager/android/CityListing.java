package com.avalutions.lou.manager.android;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import com.avalutions.lou.manager.R;
import com.avalutions.lou.manager.android.adapters.CityAdapter;
import com.avalutions.lou.manager.net.Session;
import com.avalutions.lou.manager.net.commands.GetPlayerInfo;
import com.avalutions.lou.manager.net.commands.responses.PlayerInfoResponse;
import com.avalutions.lou.manager.net.commands.responses.poll.City;

import java.text.NumberFormat;

public class CityListing extends ListActivity {

    private AsyncTask<Void, Void, PlayerInfoResponse> playerInfoTask = new AsyncTask<Void, Void, PlayerInfoResponse>() {
        private ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(CityListing.this);
            dialog.setMessage("Loading player information");
            dialog.show();
        }

        @Override
        protected PlayerInfoResponse doInBackground(Void... voids) {
            GetPlayerInfo getPlayerInfo = new GetPlayerInfo();
            return getPlayerInfo.run();
        }

        @Override
        protected void onPostExecute(PlayerInfoResponse playerInfoResponse) {
            updateFromInfo(playerInfoResponse);

            if(dialog.isShowing()) {
                dialog.dismiss();
            }
        }
    };

    private void updateFromInfo(PlayerInfoResponse player) {
        NumberFormat formatter = NumberFormat.getIntegerInstance();

        TextView tvPlayer = (TextView)this.findViewById(R.id.txtPlayerName);
        tvPlayer.setText(player.name);
        TextView tvScore = (TextView)this.findViewById(R.id.txtPlayerScore);
        tvScore.setText(formatter.format(player.score));
        CityAdapter adapter = new CityAdapter(this, player.cities);
        setListAdapter(adapter);
        if(Session.getActive().world.getAlliance() != null) {
            TextView tvAlliance = (TextView)this.findViewById(R.id.txtAlliance);
            tvAlliance.setText(Session.getActive().world.getAlliance().name);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.worlddetails);

        playerInfoTask.execute();
    }
    
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        City city = (City)this.getListAdapter().getItem(position);
        Session.getActive().world.setCurrentCity(city);

        Intent intent = new Intent(this, CityDetails.class);
        startActivity(intent);
        
    }
}
