package com.example.muhwezidenisliam.breedingapp.view_records;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.muhwezidenisliam.breedingapp.R;
import com.example.muhwezidenisliam.breedingapp.databases.LoginDataBaseAdapter;
import com.example.muhwezidenisliam.breedingapp.view_adapters.Data_Cattle;
import com.example.muhwezidenisliam.breedingapp.view_adapters.Data_Milk;
import com.example.muhwezidenisliam.breedingapp.view_adapters.RecyclerViewAdapterCattle;
import com.example.muhwezidenisliam.breedingapp.view_adapters.RecyclerViewAdapterMilk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Muhwezi Denis Liam on 6/28/2017.
 */

public class View_Milk  extends AppCompatActivity {


    List<Data_Milk> dataMilkList;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerViewAdapterMilk adapter;
    ArrayList<HashMap<String,String>> info = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_category);

        ButterKnife.bind(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setHomeButtonEnabled(true);

        setTitle("View Milk Records");

        dataMilkList = new ArrayList<>();

        LoginDataBaseAdapter loginDataBaseAdapter = new LoginDataBaseAdapter(this);
        loginDataBaseAdapter.open();

        info = loginDataBaseAdapter.getMilk();

        for(int index =0; index<info.size(); index++)
        {
            dataMilkList.add(new Data_Milk(info.get(index).get("a"),info.get(index).get("b")
                    ,info.get(index).get("c"),info.get(index).get("d")));
        }


        recyclerView=(RecyclerView)findViewById(R.id.recyclerViewMain);
        layoutManager =new LinearLayoutManager(View_Milk.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter =new RecyclerViewAdapterMilk(dataMilkList);
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
