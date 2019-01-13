package com.shubhammm.wheelsonrent.Splash_and_Home_Screen;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shubhammm.wheelsonrent.R;
import com.shubhammm.wheelsonrent.Vehicles_Card_View.List_Bike;
import com.shubhammm.wheelsonrent.Vehicles_Card_View.List_Scooty;
import com.shubhammm.wheelsonrent.post_firebase;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    ViewFlipper v_flipper;
    ViewFlipper v_flipper2;
    private RecyclerView post_list;
    private DatabaseReference mDatabase, mDatabase2;
    private Context mCtx;
    FragmentTransaction fragmentTransaction;


     String image_url[] = new String[5];


    String c = "Image_url";
    String x,y,z;

    public HomeFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myFragmentView =  inflater.inflate(R.layout.fragment_home, container, false);

       // int images[] = {R.drawable.slided,R.drawable.slideb,R.drawable.slidec};
       // int images2[] = {R.drawable.slideaa,R.drawable.slidebb,R.drawable.slidecc,R.drawable.slidedd};

       // fetch_url();
        v_flipper = (ViewFlipper)myFragmentView.findViewById(R.id.flipper);


        mDatabase2 = FirebaseDatabase.getInstance().getReference().child("Image_url");

        mDatabase2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                image_url[0] = dataSnapshot.child("url1").getValue().toString();
                image_url[1] = dataSnapshot.child("url2").getValue().toString();
                image_url[2] = dataSnapshot.child("url3").getValue().toString();
                image_url[3] = dataSnapshot.child("url4").getValue().toString();
                image_url[4] = dataSnapshot.child("url5").getValue().toString();
              //  x = dataSnapshot.child("url1").getValue().toString();
              //  y = dataSnapshot.child("url2").getValue().toString();
                for(int i=0;i<image_url.length;i++) {

                    FlipperImages(image_url[i]);

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });








      //  v_flipper2 = (ViewFlipper)myFragmentView.findViewById(R.id.flipper2);





        Button buttona = (Button)myFragmentView.findViewById(R.id.book_bike);
        buttona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              //  Fragment fragment = new tasks();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container,new List_Bike());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

               // Intent i = new Intent(getActivity(),List_Bike.class);
               // startActivity(i);
            }
        });
        Button buttonb = (Button)myFragmentView.findViewById(R.id.book_scooty);
        buttonb.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container,new List_Scooty());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }

        });





    //    mDatabase = FirebaseDatabase.getInstance().getReference().child("POST");
        mDatabase = FirebaseDatabase.getInstance().getReference("Post");



       mDatabase.keepSynced(true);

        post_list=(RecyclerView)myFragmentView.findViewById(R.id.recyclerView);
        post_list.setHasFixedSize(true);
    //    post_list.setLayoutManager(new LinearLayoutManager(HomeFragment.this.getContext()));
//        post_list.setLayoutManager(new LinearLayoutManager(HomeFragment.this.getActivity()));
        post_list.setLayoutManager(new LinearLayoutManager(getActivity()));


       // Toast.makeText(getActivity(), "please Wait ! Loading...", Toast.LENGTH_SHORT).show();


        /*
        for(int i=0;i<images2.length;i++) {
            FlipperImages2(images2[i]);
        }*/
        return  myFragmentView;
    }


/*

    private void fetch_url(){

        mDatabase2 = FirebaseDatabase.getInstance().getReference("Image_url");

        mDatabase2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

              //  image_url[0] = dataSnapshot.child("url1").getValue().toString();
              //  image_url[1] = dataSnapshot.child("url2").getValue().toString();
                x = dataSnapshot.child("url1").getValue().toString();
                y = dataSnapshot.child("url2").getValue().toString();
                //  String image = dataSnapshot.child("profile_pic").getValue().toString();
                //  Picasso.with(ctx).load(image).into(viewImage);
                image_url[0]=x;
                image_url[1]=y;


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
*/




    @Override
    public void onStart(){
        super.onStart();


        FirebaseRecyclerAdapter<post_firebase,HomeFragment.PostHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter
                <post_firebase,HomeFragment.PostHolder>
                (post_firebase.class,R.layout.firebase_post,HomeFragment.PostHolder.class,mDatabase){

            public void populateViewHolder(HomeFragment.PostHolder viewHolder, post_firebase model ,int position){
                viewHolder.setTitle(model.getTitle());
                viewHolder.setDesc(model.getDesc());
                viewHolder.setImage(getContext(),model.getImage());

/*
                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent x = new Intent(getActivity(),Book_Vehicle.class);
                        startActivity(x);
                    }
                });
*/
            }
        };

        post_list.setAdapter(firebaseRecyclerAdapter);

    }


    public static class PostHolder extends RecyclerView.ViewHolder{
        View mView;
        public PostHolder(View itemView){
            super(itemView);
            mView=itemView;
        }
        public void setTitle(String title){
            TextView post_title=(TextView)mView.findViewById(R.id.post_title);
            post_title.setText(title);
        }
        public void setDesc(String desc){
            TextView post_desc=(TextView)mView.findViewById(R.id.post_desc);
            post_desc.setText(desc);
        }

        public void setImage(Context ctx, String image){
            ImageView post_Image=(ImageView)mView.findViewById(R.id.post_image);
            Picasso.with(ctx).load(image).into(post_Image);
        }

    }

    public void FlipperImages(String image)
    {
        ImageView imageview = new ImageView(getActivity());
       // imageview.setBackgroundResource(image);

        Picasso.with(getContext()).load(image).into(imageview);

        v_flipper.addView(imageview);

        v_flipper.setAutoStart(true);
        v_flipper.setFlipInterval(2800);
        v_flipper.startFlipping();
        v_flipper.setInAnimation(getActivity(),android.R.anim.slide_in_left);
        v_flipper.setOutAnimation(getActivity(),android.R.anim.slide_out_right);

    }


/*
    public void FlipperImages2(int image)
    {
        ImageView imageview = new ImageView(getActivity());
        imageview.setBackgroundResource(image);

        v_flipper2.addView(imageview);
        v_flipper2.setFlipInterval(4200);
        v_flipper2.setAutoStart(true);

        v_flipper2.setInAnimation(getActivity(),android.R.anim.slide_in_left);
        v_flipper2.setOutAnimation(getActivity(),android.R.anim.slide_out_right);

    }
*/

}

/*

        final ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                String desc, image="", title;
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {

                    desc = postSnapshot.child("desc").getValue().toString();
                    image = postSnapshot.child("image").getValue().toString();
                    title = postSnapshot.child("title").getValue().toString();
                    System.out.println("check "+desc+ " : "+ title);
                    break;

                }
                getBitmapFromURL(image);
                //Post post = dataSnapshot.getValue(Post.class);
                // ...
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
               // Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        };
        mDatabase.addListenerForSingleValueEvent(postListener);

    public static Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    } // Author: silentnuke
*/