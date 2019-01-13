package com.shubhammm.wheelsonrent.User;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.shubhammm.wheelsonrent.Splash_and_Home_Screen.Home;
import com.shubhammm.wheelsonrent.R;

public class Sign_Up extends AppCompatActivity {

    EditText username,password,phone;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign__up);

        Window window = getWindow();
        window.setStatusBarColor(getColor(R.color.yellow));

        progressDialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser()!= null)
        {
            // LOGIN ACTIVITY
            finish();
            startActivity(new Intent(getApplicationContext(),Home.class));
        }




        username=(EditText) findViewById(R.id.username);
        password=(EditText) findViewById(R.id.password);
        phone=(EditText) findViewById(R.id.phone);

    }


    public void signup(View view)
    {


        String  str_username= username.getText().toString() + "@gmail.com";
        String  str_password= password.getText().toString();




        if(TextUtils.isEmpty(str_username)){
            Toast.makeText(this,"Please enter email",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(str_password)){
            Toast.makeText(this,"Please enter password",Toast.LENGTH_LONG).show();
            return;
        }

        progressDialog.setMessage("Registering User....");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(str_username,str_password).addOnCompleteListener(this,new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(Sign_Up.this,"Registered Successfully",Toast.LENGTH_SHORT).show();

                    finish();
                    startActivity(new Intent(getApplicationContext(),SetupProfile.class));

                }
                else
                {
                    Toast.makeText(Sign_Up.this,"TRY Again",Toast.LENGTH_SHORT).show();
                }
                progressDialog.dismiss();

            }
        });

    }


    public void login(View view)
    {

        Intent i = new Intent(this,Login.class);
        startActivity(i);


    }
}
