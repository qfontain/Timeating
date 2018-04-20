package com.example.quentin.quentintest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Invitation_3 extends AppCompatActivity {
    Button vote;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invitation_3);

        vote = (Button) findViewById(R.id.vote);
        vote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        Invitation_3.this,
                        MainActivity.class
                );
                Toast.makeText(Invitation_3.this, "Vote pris en compte !",
                        Toast.LENGTH_LONG).show();
                startActivityFromChild(Invitation_3.this,intent,333);
            }
        });
    }
}
