package com.example.root.sharide;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Shashank Bhusha on 7/9/2015.
 */
public class NewRideActivity_shashank extends AppCompatActivity implements AdapterView.OnItemSelectedListener,View.OnClickListener, AdapterView.OnItemClickListener {
     Boolean mBoys= false,mGirls= false;
    private TextView mtimePicker,mdatePicker;
    CheckBox mgirlsonly,mHaveRide ;
    private Spinner mOrigin , mEnd , mMode;
    EditText mOtherInfo , mAmount,mAllowedPeople;
    TimePicker mTimePicker;
    CardView mOtherinfoCard,mTransportModeCard,mAmountCard;
    Button Submit;


    final Calendar c = Calendar.getInstance();
    private int mYear = c.get(Calendar.YEAR),mMonth = c.get(Calendar.MONTH),mDay = c.get(Calendar.DAY_OF_MONTH),mHour = c.get(Calendar.HOUR_OF_DAY),mMinute = c.get(Calendar.MINUTE);
    int millisyear = c.get(Calendar.YEAR),millismonth = c.get(Calendar.MONTH),millisdayofmonth= c.get(Calendar.DAY_OF_MONTH),millishour= c.get(Calendar.HOUR_OF_DAY),millisminutes= c.get(Calendar.MINUTE);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_ride_shashank);
        Toolbar ltoolbar = (Toolbar) findViewById(R.id.my_awesome_toolbar);
        setSupportActionBar(ltoolbar);
        mOrigin = (Spinner)findViewById(R.id.originSpinner);//to set the starting point of the journey .
        ArrayAdapter<CharSequence> adapterorigin = ArrayAdapter.createFromResource(this,
                R.array.Stops1, android.R.layout.simple_spinner_item);
        adapterorigin.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mOrigin.setAdapter(adapterorigin);


        mEnd = (Spinner)findViewById(R.id.endSpinner);// to set the end point of the journey .
        ArrayAdapter<CharSequence> adapterend = ArrayAdapter.createFromResource(this,
                R.array.Stops2,android.R.layout.simple_spinner_item);
        adapterend.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mEnd.setAdapter(adapterend);


        mdatePicker = (TextView)findViewById(R.id.datePicker);//to set eh date of journey .
        mdatePicker.setOnClickListener(this);

        mtimePicker=(TextView)findViewById(R.id.timePicker);// to pick a time of the starting of the journey .
        mtimePicker.setOnClickListener(this);

        mHaveRide = (CheckBox)findViewById(R.id.haveRide);//to check if the user has already booked a ride or not .
        mHaveRide.setOnClickListener(this);

        mgirlsonly = (CheckBox)findViewById(R.id.samesex);//to check if the user wants only the same type of companions or not.
        mOtherInfo=(EditText)findViewById(R.id.otherinfo); // to set he info about the ride he has booked if any .
        mAllowedPeople=(EditText)findViewById(R.id.freeSpace);//no of people allowed to ride with them .
        mAmount=(EditText)findViewById(R.id.amount); // amount the user had already paid the to the ride driver if any.
        mMode = (Spinner)findViewById(R.id.transportmode);
        ArrayAdapter<CharSequence> adaptermode = ArrayAdapter.createFromResource(this,
                R.array.mode, android.R.layout.simple_spinner_item);
        adaptermode.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mMode.setAdapter(adaptermode);
        mMode.setOnItemSelectedListener(this);

        mOtherinfoCard = (CardView)findViewById(R.id.otherinfocard);// a cardview created to make it easired to make the field visible
        mAmountCard = (CardView)findViewById(R.id.amountcard);// a cardview created to make it easired to make the field visible
        mTransportModeCard = (CardView) findViewById(R.id.transportmodecard);// if the user has booked the ride other than the auto or tempo then he has to specify the choice he has made .

        Submit =(Button)findViewById(R.id.button);
        Submit.setOnClickListener(this);

    }


    /**
     * <p>Callback method to be invoked when an item in this view has been
     * selected. This callback is invoked only when the newly selected
     * position is different from the previously selected position or if
     * there was no selected item.</p>
     * <p/>
     * Impelmenters can call getItemAtPosition(position) if they need to access the
     * data associated with the selected item.
     *
     * @param parent   The AdapterView where the selection happened
     * @param view     The view within the AdapterView that was clicked
     * @param position The position of the view in the adapter
     * @param id       The row id of the item that is selected
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        if(position == 3){
            mOtherinfoCard.setVisibility(View.VISIBLE);
        }
        else{
            mOtherinfoCard.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * Callback method to be invoked when the selection disappears from this
     * view. The selection can disappear for instance when touch is activated
     * or when the adapter becomes empty.
     *
     * @param parent The AdapterView that now contains no selected item.
     */
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(final View v) {
        if (v == mdatePicker) {
            // Process to get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);

            // Launch Date Picker Dialog
            DatePickerDialog dpd = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                            // Display Selected date in textbox
                            mYear = c.get(Calendar.YEAR);
                            mMonth = c.get(Calendar.MONTH);
                            mDay = c.get(Calendar.DAY_OF_MONTH);
                            mdatePicker.setText(dayOfMonth + "-"
                                    + (monthOfYear + 1) + "-" + year);
                        }
                    }, mYear, mMonth, mDay);
            dpd.show();
        }
        if (v == mtimePicker) {
            // Process to get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);
            // Launch Time Picker Dialog
            CustomTimePickerDialog tpd = new CustomTimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {
                            millishour = hourOfDay;
                            millisminutes = minute;                            // Display Selected time in textbox
                            if(minute != 0)
                            mtimePicker.setText(hourOfDay + " : " + minute);
                            else
                                mtimePicker.setText(hourOfDay + " : " + minute+"0");

                        }
                    }, mHour, mMinute, false);
            tpd.show();

        }
        if (v == mHaveRide) {
            if(mHaveRide.isChecked()){
            Toast.makeText(getApplicationContext(),"You selected this",
                    Toast.LENGTH_LONG).show();
            mAmountCard.setVisibility(View.VISIBLE);
            mTransportModeCard.setVisibility(View.VISIBLE);

        }
        }
            if (v == Submit) {

                Calendar calendar = Calendar.getInstance();

//                Calendar calendar = Calendar.getInstance();

                calendar.set(millisyear, millismonth, millisdayofmonth,
                        millishour, millisminutes, 0);
                long startTime = calendar.getTimeInMillis();
                String token =PreferenceManager.getDefaultSharedPreferences(getApplication()).getString("auth", "defaultStringIfNothingFound");
                System.out.print("YAHA hai KYA likha TOKEN"+token);
                RidePost mRidePost = new RidePost();
                mRidePost.setladminname(GlobalObjects.username);
//            mRidePost.setltoken(token);
            mRidePost.setlmillis(startTime);
            mRidePost.setlOrigin(mOrigin.getSelectedItem().toString());
            mRidePost.setlDestination(mEnd.getSelectedItem().toString());
            mRidePost.setlTime(mtimePicker.getText().toString());
            mRidePost.setlDate(mdatePicker.getText().toString());
            mRidePost.setlfreeSpace(Integer.parseInt(mAllowedPeople.getText().toString()));
            mRidePost.setlGirls(mgirlsonly.isChecked());
//             mRidePost.setlPrice(Integer.parseInt(mAmount.getText().toString()));
            if(mHaveRide.isChecked()){
                mRidePost.setlHas_booked(true);
                mRidePost.setlPrice(Integer.parseInt(mAmount.getText().toString()));
                mRidePost.setlTransport_mode(mMode.getSelectedItem().toString());
                if(mMode.getSelectedItemPosition() == 3){
                    mRidePost.setlTransport_mode_info(mOtherInfo.getText().toString());
                }

            }
            else {
//                mRidePost.setlTransport_mode(mMode.getSelectedItem().toString());
                mRidePost.setlHas_booked(false);
                mRidePost.setlTransport_mode(null);
                mRidePost.setlPrice(null);
                mRidePost.setlTransport_mode_info(null);
//                if(mMode.getSelectedItemPosition() == 3){
//                    mRidePost.setlTransport_mode(mOtherInfo.getText().toString());
//                }
            }
//            mRidePost.setlGirls(false);
//            mRidePost.setlBoys(false);
//            mRidePost.setlOther_Riders(new ArrayList<OtherRider>());
//            mRidePost.setlLatitude("54 .32'");
//            mRidePost.setlLongitude("45 .32'");

//                JsonObject dataObject = new JsonObject();
//                dataObject = mRidePost+token;
////                dataObject.addProperty();
//                dataObject.addProperty("newride", String.valueOf(mRidePost));
//                dataObject.addProperty("token",token);
//                SharedPreferences token = getApplication().getSharedPreferences("com.example.root.sharide", MODE_PRIVATE);
//                String Strtoken = token.getString('AUTH_TOKEN',null);

                //get the tokenfomsharedpreferences

//                SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);

                String authToken = GlobalObjects.String_token;

                AppClient.addRide(mRidePost, authToken, new AppClient.INetworkResponse<JsonObject>() {
                    @Override
                    public void onSuccess(JsonObject data) {
                        Snackbar.make(v, "Your RidePost has been added" + data.get("resp"), Snackbar.LENGTH_LONG).show();
                    }
                    @Override
                    public void onError(Exception e) {

                    }
                });
                startActivity(new Intent(NewRideActivity_shashank.this, RidesActivity.class));

            }
    }

    /**
     * Callback method to be invoked when an item in this AdapterView has
     * been clicked.
     * <p/>
     * Implementers can call getItoriginemAtPosition(position) if they need
     * to access the data associated with the selected item.
     *
     * @param parent   The AdapterView where the click happened.
     * @param view     The view within the AdapterView that was clicked (this
     *                 will be a view provided by the adapter)
     * @param position The position of the view in the adapter.
     * @param id       The row id of the item that was clicked.
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        if(mMode.getItemAtPosition(position) == mMode.getItemAtPosition(3)){
//            mOtherInfo.setVisibility(View.VISIBLE);
//
//        }
//        if(position == 3){
//            mOtherInfo.setVisibility(View.VISIBLE);
//        }
    }

    /**
     * Callback method to be invoked when an item in this AdapterView has
     * been clicked.
     * <p/>
     * Implementers can call getItemAtPosition(position) if they need
     * to access the data associated with the selected item.
     *
     * @param parent   The AdapterView where the click happened.
     * @param view     The view within the AdapterView that was clicked (this
     *                 will be a view provided by the adapter)
     * @param position The position of the view in the adapter.
     * @param id       The row id of the item that was clicked.
     */

}
