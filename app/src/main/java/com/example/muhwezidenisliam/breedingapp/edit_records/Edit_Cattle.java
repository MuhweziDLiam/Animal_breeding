package com.example.muhwezidenisliam.breedingapp.edit_records;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.muhwezidenisliam.breedingapp.R;
import com.example.muhwezidenisliam.breedingapp.activities.DatePickerFragment;
import com.example.muhwezidenisliam.breedingapp.databases.LoginDataBaseAdapter;
import com.example.muhwezidenisliam.breedingapp.view_records.View_Cattle;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Muhwezi Denis Liam on 6/28/2017.
 */

public class Edit_Cattle extends AppCompatActivity {


    @Bind(R.id.cow_name_cattle)
    EditText cow_name_cattle;
    @Bind(R.id.dob_cattle)
    EditText dob_cattle;
    @Bind(R.id.cattle_sex)
    EditText cattle_sex;
    @Bind(R.id.cattle_weight)
    EditText cattle_weight;
    @Bind(R.id.cattle_breed)
    EditText cattle_breed;
    @Bind(R.id.btn_add_cattle)
    Button btn_add_cattle;

    Bundle bundle;
    String date_insert;

    LoginDataBaseAdapter loginDataBaseAdapter;
    ArrayList<HashMap<String,String>> info;
    String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_cattle);
        ButterKnife.bind(this);

        setTitle("Edit Cattle Record");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setHomeButtonEnabled(true);

        bundle = getIntent().getExtras();

        info = new ArrayList<>();

        loginDataBaseAdapter = new LoginDataBaseAdapter(Edit_Cattle.this);

        loginDataBaseAdapter.open();

        info = loginDataBaseAdapter.getCattleId(bundle.getString("id"));

        cow_name_cattle.setText(info.get(0).get("a"));

        dob_cattle.setText(info.get(0).get("b"));

        cattle_sex.setText(info.get(0).get("c"));

        cattle_weight.setText(info.get(0).get("d"));

        cattle_breed.setText(info.get(0).get("e"));


        dob_cattle.setOnFocusChangeListener(new View.OnFocusChangeListener() {
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
                            dob_cattle.setText(sdf.format(c.getTime()));
                           // nextField.requestFocus(); //moves the focus to something else after dialog is closed
                        }
                    };
                    datePickerFragment.show(getFragmentManager(), "datePicker");
                }
            }
        });



        btn_add_cattle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!validate()) {
                    failed();
                    return;
                }
                else
                {

                    String name_insert = cow_name_cattle.getText().toString();

                    String sex_insert = cattle_sex.getText().toString();

                    String weight_insert = cattle_weight.getText().toString();

                    String breed_insert = cattle_breed.getText().toString();


                    loginDataBaseAdapter.updateEntryCattle(bundle.getString("id"),name_insert,date_insert,sex_insert,weight_insert,breed_insert);

                }


                Intent add_cattle = new Intent(Edit_Cattle.this,View_Cattle.class);

                startActivity(add_cattle);
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

       cow_name_cattle.setText("");

        dob_cattle.setText("");

       cattle_sex.setText("");

       cattle_weight.setText("");

         cattle_breed.setText("");

    }

    public boolean validate() {
        boolean valid = true;

        String name = cow_name_cattle.getText().toString();

        String sex = cattle_sex.getText().toString();

        String weight = cattle_weight.getText().toString();

        String breed = cattle_breed.getText().toString();

        if (name.isEmpty()) {
            cow_name_cattle.setError("Please Enter animal Id");
            valid = false;
        } else {
            cow_name_cattle.setError(null);
        }

        if (sex.isEmpty())
        {
            cattle_sex.setError("Please enter Animal sex");
            valid = false;
        }
        else {
            cattle_sex.setError(null);
        }

        if (weight.isEmpty())
        {
            cattle_weight.setError("Please enter animal weight");
            valid = false;
        }
        else {
            cattle_weight.setError(null);
        }

        if (breed.isEmpty())
        {
            cattle_breed.setError("Please enter Animal breed");
            valid = false;
        }
        else {
            cattle_breed.setError(null);
        }


        return valid;
    }




}
