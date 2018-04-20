package com.example.quentin.quentintest;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Premium_4 extends AppCompatActivity {
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_premium_4);
        button = (Button) findViewById(R.id.buttonprem4);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        Premium_4.this,
                        Invitation_1.class
                );
                int day = getIntent().getIntExtra("day",0);
                int month = getIntent().getIntExtra("month",0);
                int year = getIntent().getIntExtra("year",0);
                int hour = getIntent().getIntExtra("hour",0);
                int minute = getIntent().getIntExtra("minute",0);
                showNotification("Invitation","Marie Malécot souhaite vous inviter à déjeuner le "+day+"/"+month+"/"+year+" à "+hour+":"+minute+".");
                intent.putExtra("day",day);
                intent.putExtra("month",month);
                intent.putExtra("year",year);
                intent.putExtra("hour",hour);
                intent.putExtra("minute",minute);
                startActivityFromChild(Premium_4.this,intent,44444);
            }
        });

    }
    void showNotification(String title, String content) {
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("default",
                    "YOUR_CHANNEL_NAME",
                    NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("YOUR_NOTIFICATION_CHANNEL_DISCRIPTION");
            mNotificationManager.createNotificationChannel(channel);
        }
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext(), "default")
                .setSmallIcon(R.mipmap.ic_launcher) // notification icon
                .setContentTitle(title) // title for notification
                .setContentText(content)// message for notification
                .setAutoCancel(true) // clear notification after click
                .setVibrate(new long[] { 1000, 1000, 1000, 1000, 1000 })
                .setStyle(new NotificationCompat.BigTextStyle().bigText(content)); // To display the full message !
        Intent intent = new Intent(getApplicationContext(), Premium_4.class);
        PendingIntent pi = PendingIntent.getActivity(Premium_4.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(pi);
        mNotificationManager.notify(0, mBuilder.build());
        startActivityFromChild(Premium_4.this,intent,44444);
    }
}
