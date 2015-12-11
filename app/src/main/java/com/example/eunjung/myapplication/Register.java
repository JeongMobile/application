package com.example.eunjung.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

public class Register extends AppCompatActivity implements View.OnClickListener {

    Button bRegister, bCancel;
    EditText etName, etUsername, etPassword, etCheckPassword;
    EditText etPhone, etEmail, etUserID, etNicName;

    User user;
    String checkPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etUserID = (EditText)findViewById(R.id.etUserID);
        etName = (EditText)findViewById(R.id.etname);
        etNicName = (EditText)findViewById(R.id.etnicname);
        etUsername = (EditText)findViewById(R.id.etUsername);
        etPassword = (EditText)findViewById(R.id.etPassword);
        etCheckPassword = (EditText)findViewById(R.id.etCheckPassword);
        etPhone = (EditText)findViewById(R.id.etPhone);
        etEmail = (EditText)findViewById(R.id.etEmail);

        bRegister = (Button) findViewById( R.id.bRegister);
        bCancel = (Button) findViewById( R.id.bCancel);
        bRegister.setOnClickListener(this);
        bCancel.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bRegister:
                user = new User(etUserID.getText().toString().trim(),
                        etName.getText().toString().trim(),
                        etNicName.getText().toString().trim(),
                        etPassword.getText().toString().trim(),
                        etPhone.getText().toString().trim(),
                        etEmail.getText().toString().trim()
                );
                checkPassword = etCheckPassword.getText().toString().trim();
                if(user.userID.equals("") || user.userName.equals("") || user.password.equals("") || user.nicName.equals("") || user.phoneNumber.equals("") || user.Email.equals("")){
                    Toast.makeText(getApplicationContext(), "데이터를 다 넣어 주세요!.", Toast.LENGTH_LONG).show();
                    return;
                }
                if(!user.password.equals(checkPassword)) {
                    Toast.makeText(getApplicationContext(), "password가 같지 않습니다.", Toast.LENGTH_SHORT).show();
                    return;
                }

                String urlString = "http://"+NetworkManager.ServerIP+":8899/User";
                new JSONTask().execute(urlString);

                break;

            case R.id.bCancel:
                Register.this.finish();
                break;
        }

    }

    public class JSONTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;
            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                Log.d("line : ", "connect");
                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();
                String line = "data is null";
                Log.d("line : ", line);
                while ((line = reader.readLine()) != null) {
                    Log.d("line : ", line);
                    buffer.append(line);
                }
                return buffer.toString();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (connection != null)
                    connection.disconnect();
                try {
                    if (reader != null)
                        reader.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {

            super.onPostExecute(result);
            if(result == null){
                Log.d("Register","null");
                return ;
            }
            if(result.equals("success")){
                Toast.makeText(getApplicationContext(), "계정 만들기 성공!.", Toast.LENGTH_LONG).show();
                Log.d("Register","success");
                Register.this.finish();
                return;
            }
            try {
                JSONArray jsonArray = new JSONArray(result);

                for(int i = 0; i < jsonArray.length(); i++) {
                    JSONObject json = jsonArray.getJSONObject(i);
                    String module = json.getString("userID");
                    Log.d("Register",module);
                    if(user.userID.equals(module)) {
                        Toast.makeText(getApplicationContext(), "같은 아이디가 존재합니다..", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }

                Log.d("Register", "True");
                JSONObject temp = new JSONObject();
                temp.put("userID",URLEncoder.encode(user.userID, "UTF-8"));
                temp.put("Email",URLEncoder.encode(user.Email, "UTF-8"));
                temp.put("nicName",URLEncoder.encode(user.nicName, "UTF-8"));
                temp.put("password",URLEncoder.encode(user.password, "UTF-8"));
                temp.put("phoneNumber",URLEncoder.encode(user.phoneNumber, "UTF-8"));
                temp.put("userName",URLEncoder.encode(user.userName, "UTF-8"));


                String url ="http://"+NetworkManager.ServerIP+":8899/InsertUser/?data="+temp.toString();
                new JSONTask().execute(url);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}