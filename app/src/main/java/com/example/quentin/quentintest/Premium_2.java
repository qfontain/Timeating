package com.example.quentin.quentintest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Premium_2 extends AppCompatActivity {
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_premium_2);
        button = (Button) findViewById(R.id.buttonprem2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        Premium_2.this,
                        Premium_3.class
                );
                startActivityFromChild(Premium_2.this,intent,22222);
            }
        });
    }
}
