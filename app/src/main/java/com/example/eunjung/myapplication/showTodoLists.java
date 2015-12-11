package com.example.eunjung.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class showTodoLists extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ArrayAdapter arrayAdapterReady,arrayAdapterDo,arrayAdapterDone;
    ListView lvReady, lvDo, lvEnd;
    ArrayList<String> readyList = new ArrayList<>(),doList=new ArrayList<>(),endList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_todo_lists);
        readyList.add("0ddddddddddddddddddddddddd");
        readyList.add("0ddddddddddddddddddddddddd");
        readyList.add("0ddddddddddddddddddddddddd");
        readyList.add("0ddddddddddddddddddddddddd");
        readyList.add("0ddddddddddddddddddddddddd");
        readyList.add("0ddddddddddddddddddddddddd");
        readyList.add("0ddddddddddddddddddddddddd");
        readyList.add("0ddddddddddddddddddddddddd");
        readyList.add("0ddddddddddddddddddddddddd");
        readyList.add("0ddddddddddddddddddddddddd");
        lvReady = (ListView)findViewById(R.id.lvReady);
        lvDo = (ListView)findViewById(R.id.lvDo);
        lvEnd = (ListView)findViewById(R.id.lvEnd);
        arrayAdapterReady = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, readyList);
        lvReady.setAdapter(arrayAdapterReady);
        arrayAdapterDo = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, doList);
        lvDo.setAdapter(arrayAdapterDo);
        arrayAdapterDone = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, endList);
        lvEnd.setAdapter(arrayAdapterDone);
        lvDo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        lvDo.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                endList.add(doList.get(position));
                arrayAdapterDone.notifyDataSetChanged();
                doList.remove(position);
                arrayAdapterDo.notifyDataSetChanged();
                return true;
            }
        });
        lvReady.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        lvReady.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                doList.add(readyList.get(position));
                arrayAdapterDo.notifyDataSetChanged();
                readyList.remove(position);
                arrayAdapterReady.notifyDataSetChanged();
                return true;
            }
        });
        lvEnd.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}

