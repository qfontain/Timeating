package com.example.quentin.quentintest;

/**
 * Created by sebas on 05/06/2018.
 */

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class ListId extends AsyncTask<String, String, List<String>> {

    List<String> listofIDs = new ArrayList<String>();
    private ScrollingActivity activity;

    public ListId(ScrollingActivity activity) {

        this.activity = activity;
    }

    protected List<String> doInBackground(String... params) {

        HttpURLConnection connection = null;
        BufferedReader reader = null;

        try {
            URL url = new URL(params[0]);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();


            InputStream stream = connection.getInputStream();

            reader = new BufferedReader(new InputStreamReader(stream));

            StringBuffer buffer = new StringBuffer();
            String line = "";
            String id = "";


            while ((line = reader.readLine()) != null) {
                buffer.append(line+"\n");

            }
            JSONObject json = new JSONObject(buffer.toString());
            JSONArray results = json.getJSONArray("results");
            for (int i = 0; i < results.length(); i++) {
                JSONObject jsonObject = results.getJSONObject(i);
                id = jsonObject.getString("place_id");
                listofIDs.add(id);
            }
            connection.disconnect();
            return listofIDs;


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }  catch (JSONException e) {
            e.printStackTrace();
        }  finally {
            if (connection != null) {
                connection.disconnect();
            }
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(List<String> result) {
        super.onPostExecute(result);
        activity.setList(listofIDs);
    }
}

