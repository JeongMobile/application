package com.example.eunjung.myapplication;


import android.database.Cursor;
import android.util.Log;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TodoLists implements Serializable{

    public static final String TODO_TABLE_NAME = "TodoLists", LIST_INDEX = "listIndex"
            , CONTENT = "content", START_DATE = "startDate", END_DATE = "endDate"
            , IMPROTANCE = "improtance", DETAIL_CONTENTS = "detailContents", STATE = "state";
    int listIndex;
    String content, startDate, endDate;
    int importance;
    String detailContents;
    int state;

    TodoLists (int listIndex, String content, String startDate, String endDate, int importance,
               String detailContents, int state){
        this.listIndex = listIndex;
        this.content = content;
        this.startDate = startDate;
        this.endDate = endDate;
        this.importance = importance;
        this.detailContents = detailContents;
        this.state = state;
    }
    TodoLists(Cursor cursor){
        this.listIndex = cursor.getInt(0);
        this.content = cursor.getString(1);
        this.startDate = cursor.getString(2);
        this.endDate = cursor.getString(3);
        this.importance = cursor.getInt(4);
        this.detailContents = cursor.getString(5);
        this.state = cursor.getInt(6);
    }
}
