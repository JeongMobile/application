package com.example.eunjung.myapplication;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.eunjung.myapplication.GroupInfo;
import com.example.eunjung.myapplication.TodoLists;
import com.example.eunjung.myapplication.User;

import java.util.List;

public class DBManager {

    private static final String DATABASE_NAME = "inner.db";

    private static final int DATABASE_VERSION = 1;
    public static SQLiteDatabase mDB;
    private DatabaseHelper mDBHelper;
    private Context mCtx;

    public DBManager(Context context){
        this.mCtx = context;
    }

    private class DatabaseHelper extends SQLiteOpenHelper {
        public DatabaseHelper(Context context, String name,
                              SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table "+ User.USER_TABLE_NAME+"("
                    +User.USER_ID+" text primary key, "    //0
                    +User.USER_NAME+" text not null , "                     //1
                    +User.NIC_NAME+" text not null , "                      //2
                    +User.PASSWORD+" text not null , "                      //3
                    +User.PHONE+" text not null , "                         //4
                    +User.EMAIL+" text not null ); ");                      //12
            db.execSQL("create table "+ GroupInfo.GROUP_TABLE_NAME+"("
                    +GroupInfo.GROUP_ID+" integer primary key autoincrement, "  //0
                    +GroupInfo.GROUP_NAME+" text not null , "                   //1
                    +GroupInfo.USER_ID+" text not null , "                   //2
                    +GroupInfo.GROUP__BY+" text); ");                           //3
            db.execSQL("create table "+TodoLists.TODO_TABLE_NAME+"("
                    + TodoLists.LIST_INDEX+" integer primary key, "            //0
                    +TodoLists.CONTENT+" text, "                              //1
                    +TodoLists.START_DATE+" datetime, "                         //2
                    +TodoLists.END_DATE+" datetime , "                          //3
                    +TodoLists.IMPROTANCE+" integer , "                         //4
                    +TodoLists.DETAIL_CONTENTS+" text , "                       //5
                    +TodoLists.STATE+" integer);"                              //6
                    );                           //7
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS "+ User.USER_TABLE_NAME);
            db.execSQL("DROP TABLE IF EXISTS "+ GroupInfo.GROUP_TABLE_NAME);
            db.execSQL("DROP TABLE IF EXISTS "+ TodoLists.TODO_TABLE_NAME);
            onCreate(db);
        }
    }
    public DBManager open() throws SQLException {
        mDBHelper = new DatabaseHelper(mCtx, DATABASE_NAME, null, DATABASE_VERSION);
        mDB = mDBHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        mDB.close();
    }

}
