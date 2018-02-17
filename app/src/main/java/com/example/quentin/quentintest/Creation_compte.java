package com.example.quentin.quentintest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Creation_compte extends AppCompatActivity {
    Button b_creation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creation_compte);

        b_creation = findViewById(R.id.button_confirmation_creation_compte);

        b_creation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        Creation_compte.this,
                        Informations.class
                );
                startActivityFromChild(Creation_compte.this,intent,7879);
            }
        });
    }
}
