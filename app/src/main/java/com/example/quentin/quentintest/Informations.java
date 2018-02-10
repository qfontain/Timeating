package com.example.quentin.quentintest;

import android.content.Intent;
import android.icu.text.IDNA;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Informations extends AppCompatActivity {
    Button fin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informations);
        fin = (Button) findViewById(R.id.info_taken);
        fin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        Informations.this,
                        MainActivity.class
                );
                startActivityFromChild(Informations.this,intent,222);
            }
        });
    }
}
