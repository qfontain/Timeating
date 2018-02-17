package com.example.quentin.quentintest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Avec_Sans_Compte extends AppCompatActivity {
    Button Avec;
    Button Sans;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avec__sans__compte);

        Avec = (Button) findViewById(R.id.button_avec_compte);
        Sans = (Button) findViewById(R.id.button_sans_compte);

        Avec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (
                        Avec_Sans_Compte.this,
                        Connexion.class
                );
                startActivityFromChild(Avec_Sans_Compte.this,intent,2323);
            }
        });
        Sans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        Avec_Sans_Compte.this,
                        MainActivity.class
                );
                startActivityFromChild(Avec_Sans_Compte.this,intent,1313);
            }
        });
    }
}
