package com.avalutions.lou.manager.android;

import com.avalutions.lou.manager.R;
import com.avalutions.lou.manager.common.LouSession;
import com.avalutions.lou.manager.net.SessionManager;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends Activity {
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Log.i("Details", "Starting up");
        
        Button btn = (Button)this.findViewById(R.id.btnLogin);
        final EditText txtUsername = (EditText)this.findViewById(R.id.txtUsername);
        final EditText txtPassword = (EditText)this.findViewById(R.id.txtPassword);
        btn.setOnClickListener(new OnClickListener() {
        	public void onClick(View v) {
        	    GetListTask lst = new GetListTask();
        	    lst.execute(txtUsername.getText().toString(), txtPassword.getText().toString());
        	}
        });
    }
    private class GetListTask extends AsyncTask<String, Void, LouSession[]> {
        private final ProgressDialog dialog = new ProgressDialog(LoginActivity.this);
        
        @Override
        protected void onPreExecute() {
            dialog.setMessage("Logging in");
            dialog.show();
        }
        
        /**
         * Let's make the http request and return the result as a String.
         */
        protected LouSession[] doInBackground(String... args) {
            return SessionManager.LogIn(args[0], args[1]);
        }
     
        /**
         * Parse the String result, and create a new array adapter for the list
         * view.
         */
        protected void onPostExecute(LouSession[] objResult) {
            if (this.dialog.isShowing()) {
                this.dialog.dismiss();
             }
            
            
            // check to make sure we're dealing with a string
            if(objResult != null) {
                Intent intent = new Intent(getBaseContext(), WorldListing.class);
                startActivity(intent);
            }
        }
     
    }
}