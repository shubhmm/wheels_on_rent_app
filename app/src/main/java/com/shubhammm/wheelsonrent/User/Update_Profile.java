package com.shubhammm.wheelsonrent.User;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.shubhammm.wheelsonrent.R;
import com.shubhammm.wheelsonrent.Splash_and_Home_Screen.Home;
import com.shubhammm.wheelsonrent.UserInformation;

import java.io.IOException;
import java.util.UUID;

public class Update_Profile extends AppCompatActivity {


    EditText name, phone, regno;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference, myref, mDatabase;
    private Button select;
    private ImageView Profile_pic;

    private Uri filePath1;
    private FirebaseStorage storage;
    private StorageReference storageReference;

    private final int PICK_IMAGE_REQUEST = 70;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update__profile);

        Window window = getWindow();
        //if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        window.setStatusBarColor(getColor(R.color.LoginActivityColor));

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();


        name = (EditText) findViewById(R.id.name);
        phone = (EditText) findViewById(R.id.phone);
        regno = (EditText) findViewById(R.id.regno);

        select = (Button) findViewById(R.id.select);


        Profile_pic = (ImageView) findViewById(R.id.profile_pic);

        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choose_image();

            }
        });


    }

    public void choose_image() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            filePath1 = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath1);
                Profile_pic.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void submit(View view) {

        String str_name = name.getText().toString().trim();
        String str_phone = phone.getText().toString().trim();
        String str_regno = regno.getText().toString().trim();

        // sEditor.putString(getString(R.string.date),str_name);
        // sEditor.commit();


        UserInformation userInformation = new UserInformation(str_name,str_phone,str_regno);

        FirebaseUser user = firebaseAuth.getCurrentUser();

        myref = databaseReference.child(user.getUid());
        myref.child("User Info :").setValue(userInformation);
        mDatabase=databaseReference.child(user.getUid()).child("Profile_pic");
        String uid = user.getUid();

        if(filePath1 != null)
        {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            StorageReference ref = storageReference.child(uid+"/"+ UUID.randomUUID().toString());
            ref.putFile(filePath1)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            String image_url =taskSnapshot.getDownloadUrl().toString();
                            Profile_url pic_url = new Profile_url(image_url);
                            mDatabase.setValue(pic_url);

                           // Toast.makeText(Update_Profile.this, "Uploaded", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(Update_Profile.this, "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage("Uploaded "+(int)progress+"%");
                        }
                    });
        }


        Toast.makeText(this,"Information Updated ...",Toast.LENGTH_SHORT).show();
        finish();
        Intent intent = new Intent(Update_Profile.this,Home.class);
        // intent.putExtra("NAME",str_name);
        // intent.putExtra("PHONE NO.",str_phone);
        // intent.putExtra("REGISTRATION NO.",str_regno);
      //  intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);

    }

}
