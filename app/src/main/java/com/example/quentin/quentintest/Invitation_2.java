package com.example.quentin.quentintest;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Invitation_2 extends AppCompatActivity {
    Button sondage;
    Button decline;
    TextView tv;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invitation_2);
        int day = getIntent().getIntExtra("day",0);
        int month = getIntent().getIntExtra("month",0);
        int year = getIntent().getIntExtra("year",0);
        int hour = getIntent().getIntExtra("hour",0);
        int minute = getIntent().getIntExtra("minute",0);
        sondage = (Button) findViewById(R.id.sondage);
        decline = (Button) findViewById(R.id.decline);
        tv = findViewById(R.id.textView2);
        if(day!=0 && month!=0 && year!=0)
        {
            tv.setText("\nMarie Malécot souhaite vous inviter à déjeuner le "+day+"/"+month+"/"+year+" à "+hour+":"+minute+".");
        }
        sondage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        Invitation_2.this,
                        Invitation_3.class
                );
                startActivityFromChild(Invitation_2.this,intent,222222);
            }
        });
        decline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        Invitation_2.this,
                        MainActivity.class
                );
                Toast.makeText(Invitation_2.this, "Invitation déclinée !",
                        Toast.LENGTH_LONG).show();
                startActivityFromChild(Invitation_2.this,intent,22);
            }
        });
    }
}
