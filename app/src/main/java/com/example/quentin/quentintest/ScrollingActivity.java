package com.example.quentin.quentintest;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.app.Activity;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.Button;

import java.util.Calendar;
import java.util.Date;


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
        long time_millis =System.currentTimeMillis();
        long total_secondes = time_millis/1000;
        final int seconde =  (int) (total_secondes-total_secondes%3600);
        long total_minutes = total_secondes/60;
        int hour = (int)total_minutes%60;
        final int minutes = (int) (total_minutes -hour*60);
        S_comme_sushi.setOnClickListener(new View.OnClickListener() { // même principe que dans l'activité 1 pour passer à l'activité suivante
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(
                        ScrollingActivity.this,
                        MapsActivity.class
                );
                intent2.putExtra("Resto","S+Comme+Sushi");
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
                startActivityFromChild(ScrollingActivity.this,intent2,FIRST_CALL_ID);
            }

        });
    }
    public void scheduleNotification(Context context, long delay, int notificationId) {//delay is after how much time(in millis) from current time you want to schedule the notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setContentTitle("Départ du restaurant dans 5 minutes !")
                .setContentText("")
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.splash_screen)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));

        Intent intent = new Intent(context, ScrollingActivity.class);
        PendingIntent activity = PendingIntent.getActivity(context, notificationId, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        builder.setContentIntent(activity);

        Notification notification = builder.build();

        Intent notificationIntent = new Intent(context, MyNotificationPublisher.class);
        notificationIntent.putExtra(MyNotificationPublisher.NOTIFICATION_ID, notificationId);
        notificationIntent.putExtra(MyNotificationPublisher.NOTIFICATION, notification);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, notificationId, notificationIntent, PendingIntent.FLAG_CANCEL_CURRENT);

        long futureInMillis = SystemClock.elapsedRealtime() + delay;
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis, pendingIntent);
    }
}
