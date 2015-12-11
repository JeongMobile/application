package com.example.eunjung.myapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class showMember extends AppCompatActivity {

    private TextView evGroupName;
    private ArrayAdapter arrayAdapter;
    private ListView lvGroupMember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        evGroupName = (TextView)findViewById(R.id.evGroupName);
        lvGroupMember = (ListView)findViewById(R.id.lvGroupMember);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_member);

        String urlString = "http://165.194.33.149:8899/Login/";
        try {
            //urlString += groupID + "/" + URLEncoder.encode(userID, "UTF-8");

        } catch (Exception e){
            e.printStackTrace();
        }
        new JSONTask().execute(urlString);

    }

    public void init(String GroupName, String[] UserLists ) {
        evGroupName.setText(GroupName);
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, UserLists);
        lvGroupMember.setAdapter(arrayAdapter);
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

            try {
                JSONArray jsonArray = new JSONArray(result);

                for(int i = 0; i < jsonArray.length(); i++) {
                    JSONObject json = jsonArray.getJSONObject(i);
                    String module = json.getString("userID");
                    Log.d("Register",module);
                    String[] User = new String[100];
                    User[i] = module;
                }
                Log.d("Register","True");

                //init

            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }
}
