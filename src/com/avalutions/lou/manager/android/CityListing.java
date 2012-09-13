package com.avalutions.lou.manager.android;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import com.avalutions.lou.manager.R;
import com.avalutions.lou.manager.android.adapters.CityAdapter;
import com.avalutions.lou.manager.net.Session;
import com.avalutions.lou.manager.net.commands.GetPlayerInfo;
import com.avalutions.lou.manager.net.commands.Poll;
import com.avalutions.lou.manager.net.commands.responses.PlayerInfoResponse;
import com.avalutions.lou.manager.net.commands.responses.PollResponse;
import com.avalutions.lou.manager.net.commands.responses.poll.City;

import java.text.NumberFormat;

public class CityListing extends Activity {
    private ListView cityList;

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

            Poll poll = new Poll(0);
            PollResponse response = poll.run();
        }
    };

    private void updateFromInfo(PlayerInfoResponse player) {
        NumberFormat formatter = NumberFormat.getIntegerInstance();

        TextView tvPlayer = (TextView)this.findViewById(R.id.txtPlayerName);
        tvPlayer.setText(player.name);
        TextView tvScore = (TextView)this.findViewById(R.id.txtPlayerScore);
        tvScore.setText(formatter.format(player.score));
        CityAdapter adapter = new CityAdapter(this, player.cities);
        cityList = (ListView)findViewById(R.id.cityList);
        cityList.setAdapter(adapter);
        cityList.setOnItemClickListener(listItemClicked);
        if(Session.getActive().world.getAlliance() != null) {
            TextView tvAlliance = (TextView)this.findViewById(R.id.txtAlliance);
            tvAlliance.setText(Session.getActive().world.getAlliance().name);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.world_details);

        playerInfoTask.execute();
    }

    private AdapterView.OnItemClickListener listItemClicked = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            City city = (City)adapterView.getItemAtPosition(i);
            Session.getActive().world.setCurrentCity(city);

            Intent intent = new Intent(CityListing.this, CityDetails.class);
            startActivity(intent);
        }
    };
}
