package com.example.eunjung.myapplication;


import android.content.Context;
import android.database.Cursor;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class DAO {

    public static User getUser(Context context) {

        DBManager mDBHelper = new DBManager(context);
        User myUser = null;
        Cursor cursor = mDBHelper.open().mDB.rawQuery("select * from " + User.USER_TABLE_NAME, null);
        while (cursor.moveToNext()) {
            myUser = new User(cursor);
        }

        cursor.close();
        mDBHelper.close();
        return myUser;
    }

    public static ArrayList<String> getGroupName(Context context, String userID) {

        ArrayList<String> groupName = new ArrayList<>();

        DBManager mDBHelper = new DBManager(context);

        groupName.clear();
        Cursor cursor = mDBHelper.open().mDB.rawQuery("select " + GroupInfo.GROUP_NAME + " from " + GroupInfo.GROUP_TABLE_NAME + " where " + GroupInfo.USER_ID + " = " + userID + ";", null);
        while (cursor.moveToNext()) {
            groupName.add(cursor.getString(0));
        }

        cursor.close();
        mDBHelper.close();
        return groupName;
    }


    public static GroupInfo getGroup(Context context) {

        DBManager mDBHelper = new DBManager(context);
        GroupInfo group = null;
        Cursor cursor = mDBHelper.open().mDB.rawQuery("select * from " + GroupInfo.GROUP_TABLE_NAME, null);
        while (cursor.moveToNext()) {
            group = new GroupInfo(cursor);
        }

        cursor.close();
        mDBHelper.close();
        return group;
    }

    public static TodoLists getTodo(Context context) {

        DBManager mDBHelper = new DBManager(context);
        TodoLists myTodo = null;
        Cursor cursor = mDBHelper.open().mDB.rawQuery("select * from '" + TodoLists.TODO_TABLE_NAME + "'" , null);
        while (cursor.moveToNext()) {
            myTodo = new TodoLists(cursor);
        }

        cursor.close();
        mDBHelper.close();
        return myTodo;
    }

    public static void insertUserData(Context context, User user) {
        DBManager mDBHelper = new DBManager(context);
        mDBHelper.open().mDB.execSQL("insert into '" + User.USER_TABLE_NAME + "' values ( '" + user.userID + "','" + user.userName + "','" + user.nicName + "','" + user.password + "','" + user.phoneNumber + "','" + user.Email + "');");
        mDBHelper.close();
    }

    public static void insertGroupData(Context context, GroupInfo group) {
        DBManager mDBHelper = new DBManager(context);
        mDBHelper.open().mDB.execSQL("insert into '" + GroupInfo.GROUP_TABLE_NAME + "' values ('" + group.groupID + "','" + group.groupName + "','" + group.userID + "','" + group.groupBy + "');");
        mDBHelper.close();
    }

    public static void insertToDoData(Context context, TodoLists todo) {
        DBManager mDBHelper = new DBManager(context);
        mDBHelper.open().mDB.execSQL("insert into '" + TodoLists.TODO_TABLE_NAME + "' values ('" + todo.listIndex + "','" + todo.content + "','" + todo.startDate + "','" + todo.endDate + "','" + todo.importance + "','" + todo.detailContents + "','" + todo.state + "');");
        mDBHelper.close();
    }

    public static void deleteToDoData(Context context, TodoLists todo) {
        DBManager mDBHelper = new DBManager(context);
        mDBHelper.open().mDB.execSQL("delete from '" + TodoLists.TODO_TABLE_NAME + "' where ID = '" + todo.listIndex + "';");
        mDBHelper.close();
    }

    public static void deleteToDoData(Context context) {
        DBManager mDBHelper = new DBManager(context);
        mDBHelper.open().mDB.execSQL("delete from '" + TodoLists.TODO_TABLE_NAME + "';");
        mDBHelper.close();
    }

    public static void deleteUserData(Context context) {
        DBManager mDBHelper = new DBManager(context);
        mDBHelper.open().mDB.execSQL("delete from '" + User.USER_TABLE_NAME + "';");
        mDBHelper.close();
    }

    public static void deleteGroupData(Context context) {
        DBManager mDBHelper = new DBManager(context);
        mDBHelper.open().mDB.execSQL("delete from '" + GroupInfo.GROUP_TABLE_NAME + "';");
        mDBHelper.close();
    }
}
