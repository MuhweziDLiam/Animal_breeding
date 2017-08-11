package com.example.muhwezidenisliam.breedingapp.edit_records;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.muhwezidenisliam.breedingapp.R;
import com.example.muhwezidenisliam.breedingapp.databases.LoginDataBaseAdapter;
import com.example.muhwezidenisliam.breedingapp.view_records.View_Breed;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Muhwezi Denis Liam on 6/28/2017.
 */

public class Edit_Breed extends AppCompatActivity {

    @Bind(R.id.cow_name_breed)
    EditText cow_name_breed;
    @Bind(R.id.insemination_date)
    EditText insemination_date;
    @Bind(R.id.damed_type)
    EditText dam;
    @Bind(R.id.service_number)
    EditText service_number;
    @Bind(R.id.btn_add_breed)
    Button btn_add_breed;
    Bundle bundle;

    LoginDataBaseAdapter loginDataBaseAdapter;
    ArrayList<HashMap<String,String>> info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_breed);
        ButterKnife.bind(this);

        setTitle("Edit Breeding Record");

        bundle = getIntent().getExtras();

        info = new ArrayList<>();


        loginDataBaseAdapter = new LoginDataBaseAdapter(Edit_Breed.this);

        loginDataBaseAdapter.open();

        info = new ArrayList<>();

        info = loginDataBaseAdapter.getBreedId(bundle.getString("id"));


        Log.d("size",""+info.size());

        cow_name_breed.setText(info.get(0).get("a"));

        insemination_date.setText(info.get(0).get("b"));

        dam.setText(info.get(0).get("c"));

        service_number.setText(info.get(0).get("d"));




        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setHomeButtonEnabled(true);

        btn_add_breed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!validate()) {
                    failed();
                    return;
                }
                else
                {

                    String name_insert = cow_name_breed.getText().toString();

                    String date_insert = insemination_date.getText().toString();

                    String number_insert = service_number.getText().toString();

                    String dame = dam.getText().toString();



                    loginDataBaseAdapter.updateEntryBreed(bundle.getString("id"),name_insert,date_insert,dame,number_insert);

                }


                Intent add_breed = new Intent(Edit_Breed.this,View_Breed.class);

                startActivity(add_breed);
                finish();
            }

        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void failed() {

        cow_name_breed.setText("");

        insemination_date.setText("");

        service_number.setText("");

    }

    public boolean validate() {
        boolean valid = true;

        String name = cow_name_breed.getText().toString();

        String date = insemination_date.getText().toString();

        String number = service_number.getText().toString();

        if (name.isEmpty()) {
            cow_name_breed.setError("Please Enter animal Id");
            valid = false;
        } else {
            cow_name_breed.setError(null);
        }

        if (number.isEmpty())
        {
            service_number.setError("Please enter number of service times");
            valid = false;
        }
        else {
            service_number.setError(null);
        }


        return valid;
    }
}
