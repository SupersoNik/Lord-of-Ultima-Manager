package com.avalutions.lou.manager.android.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.avalutions.lou.manager.R;

public class TradeRequest extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.city_list);

        Intent intent = this.getIntent();
    }
}
