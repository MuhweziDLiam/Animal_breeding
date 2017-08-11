package com.example.muhwezidenisliam.breedingapp.activities;

import android.content.Context;
import android.util.Log;

import com.example.muhwezidenisliam.breedingapp.databases.LoginDataBaseAdapter;
import com.kosalgeek.asynctask.AsyncResponse;
import com.kosalgeek.asynctask.PostResponseAsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Muhwezi Denis Liam on 7/19/2017.
 */

public class SyncAdapter {

    Context context;

    public SyncAdapter(Context context)
    {
        this.context = context;
    }

    public void syncBreed()
    {
        LoginDataBaseAdapter loginDataBaseAdapter
                = new LoginDataBaseAdapter(context);

        ArrayList<HashMap<String,String>> info = new ArrayList<>();

        info = loginDataBaseAdapter.getBreed();

        for(int index =0; index<info.size(); index++)
        {
            HashMap<String,String> postData = new HashMap<>();
            postData.put("a",info.get(index).get("a"));
            postData.put("b",info.get(index).get("b"));
            postData.put("c",info.get(index).get("c"));
            postData.put("d",info.get(index).get("d"));
            PostResponseAsyncTask breedTask = new PostResponseAsyncTask(context, postData,new AsyncResponse() {
                @Override
                public void processFinish(String s) {
                    Log.d("out",s);
                }
            });
            breedTask.execute("http://welshuganda.com/breeding/json_files/add_breedingJson.php");

        }
    }

    public void sycnCow()
    {
        LoginDataBaseAdapter loginDataBaseAdapter
                = new LoginDataBaseAdapter(context);

        ArrayList<HashMap<String,String>> info = new ArrayList<>();

        info = loginDataBaseAdapter.getCattle();

        for(int index =0; index<info.size(); index++)
        {
            HashMap<String,String> postData = new HashMap<>();
            postData.put("a",info.get(index).get("a"));
            postData.put("b",info.get(index).get("b"));
            postData.put("c",info.get(index).get("c"));
            postData.put("d",info.get(index).get("d"));
            postData.put("e",info.get(index).get("e"));
            PostResponseAsyncTask breedTask = new PostResponseAsyncTask(context,postData, new AsyncResponse() {
                @Override
                public void processFinish(String s) {
                    Log.d("out",s);
                }
            });
            breedTask.execute("http://welshuganda.com/breeding/json_files/add_cowJson.php");

        }

    }

    public void syncHeifer()
    {
        LoginDataBaseAdapter loginDataBaseAdapter
                = new LoginDataBaseAdapter(context);

        ArrayList<HashMap<String,String>> info = new ArrayList<>();

        info = loginDataBaseAdapter.getHeifer();

        for(int index =0; index<info.size(); index++)
        {
            HashMap<String,String> postData = new HashMap<>();
            postData.put("a",info.get(index).get("a"));
            postData.put("b",info.get(index).get("b"));
            postData.put("c",info.get(index).get("c"));
            postData.put("d",info.get(index).get("d"));
            PostResponseAsyncTask breedTask = new PostResponseAsyncTask(context,postData, new AsyncResponse() {
                @Override
                public void processFinish(String s) {
                    Log.d("out",s);
                }
            });
            breedTask.execute("http://welshuganda.com/breeding/json_files/add_heiferJson.php");

        }
    }

    public void syncMilk()
    {
        LoginDataBaseAdapter loginDataBaseAdapter
                = new LoginDataBaseAdapter(context);

        ArrayList<HashMap<String,String>> info = new ArrayList<>();

        info = loginDataBaseAdapter.getMilk();

        for(int index =0; index<info.size(); index++)
        {
            HashMap<String,String> postData = new HashMap<>();
            postData.put("a",info.get(index).get("a"));
            postData.put("b",info.get(index).get("b"));
            postData.put("c",info.get(index).get("c"));
            postData.put("d",info.get(index).get("d"));
            PostResponseAsyncTask breedTask = new PostResponseAsyncTask(context,postData, new AsyncResponse() {
                @Override
                public void processFinish(String s) {
                    Log.d("out",s);
                }
            });
            breedTask.execute("http://welshuganda.com/breeding/json_files/add_milkingJson.php");

        }

    }

    public void SyncBreed()
    {

        PostResponseAsyncTask DataTask = new PostResponseAsyncTask(context, new AsyncResponse() {
            @Override
            public void processFinish(String s) {
                Log.d("string", s);

                LoginDataBaseAdapter loginDataBaseAdapter
                        = new LoginDataBaseAdapter(context);

                loginDataBaseAdapter.open();

                try {
                    JSONObject jsonResponse = new JSONObject(s);
                    JSONArray jsonArray = jsonResponse.getJSONArray("result");


                    for (int index = 0; index < jsonArray.length(); index++) {


                        JSONObject jsonObject = jsonArray.getJSONObject(index);

                        String animal_id = jsonObject.getString("a");
                        String mating_day = jsonObject.getString("b");
                        String dam = jsonObject.getString("c");
                        String service_times = jsonObject.getString("d");

                        loginDataBaseAdapter.insertEntryBreed(animal_id,mating_day,dam,service_times);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        });

        DataTask.execute("http://welshuganda.com/breeding/json_files/breedingJsonData.php");
    }

    public void SyncCow()
    {


        PostResponseAsyncTask DataTask = new PostResponseAsyncTask(context, new AsyncResponse() {
            @Override
            public void processFinish(String s) {
                Log.d("string", s);


                try {
                    LoginDataBaseAdapter loginDataBaseAdapter
                            = new LoginDataBaseAdapter(context);

                    loginDataBaseAdapter.open();


                    JSONObject jsonResponse = new JSONObject(s);
                    JSONArray jsonArray = jsonResponse.getJSONArray("result");


                    for (int index = 0; index < jsonArray.length(); index++) {


                        JSONObject jsonObject = jsonArray.getJSONObject(index);

                        String animal_id = jsonObject.getString("a");
                        String dob = jsonObject.getString("b");
                        String calving = jsonObject.getString("e");
                        String breed = jsonObject.getString("d");
                        String weight = jsonObject.getString("c");

                        loginDataBaseAdapter.insertEntryCattle(animal_id,dob,calving,breed,weight);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        });

        DataTask.execute("http://welshuganda.com/breeding/json_files/cowJsonData.php");

    }

    public void SyncHeifer()
    {


        PostResponseAsyncTask DataTask = new PostResponseAsyncTask(context, new AsyncResponse() {
            @Override
            public void processFinish(String s) {
                Log.d("string", s);

                LoginDataBaseAdapter loginDataBaseAdapter
                        = new LoginDataBaseAdapter(context);

                loginDataBaseAdapter.open();

                try {
                    JSONObject jsonResponse = new JSONObject(s);
                    JSONArray jsonArray = jsonResponse.getJSONArray("result");


                    for (int index = 0; index < jsonArray.length(); index++) {


                        JSONObject jsonObject = jsonArray.getJSONObject(index);

                        String animal_id = jsonObject.getString("a");
                        String dob = jsonObject.getString("b");
                        String calving = jsonObject.getString("c");
                        String breed = jsonObject.getString("d");

                        loginDataBaseAdapter.insertEntryHeifer(animal_id,dob,calving,breed);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        });

        DataTask.execute("http://welshuganda.com/breeding/json_files/heiferJsonData.php");

    }

    public void SyncMilk()
    {


        PostResponseAsyncTask DataTask = new PostResponseAsyncTask(context, new AsyncResponse() {
            @Override
            public void processFinish(String s) {
                Log.d("string", s);

                LoginDataBaseAdapter loginDataBaseAdapter
                        = new LoginDataBaseAdapter(context);

                loginDataBaseAdapter.open();

                try {
                    JSONObject jsonResponse = new JSONObject(s);
                    JSONArray jsonArray = jsonResponse.getJSONArray("result");


                    for (int index = 0; index < jsonArray.length(); index++) {


                        JSONObject jsonObject = jsonArray.getJSONObject(index);

                        String animal_id = jsonObject.getString("a");
                        String dob = jsonObject.getString("b");
                        String calving = jsonObject.getString("c");
                        String breed = jsonObject.getString("d");

                        loginDataBaseAdapter.insertEntryMilk(animal_id,dob,calving,breed);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        });

        DataTask.execute("http://welshuganda.com/breeding/json_files/milkingJsonData.php");

    }
}
