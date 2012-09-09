package com.avalutions.lou.manager.android;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.TextView;
import com.avalutions.lou.manager.R;
import com.avalutions.lou.manager.models.City;
import com.avalutions.lou.manager.models.CityResource;
import com.avalutions.lou.manager.net.Session;

import java.text.NumberFormat;

public class CityTrade extends Activity {
    private final ProgressDialog dialog = new ProgressDialog(CityTrade.this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.city_trade);
        
        if(Session.getActive() != null &&
                Session.getActive().world != null &&
                Session.getActive().world.getCurrentCity() != null &&
                Session.getActive().world.getCurrentCity().getResources() == null) {
            dialog.setMessage("Loading Resources...");
            dialog.show();
        }
        updateDetails();
	}
	
	private void updateDetails() {
        City city = Session.getActive().world.getCurrentCity();
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
}
