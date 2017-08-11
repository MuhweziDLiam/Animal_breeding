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
import com.example.muhwezidenisliam.breedingapp.view_records.View_Heifer;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Muhwezi Denis Liam on 6/28/2017.
 */

public class Edit_Heifer  extends AppCompatActivity {


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

    Bundle bundle;
    String date_insert;

    LoginDataBaseAdapter loginDataBaseAdapter;
    ArrayList<HashMap<String,String>> info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_heifer);
        ButterKnife.bind(this);

        setTitle("Edit Heifer Record");

        bundle = getIntent().getExtras();

        info = new ArrayList<>();

        loginDataBaseAdapter = new LoginDataBaseAdapter(Edit_Heifer.this);

        loginDataBaseAdapter.open();

        info = loginDataBaseAdapter.getHeiferId(bundle.getString("id"));


        cow_name_heifer.setText(info.get(0).get("a"));

        dam_type.setText(info.get(0).get("b"));

        heifer_weight.setText(info.get(0).get("c"));

        feeding_behaviour.setText(info.get(0).get("d"));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setHomeButtonEnabled(true);

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

                    String dam_type_insert = dam_type.getText().toString();

                    String weight_insert = heifer_weight.getText().toString();

                    String feeding_insert = feeding_behaviour.getText().toString();


                    loginDataBaseAdapter.updateEntryHeifer(bundle.getString("id"),name_insert,dam_type_insert,weight_insert,feeding_insert);

                }


                Intent add_Heifer = new Intent(Edit_Heifer.this,View_Heifer.class);

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

        String dam_type_ = dam_type.getText().toString();

        String weight = heifer_weight.getText().toString();

        String feeding = feeding_behaviour.getText().toString();

        if (name.isEmpty()) {
            cow_name_heifer.setError("Please Enter animal Id");
            valid = false;
        } else {
            cow_name_heifer.setError(null);
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
