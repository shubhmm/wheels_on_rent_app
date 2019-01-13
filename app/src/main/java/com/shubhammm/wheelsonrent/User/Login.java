package com.shubhammm.wheelsonrent.User;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
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


public class Login extends AppCompatActivity {


    EditText username,password;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    boolean doubleTap=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Window window = getWindow();
        //if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            window.setStatusBarColor(getColor(R.color.LoginActivityColor));

        //else{
         //   window.setStatusBarColor(getResources()getColor(R.color.LoginActivityColor));
       // }

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


    }

    public void login(View view)
    {

        String  str_email= username.getText().toString() + "@gmail.com";
        String  str_password= password.getText().toString();


        progressDialog.setMessage("Logging in....");
        progressDialog.show();


        firebaseAuth.signInWithEmailAndPassword(str_email,str_password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful())
                {
                    Toast.makeText(Login.this,"Logged In Successfully",Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(new Intent(getApplicationContext(),Home.class));
                }

                else{
                    Toast.makeText(Login.this,"Username or Password INCORRECT",Toast.LENGTH_SHORT).show();
                }

                progressDialog.dismiss();

            }
        });

    }



    public void signup(View view)
    {

        Intent i = new Intent(this,Sign_Up.class);
        startActivity(i);


    }
}
