package com.shubhammm.wheelsonrent.Vehicles_Card_View;

/**
 * Created by Shubhamm Arora on 23-09-2018.
 */

public class Product {

    private int id;
    private String title;
    private String shortdesc;
    //private double rating;
    private String price;
    private int image;

    public Product(int id, String title, String shortdesc,  String price, int image) {
        this.id = id;
        this.title = title;
        this.shortdesc = shortdesc;
        this.price = price;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getShortdesc() {
        return shortdesc;
    }



    public String getPrice() {
        return price;
    }

    public int getImage() {
        return image;
    }



}
