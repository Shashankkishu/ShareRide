package com.example.root.sharide;

/**
 * Created by root on 22/5/15.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.JsonObject;


public class SignInFragment extends Fragment implements  View.OnClickListener{

    public SignInFragment(){

    }

    private EditText userName;
    private EditText password;
    private Button signInButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_signin, container, false);

        this.userName = (EditText)view.findViewById(R.id.userEmail);
        this.password = (EditText)view.findViewById(R.id.userPassword);

        this.signInButton = (Button) view.findViewById(R.id.signInButton);
        this.signInButton.setOnClickListener(this);

        return view;
    }

    public static String getEditTextValue(EditText editText) {
        return editText.getText().toString().trim();
    }

    @Override
    public void onClick(View view) {

        if(view == this.signInButton){

            JsonObject dataobject = new JsonObject();
            dataobject.addProperty("x-auth-password", getEditTextValue(password));
            dataobject.addProperty("x-auth-email", getEditTextValue(userName));
            AppClient.authenticateUser(dataobject, new AppClient.INetworkResponse<JsonObject>() {

                @Override
                public void onSuccess(JsonObject data) {
                    Log.i(getTag(), "response of the login"+data.toString());
                    if(data.get("res").getAsBoolean()){
                        Log.i(getTag(), "response of the login" + data.toString());
                        Snackbar.make(getView(), data.get("res").getAsString(), Snackbar.LENGTH_LONG).show();

                        SharedPreferencesManager.get(getActivity()).setString("token", data.get("token").getAsString());
                        SharedPreferencesManager.get(getActivity()).setString("name", data.get("name").getAsString());
                        SharedPreferencesManager.get(getActivity()).setString("email", data.get("email").getAsString());

                        startActivity(new Intent(getActivity(), RidesActivity.class));
                        getActivity().finish();
                    }
                    else{
                        Snackbar.make(getView(),"check your credentials again", Snackbar.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onError(Exception e) {
                    Snackbar.make(getView(), "Can not create account, Please try again.", Snackbar.LENGTH_SHORT).show();
                }
            });

        }
    }
}
