package com.example.eunjung.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SplashActivity extends Activity {

    public static int MAX_ID = 0;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Handler hd = new Handler();
        hd.postDelayed(new splashhandler(), 1000); // 3초 후에 hd Handler 실행
    }

    private class splashhandler implements Runnable {
        DBManager infoDBmanager;

        public void run() {

            User user = null;
            user = DAO.getUser(getApplicationContext());

            //SQLiteDatabase db = infoDBmanager.getReadableDatabase();
            if(user != null) {
                String urlString = "http://165.194.33.149:8899/Login/";
                urlString += user.userID + "/" + user.password;
                new JSONTask().execute(urlString);
            }
            else{
                Log.d("Login","NULL");
                Toast.makeText(getApplicationContext(), "User 정보가 없습니다.", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplication(),  MainActivity.class)); // 로딩이 끝난후 이동할 Activity
                SplashActivity.this.finish(); // 로딩페이지 Activity Stack에서 제거
            }
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
                return ;
            }
            if (result.equals("success")) {
                startActivity(new Intent(getApplication(), showTodoLists.class)); // 로딩이 끝난후 이동할 Activity
                SplashActivity.this.finish(); // 로딩페이지 Activity Stack에서 제거
            } else if(result.equals("error")) {
                Toast.makeText(getApplicationContext(), "User 정보가 올바르지 않습니다.", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplication(), MainActivity.class)); // 로딩이 끝난후 이동할 Activity
                SplashActivity.this.finish(); // 로딩페이지 Activity Stack에서 제거
            } else
                return;
        }

    }
}

