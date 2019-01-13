package com.shubhammm.wheelsonrent.User;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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


public class SetupProfile extends AppCompatActivity {

    EditText name,phone,regno;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference,myref ,mDatabase;
    private SharedPreferences sPreferences;
    private SharedPreferences.Editor sEditor;
    private Button upload_dl ;
    private static final int  CAMERA_REQUEST_CODE=1;


    private Button BtnChooseDL,BtnChooseID, BtnUpload;
    private ImageView ImageView;
    private ImageView dl_image,id_image;

    private Uri filePath1,filePath2;
   private FirebaseStorage storage;
   private StorageReference storageReference;

    private final int PICK_IMAGE_REQUEST = 70;
    private final int PICK_IMAGE_REQUEST2 = 71;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_profile);

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        storage= FirebaseStorage.getInstance();
        storageReference = storage.getReference();


        name = (EditText) findViewById(R.id.name);
        phone = (EditText) findViewById(R.id.phone);
        regno = (EditText) findViewById(R.id.regno);

        BtnChooseDL = (Button)findViewById(R.id.dl);
        BtnChooseID = (Button)findViewById(R.id.collg_id);
        BtnUpload = (Button)findViewById(R.id.Upload);

        dl_image = (ImageView)findViewById(R.id.image_dl);
        id_image = (ImageView)findViewById(R.id.image_id);


        // sPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        //sEditor = sPreferences.edit();

        BtnChooseDL.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                chooseImage_dl();

            }
        });

        BtnChooseID.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                chooseImage_id();

            }
        });

        BtnUpload.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                uploadImage();

            }
        });


    }

    private void chooseImage_dl() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    private void chooseImage_id() {
        Intent intent1 = new Intent();
        intent1.setType("image/*");
        intent1.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent1, "Select Picture"), PICK_IMAGE_REQUEST2);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null )
        {
            filePath1 = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath1);
                dl_image.setImageBitmap(bitmap);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        if(requestCode == PICK_IMAGE_REQUEST2 && resultCode == RESULT_OK
                && data != null && data.getData() != null )
        {
            filePath2 = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath2);
                id_image.setImageBitmap(bitmap);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }


    }





    private void uploadImage() {

        FirebaseUser user = firebaseAuth.getCurrentUser();

        myref = databaseReference.child(user.getUid());

        String uid = user.getUid();

        if(filePath1 != null)
        {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            StorageReference ref = storageReference.child(uid+"/" + UUID.randomUUID().toString());
            ref.putFile(filePath1)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(SetupProfile.this, "Uploaded", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(SetupProfile.this, "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
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

        if(filePath2 != null)
        {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            StorageReference ref = storageReference.child(uid+"/"+ UUID.randomUUID().toString());
            ref.putFile(filePath2)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(SetupProfile.this, "Uploaded", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(SetupProfile.this, "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
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


        Toast.makeText(this,"Information Updated ...",Toast.LENGTH_SHORT).show();
        finish();
        Intent intent = new Intent(SetupProfile.this,Home.class);
       // intent.putExtra("NAME",str_name);
       // intent.putExtra("PHONE NO.",str_phone);
       // intent.putExtra("REGISTRATION NO.",str_regno);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);

    }

}














/*




 Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, CAMERA_REQUEST_CODE);


    public void upload_dl(View v) {

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==CAMERA_REQUEST_CODE && resultCode==RESULT_OK){
            Uri uri = data.getData();

            final StorageReference filepath = storage.child("Driving License").child(uri.getLastPathSegment());
            filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(SetupProfile.this,"UPLOAD Finished ...",Toast.LENGTH_SHORT).show();


                }
            });
        }

    }
*/