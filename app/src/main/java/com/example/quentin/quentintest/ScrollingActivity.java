package com.example.quentin.quentintest;


import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.os.SystemClock;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import ru.whalemare.sheetmenu.SheetMenu;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ParseException;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.Formatter;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.ProtocolException;
import java.net.SocketException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONTokener;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


public class ScrollingActivity extends Activity {

    RecyclerView recyclerView;
    RecyclerAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Restaurants> arrayList;
    ListView lv;
    List<String> list;
    String url1;
    private String content;
    private double latitudeDepart;
    private double longitudeDepart;
    private double latitudeRetour;
    private double longitudeRetour;
    private final static int FIRST_CALL_ID = 12;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);

        context = getApplicationContext();
        latitudeDepart = getIntent().getDoubleExtra("latitudeDepart",0);
        longitudeDepart = getIntent().getDoubleExtra("longitudeDepart",0);
        latitudeRetour = getIntent().getDoubleExtra("latitudeRetour",0);
        longitudeRetour = getIntent().getDoubleExtra("longitudeRetour",0);

        Intent intent = new Intent(
                ScrollingActivity.this, // On part de ça d'où le .this
                RecyclerAdapter.class // Pour aller vers cette classe
        );
        intent.putExtra("latitudeRetour",latitudeRetour);
        intent.putExtra("longitudeRetour",longitudeRetour);

        Intent intent1 = new Intent(
                ScrollingActivity.this, // On part de ça d'où le .this
                Navigation_Citymapper.class // Pour aller vers cette classe
        );
        intent.putExtra("latitudeDepart",latitudeRetour);
        intent.putExtra("longitudeDepart",longitudeRetour);
        intent.putExtra("latitudeRetour",latitudeRetour);
        intent.putExtra("longitudeRetour",longitudeRetour);


        arrayList = new ArrayList<>();
        list = new ArrayList<String>();
        url1 = getIntent().getStringExtra("url");
        recyclerView = findViewById(R.id.recycleView);
        layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RecyclerAdapter(arrayList,context);
        recyclerView.setAdapter(adapter);

        fireYourAsyncTask();

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < list.size(); i++) {
                    new ReadJSON().execute("http://192.168.43.161/python.php", list.get(i));
                }
            }
        });
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    private void fireYourAsyncTask() {
        try {
            list = new ListId(this).execute(
                    url1).get();
        } catch (InterruptedException e) {
        } catch (ExecutionException e) {
        }
    }

    class ReadJSON extends AsyncTask<String, Integer, String> {

        @Override
        public void onPreExecute() {
        }

        @Override
        protected String doInBackground(String... params) {

            try {
                URL url = new URL(params[0]);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("POST");
                con.setDoInput(true);
                con.setDoOutput(true);

                Uri.Builder builder = new Uri.Builder().appendQueryParameter("id", params[1]);
                String query = builder.build().getEncodedQuery();

                OutputStream output = con.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output, "UTF-8"));
                writer.write(query);
                writer.flush();
                writer.close();
                output.close();

                con.connect();

                StringBuilder sb = new StringBuilder();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String json;
                while ((json = bufferedReader.readLine()) != null) {
                    sb.append(json + "\n");
                }
                return sb.toString().trim();
            } catch (Exception e) {
                return null;
            }
        }

        @Override
        protected void onPostExecute(String content) {
            try {
                AddNewRestaurant(content);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            adapter.notifyDataSetChanged();
        }
    }

    public void AddNewRestaurant(String json) throws JSONException {

        String address; //ok
        Coordinates coordinates = new Coordinates(); //ok
        String international_phone_number; //ok
        String name; //ok
        Integer Actual_Populartime = null; //ok
        Double rating = 0.0; //ok
        Time_Spent time_spent = new Time_Spent();
        Integer TravelTime = 0;
        Double TotalTime;

        String modif = json.substring(json.indexOf('{'));
        JSONObject jsonObject = new JSONObject(modif);

        name = jsonObject.getString("name");
        address = jsonObject.getString("address");
        String chaine = jsonObject.getString("coordinates");
        String temp1 = StringUtils.substringBetween(chaine, ":", ",");
        coordinates.lat = Double.parseDouble(temp1);
        String temp2 = chaine.substring(chaine.lastIndexOf(':') + 1, chaine.indexOf("}"));
        coordinates.lng = Double.parseDouble(temp2);
        international_phone_number = jsonObject.getString("international_phone_number");


        try {
            rating = jsonObject.getDouble("rating");

            JSONArray jsonArray = jsonObject.getJSONArray("populartimes");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                Calendar rightNow = Calendar.getInstance();
                if (object.getString("name").equals(rightNow.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault()))) {
                    JSONArray data = object.getJSONArray("data");
                    Actual_Populartime = data.getInt(rightNow.get(Calendar.HOUR_OF_DAY));
                }
            }

            JSONArray jsonArray2 = jsonObject.getJSONArray("time_spent");
            if (jsonArray2 != null) {
                JSONArray a2 = jsonArray2.getJSONArray(0);
                time_spent.time_min = a2.getDouble(0);
                time_spent.time_max = a2.getDouble(1);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            TravelTime = new GetTravelTime(48.8959912, coordinates.lat, 2.2354576, coordinates.lng).get();
        } catch (ExecutionException | InterruptedException ei) {
            ei.printStackTrace();
        }

        TotalTime = getTimeSpentOnSpot(Actual_Populartime, time_spent, TravelTime);


        if (Actual_Populartime != null) {
            try {
                arrayList.add(new Restaurants(address, coordinates, international_phone_number, name, Actual_Populartime, rating, time_spent, TravelTime, TotalTime));
            } catch (NullPointerException e) {

            }
        } else {
            try {
                arrayList.add(new Restaurants(address, coordinates, international_phone_number, name, null, rating, time_spent, TravelTime, TotalTime));
            } catch (NullPointerException e) {

            }
        }
    }

    public class GetTravelTime extends AsyncTask<Void, Void, Integer> {

        double lat1;
        double lat2;
        double lng1;
        double lng2;

        public GetTravelTime(double lat1, double lat2, double lng1, double lng2) {

            this.lat1 = lat1;
            this.lat2 = lat2;
            this.lng1 = lng1;
            this.lng2 = lng2;
            this.execute();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Integer doInBackground(Void... params) {

            String distance = "";
            Integer time = 0;
            String url = "https://maps.googleapis.com/maps/api/directions/xml?origin=" + lat1 + "," + lng1 + "&destination=" + lat2 + "," + lng2 + "&mode=walking&key=AIzaSyBIjo8GgvfeciXeTXO1fm8FEcJteJ0_GPU";
            String tag[] = {"text"};
            URL url1 = null;
            try {
                url1 = new URL(url);
                HttpURLConnection connection = null;
                connection = (HttpURLConnection) url1.openConnection();
                connection.setReadTimeout(30000);
                connection.setConnectTimeout(30000);
                connection.setRequestMethod("GET");
                connection.setDoOutput(true);
                connection.connect();

                InputStream is = connection.getInputStream();
                DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
                Document doc = builder.parse(is);
                if (doc != null) {
                    NodeList nl;
                    ArrayList args = new ArrayList();
                    for (String s : tag) {
                        nl = doc.getElementsByTagName(s);
                        if (nl.getLength() > 0) {
                            Node node = nl.item(0);
                            Node node1 = nl.item(1);
                            args.add(node.getTextContent());
                            args.add(node1.getTextContent());
                        } else {
                            args.add(" - ");
                        }
                    }
                    String temp = String.format("" + args.get(0));
                    Integer temp1 = temp.indexOf("mins") - 2;
                    String temp2 = temp.substring(0, temp.indexOf('m') - 1);
                    time = Integer.valueOf(temp2);
                } else {
                    System.out.print("Doc is null");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return time;
        }

        @Override
        protected void onPostExecute(Integer result) {
            super.onPostExecute(result);
        }
    }

    public double getTimeSpentOnSpot(Integer Actual_PopularTimes, Time_Spent time_spent, Integer travelTime) {
        double resultat = 0.0;
        double timeOnSpot = 0.0;
        double maxTime = 0.0;
        double minTime = 0.0;

        if (Actual_PopularTimes == null || Actual_PopularTimes == 0)
        {
            return 30 + (double) travelTime * 2;
        }
        if (time_spent != null)
        {
            if (time_spent.getTime_max() != null)
            {
                maxTime = time_spent.getTime_max();
            }
            else
            {
                maxTime = -1.0;
            }
            if (time_spent.getTime_min() != null )
            {
                minTime = time_spent.getTime_min();
            }
            else
            {
                minTime = -1.0;
            }

            if (minTime == -1.0 && maxTime == -1.0)
            {
                return 30+travelTime;
            }
            if (minTime == -1.0)
            {
                if (maxTime<3) {
                    maxTime = maxTime * 60;
                }
            }
            if (maxTime == -1.0)
            {
                if (minTime<3) {
                    minTime = minTime * 60;
                }
            }
            if (minTime != -1.0 && maxTime != -1.0)
            {
                if (maxTime<3)
                {
                    minTime = minTime*60;
                    maxTime = minTime*60;
                }
            }
            if (minTime== 0.0)
            {
                timeOnSpot = maxTime;
            }
            else
            {
                if (maxTime == 0.0)
                {
                    timeOnSpot = minTime;
                }
                else
                {
                    timeOnSpot = maxTime - minTime;
                }
            }
            if (timeOnSpot <10)
            {
                if (maxTime == 0.0 && minTime == 0.0)
                {
                    timeOnSpot = 30.0;
                }
                if (maxTime != 0.0 && minTime != 0.0)
                {
                    timeOnSpot = maxTime;
                }
                else
                {
                    if (maxTime == 0.0)
                    {
                        timeOnSpot = minTime;
                    }
                    else
                    {
                        if (minTime == 0.0)
                        {
                            timeOnSpot = maxTime;

                        }
                    }
                }
            }
            resultat = Double.valueOf(Actual_PopularTimes)*0.01 * timeOnSpot;
            Double margeErreur = 0.05 * resultat;
            resultat = resultat + margeErreur;
            if (resultat <0)
            {
                resultat = resultat*-1;
            }
            while (resultat >200)
            {
                resultat = resultat/60 +30;
            }
            if (resultat < 10)
            {
                resultat = resultat+20;
            }
        }
        else
        {
            return 30+ (double) travelTime * 2;
        }
        return resultat + (double) travelTime * 2;
    }

/*
    // Méthodes permettant de faire une notification avec un délai dans le temps en millisecondes
    public void scheduleNotification(Notification notification, int delay) {

        Intent notificationIntent = new Intent(this, NotificationPublisher.class);
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION_ID, 1);
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION, notification);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        long futureInMillis = SystemClock.elapsedRealtime() + delay;
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis, pendingIntent);
    }
    public Notification getNotification(String content) {
        Notification.Builder builder = new Notification.Builder(this);
        builder.setContentTitle("En route pour une nouvelle aventure !");
        builder.setContentText(content);
        builder.setSmallIcon(R.drawable.logo_ping);
        builder.setVibrate(new long[] { 1000, 1000, 1000, 1000, 1000 });
        return builder.build();
    }
    // Méthode permettant de montrer le menu du bas et qui gère
    public void showMenu(CharSequence name)
    {
        content = (String)name;
        SheetMenu.with(this)
                .setTitle("Sélectionnez une application de transport :")
                .setMenu(R.menu.sheet_menu)
                .setClick(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        int delay_notification = 3; // Délai en secondes avant que la notification apparaisse
                        if(menuItem.getItemId() == R.id.City)
                        {
                            Toast.makeText(ScrollingActivity.this, "Citymapper !", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(
                                    ScrollingActivity.this,
                                    Navigation_Citymapper.class
                            );
                            // Permettront de montrer à titre d'exemple dans l'activité suivante que ça marche
                            intent.putExtra("latitudeRetour",latitudeRetour);
                            intent.putExtra("longitudeRetour",longitudeRetour);
                            intent.putExtra("latitudeDepart",latitudeDepart);
                            intent.putExtra("longitudeDepart",longitudeDepart);
                            scheduleNotification(getNotification("Il est temps de partir du resto \uD83D\uDE09"), delay_notification*1000);
                            startActivityFromChild(ScrollingActivity.this,intent,9999999);
                        }
                        else
                        {
                            Toast.makeText(ScrollingActivity.this, "Maps !", Toast.LENGTH_SHORT).show();
                            Uri gmmIntentUri = Uri.parse("google.navigation:q="+content+",+France&mode=w");
                            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                            mapIntent.setPackage("com.google.android.apps.maps");
                            scheduleNotification(getNotification("Il est temps de partir du resto \uD83D\uDE09"), delay_notification*1000);
                            startActivity(mapIntent);
                        }
                        return false;
                    }
                }).show();
    }*/
}
