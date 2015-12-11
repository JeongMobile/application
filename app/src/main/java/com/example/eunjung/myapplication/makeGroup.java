package com.example.eunjung.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class makeGroup extends AppCompatActivity {

    private EditText etGroupName, etGroupTip, etUser;
    private ArrayAdapter arrayAdapter;
    private ListView lvUserLists;

//    makeGroup() {
//
//    }
//    makeGroup(boolean flag){
//        if(flag){
//            //set Data
//            //getData
//            //init(String, String, String[]);
//        }
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        etGroupName = (EditText)findViewById(R.id.etGroupname);
        etGroupTip = (EditText)findViewById(R.id.etGrouptip);
        etUser = (EditText)findViewById(R.id.etFindUser);
        lvUserLists = (ListView)findViewById(R.id.listView);

        setContentView(R.layout.activity_make_group);

    }


    public void init(String GroupName, String GroupTip, String[] UserLists ) {
        etGroupName.setText(GroupName);
        etGroupTip.setText(GroupTip);
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, UserLists);
        lvUserLists.setAdapter(arrayAdapter);
    }
}
