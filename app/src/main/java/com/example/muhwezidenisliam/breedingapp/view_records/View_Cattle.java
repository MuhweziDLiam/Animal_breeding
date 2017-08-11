package com.example.muhwezidenisliam.breedingapp.view_records;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.DatePicker;


import com.example.muhwezidenisliam.breedingapp.databases.LoginDataBaseAdapter;
import com.example.muhwezidenisliam.breedingapp.view_adapters.Data_Breed;
import com.example.muhwezidenisliam.breedingapp.view_adapters.Data_Cattle;
import com.example.muhwezidenisliam.breedingapp.view_adapters.RecyclerViewAdapterCattle;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import com.example.muhwezidenisliam.breedingapp.R;


/**
 * Created by Muhwezi Denis Liam on 6/28/2017.
 */

public class View_Cattle extends AppCompatActivity {

    List<Data_Cattle> dataCattleList;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerViewAdapterCattle adapter;

    ArrayList<HashMap<String,String>> info = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.view_category);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setHomeButtonEnabled(true);

        setTitle("View Cattle Records");

        dataCattleList = new ArrayList<>();
        LoginDataBaseAdapter loginDataBaseAdapter = new LoginDataBaseAdapter(this);
        loginDataBaseAdapter.open();

        info = loginDataBaseAdapter.getCattle();

        for(int index =0; index<info.size(); index++)
        {
            dataCattleList.add(new Data_Cattle(info.get(index).get("a"),info.get(index).get("b")
                    ,info.get(index).get("c"),info.get(index).get("d"),info.get(index).get("e")));
        }


        recyclerView=(RecyclerView)findViewById(R.id.recyclerViewMain);
        layoutManager =new LinearLayoutManager(View_Cattle.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter =new RecyclerViewAdapterCattle(dataCattleList);
        recyclerView.setAdapter(adapter);
    }

}
