package com.example.root.sharide;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.JsonObject;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button createAccount;
//    String token = GlobalObjects.editor.


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        token = LoginActivity.("token",LoginActivity.MODE_PRIVATE);
        this.createAccount = (Button) this.findViewById(R.id.createAccount);//add a account with tapping share with hop button
        this.createAccount.setOnClickListener(this);
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
            SharedPreferences token = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
            String authToken = token.getString("token", null);

            if(authToken !=null){

                AppClient.alreadyUser(authToken, new AppClient.INetworkResponse<JsonObject>() {
                    @Override
                    public void onSuccess(JsonObject jsonObject) {
                        if(jsonObject.get("res").getAsBoolean()) {
                            Intent intent = new Intent(getApplicationContext(), RidesActivity.class);
                            startActivity(intent);
                        }
                        else{

                            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                            startActivity(intent);
                        }

                    }
                    @Override
                    public void onError(Exception e) {
                        Toast.makeText(getBaseContext(), "Check your Internet connection, Please try again.",
                                Toast.LENGTH_SHORT).show();
                    }
                });
            }
            else{
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        }
    }
}
