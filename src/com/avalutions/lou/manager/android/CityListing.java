package com.avalutions.lou.manager.android;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import com.avalutions.lou.manager.R;
import com.avalutions.lou.manager.android.adapters.CityAdapter;
import com.avalutions.lou.manager.models.World;
import com.avalutions.lou.manager.net.Session;
import com.avalutions.lou.manager.net.commands.GetPlayerInfo;
import com.avalutions.lou.manager.net.commands.responses.PlayerInfoResponse;
import com.avalutions.lou.manager.net.commands.responses.poll.PlayerCity;
import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CityListing extends RoboActivity {
    private static final int POLL_SECONDS = 15;

    @InjectView(R.id.cityList) ListView cityList;
    @InjectView(R.id.txtPlayerName) TextView playerNameView;
    @InjectView(R.id.txtPlayerScore) TextView scoreView;
    @InjectView(R.id.txtAlliance) TextView allianceNameView;
    @InjectView(R.id.txtPlayerRank) TextView playerRankView;
    @InjectView(R.id.updatedTime) TextView updatedTimeView;
    @InjectView(R.id.button_mail) Button mailboxButton;
    @InjectView(R.id.button_quests) Button questButton;
    @InjectView(R.id.button_alliance) Button allianceButton;

    private final Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            new PollTask().execute();
        }
    };

    private class PollTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            Session.getActive().world.update();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            updatePoll();
            handler.sendEmptyMessageDelayed(0, POLL_SECONDS * 1000);
        }
    };

    private final AsyncTask<Void, Void, PlayerInfoResponse> playerInfoTask = new AsyncTask<Void, Void, PlayerInfoResponse>() {
        private ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(CityListing.this);
            dialog.setMessage("Loading player information");
            dialog.show();
        }

        @Override
        protected PlayerInfoResponse doInBackground(Void... voids) {
            Session.getActive().world.update();
            GetPlayerInfo getPlayerInfo = new GetPlayerInfo();
            return getPlayerInfo.run();
        }

        @Override
        protected void onPostExecute(PlayerInfoResponse playerInfoResponse) {
            updateFromInfo(playerInfoResponse);

            handler.sendEmptyMessageDelayed(0, POLL_SECONDS * 1000);

            if(dialog.isShowing()) {
                dialog.dismiss();
            }
        }
    };

    private void updateFromInfo(PlayerInfoResponse player) {
        NumberFormat formatter = NumberFormat.getIntegerInstance();

        playerNameView.setText(player.name);

        scoreView.setText(formatter.format(player.score));

        CityAdapter adapter = new CityAdapter(this, player.cities);
        cityList.setAdapter(adapter);
        cityList.setOnItemClickListener(listItemClicked);

        updatePoll();
    }

    private void updatePoll() {
        World world = Session.getActive().world;

        if(world.getAlliance() != null) {
            allianceNameView.setText(Session.getActive().world.getAlliance().name);
        }

        if(world.getPlayer() != null) {
            playerRankView.setText(String.valueOf(world.getPlayer().rank));

            scoreView.setText(String.valueOf(world.getPlayer().score));
        }

        if(world.getMailbox() != null) {
            mailboxButton.setText(String.format("Mail (%d)", world.getMailbox().u));
        }

        if(world.getQuests() != null) {
            questButton.setText(String.format("Mail (%d)", world.getQuests().v));
        }

        final SimpleDateFormat formatter = new SimpleDateFormat("HH.mm.ss a");
        updatedTimeView.setText(formatter.format(new Date()));
    }

    private View.OnClickListener allianceButtonClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivity(new Intent(CityListing.this, AllianceActivity.class));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.world_details);

        allianceButton.setOnClickListener(allianceButtonClicked);

        playerInfoTask.execute();
    }

    private AdapterView.OnItemClickListener listItemClicked = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            PlayerCity city = (PlayerCity)adapterView.getItemAtPosition(i);
            Session.getActive().world.currentCityId = city.id;

            Intent intent = new Intent(CityListing.this, CityDetails.class);
            startActivity(intent);
        }
    };
}
