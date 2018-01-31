package com.example.quentin.quentintest;


import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;


public class ScrollingActivity extends Activity {
    Button S_comme_sushi;
    Button Lune_Sushi;
    Button KFC_La_Defense;
    Button Mc_Donald_La_Defense;
    Button Brioche_Doree;
    Button Burger_King_La_Defense;
    Button Vapiano_La_Defense;
    Button Paradis_du_Fruit;

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

        final int delay_notification = 3; // Délai en secondes avant que la notification apparaisse

        S_comme_sushi.setOnClickListener(new View.OnClickListener() { // même principe que dans l'activité 1 pour passer à l'activité suivante
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(
                        ScrollingActivity.this,
                        MapsActivity.class
                );
                intent2.putExtra("Resto","S+Comme+Sushi");
                scheduleNotification(getNotification("Il est temps de partir du resto \uD83D\uDE09"), delay_notification*1000); // Programmation de la notification
                startActivityFromChild(ScrollingActivity.this,intent2,FIRST_CALL_ID);
            }

        });
        Lune_Sushi.setOnClickListener(new View.OnClickListener() { // même principe que dans l'activité 1 pour passer à l'activité suivante
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(
                        ScrollingActivity.this,
                        MapsActivity.class
                );
                intent2.putExtra("Resto","Lune+Sushi");
                scheduleNotification(getNotification("Il est temps de partir du resto \uD83D\uDE09"), delay_notification*1000);
                startActivityFromChild(ScrollingActivity.this,intent2,FIRST_CALL_ID);
            }

        });
        KFC_La_Defense.setOnClickListener(new View.OnClickListener() { // même principe que dans l'activité 1 pour passer à l'activité suivante
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(
                        ScrollingActivity.this,
                        MapsActivity.class
                );
                intent2.putExtra("Resto","KFC+La+Defense");
                scheduleNotification(getNotification("Il est temps de partir du resto \uD83D\uDE09"), delay_notification*1000);
                startActivityFromChild(ScrollingActivity.this,intent2,FIRST_CALL_ID);
            }

        });
        Mc_Donald_La_Defense.setOnClickListener(new View.OnClickListener() { // même principe que dans l'activité 1 pour passer à l'activité suivante
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(
                        ScrollingActivity.this,
                        MapsActivity.class
                );
                intent2.putExtra("Resto","Mc+Donald+La+Defense");
                scheduleNotification(getNotification("Il est temps de partir du resto \uD83D\uDE09"), delay_notification*1000);
                startActivityFromChild(ScrollingActivity.this,intent2,FIRST_CALL_ID);
            }

        });
        Brioche_Doree.setOnClickListener(new View.OnClickListener() { // même principe que dans l'activité 1 pour passer à l'activité suivante
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(
                        ScrollingActivity.this,
                        MapsActivity.class
                );
                intent2.putExtra("Resto","Brioche+Doree+La+Defense");
                scheduleNotification(getNotification("Il est temps de partir du resto \uD83D\uDE09"), delay_notification*1000);
                startActivityFromChild(ScrollingActivity.this,intent2,FIRST_CALL_ID);
            }

        });
        Burger_King_La_Defense.setOnClickListener(new View.OnClickListener() { // même principe que dans l'activité 1 pour passer à l'activité suivante
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(
                        ScrollingActivity.this,
                        MapsActivity.class
                );
                intent2.putExtra("Resto","Burger+King+La+Defense");
                scheduleNotification(getNotification("Il est temps de partir du resto \uD83D\uDE09"), delay_notification*1000);
                startActivityFromChild(ScrollingActivity.this,intent2,FIRST_CALL_ID);
            }

        });
        Vapiano_La_Defense.setOnClickListener(new View.OnClickListener() { // même principe que dans l'activité 1 pour passer à l'activité suivante
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(
                        ScrollingActivity.this,
                        MapsActivity.class
                );
                intent2.putExtra("Resto","Vapiano+La+Defense");
                scheduleNotification(getNotification("Il est temps de partir du resto \uD83D\uDE09"), delay_notification*1000);
                startActivityFromChild(ScrollingActivity.this,intent2,FIRST_CALL_ID);
            }

        });
        Paradis_du_Fruit.setOnClickListener(new View.OnClickListener() { // même principe que dans l'activité 1 pour passer à l'activité suivante
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(
                        ScrollingActivity.this,
                        MapsActivity.class
                );
                intent2.putExtra("Resto","Paradis+du+Fruit+La+Defense");
                scheduleNotification(getNotification("Il est temps de partir du resto \uD83D\uDE09"), delay_notification*1000);
                startActivityFromChild(ScrollingActivity.this,intent2,FIRST_CALL_ID);
            }

        });
    }
    // Méthode permettant de faire une notification avec un délai dans le temps en millisecondes
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
}
