package com.example.muhwezidenisliam.breedingapp.generate_reports;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.muhwezidenisliam.breedingapp.databases.LoginDataBaseAdapter;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.Legend.LegendPosition;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet;

import java.util.ArrayList;
import java.util.Map;

import com.example.muhwezidenisliam.breedingapp.R;


public class Gen_Breed extends AppCompatActivity {

    private RadarChart mChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_radarchart_noseekbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setHomeButtonEnabled(true);

        setTitle("View Breeding Report");


        mChart = (RadarChart) findViewById(R.id.chart1);

        mChart.getDescription().setEnabled(false);

        mChart.setWebLineWidth(1f);
        mChart.setWebColor(Color.BLACK);
        mChart.setWebLineWidthInner(1f);
        mChart.setWebColorInner(Color.BLACK);
        mChart.setWebAlpha(100);

        // create a custom MarkerView (extend MarkerView) and specify the layout
        // to use for it
        MarkerView mv = new RadarMarkerView(this, R.layout.radar_markerview);
        mv.setChartView(mChart); // For bounds control
        mChart.setMarker(mv); // Set the marker to the chart

        setData();

        mChart.animateXY(
                1400, 1400,
                Easing.EasingOption.EaseInOutQuad,
                Easing.EasingOption.EaseInOutQuad);

        XAxis xAxis = mChart.getXAxis();

        xAxis.setTextSize(9f);
        xAxis.setYOffset(0f);
        xAxis.setXOffset(0f);


        xAxis.setValueFormatter(new IAxisValueFormatter() {




            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                LoginDataBaseAdapter loginDataBaseAdapter = new LoginDataBaseAdapter(Gen_Breed.this);
                loginDataBaseAdapter.open();

                String[] mActivities = new String[loginDataBaseAdapter.getRepBreed().size()];

                for(int i = 0; i < loginDataBaseAdapter.getRepBreed().size(); i++)

                {
                    mActivities[i] = loginDataBaseAdapter.getRepBreed().get(i).get("a");
                }
                return mActivities[(int) value % mActivities.length];
            }
        });
        xAxis.setTextColor(Color.BLACK);

        YAxis yAxis = mChart.getYAxis();
        yAxis.setLabelCount(5, false);
        yAxis.setTextSize(9f);
        yAxis.setAxisMinimum(0f);
        yAxis.setAxisMaximum(80f);
        yAxis.setDrawLabels(false);

        Legend l = mChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);

        l.setXEntrySpace(7f);
        l.setYEntrySpace(5f);
        l.setTextColor(Color.BLACK);
    }

    public void setData() {
        LoginDataBaseAdapter loginDataBaseAdapter = new LoginDataBaseAdapter(this);
        loginDataBaseAdapter.open();

        int count =0;

        Log.d("count_size",""+ loginDataBaseAdapter.getRepBreed().size());
        ArrayList<RadarEntry> entries1 = new ArrayList<RadarEntry>();
        ArrayList<RadarEntry> entries2 = new ArrayList<RadarEntry>();

        for (int i = 0; i < loginDataBaseAdapter.getRepBreed().size(); i++) {
            count+=Integer.parseInt(loginDataBaseAdapter.getRepBreed().get(i).get("b"));
        }

        Log.d("shit",""+count);

        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
        // the chart.
        for (int i = 0; i < loginDataBaseAdapter.getRepBreed().size(); i++) {

            float val1 = (float) Math.abs((Integer.parseInt(loginDataBaseAdapter.getRepBreed().get(i).get("b"))*10)-100);

            Log.d("shit",""+val1);


            entries1.add(new RadarEntry(val1));

//            float val2 = (float) (Math.random() * mult) + min;
//            entries2.add(new RadarEntry(val2));
        }

        RadarDataSet set1 = new RadarDataSet(entries1, "% Probability of Conception after breeding");
        set1.setColor(Color.rgb(103, 110, 129));
        set1.setFillColor(Color.rgb(103, 110, 129));
        set1.setDrawFilled(true);
        set1.setFillAlpha(180);
        set1.setLineWidth(2f);
        set1.setDrawHighlightCircleEnabled(true);
        set1.setDrawHighlightIndicators(false);

//        RadarDataSet set2 = new RadarDataSet(entries2, "This Week");
//        set2.setColor(Color.rgb(121, 162, 175));
//        set2.setFillColor(Color.rgb(121, 162, 175));
//        set2.setDrawFilled(true);
//        set2.setFillAlpha(180);
//        set2.setLineWidth(2f);
//        set2.setDrawHighlightCircleEnabled(true);
//        set2.setDrawHighlightIndicators(false);

        ArrayList<IRadarDataSet> sets = new ArrayList<IRadarDataSet>();
        sets.add(set1);
//        sets.add(set2);

        RadarData data = new RadarData(sets);

        data.setValueTextSize(8f);
        data.setDrawValues(false);
        data.setValueTextColor(Color.BLACK);

        mChart.setData(data);
        mChart.invalidate();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
