package com.example.root.sharide;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by shashank on 26/7/15.
 */
public class RideDedicatedPage extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dedicated_rides);

//        Toolbar toolbar = (Toolbar) this.findViewById(R.id.my_awesome_toolbar);
//        setSupportActionBar(toolbar);
        Button send =(Button)findViewById(R.id.sendbutton);
        send.setOnClickListener((View.OnClickListener) this);
    }
    @Override
    public void onClick(View v) {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.popup_layout);
        dialog.setTitle("Custom Alert Dialog");

        final EditText mPhone = (EditText) dialog.findViewById(R.id.phone);
        Button send = (Button) dialog.findViewById(R.id.send);
        Button btnCancel = (Button) dialog.findViewById(R.id.cancel);
        dialog.show();
    }
}
