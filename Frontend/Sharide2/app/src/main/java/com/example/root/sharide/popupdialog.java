package com.example.root.sharide;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by shashank on 26/7/15.
 */
public class popupdialog  extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.popup_layout);
        dialog.setTitle("Custom Alert Dialog");

        final EditText mPhone = (EditText) dialog.findViewById(R.id.phone);
        Button send = (Button) dialog.findViewById(R.id.send);
        Button btnCancel = (Button) dialog.findViewById(R.id.cancel);
        dialog.show();
    }
}
