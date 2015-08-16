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

            JsonObject dataObject = new JsonObject();
            dataObject.addProperty("token","841b0e160345eff8ca55e264e572d1706df7b622324b198b5b6e695e90b55f90168b591adf0e73852fbb3cf2f59d3d4329d7ea51e2383bb316b50b9ec818af40");
            AppClient.alreadyUser(dataObject, new AppClient.INetworkResponse<JsonObject>() {
                @Override
                public void onSuccess(JsonObject data) {
//                                                System.out.print(data.getAsString());
                    if (data.get("res").getAsBoolean()) {
                        startActivity(new Intent(getApplicationContext(), RidesActivity.class));
                    } else {
//                        Snackbar.make(view, data.get("response").getAsString(), Snackbar.LENGTH_LONG).show();
                        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    }
                }

                @Override
                public void onError(Exception e) {
                    Snackbar.make(view, "Check Your Internet Connection", Snackbar.LENGTH_LONG).show();
                    return;

                }
            });
//                if()
//            Intent intent = new Intent(this, LoginActivity.class);
//            startActivity(intent);

            this.finish();
        }
    }
}
