package com.example.muhwezidenisliam.breedingapp.add_records;

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
import com.example.muhwezidenisliam.breedingapp.view_records.View_Breed;
import com.example.muhwezidenisliam.breedingapp.view_records.View_Heifer;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Muhwezi Denis Liam on 6/28/2017.
 */

public class Add_Heifer  extends AppCompatActivity {


    @Bind(R.id.cow_name_heifer)
    EditText cow_name_heifer;
    @Bind(R.id.dam_type)
    EditText dam_type;
    @Bind(R.id.heifer_weight)
    EditText heifer_weight;
    @Bind(R.id.feeding_behaviour)
    EditText feeding_behaviour;
    @Bind(R.id.btn_add_heifer)
    Button btn_add_heifer;
    String dam_type_;
    String dam_type_insert;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_heifer);
        ButterKnife.bind(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setHomeButtonEnabled(true);

        setTitle("Add Heifer Record");

        dam_type.setOnFocusChangeListener(new View.OnFocusChangeListener() {
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
                            dam_type_insert = sdf.format(c.getTime());
                            dam_type_ = sdf.format(c.getTime());
                            dam_type.setText(sdf.format(c.getTime()));
                            // nextField.requestFocus(); //moves the focus to something else after dialog is closed
                        }
                    };
                    datePickerFragment.show(getFragmentManager(), "datePicker");
                }
            }
        });

        btn_add_heifer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!validate()) {
                    failed();
                    return;
                }
                else
                {

                    String name_insert = cow_name_heifer.getText().toString();



                    String weight_insert = heifer_weight.getText().toString();

                    String feeding_insert = feeding_behaviour.getText().toString();

                    LoginDataBaseAdapter loginDataBaseAdapter = new LoginDataBaseAdapter(Add_Heifer.this);

                    loginDataBaseAdapter.open();

                    loginDataBaseAdapter.insertEntryHeifer(name_insert,dam_type_insert,weight_insert,feeding_insert);

                }


                Intent add_Heifer = new Intent(Add_Heifer.this,View_Heifer.class);

                startActivity(add_Heifer);
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

        cow_name_heifer.setText("");

       dam_type.setText("");

        heifer_weight.setText("");

        feeding_behaviour.setText("");

    }

    public boolean validate() {
        boolean valid = true;

        String name= cow_name_heifer.getText().toString();


        String weight = heifer_weight.getText().toString();

        String feeding = feeding_behaviour.getText().toString();

        if (name.isEmpty()) {
            cow_name_heifer.setError("Please Enter animal Id");
            valid = false;
        } else {
            cow_name_heifer.setError(null);
        }

        if (dam_type_.isEmpty())
        {
            dam_type.setError("Please enter dam type");
            valid = false;
        }
        else {
            dam_type.setError(null);
        }

        if (weight.isEmpty())
        {
            heifer_weight.setError("Please enter Heifer weight");
            valid = false;
        }
        else {
            heifer_weight.setError(null);
        }

        if (feeding.isEmpty())
        {
            feeding_behaviour.setError("Please enter Heifer feeding patterns");
            valid = false;
        }
        else {
            feeding_behaviour.setError(null);
        }


        return valid;
    }
}
