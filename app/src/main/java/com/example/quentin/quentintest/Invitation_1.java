package com.example.quentin.quentintest;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Invitation_1 extends AppCompatActivity {
    Button button;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invitation_1);
        int day = getIntent().getIntExtra("day",0);
        int month = getIntent().getIntExtra("month",0);
        int year = getIntent().getIntExtra("year",0);
        int hour = getIntent().getIntExtra("hour",0);
        int minute = getIntent().getIntExtra("minute",0);
        button = (Button) findViewById(R.id.Mary);
        if(day!=0 && month!=0 && year!=0)
        {
            button.setText("\n Marie Malécot souhaite vous inviter à déjeuner le "+day+"/"+month+"/"+year+" à "+hour+":"+minute);
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        Invitation_1.this,
                        Invitation_2.class
                );
                int day = getIntent().getIntExtra("day",0);
                int month = getIntent().getIntExtra("month",0);
                int year = getIntent().getIntExtra("year",0);
                intent.putExtra("day",day);
                intent.putExtra("month",month);
                intent.putExtra("year",year);
                int hour = getIntent().getIntExtra("hour",0);
                int minute = getIntent().getIntExtra("minute",0);
                intent.putExtra("hour",hour);
                intent.putExtra("minute",minute);
                startActivityFromChild(Invitation_1.this,intent,11111);
            }
        });
    }
}
