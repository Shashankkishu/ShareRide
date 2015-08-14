package com.example.root.sharide;

/**
 * Created by root on 22/5/15.
 */

import android.app.Dialog;
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
import android.widget.Toast;

import com.google.gson.JsonObject;

public class SignUpFragment extends Fragment implements  View.OnClickListener{

    private static EditText name;
    private static EditText emailId;
    private static EditText password;
    Boolean otp_true;
    private Button registerButton;

    public SignUpFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_signup, container, false);
        name = (EditText) view.findViewById(R.id.name);
        emailId = (EditText) view.findViewById(R.id.emailAddress);
        password = (EditText) view.findViewById(R.id.newPassword);

        this.registerButton = (Button)view.findViewById(R.id.signUpButton);
        this.registerButton.setOnClickListener(this);

        return view;
    }

    public static String getEditTextValue(EditText editText) {
        return editText.getText().toString().trim();
    }

    @Override
    public void onClick(View view) {
        if (view == this.registerButton) {
            if (getEditTextValue(name).length() > 1 && getEditTextValue(password).length() > 1 && getEditTextValue(emailId).length() > 1) {


                final Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.popup_layout);
                dialog.setTitle("Custom Alert Dialog");

                final EditText mPhone = (EditText) dialog.findViewById(R.id.phone);
                Button send = (Button) dialog.findViewById(R.id.send);
                Button btnCancel = (Button) dialog.findViewById(R.id.cancel);
                dialog.show();
                dialog.setCanceledOnTouchOutside(false);
                dialog.setCancelable(false);
                JsonObject dataObject_OPT = new JsonObject();
                dataObject_OPT.addProperty("x-auth-name", getEditTextValue(name));
                AppClient.registerUser(dataObject_OPT, new AppClient.INetworkResponse<JsonObject>() {
                    @Override
                    public void onSuccess(JsonObject data) {

                        if (data.get("res").getAsBoolean()) {

                            Snackbar.make(getView(), (String) data.get("response").getAsString(), Snackbar.LENGTH_LONG).show();

//                        startActivity(new Intent(getActivity(), RidesActivity.class));
//
//                        getActivity().finish();
                            otp_true = true;
                            return;
                        } else {
                            Snackbar.make(getView(), (String) data.get("response").getAsString(), Snackbar.LENGTH_LONG).show();
                            return;
                        }
                    }

                    @Override
                    public void onError(Exception e) {
                        Snackbar.make(getView(), "Check Your Internet Connection", Snackbar.LENGTH_LONG).show();
                        return;

                    }
                });

                if(otp_true) {
                    JsonObject dataObject = new JsonObject();
                    dataObject.addProperty("x-auth-name", getEditTextValue(name));
                    dataObject.addProperty("x-auth-password", getEditTextValue(password));
                    dataObject.addProperty("x-auth-email", getEditTextValue(emailId));
                    AppClient.registerUser(dataObject, new AppClient.INetworkResponse<JsonObject>() {
                        @Override
                        public void onSuccess(JsonObject data) {

                            if (data.get("res").getAsBoolean()) {

                                Snackbar.make(getView(), (String) data.get("response").getAsString(), Snackbar.LENGTH_LONG).show();

                                SharedPreferences prefs = getActivity().getSharedPreferences("com.example.root.sharide", Context.MODE_PRIVATE);
                                prefs.edit().putString(Config.AUTH_TOKEN, data.get("token").getAsString()).apply();

//                        startActivity(new Intent(getActivity(), RidesActivity.class));
//
//                        getActivity().finish();
                                return;
                            } else {
                                Snackbar.make(getView(), (String) data.get("response").getAsString(), Snackbar.LENGTH_LONG).show();
                                return;
                            }
                        }

                        @Override
                        public void onError(Exception e) {
                            Snackbar.make(getView(), "Check Your Internet Connection", Snackbar.LENGTH_LONG).show();
                            return;

                        }
                    });

                }



            } else {
                Snackbar.make(getView(), "Check You have entered all feilds", Snackbar.LENGTH_LONG).show();
            }
        }

    }
}
