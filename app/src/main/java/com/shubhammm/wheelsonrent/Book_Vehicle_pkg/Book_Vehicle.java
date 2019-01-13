package com.shubhammm.wheelsonrent.Book_Vehicle_pkg;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.shubhammm.wheelsonrent.My_Bookings.DatabaseHelper;
import com.shubhammm.wheelsonrent.R;
import com.shubhammm.wheelsonrent.Splash_and_Home_Screen.Home;

import java.util.Locale;

public class Book_Vehicle extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference,myref;
    Calendar myCalendar = Calendar.getInstance();
    EditText edittext;
    EditText time;
    EditText duration;
    EditText contact_no;
    EditText promocode;
    EditText vehicle_name;

    DatabaseHelper mDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book__vehicle);
        Window window = getWindow();
        //if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        window.setStatusBarColor(getColor(R.color.LoginActivityColor));
        contact_no = (EditText)findViewById(R.id.contact_no);
        promocode = (EditText)findViewById(R.id.promocode);

        firebaseAuth = FirebaseAuth.getInstance();

       FirebaseUser user = firebaseAuth.getCurrentUser();

        databaseReference = FirebaseDatabase.getInstance().getReference();

        edittext= (EditText) findViewById(R.id.Date_Picker);
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };

        edittext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v ) {
                new DatePickerDialog(Book_Vehicle.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        time = (EditText) findViewById(R.id.Time_Picker);
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(Book_Vehicle.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        time.setText(selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });

        duration = (EditText) findViewById(R.id.duration);

       // mDatabaseHelper = new DatabaseHelper(this);
        vehicle_name = (EditText) findViewById(R.id.vehicle_Name);
        vehicle_name.setText(getIntent().getStringExtra("NAME"));
    }


/*
    public void AddData(String a, String b, String c){

       mDatabaseHelper.addData(a,b,c);

    }
*/
    public void AddData(String a){

        mDatabaseHelper.addData(a);

    }

    private void updateLabel() {
        String myFormat = "dd/mm/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        edittext.setText(sdf.format(myCalendar.getTime()));
    }


    public void Book(View view) {

        String str_vehicle = vehicle_name.getText().toString().trim();
        String str_date = edittext.getText().toString().trim();
        String str_s_time = time.getText().toString().trim();
        String str_d_time = duration.getText().toString().trim();
        String str_contact_no = contact_no.getText().toString().trim();
        String str_promocode = promocode.getText().toString().trim();


      // AddData(str_date);
        //AddData(str_date,str_s_time,str_d_time);


        Book_Vehicle_db book_vehicle = new Book_Vehicle_db(str_vehicle,str_date,str_s_time,str_d_time,str_contact_no,str_promocode);

        FirebaseUser user = firebaseAuth.getCurrentUser();

       myref = databaseReference.child(user.getUid());
        myref.child("Book_Vehicle").push().setValue(book_vehicle);


        databaseReference.child("Seller_Booking").push().setValue(book_vehicle);
        Toast.makeText(this,"We HAve Received Your Request , We will Contact you SOON !!!!",Toast.LENGTH_LONG).show();
        finish();
        Intent intent = new Intent(Book_Vehicle.this,Home.class);

        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);


    }
}
