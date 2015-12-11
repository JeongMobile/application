package com.example.eunjung.myapplication;


import android.database.Cursor;
import android.util.Log;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
public class GroupInfo implements Serializable{

    public static final String GROUP_TABLE_NAME = "GroupInfo", GROUP_ID = "groupID"
            , GROUP_NAME = "groupName", USER_ID = "userID"
            , GROUP__BY = "groupBy";
    int userID, groupID;
    String groupName, groupBy;

    GroupInfo (int groupID, String groupName, int userID, String groupBy){
        this.groupID = groupID;
        this.groupName = groupName;
        this.userID = userID;
        this.groupBy = groupBy;
    }

    GroupInfo(Cursor cursor){
        this.groupID = cursor.getInt(0);
        this.groupName = cursor.getString(1);
        this.userID = cursor.getInt(2);
        this.groupBy = cursor.getString(3);
    }
}
