package com.example.muhwezidenisliam.breedingapp.generate_reports;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.graphics.Color;

import android.view.MenuItem;
import android.widget.TextView;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.TableRow.LayoutParams;

import com.example.muhwezidenisliam.breedingapp.R;
import com.example.muhwezidenisliam.breedingapp.databases.LoginDataBaseAdapter;

/**
 * Created by Muhwezi Denis Liam on 6/28/2017.
 */

public class Gen_Heifer  extends AppCompatActivity {


        TableLayout tl;
        TableRow tr;
        TextView companyTV,valueTV,valueTV1,valueTV2;

@Override
public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        tl = (TableLayout) findViewById(R.id.maintable);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    getSupportActionBar().setHomeButtonEnabled(true);

    setTitle("View Heifer Report");
        addHeaders();
        addData();
        }

/** This function add the headers to the table **/
public void addHeaders(){

        /** Create a TableRow dynamically **/
        tr = new TableRow(this);
        tr.setLayoutParams(new LayoutParams(
        LayoutParams.FILL_PARENT,
        LayoutParams.WRAP_CONTENT));

        /** Creating a TextView to add to the row **/
        TextView companyTV = new TextView(this);
        companyTV.setText("Heifer Id");
        companyTV.setTextColor(Color.GRAY);
        companyTV.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        companyTV.setPadding(5, 5, 5, 0);
        tr.addView(companyTV);  // Adding textView to tablerow.

        /** Creating another textview **/
        TextView valueTV1 = new TextView(this);
        valueTV1.setText("Date of Birth");
        valueTV1.setTextColor(Color.GRAY);
        valueTV1.setPadding(5, 5, 5, 0);
        valueTV1.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        tr.addView(valueTV1); // Adding textView to tablerow.

        /** Creating another textview **/
        TextView valueTV2 = new TextView(this);
        valueTV2.setText("Breed");
        valueTV2.setTextColor(Color.GRAY);
        valueTV2.setPadding(5, 5, 5, 0);
        valueTV2.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        tr.addView(valueTV2); // Adding textView to tablerow.

        /** Creating another textview **/
        TextView valueTV3 = new TextView(this);
        valueTV3.setText("Weight");
        valueTV3.setTextColor(Color.GRAY);
        valueTV3.setPadding(5, 5, 5, 0);
        valueTV3.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        tr.addView(valueTV3); // Adding textView to tablerow.

        /** Creating another textview **/
//        TextView valueTV4 = new TextView(this);
//        valueTV4.setText("Dam type");
//        valueTV4.setTextColor(Color.GRAY);
//        valueTV4.setPadding(5, 5, 5, 0);
//        valueTV4.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
//        tr.addView(valueTV4); // Adding textView to tablerow.

        // Add the TableRow to the TableLayout
        tl.addView(tr, new TableLayout.LayoutParams(
        LayoutParams.FILL_PARENT,
        LayoutParams.WRAP_CONTENT));

        // we are adding two textviews for the divider because we have two columns
        tr = new TableRow(this);
        tr.setLayoutParams(new LayoutParams(
        LayoutParams.FILL_PARENT,
        LayoutParams.WRAP_CONTENT));

        /** Creating another textview **/
        TextView divider = new TextView(this);
        divider.setText("-----------------");
        divider.setTextColor(Color.BLACK);
        divider.setPadding(5, 0, 0, 0);
        divider.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        tr.addView(divider); // Adding textView to tablerow.

        TextView divider2 = new TextView(this);
        divider2.setText("-------------------------");
        divider2.setTextColor(Color.BLACK);
        divider2.setPadding(5, 0, 0, 0);
        divider2.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        tr.addView(divider2); // Adding textView to tablerow.

        TextView divider3 = new TextView(this);
        divider3.setText("-------------------------");
        divider3.setTextColor(Color.BLACK);
        divider3.setPadding(5, 0, 0, 0);
        divider3.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        tr.addView(divider3); // Adding textView to tablerow.

        TextView divider4 = new TextView(this);
        divider4.setText("-------------------------");
        divider4.setTextColor(Color.BLACK);
        divider4.setPadding(5, 0, 0, 0);
        divider4.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        tr.addView(divider4); // Adding textView to tablerow.

        // Add the TableRow to the TableLayout
        tl.addView(tr, new TableLayout.LayoutParams(
        LayoutParams.FILL_PARENT,
        LayoutParams.WRAP_CONTENT));
        }

/** This function add the data to the table **/
public void addData(){

    LoginDataBaseAdapter loginDataBaseAdapter = new LoginDataBaseAdapter(this);
    loginDataBaseAdapter.open();
        for (int i = 0; i < loginDataBaseAdapter.getHeifer().size(); i++)
        {
        /** Create a TableRow dynamically **/
        tr = new TableRow(this);
        tr.setLayoutParams(new LayoutParams(
        LayoutParams.FILL_PARENT,
        LayoutParams.WRAP_CONTENT));

        /** Creating a TextView to add to the row **/
        companyTV = new TextView(this);
        companyTV.setText(loginDataBaseAdapter.getHeifer().get(i).get("a"));
        companyTV.setTextColor(Color.BLACK);
        companyTV.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        companyTV.setPadding(5, 5, 5, 5);
        tr.addView(companyTV);  // Adding textView to tablerow.

            /** Creating another textview **/
            valueTV = new TextView(this);
            valueTV.setText(loginDataBaseAdapter.getHeifer().get(i).get("b"));
            valueTV.setTextColor(Color.BLACK);
            valueTV.setPadding(5, 5, 5, 5);
            valueTV.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            tr.addView(valueTV); // Adding textView to tablerow.

            /** Creating another textview **/
            valueTV1 = new TextView(this);
            valueTV1.setText(loginDataBaseAdapter.getHeifer().get(i).get("c"));
            valueTV1.setTextColor(Color.BLACK);
            valueTV1.setPadding(5, 5, 5, 5);
            valueTV1.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            tr.addView(valueTV1); // Adding textView to tablerow.

        /** Creating another textview **/
        valueTV2 = new TextView(this);
        valueTV2.setText(loginDataBaseAdapter.getHeifer().get(i).get("d"));
        valueTV2.setTextColor(Color.BLACK);
        valueTV2.setPadding(5, 5, 5, 5);
        valueTV2.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        tr.addView(valueTV2); // Adding textView to tablerow.

        // Add the TableRow to the TableLayout
        tl.addView(tr, new TableLayout.LayoutParams(
        LayoutParams.FILL_PARENT,
        LayoutParams.WRAP_CONTENT));
        }
        }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
