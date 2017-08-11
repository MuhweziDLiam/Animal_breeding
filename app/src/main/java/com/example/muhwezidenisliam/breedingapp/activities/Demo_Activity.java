package com.example.muhwezidenisliam.breedingapp.activities;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ashokvarma.bottomnavigation.BadgeItem;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.example.muhwezidenisliam.breedingapp.R;
import com.example.muhwezidenisliam.breedingapp.databases.LoginDataBaseAdapter;
import com.example.muhwezidenisliam.breedingapp.records_adapter.AdapterSectionRecycler;
import com.example.muhwezidenisliam.breedingapp.records_adapter.AdapterSectionRecyclerOne;
import com.example.muhwezidenisliam.breedingapp.records_adapter.Child;
import com.example.muhwezidenisliam.breedingapp.records_adapter.SectionHeader;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Muhwezi Denis Liam on 7/13/2017.
 */

public class Demo_Activity extends AppCompatActivity{

    private CollapsingToolbarLayout collapsingToolbarLayout;

    ArrayList<HashMap<String,String>> dates = new ArrayList<>();

    RecyclerView recyclerView;
    AdapterSectionRecyclerOne adapterRecycler;
    List<SectionHeader> sectionHeaders;
    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo);

        session = new SessionManager(this);

        session.checkLogin();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_one);
        setSupportActionBar(toolbar);

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_one);

        collapsingToolbarLayout.setExpandedTitleColor(Color.parseColor("#00FFFFFF"));

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        //setLayout Manager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        List<Child> childList = new ArrayList<>();

        childList = new ArrayList<>();
        childList.add(new Child("Breeding","Cow","Heifer","Milk"
                ,getResources().getDrawable(R.drawable.pregnant_cow),getResources().getDrawable(R.drawable.cow_one)
                ,getResources().getDrawable(R.drawable.heifer),getResources().getDrawable(R.drawable.cow_two)));

        sectionHeaders = new ArrayList<>();
        sectionHeaders.add(new SectionHeader(childList, "View Records", 1));

        childList = new ArrayList<>();
        childList.add(new Child("Breeding","Cattle","Heifer","Milk"
                ,getResources().getDrawable(R.drawable.pregnant_cow),getResources().getDrawable(R.drawable.cow_one)
                ,getResources().getDrawable(R.drawable.heifer),getResources().getDrawable(R.drawable.cow_two)));
//        sectionHeaders = new ArrayList<>();
        sectionHeaders.add(new SectionHeader(childList, "View Reports", 2));

        adapterRecycler = new AdapterSectionRecyclerOne(this, sectionHeaders);
        recyclerView.setAdapter(adapterRecycler);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            session.logoutUser();
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
