package com.shubhammm.wheelsonrent.My_Bookings;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.shubhammm.wheelsonrent.R;

public class My_Booking extends AppCompatActivity {

    private RecyclerView post_list;
    private DatabaseReference mDatabase,myref;
   // private static  final String TAG = "My_Booking";
  //  DatabaseHelper mDatabaseHelper;
  //  private ListView mListView;
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;

    private  String userID , mybookings="My_Bookings";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my__booking);


        Window window = getWindow();
        //if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        window.setStatusBarColor(getColor(R.color.LoginActivityColor));

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();


      //  user = mAuth.getCurrentUser();
        userID = user.getUid();
        myref = FirebaseDatabase.getInstance().getReference().child(user.getUid());

        mDatabase = myref.child(mybookings);
       // mDatabase = myref.child("Book_Vehicle").child("My_Bookings");



        mDatabase.keepSynced(true);

        post_list=(RecyclerView)findViewById(R.id.recyclerView);
        post_list.setHasFixedSize(true);
        post_list.setLayoutManager(new LinearLayoutManager(My_Booking.this));

        Toast.makeText(My_Booking.this, "please Wait ! Loading...", Toast.LENGTH_SHORT).show();

    }


    @Override
    public void onStart(){
        super.onStart();


        FirebaseRecyclerAdapter<my_booking_post,My_Booking.PostHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter
                <my_booking_post,My_Booking.PostHolder>
                (my_booking_post.class,R.layout.my_booking_card,My_Booking.PostHolder.class,mDatabase){

            public void populateViewHolder(My_Booking.PostHolder viewHolder, my_booking_post model ,int position){

                viewHolder.setDate(model.getDate());
                viewHolder.setVehicle(model.getVehicle());
                viewHolder.setFrom(model.getFrom());
                viewHolder.setTo(model.getTo());
                viewHolder.setFare(model.getFare());
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
        public void setDate(String date){
            TextView post_date=(TextView)mView.findViewById(R.id.post_date);
            post_date.setText(date);
        }
        public void setVehicle(String vehicle){
            TextView post_vehicle=(TextView)mView.findViewById(R.id.post_vehicle);
            post_vehicle.setText(vehicle);
        }
        public void setFrom(String from){
            TextView post_from=(TextView)mView.findViewById(R.id.post_from);
            post_from.setText(from);
        }
        public void setTo(String to){
            TextView post_to=(TextView)mView.findViewById(R.id.post_to);
            post_to.setText(to);
        }
        public void setFare(String fare){
            TextView post_fare=(TextView)mView.findViewById(R.id.post_fare);
            post_fare.setText(fare);
        }


    }


}

/*
    mListView = (ListView)findViewById(R.id.List_View);

            populateListView();



private void populateListView() {
        Log.d(TAG,"populateListView : Displaying data in the ListView.");
        Cursor data = mDatabaseHelper.getData();
        ArrayList<String> listdata = new ArrayList<>();
        while(data.moveToNext()){
        listdata.add(data.getString(1));
        // listdata.add(data.getString(2));
        // listdata.add(data.getString(3));
        }
        ListAdapter adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,listdata);
        mListView.setAdapter(adapter);
        }

*/