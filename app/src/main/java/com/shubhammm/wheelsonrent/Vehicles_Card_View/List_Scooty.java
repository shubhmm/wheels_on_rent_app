package com.shubhammm.wheelsonrent.Vehicles_Card_View;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.shubhammm.wheelsonrent.Book_Vehicle_pkg.Book_Vehicle;
import com.shubhammm.wheelsonrent.R;
import com.shubhammm.wheelsonrent.post_firebase;
import com.squareup.picasso.Picasso;

/**
 * Created by Shubhamm Arora on 27-09-2018.
 */

public class List_Scooty extends Fragment {
    private RecyclerView post_list;
    private DatabaseReference mDatabase;
    FragmentTransaction fragmentTransaction;

    public void List_Scooty(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //  return inflater.inflate(R.layout.fragment_list__bike, container, false);

        View myFragmentView =  inflater.inflate(R.layout.fragment_list__bike, container, false);

        mDatabase = FirebaseDatabase.getInstance().getReference("List_Scooty");



        mDatabase.keepSynced(true);

        post_list=(RecyclerView)myFragmentView.findViewById(R.id.recyclerView);
        post_list.setHasFixedSize(true);
        //    post_list.setLayoutManager(new LinearLayoutManager(HomeFragment.this.getContext()));
//        post_list.setLayoutManager(new LinearLayoutManager(HomeFragment.this.getActivity()));
        post_list.setLayoutManager(new LinearLayoutManager(getActivity()));


      //  Toast.makeText(getActivity(), "please Wait ! Loading...", Toast.LENGTH_SHORT).show();


        /*
        for(int i=0;i<images2.length;i++) {
            FlipperImages2(images2[i]);
        }*/
        return  myFragmentView;


    }

    @Override
    public void onStart(){
        super.onStart();


        FirebaseRecyclerAdapter<post_firebase,List_Scooty.PostHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter
                <post_firebase,List_Scooty.PostHolder>
                (post_firebase.class,R.layout.firebase_post,List_Scooty.PostHolder.class,mDatabase){

            public void populateViewHolder(List_Scooty.PostHolder viewHolder, post_firebase model ,int position){
                viewHolder.setTitle(model.getTitle());
                viewHolder.setDesc(model.getDesc());
                viewHolder.setImage(getContext(),model.getImage());


                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        Intent x = new Intent(getActivity(),Book_Vehicle.class);
                        startActivity(x);
                    }
                });

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

}
