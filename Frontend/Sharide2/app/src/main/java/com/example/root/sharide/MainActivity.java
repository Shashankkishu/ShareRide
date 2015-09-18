package com.example.root.sharide;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.gson.JsonObject;

import java.io.IOException;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    public static final String EXTRA_MESSAGE = "message";
    public static final String PROPERTY_REG_ID = "registration_id";
    private static final String PROPERTY_APP_VERSION = "appVersion";
    private final static String TAG = "LaunchActivity";
    protected String SENDER_ID = "837501438389"; // this is project id
    private GoogleCloudMessaging gcm =null;
    private String regid = null;
    private Context context= null;
    private Button createAccount;
//    String token = GlobalObjects.editor.


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = getApplicationContext();
        if (checkPlayServices())
        {
            gcm = GoogleCloudMessaging.getInstance(this);
            regid = getRegistrationId(context);

            if (regid.isEmpty())//checks if this already has registered or not
            {
                registerInBackground();
            }
            else
            {
                Log.d(TAG, "No valid Google Play Services APK found.");
            }
        }

//        token = LoginActivity.("token",LoginActivity.MODE_PRIVATE);
        this.createAccount = (Button) this.findViewById(R.id.createAccount);//add a account with tapping share with hop button
        this.createAccount.setOnClickListener(this);
    }
    @Override protected void onResume()
    {
        super.onResume();       checkPlayServices();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(final View view) {

        if(view == this.createAccount){
//            SharedPreferences token = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
            String authToken = GlobalObjects.String_token;
            Toast.makeText(getBaseContext(), GlobalObjects.String_token,Toast.LENGTH_SHORT).show();
//                                Toast.LENGTH_SHORT).show();
            if(authToken !=null){

//                AppClient.alreadyUser(authToken, new AppClient.INetworkResponse<JsonObject>() {
//                    @Override
//                    public void onSuccess(JsonObject jsonObject) {
//                        if(jsonObject.get("res").getAsBoolean()) {
                            Intent intent = new Intent(getApplicationContext(), RidesActivity.class);
                            startActivity(intent);
//                        }
//                        else{
//
//                            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
//                            startActivity(intent);
//                        }
//
//                    }
//                    @Override
//                    public void onError(Exception e) {
//                        Toast.makeText(getBaseContext(), "Check your Internet connection, Please try again.",
//                                Toast.LENGTH_SHORT).show();
//                    }
//                });
            }
            else{
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        }
    }
    private boolean checkPlayServices() {
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                GooglePlayServicesUtil.getErrorDialog(resultCode, this,
                        PLAY_SERVICES_RESOLUTION_REQUEST).show();
            } else {
                Log.d(TAG, "This device is not supported - Google Play Services.");
                finish();
            }
            return false;
        }
        return true;
    }

    private String getRegistrationId(Context context)
    {
        final SharedPreferences prefs = getGCMPreferences(context);
        String registrationId = prefs.getString(PROPERTY_REG_ID, "");
        if (registrationId.isEmpty()) {
            Log.d(TAG, "Registration ID not found.");
            return "";
        }
        int registeredVersion = prefs.getInt(PROPERTY_APP_VERSION, Integer.MIN_VALUE);
        int currentVersion = getAppVersion(context);
        if (registeredVersion != currentVersion) {
            Log.d(TAG, "App version changed.");
            return "";
        }
        return registrationId;
    }

    private SharedPreferences getGCMPreferences(Context context)
    {
        return getSharedPreferences(MainActivity.class.getSimpleName(),
                Context.MODE_PRIVATE);
    }

    private static int getAppVersion(Context context)
    {
        try
        {
            PackageInfo packageInfo = context.getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionCode;
        }
        catch (PackageManager.NameNotFoundException e)
        {
            throw new RuntimeException("Could not get package name: " + e);
        }
    }


    private void registerInBackground()
    {     new AsyncTask() {
        @Override
        protected Object doInBackground(Object... params)
        {
            String msg = "";
            try
            {
                if (gcm == null)
                {
                    gcm = GoogleCloudMessaging.getInstance(context);
                }
                regid = gcm.register(SENDER_ID);               Log.d(TAG, "########################################");
                Log.d(TAG, "Current Device's Registration ID is: "+regid);
                GlobalObjects.reg_ID=regid;
            }
            catch (IOException ex)
            {
                msg = "Error :" + ex.getMessage();
            }
            return null;
        }     protected void onPostExecute(Object result)
        { //to do here
        };
    }.execute(null, null, null);
    }
}
