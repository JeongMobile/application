package com.example.eunjung.myapplication;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class Test extends AppCompatActivity {

    private TextView tvData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        tvData = (TextView) findViewById(R.id.tvTest);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String urlString = "http://165.194.33.149:8899/User";
        new JSONTask().execute(urlString);

    }

    public class JSONTask extends AsyncTask<String, String, String >{

        @Override
        protected String doInBackground(String... params){
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


            try {

                JSONArray jsonArray = new JSONArray(result);
                try {
                    //jsonArray.length();
                    JSONObject json = jsonArray.getJSONObject(0);
                    String module = json.getString("userName");
                    tvData.setText(module);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
