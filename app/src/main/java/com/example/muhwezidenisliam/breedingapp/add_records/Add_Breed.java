package com.example.muhwezidenisliam.breedingapp.add_records;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.muhwezidenisliam.breedingapp.R;
import com.example.muhwezidenisliam.breedingapp.activities.DatePickerFragment;
import com.example.muhwezidenisliam.breedingapp.databases.LoginDataBaseAdapter;
import com.example.muhwezidenisliam.breedingapp.view_records.View_Breed;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Muhwezi Denis Liam on 6/28/2017.
 */

public class Add_Breed extends AppCompatActivity {

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
    String date;
    String date_insert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_breed);

        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setHomeButtonEnabled(true);

        setTitle("Add Breeding Record");

        insemination_date.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    DialogFragment datePickerFragment = new DatePickerFragment() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int day) {

                            String myFormat = "dd/MM/yyyy";
                            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                            Calendar c = Calendar.getInstance();
                            c.set(year, month, day);
                            date_insert = sdf.format(c.getTime());
                            date = sdf.format(c.getTime());
                            insemination_date.setText(sdf.format(c.getTime()));
                            // nextField.requestFocus(); //moves the focus to something else after dialog is closed
                        }
                    };
                    datePickerFragment.show(getFragmentManager(), "datePicker");
                }
            }
        });


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


                    String number_insert = service_number.getText().toString();

                    String dam_insert = dam.getText().toString();

                    LoginDataBaseAdapter loginDataBaseAdapter = new LoginDataBaseAdapter(Add_Breed.this);

                    loginDataBaseAdapter.open();

                    loginDataBaseAdapter.insertEntryBreed(name_insert,date_insert,dam_insert,number_insert);

                }


                Intent add_breed = new Intent(Add_Breed.this,View_Breed.class);
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

        dam.setText("");

    }

    public boolean validate() {
        boolean valid = true;

        String name = cow_name_breed.getText().toString();

        date = insemination_date.getText().toString();

        String number = service_number.getText().toString();

        String damed = dam.getText().toString();

        if (name.isEmpty()) {
            cow_name_breed.setError("Please Enter animal Id");
            valid = false;
        } else {
            cow_name_breed.setError(null);
        }

        if (date.isEmpty())
        {
            insemination_date.setError("Please enter service date");
            valid = false;
        }
        else {
            insemination_date.setError(null);
        }

        if (number.isEmpty())
        {
            service_number.setError("Please enter number of service times");
            valid = false;
        }
        else {
            service_number.setError(null);
        }
        if (damed.isEmpty())
        {
            dam.setError("Please enter dam type");
            valid = false;
        }
        else {
            dam.setError(null);
        }


        return valid;
    }
}
