package com.shubhammm.wheelsonrent.Bike;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.shubhammm.wheelsonrent.Book_Vehicle_pkg.Book_Vehicle;
import com.shubhammm.wheelsonrent.R;

/**
 * Created by Shubhamm Arora on 29-06-2018.
 */

public class SliderAdapter_b extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public  SliderAdapter_b(Context context){
        this.context = context;


    }

    public  int[] slide_images = {
            R.drawable.pulsar_blue,
            R.drawable.apache_white,
            R.drawable.enfield_white,
            R.drawable.ktm_white,
            R.drawable.avenger_black,
            R.drawable.fzs_blue,
    };
    @Override
    public int getCount() {
        return slide_images.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {

        return  view == (RelativeLayout)object ;
    }


    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        layoutInflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

        View view = layoutInflater.inflate(R.layout.slide_layout, container,false);
        ImageView slideImageView = (ImageView) view.findViewById(R.id.slide_image);



        slideImageView.setImageResource(slide_images[position]);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position == 0) {
                    Intent i =new  Intent(context,Book_Vehicle.class);
                    i.putExtra("NAME","PULSAR");
                    context.startActivity(i);
                }else if(position==1){
                    Intent i =new  Intent(context,Book_Vehicle.class);
                    i.putExtra("NAME","APACHE");
                    context.startActivity(i);
                }
                else if(position==2){
                    Intent i =new  Intent(context,Book_Vehicle.class);
                    i.putExtra("NAME","ROYAL-ENFIELD");
                    context.startActivity(i);
                }

                if (position == 3) {
                    Intent i =new  Intent(context,Book_Vehicle.class);
                    i.putExtra("NAME","KTM");
                    context.startActivity(i);
                }else if(position==4){
                    Intent i =new  Intent(context,Book_Vehicle.class);
                    i.putExtra("NAME","AVENGER");
                    context.startActivity(i);
                }
                else if(position==5){
                    Intent i =new  Intent(context,Book_Vehicle.class);
                    i.putExtra("NAME","YAMAHA-FZ");
                    context.startActivity(i);
                }
            }
        });
        container.addView(view);

        return view;

    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout)object);
    }


}
