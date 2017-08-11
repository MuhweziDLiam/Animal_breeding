package com.example.muhwezidenisliam.breedingapp.edit_records;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.muhwezidenisliam.breedingapp.R;
import com.example.muhwezidenisliam.breedingapp.activities.DemoActivity;
import com.example.muhwezidenisliam.breedingapp.databases.LoginDataBaseAdapter;
import com.example.muhwezidenisliam.breedingapp.view_records.View_Milk;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Muhwezi Denis Liam on 6/28/2017.
 */

public class Edit_Milk  extends AppCompatActivity {


    @Bind(R.id.cow_name_milk)
    EditText cow_name_milk;

    @Bind(R.id.date_of_milking)
    EditText date_of_milking;

    @Bind(R.id.number_milking_times)
    EditText number_of_milking_times;

    @Bind(R.id.litres_produced)
    EditText litres_produced;

    @Bind(R.id.btn_add_milk)
    Button btn_add_milk;
    int litres[];

    String cow_name, milk_litres;

    ArrayList<HashMap<String,String>> info = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_milk);

        ButterKnife.bind(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setHomeButtonEnabled(true);

        setTitle("Edit Milk Record");

        btn_add_milk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (!validate()) {
                    failed();
                    return;
                }
                else
                {

                    String name_insert = cow_name_milk.getText().toString();

                    String date_insert = date_of_milking.getText().toString();

                    String number_insert = number_of_milking_times.getText().toString();

                    String litres_insert = litres_produced.getText().toString();

                    LoginDataBaseAdapter loginDataBaseAdapter = new LoginDataBaseAdapter(Edit_Milk.this);

                    loginDataBaseAdapter.open();

                    loginDataBaseAdapter.insertEntryMilk(name_insert,date_insert,number_insert,litres_insert);

//                    info = loginDataBaseAdapter.getMilk();
//
//                    litres = new int[info.size()];
//
//                    if(loginDataBaseAdapter.getProfilesMilkCount()>2) {
//
//                        for (int index = 0; index < info.size(); index++) {
//                            cow_name = info.get(index).get("a");
//                            milk_litres = info.get(index).get("d").trim();
//
//                            litres[index] = Integer.parseInt(milk_litres);
//
//                        }
//
//                        Arrays.sort(litres);
//
//                        if (Math.abs(litres[0] - litres[litres.length - 1]) > 10) {
//                            createNotification("cow index");
//                        }
//                    }
                }


                Intent add_Milk = new Intent(Edit_Milk.this,View_Milk.class);

                startActivity(add_Milk);
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

        cow_name_milk.setText("");

        date_of_milking.setText("");

        number_of_milking_times.setText("");

        litres_produced.setText("");

    }

    public boolean validate() {
        boolean valid = true;

        String name = cow_name_milk.getText().toString();

        String date = date_of_milking.getText().toString();

        String number = number_of_milking_times.getText().toString();

        String litres = litres_produced.getText().toString();

        if (name.isEmpty()) {
            cow_name_milk.setError("Please Enter animal Id");
            valid = false;
        } else {
            cow_name_milk.setError(null);
        }

        if (number.isEmpty())
        {
            number_of_milking_times.setError("Please enter Number of milking times");
            valid = false;
        }
        else {
            number_of_milking_times.setError(null);
        }

        if (litres.isEmpty())
        {
            litres_produced.setError("Please enter Litres oof milk produced");
            valid = false;
        }
        else {
            litres_produced.setError(null);
        }


        return valid;
    }

//    public void createNotification(String info) {
//
//
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
//                .setSmallIcon(R.drawable.icon_launcher)
//                .setContentTitle("Cattle info")
//                .setContentText("heat period");
//
//        NotificationCompat.BigTextStyle bigText = new NotificationCompat.BigTextStyle();
//        bigText.bigText(info+ " is on heat");
//
//        builder.setStyle(bigText);
//
//        Intent resultIntent = new Intent(this, DemoActivity.class);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
//        builder.setContentIntent(pendingIntent);
//
//        Notification notification = builder.build();
//        notification.defaults |= Notification.DEFAULT_SOUND;
//        notification.defaults |= Notification.DEFAULT_VIBRATE;
//        notification.flags = Notification.FLAG_AUTO_CANCEL;
//
//        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
//        notificationManager.notify(0, notification);
//    }

}
