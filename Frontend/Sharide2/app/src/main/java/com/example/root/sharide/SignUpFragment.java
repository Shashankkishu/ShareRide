package com.example.root.sharide;

/**
 * Created by root on 22/5/15.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class SignUpFragment extends Fragment implements  View.OnClickListener{

    private static EditText name;
    private static EditText emailId;
    private static EditText password;

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

        if(view == this.registerButton){


            if(true){
                startActivity(new Intent(getActivity(), RidesActivity.class));
                        getActivity().finish();
                                        Snackbar.make(getView(), "Account created.", Snackbar.LENGTH_LONG).show();
            }
            else{
                        Snackbar.make(getView(), "You Are Not Shashank", Snackbar.LENGTH_LONG).show();
                    }
//
//            AppClient.registerUser(getEditTextValue(name), getEditTextValue(emailId), getEditTextValue(password), new AppClient.INetworkResponse<JsonObject>() {
//                @Override
//                public void onSuccess(JsonObject data) {
//
//                    if(data.get("success").getAsBoolean()){
//                        Snackbar.make(getView(), "Account created.", Snackbar.LENGTH_LONG).show();
//
//                        SharedPreferences prefs = getActivity().getSharedPreferences("com.example.root.sharide", Context.MODE_PRIVATE);
//                        prefs.edit().putString(Config.AUTH_TOKEN, data.get("token").getAsString()).apply();
//
//                        startActivity(new Intent(getActivity(), RidesActivity.class));
//
//                        getActivity().finish();
//                    }
//                    else{
//                        Snackbar.make(getView(), data.get("message").getAsString(), Snackbar.LENGTH_LONG).show();
//                    }
//                }
//
//                @Override
//                public void onError(Exception e) {
//                    Snackbar.make(getView(), "Can not create account, Please try again.", Snackbar.LENGTH_SHORT).show();
//                }
//            });
        }
    }
}
