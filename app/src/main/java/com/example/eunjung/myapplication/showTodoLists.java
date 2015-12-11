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
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class showTodoLists extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ArrayAdapter arrayAdapter;
    ListView lvReady, lvDo, lvEnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_todo_lists);

        lvReady = (ListView)findViewById(R.id.lvReady);
        lvDo = (ListView)findViewById(R.id.lvDo);
        lvEnd = (ListView)findViewById(R.id.lvEnd);

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

    public void init(String[] readyLists, String[] doLists, String[] endlists) {

        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, readyLists);
        lvReady.setAdapter(arrayAdapter);
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, doLists);
        lvDo.setAdapter(arrayAdapter);
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, endlists);
        lvEnd.setAdapter(arrayAdapter);
        }
}

