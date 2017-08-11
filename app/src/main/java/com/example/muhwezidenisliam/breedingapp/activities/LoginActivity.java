package com.example.muhwezidenisliam.breedingapp.activities;

import android.app.ProgressDialog;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;

import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import android.widget.TextView;
import android.widget.Toast;


import com.example.muhwezidenisliam.breedingapp.R;

import com.kosalgeek.asynctask.AsyncResponse;
import com.kosalgeek.asynctask.PostResponseAsyncTask;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;
//import butterknife.InjectView;

public class LoginActivity extends AppCompatActivity implements AsyncResponse{



    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;



    //String storedUsername, storedPassword;

    String User, Password, user_name;

    SessionManager session;


    Boolean login = true;


//    ProgressDialog progressDialog;

    @Bind(R.id.input_email)
    EditText _passwordText;
    @Bind(R.id.input_name)
    EditText _nameText;
    @Bind(R.id.btn_login)
    Button _loginButton;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);


        // loginDataBaseAdapter = new LoginDataBaseAdapter(getApplicationContext());loginDataBaseAdapter.open();

        _loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                    login();

            }
        });

    }

    public void login() {

            Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed_one();
            return;
        }

            _loginButton.setEnabled(false);

//            progressDialog = new ProgressDialog(LoginActivity.this,
//                    R.style.AppTheme_Dark_Dialog);
//            progressDialog.setIndeterminate(true);
//            progressDialog.setMessage("Authenticating...");
//
//            progressDialog.show();

            User = _nameText.getText().toString();
            Password = _passwordText.getText().toString();

            // TODO: Implement your own authentication logic here.

     //   storedUsername = loginDataBaseAdapter.getSingleEntry_username(User);

       // storedPassword = loginDataBaseAdapter.getSingleEntry_password(Password);

        HashMap <String, String> postData = new HashMap<>();
        postData.put("username",User);
        postData.put("password",Password);

        PostResponseAsyncTask task = new PostResponseAsyncTask(this ,postData,this);
        task.execute("http://welshuganda.com/breeding/json_files/login.php");

//
//            new android.os.Handler().postDelayed(
//                    new Runnable() {
//                        public void run() {
//
//
//                            // On complete call either onLoginSuccess or onLoginFailed
//                            if(login)
//                            {
//                                onLoginSuccess();
//                            }
//                            else
//                            {
//                                onLoginFailed();
//                            }
//
//                            // onLoginFailed();
//
//                        }
//                    }, 3000);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                this.finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        // Disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void onLoginSuccess() {


            Intent loggedin=new Intent(LoginActivity.this,DemoActivity.class);
            finish();
            startActivity(loggedin);



    }

    public void onLoginSuccess_one() {


        Intent loggedin=new Intent(LoginActivity.this,Demo_Activity.class);
        finish();
        startActivity(loggedin);



    }

    public void onLoginFailed() {

        _nameText.setText("");

        _passwordText.setText("");

        _loginButton.setEnabled(true);

//        progressDialog.dismiss();

        Toast.makeText(this,"Wrong username or password (Case sensitive)",Toast.LENGTH_SHORT).show();

    }


    public void onLoginFailed_one() {

        _nameText.setText("");

        _passwordText.setText("");

        _loginButton.setEnabled(true);



    }
    public boolean validate() {
        boolean valid = true;

        String name = _nameText.getText().toString();
        String password = _passwordText.getText().toString();

        if (name.isEmpty()) {

            _nameText.setError("enter a Username");
            valid = false;
        } else {
            _nameText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        overridePendingTransition(0,0);
        super.onPause();
    }

    @Override
    public void processFinish(String s) {

         Log.d("string",s);

        try {
        JSONObject jsonResponse = new JSONObject(s);
        boolean success = jsonResponse.getBoolean("success");

            if (success) {

                String user = jsonResponse.getString("username");
                String pass = jsonResponse.getString("password");
                String role  = jsonResponse.getString("role");

                session = new SessionManager(this);

                session.createLoginSession(user, pass);

                if(role.equalsIgnoreCase("Stake holder"))
                {
                    onLoginSuccess_one();
                }
                else
                {
                    onLoginSuccess();
                }

            }

            else
            {
                onLoginFailed();
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
