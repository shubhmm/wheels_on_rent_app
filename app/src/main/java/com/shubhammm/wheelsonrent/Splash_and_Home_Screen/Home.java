package com.shubhammm.wheelsonrent.Splash_and_Home_Screen;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shubhammm.wheelsonrent.Feedback.Feedback;
import com.shubhammm.wheelsonrent.List_Vehicle.ListYourVehicle;
import com.shubhammm.wheelsonrent.My_Bookings.My_Booking;
import com.shubhammm.wheelsonrent.R;
import com.shubhammm.wheelsonrent.User.Login;
import com.shubhammm.wheelsonrent.User.Update_Profile;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private Toolbar mToolbar;
    private FirebaseAuth firebaseAuth;
    ViewFlipper v_flipper;
    FragmentTransaction fragmentTransaction;
    NavigationView navigationView;
    ImageView viewImage;
    TextView username;
    private FirebaseAuth mAuth;
    boolean doubleTap = false;
    private static final int CAMERA_REQUEST_CODE = 1;
    private final int PICK_IMAGE_REQUEST2 = 70;
    private static final int STORAGE_PERMISSION_CODE = 1;
    private Uri filePath;
    private DatabaseReference mDatabase,myref;
Context ctx = Home.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //  int images[] = {R.drawable.slided,R.drawable.slideb,R.drawable.slidec};



        //  v_flipper = (ViewFlipper) findViewById(R.id.flipper);

        mToolbar = (Toolbar) findViewById(R.id.nav_action);
        setSupportActionBar(mToolbar);

        mAuth = FirebaseAuth.getInstance();

        Window window = getWindow();
        //if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        window.setStatusBarColor(getColor(R.color.LoginActivityColor));
        //SharedPreferences spreferences = PreferenceManager.getDefaultSharedPreferences(this);
        // SharedPreferences.Editor editor = spreferences.edit();
        // String s_name = spreferences.getString(getString(R.string.date),"");
        //username.setText("" + s_name);


        mDrawerLayout = (DrawerLayout) findViewById(R.id.Drawerlayout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() == null) {
            // LOGIN ACTIVITY
            // finish();
            startActivity(new Intent(getApplicationContext(), Login.class));
        }


        // for(int i=0;i<images.length;i++) {
        //     FlipperImages(images[i]);
        // }


        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, new HomeFragment());
        fragmentTransaction.commit();
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.fragment_container, new HomeFragment());
                        fragmentTransaction.commit();
                        item.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.nav_customercare:
                        Intent inte = new Intent(getApplicationContext(), ContactUs.class);
                        startActivity(inte);
                        item.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        break;

                    case R.id.nav_mybookings:
                        Intent i = new Intent(getApplicationContext(), My_Booking.class);
                        startActivity(i);
                        item.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.nav_profile:
                        Intent k = new Intent(getApplicationContext(), Update_Profile.class);
                        startActivity(k);
                        item.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        break;

                    case R.id.nav_policies:
                        Intent p = new Intent(getApplicationContext(), policie.class);
                        startActivity(p);
                        /*Intent browser = new Intent(Intent.ACTION_VIEW, Uri.parse("https://wheelsonrentallahabad.wordpress.com/procedure-to-hire-a-bike/"));
                        startActivity(browser);
                        item.setChecked(true);
                        mDrawerLayout.closeDrawers();*/
                        break;

                    case R.id.nav_offers:
                        Toast.makeText(getApplicationContext(), "Scroll Down to check Offers !!!", Toast.LENGTH_LONG).show();
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.fragment_container, new HomeFragment());
                        fragmentTransaction.commit();
                        item.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        break;

                    case R.id.nav_listyourvehicle:
                        Intent in = new Intent(getApplicationContext(), ListYourVehicle.class);
                        startActivity(in);
                        item.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.nav_feedback:
                        Intent fd = new Intent(getApplicationContext(), Feedback.class);
                        startActivity(fd);
                        item.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        break;

                    case R.id.nav_aboutus:
                        Intent q = new Intent(getApplicationContext(), about.class);
                        startActivity(q);
                        /*Intent browserr = new Intent(Intent.ACTION_VIEW, Uri.parse("https://wheelsonrentallahabad.wordpress.com/about"));
                        startActivity(browserr);
                        item.setChecked(true);
                        mDrawerLayout.closeDrawers();*/
                        break;
                    case R.id.nav_termsncond:
                        Intent l = new Intent(getApplicationContext(), terms.class);
                        startActivity(l);
                        /*Intent browserr = new Intent(Intent.ACTION_VIEW, Uri.parse("https://wheelsonrentallahabad.wordpress.com/about"));
                        startActivity(browserr);
                        item.setChecked(true);
                        mDrawerLayout.closeDrawers();*/
                        break;

                    case R.id.nav_logout:
                        mAuth.signOut();
                        finish();
                        Intent intent = new Intent(getApplicationContext(), Login.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        item.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        break;

                }

                return true;
            }
        });


        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference().child(user.getUid());
        View headerView = navigationView.getHeaderView(0);
        username = (TextView)headerView.findViewById(R.id.user_namem);
        viewImage = (ImageView)headerView.findViewById(R.id.profile_photo);
        //username.setText("Hello : ");
        // username.setText("Helo");
        getProfile_pic();



        if (checkAndRequestPermissions()) {
            //If you have already permitted the permission
        }

    }



    private boolean checkAndRequestPermissions() {
        int permissionCONTACTS = ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_CONTACTS);


        int permissionMESSAGES = ContextCompat.checkSelfPermission(this,


                Manifest.permission.READ_EXTERNAL_STORAGE);



        List<String> listPermissionsNeeded = new ArrayList<>();
        if (permissionCONTACTS != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.READ_CONTACTS);
        }
        if (permissionMESSAGES != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this,
                    listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), STORAGE_PERMISSION_CODE);
            return false;
        }

        return true;
    }


    @Override    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case STORAGE_PERMISSION_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    //Permission Granted Successfully. Write working code here.
                } else {
                    //You did not accept the request can not use the functionality.
                }
                break;
        }
    }


    @Override
    public void onBackPressed() {
        if (doubleTap) {
            super.onBackPressed();


        } else {
            Toast.makeText(this, "Press Back once more to EXIT ", Toast.LENGTH_SHORT).show();
            doubleTap = true;
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                }
            }, 500);
        }
    }

    String a="Profile_pic";
    String b="profile_pic";
    String c = "User Info :";

    private void getProfile_pic(){


        myref=mDatabase.child(c);

        myref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String name = dataSnapshot.child("name").getValue().toString();
                String phone = dataSnapshot.child("phone").getValue().toString();
                username.setText("Hi,"+"\n"+name+"\n"+""+phone);

              //  String image = dataSnapshot.child("profile_pic").getValue().toString();
              //  Picasso.with(ctx).load(image).into(viewImage);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }
/*
    public void FlipperImages(int image)
    {
        ImageView imageview = new ImageView(this);
        imageview.setBackgroundResource(image);

        v_flipper.addView(imageview);
        v_flipper.setFlipInterval(2000);
        v_flipper.setAutoStart(true);

        v_flipper.setInAnimation(this,android.R.anim.slide_in_left);
        v_flipper.setOutAnimation(this,android.R.anim.slide_out_right);

    }
*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }




}



   /*   final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};


        AlertDialog.Builder builder = new AlertDialog.Builder(Home.this);

        builder.setTitle("Add Photo!");

       builder.setItems(options, new DialogInterface.OnClickListener() {

            @Override

            public void onClick(DialogInterface dialog, int item) {

                if (options[item].equals("Take Photo"))

                {

                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                    File f = new File(android.os.Environment.getExternalStorageDirectory(), "temp.jpg");

                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));

                    startActivityForResult(intent, 1);

                } else if (options[item].equals("Choose from Gallery"))

                {

                    Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                    startActivityForResult(intent, 2);


                } else if (options[item].equals("Cancel")) {

                    dialog.dismiss();

                }

            }

        });

        builder.show();




    }


    @Override

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);



        if (resultCode == RESULT_OK) {

            if (requestCode == 1) {

                File f = new File(Environment.getExternalStorageDirectory().toString());

                for (File temp : f.listFiles()) {

                    if (temp.getName().equals("temp.jpg")) {

                        f = temp;

                        break;

                    }

                }

                try {

                    Bitmap bitmap;

                    BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();


                    bitmap = BitmapFactory.decodeFile(f.getAbsolutePath(),

                            bitmapOptions);


                    viewImage.setImageBitmap(bitmap);


                    String path = android.os.Environment.getExternalStorageDirectory() + File.separator + "Phoenix" + File.separator + "default";f.delete();

                    OutputStream outFile = null;

                    File file = new File(path, String.valueOf(System.currentTimeMillis()) + ".jpg");

                    try {

                        outFile = new FileOutputStream(file);

                        bitmap.compress(Bitmap.CompressFormat.JPEG, 85, outFile);

                        outFile.flush();

                        outFile.close();

                    } catch (FileNotFoundException e) {

                        e.printStackTrace();

                    } catch (IOException e) {

                        e.printStackTrace();

                    } catch (Exception e) {

                        e.printStackTrace();

                    }

                } catch (Exception e) {

                    e.printStackTrace();

                }



            }

            if(resultCode==RESULT_OK && requestCode == 2) {


                Uri selectedImage = data.getData();

                String[] filePath = {MediaStore.Images.Media.DATA};

                Cursor c = getContentResolver().query(selectedImage, filePath, null, null, null);

                c.moveToFirst();

                int columnIndex = c.getColumnIndex(filePath[0]);

                String picturePath = c.getString(columnIndex);

                c.close();

                Bitmap thumbnail = (BitmapFactory.decodeFile(picturePath));

              //  Log.w("path of image from gallery......******************.........", picturePath + "");

                viewImage.setImageBitmap(thumbnail);

            }

        }
*/



