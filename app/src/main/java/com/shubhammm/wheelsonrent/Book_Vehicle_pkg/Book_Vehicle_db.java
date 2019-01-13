package com.shubhammm.wheelsonrent.Book_Vehicle_pkg;

/**
 * Created by Shubhamm Arora on 25-06-2018.
 */

public class Book_Vehicle_db {

    public  String vehicle;
    public String date;
    public String s_time;
    public String d_time;
    public  String  contact_no;
    public  String  promocode;

    public Book_Vehicle_db(){

    }

    public Book_Vehicle_db(String vehicle,String date,String s_time,String d_time,String contact_no,String promocode){

        this.vehicle = vehicle;
        this.date = date;
        this.s_time = s_time;
        this.d_time = d_time;
        this.contact_no = contact_no;
        this.promocode = promocode;

    }

    public String getVehicle() {
        return vehicle;
    }

    public String getDate() {
        return date;
    }

    public String getS_time() {
        return s_time;
    }

    public String getD_time() {
        return d_time;
    }

    public String getContact_no() {
        return contact_no;
    }

    public String getPromocode() {
        return promocode;
    }



    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    public void setDate(String date) {this.date = date;}

    public void setS_time(String s_time)
    {
        this.s_time = s_time;
    }

    public void setD_time(String d_time) {this.d_time = d_time;}

    public void setContact_no(String contact_no) {this.contact_no = contact_no;}

    public void setPromocode(String promocode) {this.promocode = promocode;}

}
