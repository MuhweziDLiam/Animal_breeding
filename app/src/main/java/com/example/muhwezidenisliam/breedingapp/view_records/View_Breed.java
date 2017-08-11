package com.example.muhwezidenisliam.breedingapp.view_records;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.example.muhwezidenisliam.breedingapp.R;
import com.example.muhwezidenisliam.breedingapp.databases.LoginDataBaseAdapter;
import com.example.muhwezidenisliam.breedingapp.view_adapters.Data_Breed;
import com.example.muhwezidenisliam.breedingapp.view_adapters.RecyclerViewAdapterBreed;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Muhwezi Denis Liam on 6/28/2017.
 */

public class View_Breed extends AppCompatActivity {


    List<Data_Breed> dataBreedList;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerViewAdapterBreed adapter;

    ArrayList<HashMap<String,String>> info = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_category);

      getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setHomeButtonEnabled(true);

        setTitle("View Breeding Records");

        dataBreedList = new ArrayList<>();
        LoginDataBaseAdapter loginDataBaseAdapter = new LoginDataBaseAdapter(this);
        loginDataBaseAdapter.open();

        info = loginDataBaseAdapter.getBreed();

        for(int index =0; index<info.size(); index++)
        {
            dataBreedList.add(new Data_Breed(info.get(index).get("a"),info.get(index).get("b"),info.get(index).get("c"),info.get(index).get("d")));
        }


        recyclerView=(RecyclerView)findViewById(R.id.recyclerViewMain);
        layoutManager =new LinearLayoutManager(View_Breed.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter =new RecyclerViewAdapterBreed(dataBreedList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
