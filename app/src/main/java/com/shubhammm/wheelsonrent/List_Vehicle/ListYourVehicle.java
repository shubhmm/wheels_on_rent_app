package com.shubhammm.wheelsonrent.List_Vehicle;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.shubhammm.wheelsonrent.R;
import com.shubhammm.wheelsonrent.Splash_and_Home_Screen.Home;

public class ListYourVehicle extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private Toolbar mToolbar;
    private DatabaseReference databaseReference,myref;
    EditText name,phone_no,bike_name,model_year ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_your_vehicle);


        Window window = getWindow();
        //if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        window.setStatusBarColor(getColor(R.color.LoginActivityColor));

        mToolbar = (Toolbar) findViewById(R.id.nav_action);
        setSupportActionBar(mToolbar);


        firebaseAuth = FirebaseAuth.getInstance();

        FirebaseUser user = firebaseAuth.getCurrentUser();

        databaseReference = FirebaseDatabase.getInstance().getReference();

        name = (EditText)findViewById(R.id.name);
        phone_no = (EditText)findViewById(R.id.phone_no);
        bike_name = (EditText)findViewById(R.id.bike_name);
        model_year = (EditText)findViewById(R.id.model_year);



    }

    public void submit(View view) {
        String str_name = name.getText().toString().trim();
        String str_phone_no = phone_no.getText().toString().trim();
        String str_bike_name = bike_name.getText().toString().trim();
        String str_model_year = model_year.getText().toString().trim();



         List_Your_Vehicle_db userInformation = new List_Your_Vehicle_db(str_name,str_phone_no,str_bike_name,str_model_year);

        FirebaseUser user = firebaseAuth.getCurrentUser();

        myref = databaseReference.child(user.getUid());
                myref.child("List_Vehiclee").setValue(userInformation);

        Toast.makeText(this,"Successfully Submitted .... We will contact you Soon !!!",Toast.LENGTH_SHORT).show();
        finish();
        Intent intent = new Intent(ListYourVehicle.this,Home.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);


    }

}
