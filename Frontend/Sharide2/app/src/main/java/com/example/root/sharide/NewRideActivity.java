package com.example.root.sharide;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompletePrediction;
import com.google.android.gms.location.places.AutocompletePredictionBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog.OnDateSetListener;
import com.wdullaer.materialdatetimepicker.time.RadialPickerLayout;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class  NewRideActivity extends AppCompatActivity implements OnClickListener, TimePickerDialog.OnTimeSetListener, OnDateSetListener, GoogleApiClient.ConnectionCallbacks, OnConnectionFailedListener{

    private TextView datePicker;
    private TextView timePicker;

    private AutoCompleteTextView origin;
    private AutoCompleteTextView destination;

    private LatLngBounds mBounds;

    private GoogleApiClient mGoogleApiClient;

    private List<String> resultList;
    private ArrayAdapter<String> suggestionsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_ride);

        mGoogleApiClient = new GoogleApiClient
                                .Builder(this)
                                .addApi(Places.GEO_DATA_API)
                                .addApi(Places.PLACE_DETECTION_API)
                                .addConnectionCallbacks(this)
                                .addOnConnectionFailedListener(this)
                                .build();


        Toolbar toolbar = (Toolbar) findViewById(R.id.my_awesome_toolbar);
        setSupportActionBar(toolbar);

        datePicker = (TextView)findViewById(R.id.datePicker);
        timePicker = (TextView)findViewById(R.id.timePicker);

        datePicker.setOnClickListener(this);
        timePicker.setOnClickListener(this);

        origin = (AutoCompleteTextView) findViewById(R.id.origin);
        destination = (AutoCompleteTextView) findViewById(R.id.destination);

        origin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(final Editable s) {

                mBounds = new LatLngBounds(
                        new LatLng(85, -180),           // top left corner of map
                        new LatLng(185, 180)            // bottom right corner
                );

                if (mGoogleApiClient != null) {

                    final PendingResult<AutocompletePredictionBuffer> results = Places.GeoDataApi.getAutocompletePredictions(mGoogleApiClient, s.toString(), mBounds, null);

                    new Thread(new Runnable() {
                        @Override
                        public void run() {

                            AutocompletePredictionBuffer autocompletePredictions = results.await(60, TimeUnit.SECONDS);

                            final Status status = autocompletePredictions.getStatus();

                            if (!status.isSuccess()) {

                                Log.e("tag", "Error getting place predictions: " + status.toString());
                                autocompletePredictions.release();
                            }

                            Iterator<AutocompletePrediction> iterator = autocompletePredictions.iterator();
                            resultList = new ArrayList<>(autocompletePredictions.getCount());

                            while (iterator.hasNext()) {
                                AutocompletePrediction prediction = iterator.next();
                                resultList.add(prediction.getDescription());
                            }

                            NewRideActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                    setSuggestions();
                                }
                            });

                            // Buffer release
                            autocompletePredictions.release();
                        }
                    }).start();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_ride, menu);
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
    public void onClick(View view) {
        if(view == this.datePicker){
            Calendar now = Calendar.getInstance();
            DatePickerDialog dpd = DatePickerDialog.newInstance(
                    NewRideActivity.this,
                    now.get(Calendar.YEAR),
                    now.get(Calendar.MONTH),
                    now.get(Calendar.DAY_OF_MONTH)
            );
            dpd.show(getFragmentManager(), "Datepickerdialog");
        }
        if(view ==this.timePicker){
            Calendar now = Calendar.getInstance();
            TimePickerDialog tpd = TimePickerDialog.newInstance(NewRideActivity.this, now.getTime().getHours(), now.getTime().getMinutes(), true);
            tpd.show(getFragmentManager(), "TimePickerDialog");
        }
    }

    @Override
    public void onDateSet(DatePickerDialog datePickerDialog, int i, int i1, int i2) {

    }

    @Override
    public void onTimeSet(RadialPickerLayout radialPickerLayout, int i, int i1) {

    }

    @Override
    public void onConnected(Bundle bundle) {
        Log.i("success", "connected to google api");
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i("success", "connection suspended");
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.i("failed", "failed to connect to google api");
    }

    private void setSuggestions(){

        suggestionsAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.abc_simple_dropdown_hint, resultList);
        origin.setAdapter(suggestionsAdapter);
        origin.showDropDown();
    }
}
