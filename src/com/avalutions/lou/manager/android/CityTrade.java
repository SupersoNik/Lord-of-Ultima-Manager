package com.avalutions.lou.manager.android;

import java.text.NumberFormat;

import com.avalutions.lou.manager.R;
import com.avalutions.lou.manager.common.City;
import com.avalutions.lou.manager.common.CityResource;
import com.avalutions.lou.manager.common.LouSession;
import com.avalutions.lou.manager.net.IPollHandler;
import com.avalutions.lou.manager.net.SessionManager;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class CityTrade extends Activity implements IPollHandler {
	private LouSession session;
	private SessionManager world;
    private final ProgressDialog dialog = new ProgressDialog(CityTrade.this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.city_trade);

		Intent intent = this.getIntent();
		this.session = (LouSession) intent.getSerializableExtra("session");
		this.world = SessionManager.getInstance(session);

        world.setPollHandler(this);
        world.setContext(this);
        
        if(((City)session.getCurrentCity()).getResources() == null) {
            dialog.setMessage("Loading Resources...");
            dialog.show();
        }
        updateDetails();
	}
	
	private void updateDetails() {
        City city = (City)session.getCurrentCity();
        NumberFormat formatter = NumberFormat.getIntegerInstance();
        TextView tv = null;
        TextView tvPer = null;
        TextView tvCap = null;
        
        tv = (TextView)findViewById(R.id.city_details_cart_count);
        tv.setText(formatter.format(city.getCarts().getAmount()));
        tvCap = (TextView)findViewById(R.id.city_details_cart_max);
        tvCap.setText(formatter.format(city.getCarts().getCapacity()));
                
        tv = (TextView)findViewById(R.id.city_details_ship_count);
        tv.setText(formatter.format(city.getShips().getAmount()));
        tvCap = (TextView)findViewById(R.id.city_details_ship_max);
        tvCap.setText(formatter.format(city.getShips().getCapacity()));
        
        for (CityResource resource : city.getResources()) {

            switch (resource.getUnit()) {
            case Wood:
                tv = (TextView) findViewById(R.id.citydetails_wood);
                tvPer = (TextView) findViewById(R.id.citydetails_wood_per);
                tvCap = (TextView) findViewById(R.id.citydetails_wood_cap);
                break;
            case Stone:
                tv = (TextView) findViewById(R.id.citydetails_stone);
                tvPer = (TextView) findViewById(R.id.citydetails_stone_per);
                tvCap = (TextView) findViewById(R.id.citydetails_stone_cap);
                break;
            case Iron:
                tv = (TextView) findViewById(R.id.citydetails_iron);
                tvPer = (TextView) findViewById(R.id.citydetails_iron_per);
                tvCap = (TextView) findViewById(R.id.citydetails_iron_cap);
                break;
            case Food:
                tv = (TextView) findViewById(R.id.citydetails_food);
                tvPer = (TextView) findViewById(R.id.citydetails_food_per);
                tvCap = (TextView) findViewById(R.id.citydetails_food_cap);
                break;
            }
            
            tv.setText(formatter.format(resource.getAmount()));
            tvPer.setText(resource.toString());
            tvCap.setText(formatter.format(resource.getCapacity()));
        }
	}

    public void BucketChanged(String bucket) {
        if(bucket == getString(R.string.bucket_city_poll)) {
            if (this.dialog.isShowing()) {
                this.dialog.dismiss();
            }
            
            updateDetails();
        }
    }
}
