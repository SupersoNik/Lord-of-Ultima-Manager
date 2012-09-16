package com.avalutions.lou.manager.android;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import com.avalutions.lou.manager.R;
import com.avalutions.lou.manager.android.adapters.AllianceMemberAdapter;
import com.avalutions.lou.manager.net.Session;

/**
 * Created with IntelliJ IDEA.
 * User: benny
 * Date: 9/13/12
 * Time: 2:39 AM
 * To change this template use File | Settings | File Templates.
 */
public class AllianceActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alliance_details);

        AllianceMemberAdapter adapter = new AllianceMemberAdapter(this, Session.getActive().world.getAlliance().members);
        ListView memberList = (ListView)findViewById(R.id.memberList);
        memberList.setAdapter(adapter);
    }
}
