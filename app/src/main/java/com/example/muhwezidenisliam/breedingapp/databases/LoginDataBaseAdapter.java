package com.example.muhwezidenisliam.breedingapp.databases;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;


public class LoginDataBaseAdapter {

    static final String DATABASE_NAME = "breeding.db";
    static final int DATABASE_VERSION = 1;

    static final String DATABASE_CREATE_BREED = "create table "+"BREEDING"+
            "( " +"ID integer primary key autoincrement,"+ "ANIMAL_ID text,"+ "MATING_DATE text,"+ "DAM text,"+"SERVICE_NUMBER text,"+ "unique(ANIMAL_ID) )";

    static final String DATABASE_CREATE_CATTLE = "create table "+"COW"+
            "( " +"ID integer primary key autoincrement,"+ "ANIMAL_ID text unique," +
            ""+ "DATE_OF_BIRTH text,"+"CATTLE_CALVING text,"+"CATTLE_BREED text,"+"CATTLE_WEIGHT text,"+ "unique(ANIMAL_ID) ) ";

    static final String DATABASE_CREATE_HEIFER = "create table "+"HEIFER"+
            "( " +"ID integer primary key autoincrement,"+ "ANIMAL_ID text unique,"+
            "DATE_OF_BIRTH text,"+"HEIFER_BREED text,"+"HEIFER_WEIGHT text,"+ "unique(ANIMAL_ID)) ";

    static final String DATABASE_CREATE_MILK = "create table "+"MILK"+
            "( " +"ID integer primary key autoincrement,"+ "ANIMAL_ID text,"
                + "DATE_OF_MILKING text,"+"MILKING_TIMES text,"+"LITRES_MILK text ) ";



    public  SQLiteDatabase db;
    private final Context context;
    private DataBaseHelper dbHelper;



    public  LoginDataBaseAdapter(Context _context)
    {
        context = _context;
        dbHelper = new DataBaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);


    }
    public  LoginDataBaseAdapter open() throws SQLException
    {
        db = dbHelper.getWritableDatabase();
        return this;
    }
    public void close()
    {
        db.close();
    }


    public  SQLiteDatabase getDatabaseInstance()
    {
        return db;
    }

    public int getProfilesMilkCount() {
        String countQuery = "SELECT  * FROM " + "MILK";
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int cnt = cursor.getCount();
        cursor.close();
        return cnt;
    }


                /* insertion methods into database */
    public void insertEntryBreed(String animal_id,String service_date,String dam,String service_times)
    {
        ContentValues newValues = new ContentValues();
        newValues.put("ANIMAL_ID", animal_id);
        newValues.put("MATING_DATE",service_date);
        newValues.put("DAM",dam);
        newValues.put("SERVICE_NUMBER",service_times);

        db.insertWithOnConflict("BREEDING", null, newValues,SQLiteDatabase.CONFLICT_IGNORE);
    }


    public void insertEntryCattle(String animal_id,String date_of_birth,String cattle_sex, String cattle_weight, String cattle_breed)
    {
        ContentValues newValues = new ContentValues();
        newValues.put("ANIMAL_ID", animal_id);
        newValues.put("DATE_OF_BIRTH",date_of_birth);
        newValues.put("CATTLE_CALVING",cattle_sex);
        newValues.put("CATTLE_BREED",cattle_breed);
        newValues.put("CATTLE_WEIGHT",cattle_weight);


        db.insertWithOnConflict("COW", null, newValues,SQLiteDatabase.CONFLICT_IGNORE);
    }


    public void insertEntryHeifer(String animal_id,String dam_type,String heifer_weight, String feeding_behaviour)
    {
        ContentValues newValues = new ContentValues();
        newValues.put("ANIMAL_ID", animal_id);
        newValues.put("DATE_OF_BIRTH",dam_type);
        newValues.put("HEIFER_BREED",feeding_behaviour);
        newValues.put("HEIFER_WEIGHT",heifer_weight);


        db.insertWithOnConflict("HEIFER", null, newValues,SQLiteDatabase.CONFLICT_IGNORE);
    }


    public void insertEntryMilk(String animal_id,String date_of_milking,String milking_times, String litres_milk)
    {
        ContentValues newValues = new ContentValues();
        newValues.put("ANIMAL_ID", animal_id);
        newValues.put("DATE_OF_MILKING",date_of_milking);
        newValues.put("MILKING_TIMES",milking_times);
        newValues.put("LITRES_MILK",litres_milk);

        db.insert("MILK", null, newValues);
    }


                        /*  Update field  */

    public void updateEntryBreed(String id,String animal_id,String service_date,String dam,String service_times)
    {
        ContentValues newValues = new ContentValues();
        newValues.put("ANIMAL_ID", animal_id);
        newValues.put("SERVICE_DATE",service_date);
        newValues.put("DAM",dam);
        newValues.put("SERVICE_TIMES",service_times);

        db.update("BREEDING",newValues, "ANIMAL_ID LIKE "+"'%"+id+"%'",null);
    }



    public void updateEntryCattle(String id,String animal_id,String date_of_birth,String cattle_sex, String cattle_weight, String cattle_breed)
    {
        ContentValues newValues = new ContentValues();
        newValues.put("ANIMAL_ID", animal_id);
        newValues.put("DATE_OF_BIRTH",date_of_birth);
        newValues.put("CATTLE_SEX",cattle_sex);
        newValues.put("CATTLE_WEIGHT",cattle_weight);
        newValues.put("CATTLE_BREED",cattle_breed);

        db.update("COW", newValues,"ANIMAL_ID LIKE"+"'%"+id+"%'",null);
    }


    public void updateEntryHeifer(String id,String animal_id,String dam_type,String heifer_weight, String feeding_behaviour)
    {
        ContentValues newValues = new ContentValues();
        newValues.put("ANIMAL_ID", animal_id);
        newValues.put("DAM_TYPE",dam_type);
        newValues.put("HEIFER_WEIGHT",heifer_weight);
        newValues.put("FEEDING_BEHAVIOUR",feeding_behaviour);

        db.update("HEIFER", newValues,"ANIMAL_ID LIKE "+"'%"+id+"%'",null);
    }


    public void updateEntryMilk(String id,String animal_id,String date_of_milking,String milking_times, String litres_milk)
    {
        ContentValues newValues = new ContentValues();
        newValues.put("ANIMAL_ID", animal_id);
        newValues.put("DATE_OF_MILKING",date_of_milking);
        newValues.put("MILKING_TIMES",milking_times);
        newValues.put("LITRES_MILK",litres_milk);

        db.update("MILK",newValues, "ANIMAL_ID LIKE "+"'%"+id+"%'",null);
    }

    /* replace on conflict */


                        /* getting methods*/

    public ArrayList<HashMap<String, String>> getBreed(){

        ArrayList<HashMap<String,String>> array = new ArrayList<>();
        String selectQuery = "SELECT * FROM BREEDING";

        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        //cursor.moveToFirst();

        int i=0;

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> user = new HashMap<String, String>();
                user.put("a", cursor.getString(1));
                user.put("b", cursor.getString(2));
                user.put("c", cursor.getString(3));
                user.put("d",cursor.getString(4));
                array.add(i,user);
                i++;
            } while (cursor.moveToNext());
        }
        cursor.close();

        return array;
    }

    public ArrayList<HashMap<String, String>> getCattle(){

        ArrayList<HashMap<String,String>> array = new ArrayList<>();
        String selectQuery = "SELECT * FROM COW";

        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        //cursor.moveToFirst();

        int i=0;

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> user = new HashMap<String, String>();
                user.put("a", cursor.getString(1));
                user.put("b", cursor.getString(2));
                user.put("c", cursor.getString(3));
                user.put("d", cursor.getString(4));
                user.put("e", cursor.getString(5));
                array.add(i,user);
                i++;
            } while (cursor.moveToNext());
        }
        cursor.close();

        return array;
    }

    public ArrayList<HashMap<String, String>> getHeifer(){

        ArrayList<HashMap<String,String>> array = new ArrayList<>();
        String selectQuery = "SELECT * FROM HEIFER";

        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        //cursor.moveToFirst();

        int i=0;

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> user = new HashMap<String, String>();
                user.put("a", cursor.getString(1));
                user.put("b", cursor.getString(2));
                user.put("c", cursor.getString(3));
                user.put("d", cursor.getString(4));
                array.add(i,user);
                i++;
            } while (cursor.moveToNext());
        }
        cursor.close();

        return array;
    }

    public ArrayList<HashMap<String, String>> getMilk(){

        ArrayList<HashMap<String,String>> array = new ArrayList<>();
        String selectQuery = "SELECT * FROM MILK";

        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        //cursor.moveToFirst();

        int i=0;

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> user = new HashMap<String, String>();
                user.put("a", cursor.getString(1));
                user.put("b", cursor.getString(2));
                user.put("c", cursor.getString(3));
                user.put("d", cursor.getString(4));
                array.add(i,user);
                i++;
            } while (cursor.moveToNext());
        }
        cursor.close();

        return array;
    }

    public ArrayList<HashMap<String, String>> getBreedId(String id){

        ArrayList<HashMap<String,String>> array = new ArrayList<>();
        String selectQuery = "SELECT * FROM BREEDING WHERE ANIMAL_ID LIKE '%"+id+"%'";

        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        //cursor.moveToFirst();

        int i=0;

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> user = new HashMap<String, String>();
                user.put("a", cursor.getString(1));
                user.put("b", cursor.getString(2));
                user.put("c", cursor.getString(3));
                user.put("d", cursor.getString(3));
                array.add(i,user);
                i++;
            } while (cursor.moveToNext());
        }
        cursor.close();

        return array;
    }

    public ArrayList<HashMap<String, String>> getCattleId(String id){

        ArrayList<HashMap<String,String>> array = new ArrayList<>();
        String selectQuery = "SELECT * FROM COW WHERE ANIMAL_ID LIKE '%"+id+"%'";


        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        //cursor.moveToFirst();

        int i=0;

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> user = new HashMap<String, String>();
                user.put("a", cursor.getString(1));
                user.put("b", cursor.getString(2));
                user.put("c", cursor.getString(3));
                user.put("d", cursor.getString(4));
                user.put("e", cursor.getString(5));
                array.add(i,user);
                i++;
            } while (cursor.moveToNext());
        }
        cursor.close();

        return array;
    }

    public ArrayList<HashMap<String, String>> getHeiferId(String id){

        ArrayList<HashMap<String,String>> array = new ArrayList<>();
        String selectQuery = "SELECT * FROM HEIFER WHERE ANIMAL_ID LIKE '%"+id+"%'";


        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        //cursor.moveToFirst();

        int i=0;

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> user = new HashMap<String, String>();

                user.put("a", cursor.getString(1));
                user.put("b", cursor.getString(2));
                user.put("c", cursor.getString(3));
                user.put("d", cursor.getString(4));
                array.add(i,user);
                i++;
            } while (cursor.moveToNext());
        }
        cursor.close();

        return array;
    }

    public ArrayList<HashMap<String, String>> getMilkId(String id){

        ArrayList<HashMap<String,String>> array = new ArrayList<>();
        String selectQuery = "SELECT * FROM MILK WHERE ANIMAL_ID LIKE '%"+id+"%'";

        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        //cursor.moveToFirst();

        int i=0;

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> user = new HashMap<String, String>();
                user.put("a", cursor.getString(1));
                user.put("b", cursor.getString(2));
                user.put("c", cursor.getString(3));
                user.put("d", cursor.getString(4));
                array.add(i,user);
                i++;
            } while (cursor.moveToNext());
        }
        cursor.close();

        return array;
    }

    public Integer deleteData (String id, String TABLE_NAME) {
        db = dbHelper.getWritableDatabase();
        return db.delete(TABLE_NAME, "ANIMAL_ID = ?",new String[] {id});
    }


    public ArrayList<HashMap<String, String>> getAggMilk(){

        ArrayList<HashMap<String,String>> array = new ArrayList<>();
        String selectQuery = "SELECT ID, ANIMAL_ID, SUM(LITRES_MILK), MILKING_TIMES FROM MILK GROUP BY ANIMAL_ID";

        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        //cursor.moveToFirst();

        int i=0;

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> user = new HashMap<String, String>();
                user.put("a", cursor.getString(0));
                user.put("b", cursor.getString(1));
                user.put("c", cursor.getString(2));
                user.put("d", cursor.getString(3));

                array.add(i,user);
                i++;
            } while (cursor.moveToNext());
        }
        cursor.close();

        return array;
    }

    public ArrayList<HashMap<String, String>> getRepBreed(){

        ArrayList<HashMap<String,String>> array = new ArrayList<>();
        String selectQuery = "SELECT ANIMAL_ID, SERVICE_NUMBER FROM BREEDING";

        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        //cursor.moveToFirst();

        int i=0;

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> user = new HashMap<String, String>();
                user.put("a", cursor.getString(0));
                user.put("b", cursor.getString(1));

                array.add(i,user);
                i++;
            } while (cursor.moveToNext());
        }
        cursor.close();

        return array;
    }

}
