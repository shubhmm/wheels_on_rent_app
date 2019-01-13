package com.shubhammm.wheelsonrent.Scooty;

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

public class SliderAdapter extends PagerAdapter{

    Context context;
    LayoutInflater layoutInflater;

    public  SliderAdapter(Context context){
        this.context = context;


    }

    public  int[] slide_images = {
            R.drawable.activa_white,
            R.drawable.grey_activa,
            R.drawable.wego_black,
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
                    i.putExtra("NAME","ACTIVA-white");
                    context.startActivity(i);

                }else if(position==1){
                    Intent i =new  Intent(context,Book_Vehicle.class);
                    i.putExtra("NAME","ACTIVA-grey");
                    context.startActivity(i);
                }
                else if(position==2){
                    Intent i =new  Intent(context,Book_Vehicle.class);
                    i.putExtra("NAME","WEGO-black");
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
