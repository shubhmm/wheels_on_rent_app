package com.shubhammm.wheelsonrent.My_Bookings;

/**
 * Created by Shubhamm Arora on 29-09-2018.
 */

public class my_booking_post {
    private String date;
    private String vehicle;
    private String from;
    private String to;
    private String fare;



    public my_booking_post(){

    }

    public my_booking_post(String date, String vehicle, String from , String to , String fare) {
        this.date =  date;
        this.vehicle = vehicle;
        this.from = from;
        this.to = to;
        this.fare = fare;

    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = "DATE: "+ date;
    }

    public String getVehicle() {
        return  vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = "Vehicle Booked: " +vehicle;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = "Time(from): " + from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = "Time(to): " +to;
    }

    public String getFare() {
        return fare;
    }

    public void setFare(String fare) {
        this.fare = "Total Fare: " +fare;
    }
}
