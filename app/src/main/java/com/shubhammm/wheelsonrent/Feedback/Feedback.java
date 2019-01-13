package com.shubhammm.wheelsonrent.Feedback;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.shubhammm.wheelsonrent.R;
import com.shubhammm.wheelsonrent.Splash_and_Home_Screen.Home;

public class Feedback extends AppCompatActivity {

    EditText message ;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        firebaseAuth = FirebaseAuth.getInstance();

        databaseReference = FirebaseDatabase.getInstance().getReference();

        message = (EditText) findViewById(R.id.feedback);


    }


    public void submit(View view) {

        String str_message = message.getText().toString().trim();


        Feedback_message experienceMessage = new Feedback_message(str_message);

        FirebaseUser user = firebaseAuth.getCurrentUser();

        databaseReference.child(user.getUid()).setValue(experienceMessage);

        Toast.makeText(this,"Successfully Submitted ...",Toast.LENGTH_SHORT).show();


        finish();
        Intent intent = new Intent(Feedback.this,Home.class);

        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);

    }
}
