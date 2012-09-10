package com.avalutions.lou.manager.android;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import com.avalutions.lou.manager.R;
import com.avalutions.lou.manager.net.Session;

public class LoginActivity extends Activity {

    private OnClickListener loginHandler = new OnClickListener() {
        @Override
        public void onClick(View view) {
            final EditText usernameView = (EditText)LoginActivity.this.findViewById(R.id.txtUsername);
            final EditText passwordView = (EditText)LoginActivity.this.findViewById(R.id.txtPassword);

            loginTask.execute(usernameView.getText().toString(), passwordView.getText().toString());
        }
    };
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        final Button btn = (Button)this.findViewById(R.id.btnLogin);
        btn.setOnClickListener(loginHandler);
    }

    private AsyncTask<String, Void, Void> loginTask =new  AsyncTask<String, Void, Void>() {
        private ProgressDialog dialog;
        
        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(LoginActivity.this);
            dialog.setMessage("Logging in");
            dialog.show();
        }
        
        /**
         * Let's make the http request and return the result as a String.
         */
        @Override
        protected Void doInBackground(String... args) {
            Session.login(args[0], args[1]);
            return null;
        }
     
        /**
         * Parse the String result, and create a new array adapter for the list
         * view.
         */
        @Override
        protected void onPostExecute(Void result) {
            if (this.dialog.isShowing()) {
                this.dialog.dismiss();
             }

            // check to make sure we're dealing with a string
            Intent intent = new Intent(LoginActivity.this, SessionListing.class);
            startActivity(intent);
        }
    };
}