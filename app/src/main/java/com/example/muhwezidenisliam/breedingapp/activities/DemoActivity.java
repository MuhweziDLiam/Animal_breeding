package com.example.muhwezidenisliam.breedingapp.activities;

import android.app.DialogFragment;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.NotificationCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BadgeItem;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.example.muhwezidenisliam.breedingapp.R;
import com.example.muhwezidenisliam.breedingapp.databases.LoginDataBaseAdapter;
import com.example.muhwezidenisliam.breedingapp.edit_records.Edit_Breed;
import com.example.muhwezidenisliam.breedingapp.edit_records.Edit_Cattle;
import com.example.muhwezidenisliam.breedingapp.edit_records.Edit_Heifer;
import com.example.muhwezidenisliam.breedingapp.edit_records.Edit_Milk;
import com.example.muhwezidenisliam.breedingapp.records_adapter.AdapterSectionRecycler;
import com.example.muhwezidenisliam.breedingapp.records_adapter.Child;
import com.example.muhwezidenisliam.breedingapp.records_adapter.SectionHeader;
import com.example.muhwezidenisliam.breedingapp.view_adapters.Data_Breed;
import com.example.muhwezidenisliam.breedingapp.view_adapters.Data_Cattle;
import com.example.muhwezidenisliam.breedingapp.view_adapters.Data_Heifer;
import com.example.muhwezidenisliam.breedingapp.view_adapters.Data_Milk;
import com.example.muhwezidenisliam.breedingapp.view_adapters.RecyclerViewAdapterBreed;
import com.example.muhwezidenisliam.breedingapp.view_adapters.RecyclerViewAdapterCattle;
import com.example.muhwezidenisliam.breedingapp.view_adapters.RecyclerViewAdapterHeifer;
import com.example.muhwezidenisliam.breedingapp.view_adapters.RecyclerViewAdapterMilk;
import com.example.muhwezidenisliam.breedingapp.view_records.View_Breed;
import com.example.muhwezidenisliam.breedingapp.view_records.View_Cattle;
import com.example.muhwezidenisliam.breedingapp.view_records.View_Heifer;
import com.example.muhwezidenisliam.breedingapp.view_records.View_Milk;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class DemoActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener{

    private CollapsingToolbarLayout collapsingToolbarLayout;

  ArrayList<HashMap<String,String>> dates = new ArrayList<>();
  ArrayList<HashMap<String,String>> dates_one = new ArrayList<>();

    RecyclerView recyclerView;
    AdapterSectionRecycler adapterRecycler;
    List<SectionHeader> sectionHeaders;
    BadgeItem badgeItem;

    Boolean item_clicked = false;

    String view_category, calving;

  SessionManager session;

    int lastSelectedPosition = 0;

    BottomNavigationBar bottomNavigationBar;
    ShapeBadgeItem circularBadgeItem, sqaureBadgeItem;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_demo);

    session = new SessionManager(this);


    session.checkLogin();

      Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
      //  toolbar.setTitle("Home");
      setSupportActionBar(toolbar);

    bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);

    bottomNavigationBar.setTabSelectedListener(this);
    add_bottom();

    //create a handler in an activity or fragment
    Handler handler = new Handler();

//create a timer task and pass the handler in
    CustomTimerTask task = new CustomTimerTask(handler);

//use timer to run the task every 3 seconds
    //36000000
     new Timer().scheduleAtFixedRate(task, 0, 300000);

      collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);

      collapsingToolbarLayout.setExpandedTitleColor(Color.parseColor("#00FFFFFF"));

      recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

      //setLayout Manager
      LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
      recyclerView.setLayoutManager(linearLayoutManager);
      recyclerView.setHasFixedSize(true);

      List<Child> childList = new ArrayList<>();
      childList.add(new Child("Breeding","Cow","Heifer","Milk"
              ,getResources().getDrawable(R.drawable.pregnant_cow),getResources().getDrawable(R.drawable.cow_one)
      ,getResources().getDrawable(R.drawable.heifer),getResources().getDrawable(R.drawable.cow_two)));

      //Create a List of SectionHeader DataModel implements SectionHeader
      sectionHeaders = new ArrayList<>();
      sectionHeaders.add(new SectionHeader(childList, "Add Records", 1));

      childList = new ArrayList<>();
      childList.add(new Child("Breeding","Cow","Heifer","Milk"
              ,getResources().getDrawable(R.drawable.pregnant_cow),getResources().getDrawable(R.drawable.cow_one)
              ,getResources().getDrawable(R.drawable.heifer),getResources().getDrawable(R.drawable.cow_two)));


      sectionHeaders.add(new SectionHeader(childList, "View Records", 2));

      childList = new ArrayList<>();
      childList.add(new Child("Breeding","Cattle","Heifer","Milk"
              ,getResources().getDrawable(R.drawable.pregnant_cow),getResources().getDrawable(R.drawable.cow_one)
              ,getResources().getDrawable(R.drawable.heifer),getResources().getDrawable(R.drawable.cow_two)));

      sectionHeaders.add(new SectionHeader(childList, "View Reports", 3));

      adapterRecycler = new AdapterSectionRecycler(this, sectionHeaders);
      recyclerView.setAdapter(adapterRecycler);

  }



  public class CustomTimerTask extends TimerTask {
    private Handler handler;
    public CustomTimerTask(Handler h) {this.handler = h;}

    @Override
    public void run() {
      //run the code you want to run
      check_date();

      //update UI using the handler
      handler.post(new Runnable() {
        public void run() {
          //update UI here.
        }
      });
    }
  }

  public void check_date()
  {

    LoginDataBaseAdapter loginDataBaseAdapter = new LoginDataBaseAdapter(this);
    loginDataBaseAdapter.open();


    dates = loginDataBaseAdapter.getHeifer();

    dates_one = loginDataBaseAdapter.getCattle();


    if(dates.size()!=0) {

      for (int index = 0; index < dates.size(); index++) {

        String oldDate = dates.get(index).get("b").trim();
        SimpleDateFormat dateFormat_old = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        try {
          Date old_date_date = dateFormat_old.parse(oldDate);


        Calendar calendar = Calendar.getInstance();
        calendar.setTime(old_date_date);
          calendar.add(Calendar.MONTH,15);
          calendar.add(Calendar.DATE,21);

        Date new_result_date = calendar.getTime();

          Calendar cal = Calendar.getInstance();
          Date current = cal.getTime();

//        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
//        String newDate = dateFormat.format(new_result_date);

        if (new_result_date.after(current)) {
          createNotification(dates.get(index).get("a"));
        }

        } catch (ParseException e) {
          e.printStackTrace();
        }


      }
    }

    if(dates_one.size()!=0) {

      for (int index = 0; index < dates_one.size(); index++) {

        String oldDate = dates_one.get(index).get("b").trim();
        SimpleDateFormat dateFormat_old = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        try {
          Date old_date_date = dateFormat_old.parse(oldDate);


          Calendar calendar = Calendar.getInstance();
          calendar.setTime(old_date_date);
          calendar.add(Calendar.DATE,21);

          Date new_result_date = calendar.getTime();

          Calendar cal = Calendar.getInstance();
          Date current = cal.getTime();

//        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
//        String newDate = dateFormat.format(new_result_date);

          if (new_result_date.after(current)) {
            createNotification(dates_one.get(index).get("a"));
          }

        } catch (ParseException e) {
          e.printStackTrace();
        }


      }
    }
  }


  public void createNotification(String info) {


    NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
            .setSmallIcon(R.drawable.icon_launcher)
            .setContentTitle("Breeding Update")
            .setContentText("Mating period");

    NotificationCompat.BigTextStyle bigText = new NotificationCompat.BigTextStyle();
    bigText.bigText(" Please Monitor Animal with Id: "+info);

    builder.setStyle(bigText);

    Intent resultIntent = new Intent(this, DemoActivity.class);
    PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
    builder.setContentIntent(pendingIntent);

    Notification notification = builder.build();
    notification.defaults |= Notification.DEFAULT_SOUND;
    notification.defaults |= Notification.DEFAULT_VIBRATE;
    notification.flags = Notification.FLAG_AUTO_CANCEL;

    NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
    notificationManager.notify(0, notification);
  }

  @Override
  public void onTabSelected(int position) {
    lastSelectedPosition = position;
    setMessageText(position + " Tab Selected");
    if (badgeItem != null) {
      badgeItem.setText(Integer.toString(position));
    }
    //setScrollableText(position);
  }

  @Override
  public void onTabUnselected(int position) {
  }

  @Override
  public void onTabReselected(int position) {


    setMessageText(position + " Tab Reselected");

    switch(position)
    {
      case 0:
        case_one();
        break;

      case 1:
        case_two();
        break;

      case 2:
        case_three();
        break;

      case 3:
        case_sync();
        break;

    }
  }

  private void setMessageText(String messageText) {
    //  message.setText(messageText);
  }

  private void add_bottom() {

    bottomNavigationBar.clearAll();
//        bottomNavigationBar.setFab(fabHome, BottomNavigationBar.FAB_BEHAVIOUR_TRANSLATE_AND_STICK);
    // bottomNavigationBar.setFab(fabHome);

    //setScrollableText(lastSelectedPosition);

    badgeItem = new BadgeItem()
            .setBorderWidth(4)
            .setBackgroundColorResource(R.color.primary)
            .setText("2")
            .setHideOnSelect(true);

        /*
        * Sample to show how to add a dot like badge issue #109
        * */
    circularBadgeItem =   new ShapeBadgeItem()
            //.setBorderWidth(8)
            .setBackgroundColorResource(R.color.color_ten)
            .setDimen(8) // the size to badge in dp
            .setShape(ShapeBadgeItem.CIRCLE) // Choose between ShapeBadgeItem.CIRCLE or ShapeBadgeItem.SQUARE
            .setMargins(0, 2, 4, 0); // left, top, right, bottom


    sqaureBadgeItem =   new ShapeBadgeItem()
            //.setBorderWidth(8)
            .setBackgroundColorResource(R.color.cardview_dark_background)
            .setDimen(8) // the size to badge in dp
            .setShape(ShapeBadgeItem.SQUARE) // Choose between ShapeBadgeItem.CIRCLE or ShapeBadgeItem.SQUARE
            .setMargins(0, 2, 4, 0); // left, top, right, bottom


    bottomNavigationBar
            .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_RIPPLE);


    bottomNavigationBar
            .addItem(new BottomNavigationItem(R.drawable.ic_action_search, "Search").setActiveColorResource(R.color.primary))
            .addItem(new BottomNavigationItem(R.drawable.ic_action_add, "Add Conception record").setActiveColorResource(R.color.color_ten))
            .addItem(new BottomNavigationItem(R.drawable.ic_action_edit, "Edit").setActiveColorResource(R.color.primary))
            .addItem(new BottomNavigationItem(R.drawable.ic_action_reload, "Sync data").setActiveColorResource(R.color.cardview_dark_background))
            .setFirstSelectedPosition(lastSelectedPosition > 3 ? 3 : lastSelectedPosition)
            .initialise();
  }

  public void case_three()
  {

    final String[] view_cat = {"BREEDING","COW","HEIFER"};

    AlertDialog.Builder dialog_Builder = new AlertDialog.Builder(DemoActivity.this);
    LayoutInflater inflate = getLayoutInflater();
    final View dialog_View = inflate.inflate(R.layout.edit_dialog, null);
    dialog_Builder.setView(dialog_View);

    MaterialBetterSpinner spin = (MaterialBetterSpinner) dialog_View.findViewById(R.id.android_material_spinner_4);

    final EditText editText_one = (EditText) dialog_View.findViewById(R.id.edit_one_one);

    final Button delete = (Button) dialog_View.findViewById(R.id.btn_add_delete);

    final Button edit = (Button) dialog_View.findViewById(R.id.btn_add_edit);






    final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
            android.R.layout.simple_dropdown_item_1line, view_cat);

    spin.setAdapter(arrayAdapter);

    spin.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        view_category = view_cat[position];
        item_clicked = true;
      }
    });




    edit.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

        LoginDataBaseAdapter loginDataBaseAdapter = new LoginDataBaseAdapter(DemoActivity.this);
        loginDataBaseAdapter.open();

         String id = editText_one.getText().toString().trim();


        Intent intent;


        if(view_category.equals("BREEDING"))
        {
          intent = new Intent(DemoActivity.this, Edit_Breed.class);
        }
        else if (view_category.equals("COW"))
        {
          intent = new Intent(DemoActivity.this, Edit_Cattle.class);
        }
        else
        {
          intent = new Intent(DemoActivity.this, Edit_Heifer.class);
        }



        intent.putExtra("id",id);
        startActivity(intent);
      }
    });

    delete.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

        LoginDataBaseAdapter loginDataBaseAdapter = new LoginDataBaseAdapter(DemoActivity.this);
        loginDataBaseAdapter.open();

        String id = editText_one.getText().toString().trim();

        if(view_category.equals("BREEDING"))
        {
         loginDataBaseAdapter.deleteData(id,"BREEDING");
        }
        else if (view_category.equals("COW"))
        {
          loginDataBaseAdapter.deleteData(id,"COW");
        }
        else
        {
          loginDataBaseAdapter.deleteData(id,"HEIFER");
        }

      }
    });

    dialog_Builder.setNegativeButton("Close", new DialogInterface.OnClickListener() {
      public void onClick(DialogInterface dialog, int whichButton) {
        //pass
      }
    });
    AlertDialog build = dialog_Builder.create();
    build.show();



  }



  public void case_two()
  {


    AlertDialog.Builder dialog_Builder = new AlertDialog.Builder(DemoActivity.this);
    LayoutInflater inflate = getLayoutInflater();
    final View dialog_View = inflate.inflate(R.layout.conception_dialog, null);
    dialog_Builder.setView(dialog_View);

    final EditText animal_id_conception = (EditText) dialog_View.findViewById(R.id.cow_name_conception);

    final EditText calving_date = (EditText) dialog_View.findViewById(R.id.calving_date_conception);

    final TextView conception_date = (TextView) dialog_View.findViewById(R.id.text_calving);


    calving_date.setOnFocusChangeListener(new View.OnFocusChangeListener() {
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
              calving_date.setText(sdf.format(c.getTime()));
              c.add(Calendar.DATE, 285);
              conception_date.setText(sdf.format(c.getTime()));

               setCalving(sdf.format(c.getTime()));
              // nextField.requestFocus(); //moves the focus to something else after dialog is closed
            }
          };
          datePickerFragment.show(getFragmentManager(), "datePicker");
        }
      }
    });

    final Button btn_add_conception = (Button) dialog_View.findViewById(R.id.btn_add_conception);

    btn_add_conception.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

        ArrayList<HashMap<String,String>> info = new ArrayList<>();

        LoginDataBaseAdapter loginDataBaseAdapter = new LoginDataBaseAdapter(DemoActivity.this);
        loginDataBaseAdapter.open();

        info = loginDataBaseAdapter.getHeiferId(animal_id_conception.getText().toString().trim());

        loginDataBaseAdapter.insertEntryCattle(info.get(0).get("a"),info.get(0).get("b"),getCalving(),
                info.get(0).get("c"),info.get(0).get("d"));

        loginDataBaseAdapter.deleteData(animal_id_conception.getText().toString().trim(),"HEIFER");


      }
    });

    dialog_Builder.setNegativeButton("Close", new DialogInterface.OnClickListener() {
      public void onClick(DialogInterface dialog, int whichButton) {
        //pass
      }
    });
    AlertDialog build = dialog_Builder.create();
    build.show();
  }





  public void case_one()
  {

    /** Syncing **/




    /**end of sync **/


    final String[] view_cat = {"BREEDING","COW","HEIFER","MILK"};

    AlertDialog.Builder dialog_Builder = new AlertDialog.Builder(DemoActivity.this);
    LayoutInflater inflate = getLayoutInflater();
    final View dialog_View = inflate.inflate(R.layout.search_dialog, null);
    dialog_Builder.setView(dialog_View);

    MaterialBetterSpinner spin = (MaterialBetterSpinner) dialog_View.findViewById(R.id.android_material_spinner2);

    final RecyclerView recyclerView = (RecyclerView) dialog_View.findViewById(R.id.recycler_view);

    final Button btn_add_search = (Button) dialog_View.findViewById(R.id.btn_add_search);

    final EditText editText = (EditText) dialog_View.findViewById(R.id.edit_one);

    final String id = editText.getText().toString().trim();

    final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
            android.R.layout.simple_dropdown_item_1line, view_cat);

    spin.setAdapter(arrayAdapter);

    spin.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        view_category = view_cat[position];
        item_clicked = true;
      }
    });



    btn_add_search.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {


        LoginDataBaseAdapter loginDataBaseAdapter = new LoginDataBaseAdapter(DemoActivity.this);
        loginDataBaseAdapter.open();



        if(view_category.equals("BREEDING"))
        {

          List<Data_Breed> dataBreedList;

          RecyclerView.LayoutManager layoutManager;
          RecyclerViewAdapterBreed adapter;

          ArrayList<HashMap<String,String>> info = new ArrayList<>();

          dataBreedList = new ArrayList<>();

          info = loginDataBaseAdapter.getBreedId(id);

          for(int index =0; index<info.size(); index++)
          {
            dataBreedList.add(new Data_Breed(info.get(index).get("a"),info.get(index).get("b"),info.get(index).get("c"),info.get(index).get("d")));
          }

          layoutManager =new LinearLayoutManager(DemoActivity.this);
          recyclerView.setLayoutManager(layoutManager);
          recyclerView.setHasFixedSize(true);
          adapter =new RecyclerViewAdapterBreed(dataBreedList);
          recyclerView.setAdapter(adapter);
        }
        else if (view_category.equals("COW"))
        {

          List<Data_Cattle> dataCattleList;
          RecyclerView.LayoutManager layoutManager;
          RecyclerViewAdapterCattle adapter;

          ArrayList<HashMap<String,String>> info = new ArrayList<>();

          dataCattleList = new ArrayList<>();

          info = loginDataBaseAdapter.getCattleId(id);

          for(int index =0; index<info.size(); index++)
          {
            dataCattleList.add(new Data_Cattle(info.get(index).get("a"),info.get(index).get("b")
                    ,info.get(index).get("c"),info.get(index).get("d"),info.get(index).get("e")));
          }

          layoutManager =new LinearLayoutManager(DemoActivity.this);
          recyclerView.setLayoutManager(layoutManager);
          recyclerView.setHasFixedSize(true);
          adapter =new RecyclerViewAdapterCattle(dataCattleList);
          recyclerView.setAdapter(adapter);

        }
        else if (view_category.equals("HEIFER"))
        {

          List<Data_Heifer> dataHeiferList;
          RecyclerView.LayoutManager layoutManager;
          RecyclerViewAdapterHeifer adapter;

          ArrayList<HashMap<String,String>> info = new ArrayList<>();


          dataHeiferList = new ArrayList<>();


          info = loginDataBaseAdapter.getHeiferId(id);

          for(int index =0; index<info.size(); index++)
          {
            dataHeiferList.add(new Data_Heifer(info.get(index).get("a"),info.get(index).get("b")
                    ,info.get(index).get("c"),info.get(index).get("d")));
          }

          layoutManager =new LinearLayoutManager(DemoActivity.this);
          recyclerView.setLayoutManager(layoutManager);
          recyclerView.setHasFixedSize(true);
          adapter =new RecyclerViewAdapterHeifer(dataHeiferList);
          recyclerView.setAdapter(adapter);

        }
        else
        {

          List<Data_Milk> dataMilkList;
          RecyclerView.LayoutManager layoutManager;
          RecyclerViewAdapterMilk adapter;
          ArrayList<HashMap<String,String>> info = new ArrayList<>();

          dataMilkList = new ArrayList<>();

          info = loginDataBaseAdapter.getMilkId(id);

          for(int index =0; index<info.size(); index++)
          {
            dataMilkList.add(new Data_Milk(info.get(index).get("a"),info.get(index).get("b")
                    ,info.get(index).get("c"),info.get(index).get("d")));
          }

          layoutManager =new LinearLayoutManager(DemoActivity.this);
          recyclerView.setLayoutManager(layoutManager);
          recyclerView.setHasFixedSize(true);
          adapter =new RecyclerViewAdapterMilk(dataMilkList);
          recyclerView.setAdapter(adapter);

        }

      }
    });

    dialog_Builder.setNegativeButton("Close", new DialogInterface.OnClickListener() {
      public void onClick(DialogInterface dialog, int whichButton) {
        //pass
      }
    });
    AlertDialog build = dialog_Builder.create();
    build.show();

  }

  public String getCalving()
  {
    return calving;
  }

  public void setCalving (String calving)
  {
    this.calving = calving;
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_logout) {
      session.logoutUser();
      finish();
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  public void case_sync()
  {
    SyncAdapter syncAdapter = new SyncAdapter(this);

    syncAdapter.sycnCow();
    syncAdapter.syncBreed();
    syncAdapter.syncHeifer();
    syncAdapter.syncMilk();


    SyncAdapter syncAdapter1 = new SyncAdapter(this);
    syncAdapter1.SyncCow();
    syncAdapter1.SyncHeifer();
    syncAdapter1.SyncMilk();
    syncAdapter1.SyncBreed();

  }
}
