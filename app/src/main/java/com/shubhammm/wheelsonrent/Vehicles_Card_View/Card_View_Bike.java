package com.shubhammm.wheelsonrent.Vehicles_Card_View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Window;

import com.shubhammm.wheelsonrent.R;

import java.util.ArrayList;
import java.util.List;

public class Card_View_Bike extends AppCompatActivity {
    List<Product> productList;
private Toolbar mToolbar;
    //the recyclerview
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card__view__bike);



        Window window = getWindow();
        //if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        window.setStatusBarColor(getColor(R.color.LoginActivityColor));

        mToolbar = (Toolbar) findViewById(R.id.nav_action);
        setSupportActionBar(mToolbar);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //initializing the productlist
        productList = new ArrayList<>();


        //adding some items to our list
        productList.add(
                new Product(
                        1,
                        "TVS APACHE RTR-180",
                        "Rs 6/Km additional fuel",
                        "Rs 30/hour",
                        R.drawable.apache));

        productList.add(
                new Product(
                        1,
                        "BAJAJ PULSAR 180CC",
                        "Rs 6/Km additional fuel charges",
                        "Rs 30/hour",
                        R.drawable.pulsar_blue));


        productList.add(
                new Product(
                        1,
                        "BAJAJ PULSAR 135-DTSi",
                        "Rs 5.5/Km additional fuel charges",
                        "Rs 30/hour",
                        R.drawable.pulsar_black));


        productList.add(
                new Product(
                        1,
                        "HERO PASSION PRO-V",
                        "Rs 5/Km additional fuel charges",
                        "Rs 25/hour",
                        R.drawable.passion));

        productList.add(
                new Product(
                        1,
                        "BAJAJ DISCOVER 135CC",
                        "Rs 5/Km additional fuel charges",
                        "Rs 25/hour",
                        R.drawable.discover));



        productList.add(
                new Product(
                        1,
                        "YAMAHA FZ-S",
                        "Rs 6/Km additional fuel charges",
                        "Rs 30/hour",
                        R.drawable.fz));



        productList.add(
                new Product(
                        1,
                        "ROYAL ENFIELD CLASSIC-350",
                        "Rs 7/Km additional fuel charges",
                        "Rs 35/hour",
                        R.drawable.classic350_white));
        productList.add(
                new Product(
                        1,
                        "BAJAJ AVENGER 220",
                        "Rs 7/Km additional fuel charges",
                        "Rs 35/hour",
                        R.drawable.avenger));

        productList.add(
                new Product(
                        1,
                        "KTM-RC 390",
                        "Rs 8/Km additional fuel charges",
                        "Rs 40/hour",
                        R.drawable.ktm));

        productList.add(
                new Product(
                        1,
                        "SUZUKI INTRUDER",
                        "Rs 7/Km additional fuel charges",
                        "Rs 35/hour",
                        R.drawable.intruder));


        //creating recyclerview adapter
        ProductAdapter adapter = new ProductAdapter(this, productList);

        //setting adapter to recyclerview
        recyclerView.setAdapter(adapter);


    }
}
