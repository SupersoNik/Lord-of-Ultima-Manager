package com.avalutions.lou.manager.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.avalutions.lou.manager.R;

public class TradeRequest extends Activity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.world_details);

        Intent intent = this.getIntent();
    }
}
