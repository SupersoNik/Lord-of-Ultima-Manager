package com.avalutions.lou.manager.android.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.avalutions.lou.manager.R;
import com.avalutions.lou.manager.android.adapters.CityAdapter;
import com.avalutions.lou.manager.net.Session;
import com.avalutions.lou.manager.net.commands.GetPlayerInfo;
import com.avalutions.lou.manager.net.commands.responses.PlayerInfoResponse;
import com.avalutions.lou.manager.net.commands.responses.poll.PlayerCity;
import com.avalutions.lou.manager.net.data.World;
import com.github.rtyley.android.sherlock.roboguice.activity.RoboSherlockActivity;
import roboguice.inject.InjectView;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CityListing extends RoboSherlockActivity {
    private static final int POLL_SECONDS = 15;

    @InjectView(R.id.cityList)
    ListView cityList;
    @InjectView(R.id.txtPlayerScore)
    TextView scoreView;
    @InjectView(R.id.txtAlliance)
    TextView allianceNameView;
    @InjectView(R.id.txtPlayerRank)
    TextView playerRankView;
    @InjectView(R.id.updatedTime)
    TextView updatedTimeView;

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
    }

    ;

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
            PlayerInfoResponse result = getPlayerInfo.run();

            Session.getActive().world.changeCity(result.cities[0].id);
            return result;
        }

        @Override
        protected void onPostExecute(PlayerInfoResponse playerInfoResponse) {

            updateFromInfo(playerInfoResponse);

            handler.sendEmptyMessageDelayed(0, POLL_SECONDS * 1000);

            if (dialog.isShowing()) {
                dialog.dismiss();
            }
        }
    };

    private void updateFromInfo(PlayerInfoResponse player) {
        NumberFormat formatter = NumberFormat.getIntegerInstance();

        setTitle(player.name);

        scoreView.setText(formatter.format(player.score));

        CityAdapter adapter = new CityAdapter(this, player.cities);
        cityList.setAdapter(adapter);
        cityList.setOnItemClickListener(listItemClicked);

        updatePoll();
    }

    private void updatePoll() {
        World world = Session.getActive().world;

        if (world.getAlliance() != null) {
            allianceNameView.setText(Session.getActive().world.getAlliance().name);
        }

        if (world.getPlayer() != null) {
            playerRankView.setText(String.valueOf(world.getPlayer().rank));

            scoreView.setText(String.valueOf(world.getPlayer().score));
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
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getSupportMenuInflater();
        inflater.inflate(R.menu.city_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.alliance_item) {
            startActivity(new Intent(this, AllianceActivity.class));
            return true;
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.city_list);

        setTitle("Loading...");

        playerInfoTask.execute();
    }

    private AdapterView.OnItemClickListener listItemClicked = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            PlayerCity city = (PlayerCity) adapterView.getItemAtPosition(i);
            new ChangeCityTask().execute(city.id);
        }
    };

    private class ChangeCityTask extends AsyncTask<Long, Void, Void> {
        private ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(CityListing.this);
            dialog.setMessage("Changing cities...");
            dialog.show();
        }

        @Override
        protected Void doInBackground(Long... cityId) {
            Session.getActive().world.changeCity(cityId[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            dialog.dismiss();
            Intent intent = new Intent(CityListing.this, CityDetails.class);
            startActivity(intent);
        }
    }
}
