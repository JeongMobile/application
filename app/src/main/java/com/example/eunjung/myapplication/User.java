package com.example.eunjung.myapplication;


import android.database.Cursor;
import android.util.Log;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
public class User implements Serializable{

    public static final String USER_TABLE_NAME = "User", USER_ID = "userID"
            , USER_NAME = "userName", NIC_NAME = "nicName"
            , PASSWORD = "password"
            , PHONE = "phoneNumber", EMAIL = "Email";
    String userID;
    String userName, nicName;
    String password;
    String phoneNumber , Email;

    User(){

    }

    User (String userID, String userName, String nicName, String password, String phoneNumber, String Email){
        this.userID = userID;
        this.userName = userName;
        this.nicName = nicName;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.Email = Email;
    }
    User(Cursor cursor){
        userID = cursor.getString(0);
        userName = cursor.getString(1);
        nicName = cursor.getString(2);
        password = cursor.getString(3);
        phoneNumber = cursor.getString(4);
        Email = cursor.getString(5);
    }

}