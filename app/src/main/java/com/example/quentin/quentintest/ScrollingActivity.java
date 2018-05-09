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


public class ScrollingActivity extends Activity {
    Button S_comme_sushi;
    Button Lune_Sushi;
    Button KFC_La_Defense;
    Button Mc_Donald_La_Defense;
    Button Brioche_Doree;
    Button Burger_King_La_Defense;
    Button Vapiano_La_Defense;
    Button Paradis_du_Fruit;
    private String content;

    private double latitudeDepart;
    private double longitudeDepart;
    private double latitudeRetour;
    private double longitudeRetour;

    private final static int FIRST_CALL_ID = 12;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        S_comme_sushi = findViewById(R.id.button13);
        Lune_Sushi = findViewById(R.id.button14);
        KFC_La_Defense = findViewById(R.id.button16);
        Mc_Donald_La_Defense = findViewById(R.id.button17);
        Brioche_Doree = findViewById(R.id.button18);
        Burger_King_La_Defense=findViewById(R.id.button19);
        Vapiano_La_Defense=findViewById(R.id.button20);
        Paradis_du_Fruit=findViewById(R.id.button21);

        latitudeDepart = getIntent().getDoubleExtra("latitudeDepart",0);
        longitudeDepart = getIntent().getDoubleExtra("longitudeDepart",0);
        latitudeRetour = getIntent().getDoubleExtra("latitudeRetour",0);
        longitudeRetour = getIntent().getDoubleExtra("longitudeRetour",0);

        S_comme_sushi.setOnClickListener(new View.OnClickListener() { // même principe que dans l'activité 1 pour passer à l'activité suivante
            @Override
            public void onClick(View view) {
                showMenu(S_comme_sushi.getText());
            }

        });
        Lune_Sushi.setOnClickListener(new View.OnClickListener() { // même principe que dans l'activité 1 pour passer à l'activité suivante
            @Override
            public void onClick(View view) {
                showMenu(Lune_Sushi.getText());
            }

        });
        KFC_La_Defense.setOnClickListener(new View.OnClickListener() { // même principe que dans l'activité 1 pour passer à l'activité suivante
            @Override
            public void onClick(View view) {
                showMenu(KFC_La_Defense.getText());
            }

        });
        Mc_Donald_La_Defense.setOnClickListener(new View.OnClickListener() { // même principe que dans l'activité 1 pour passer à l'activité suivante
            @Override
            public void onClick(View view) {
                showMenu(Mc_Donald_La_Defense.getText());
            }

        });
        Brioche_Doree.setOnClickListener(new View.OnClickListener() { // même principe que dans l'activité 1 pour passer à l'activité suivante
            @Override
            public void onClick(View view) {
                showMenu(Brioche_Doree.getText());
            }

        });
        Burger_King_La_Defense.setOnClickListener(new View.OnClickListener() { // même principe que dans l'activité 1 pour passer à l'activité suivante
            @Override
            public void onClick(View view) {
                showMenu(Burger_King_La_Defense.getText());
            }

        });
        Vapiano_La_Defense.setOnClickListener(new View.OnClickListener() { // même principe que dans l'activité 1 pour passer à l'activité suivante
            @Override
            public void onClick(View view) {
                showMenu(Vapiano_La_Defense.getText());
            }

        });
        Paradis_du_Fruit.setOnClickListener(new View.OnClickListener() { // même principe que dans l'activité 1 pour passer à l'activité suivante
            @Override
            public void onClick(View view) {
                showMenu(Paradis_du_Fruit.getText());
            }

        });
    }
    // Méthodes permettant de faire une notification avec un délai dans le temps en millisecondes
    private void scheduleNotification(Notification notification, int delay) {

        Intent notificationIntent = new Intent(this, NotificationPublisher.class);
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION_ID, 1);
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION, notification);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        long futureInMillis = SystemClock.elapsedRealtime() + delay;
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis, pendingIntent);
    }
    private Notification getNotification(String content) {
        Notification.Builder builder = new Notification.Builder(this);
        builder.setContentTitle("En route pour une nouvelle aventure !");
        builder.setContentText(content);
        builder.setSmallIcon(R.drawable.logo_ping);
        builder.setVibrate(new long[] { 1000, 1000, 1000, 1000, 1000 });
        return builder.build();
    }
    // Méthode permettant de montrer le menu du bas et qui gère
    private void showMenu(CharSequence name)
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
    }
}
