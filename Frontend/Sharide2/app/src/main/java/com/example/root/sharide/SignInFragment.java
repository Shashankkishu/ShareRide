package com.example.root.sharide;

/**
 * Created by root on 22/5/15.
 */

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
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
    private String auth;

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
            final String name = userName.getText().toString();
//            final String Password = password.getText().toString();
            AppClient.authenticateUser(dataobject, new AppClient.INetworkResponse<JsonObject>() {
                @Override
                public void onSuccess(JsonObject data) {

                    if(data.get("res").getAsBoolean()){
                        Snackbar.make(getView(), "Login successful", Snackbar.LENGTH_LONG).show();

                        SharedPreferences prefs = getActivity().getSharedPreferences("com.example.root.sharide", Context.MODE_PRIVATE);
                        prefs.edit().putString(Config.AUTH_TOKEN, data.get("token").getAsString()).apply();

                        startActivity(new Intent(getActivity(), RidesActivity.class));

                        getActivity().finish();
                    }
                    else{
                        Snackbar.make(getView(), data.get("message").getAsString(), Snackbar.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onError(Exception e) {
                    Snackbar.make(getView(), "Can not create account, Please try again.", Snackbar.LENGTH_SHORT).show();
                }
            });
//            if(true){
////                startActivity(new Intent(getActivity(), RidesActivity.class));
////                        getActivity().finish();
//                startActivity(new Intent(getActivity(), RidesActivity.class));
//                getActivity().finish();
//            }
//            else{
//                Snackbar.make(getView(), "You Are Not Shashank", Snackbar.LENGTH_LONG).show();
//            }

        }
    }
}
