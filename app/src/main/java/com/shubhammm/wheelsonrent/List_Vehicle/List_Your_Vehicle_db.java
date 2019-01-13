package com.shubhammm.wheelsonrent.List_Vehicle;

/**
 * Created by Shubhamm Arora on 25-06-2018.
 */

public class List_Your_Vehicle_db {

    public String name;
    public String phone_no;
    public String bike_name;
    public  String  model_year;

    public List_Your_Vehicle_db()
    {

    }

    public List_Your_Vehicle_db(String name, String  phone_no, String bike_name,String model_year) {
        this.name = name;
        this.phone_no = phone_no;
        this.bike_name = bike_name;
        this.model_year = model_year;

    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone_no;
    }

    public String getBike_name() {
        return bike_name;
    }

    public String getModel_year() {
        return model_year;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setPhone_no(String phone_no)
    {
        this.phone_no = phone_no;
    }

    public void setBike_name(String bike_name) {this.bike_name = bike_name;}

    public void setModel_year(String model_year) {this.model_year = model_year;}

}
