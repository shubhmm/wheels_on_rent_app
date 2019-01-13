package com.shubhammm.wheelsonrent.Bike;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shubhammm.wheelsonrent.R;

public class Book_Bike extends AppCompatActivity {
    private ViewPager mViwePager;
    private LinearLayout mLinearLayout;
    private  SliderAdapter_b sliderAdapter;
    private TextView[] mDots;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book__bike);

        Window window = getWindow();
        //if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        window.setStatusBarColor(getColor(R.color.LoginActivityColor));

        mViwePager = (ViewPager)findViewById(R.id.slideViewPager);
        mLinearLayout = (LinearLayout)findViewById(R.id.dotsLayout);

        sliderAdapter = new SliderAdapter_b(this);
        mViwePager.setAdapter(sliderAdapter);
        addDotsIndicator(0);

        mViwePager.addOnPageChangeListener(viewListener);
    }

    public void addDotsIndicator(int position){

        mDots = new TextView[6];
        mLinearLayout.removeAllViews();

        for(int i=0; i <mDots.length;i++)
        {
            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226;"));
            mDots[i].setTextSize(45);
            mDots[i].setTextColor(getResources().getColor(R.color.yellow));

            mLinearLayout.addView(mDots[i]);
        }
        if (mDots.length>0)
        {
            mDots[position].setTextColor(getResources().getColor(R.color.white));
        }
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener(){
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDotsIndicator(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };


}
