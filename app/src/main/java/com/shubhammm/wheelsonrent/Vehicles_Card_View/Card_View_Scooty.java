package com.shubhammm.wheelsonrent.Vehicles_Card_View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Window;

import com.shubhammm.wheelsonrent.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shubhamm Arora on 23-09-2018.
 */

public class Card_View_Scooty extends AppCompatActivity {

private  Toolbar mToolbar;

    List<Product> productList;

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
                        "HONDA ACTIVA 110CC",
                        "Rs 5/Km additional fuel charges",
                        "Rs 25/hour",
                        R.drawable.activa_grey));

        productList.add(
                new Product(
                        1,
                        "HONDA ACTIVA 3G",
                        "Rs 5/Km additional fuel charges",
                        "Rs 25/hour",
                        R.drawable.activa_white));

        productList.add(
                new Product(
                        1,
                        "TVS JUPITER 110CC",
                        "Rs 5/Km additional fuel charges",
                        "Rs 25/hour",
                        R.drawable.jupiter));

        productList.add(
                new Product(
                        1,
                        "TVS WEGO ",
                        "Rs 5/Km additional fuel charges",
                        "Rs 25/hour",
                        R.drawable.wego_black));

        productList.add(
                new Product(
                        1,
                        "HERO MAESTRO 110CC",
                        "Rs 5/Km additional fuel charges",
                        "Rs 25/hour",
                        R.drawable.maestro));


        productList.add(
                new Product(
                        1,
                        "PIAGGIO VESPA",
                        "Rs 6/Km additional fuel charges",
                        "Rs 35/hour",
                        R.drawable.vespa));






        //creating recyclerview adapter
        ProductAdapter adapter = new ProductAdapter(this, productList);

        //setting adapter to recyclerview
        recyclerView.setAdapter(adapter);


    }
}
