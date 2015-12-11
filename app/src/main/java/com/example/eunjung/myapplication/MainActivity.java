package com.example.eunjung.myapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    Button bLogin;
    EditText etUserID, etPassword;
    TextView tvRegisterLink;

    String userID, password;

//        UserLocalStore userLocalStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUserID = (EditText) findViewById(R.id.etUserID);
        etPassword = (EditText) findViewById(R.id.etPassword);
        tvRegisterLink = (TextView) findViewById(R.id.tvRegisterLink);

        bLogin = (Button) findViewById(R.id.bLogin);

        bLogin.setOnClickListener(this);
        tvRegisterLink.setOnClickListener(this);

//            userLocalStore = new UserLocalStore(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bLogin:
                userID = etUserID.getText().toString();
                password = etPassword.getText().toString();

                String urlString = "http://165.194.33.149:8899/Login/";
                try {
                    urlString += URLEncoder.encode(userID, "UTF-8") + "/" + URLEncoder.encode(password, "UTF-8");

                } catch (Exception e){
                    e.printStackTrace();
                }
                new JSONTask().execute(urlString);

                break;

            case R.id.tvRegisterLink:
                startActivity(new Intent(this, Register.class));
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
            if (result == null) {
                return;
            }
            if (result.equals("success")) {
                startActivity(new Intent(getApplication(), showTodoLists.class)); // 로딩이 끝난후 이동할 Activity
                MainActivity.this.finish(); // 로딩페이지 Activity Stack에서 제거
            } else if (result.equals("error")) {
                Toast.makeText(getApplicationContext(), "다시 확인후 로그인 하세요.", Toast.LENGTH_LONG).show();

            } else
                return;
        }

    }
}
