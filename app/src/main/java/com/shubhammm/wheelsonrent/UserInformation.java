package com.shubhammm.wheelsonrent;

/**
 * Created by Shubhamm Arora on 02-06-2018.
 */

public class UserInformation {


    public String name;
    public String phone;
    public String regno;

    public UserInformation()
    {

    }

    public UserInformation(String name, String  phone, String regno) {
        this.name = name;
        this.phone = phone;
        this.regno = regno;

    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getRegno() {
        return regno;
    }



    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public void setRegno(String regno) {this.regno = regno;}


}
