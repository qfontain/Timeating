package com.example.quentin.quentintest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Mon_compte extends AppCompatActivity {
    Button email;
    Button mdp;
    Button premi;
    EditText mmail;
    EditText mmdp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mon_compte);
        email = findViewById(R.id.button_mail);
        mdp = findViewById(R.id.button_password);
        premi = findViewById(R.id.premi);
        email.setEnabled(false);
        mdp.setEnabled(false);

        mmail = findViewById(R.id.editText2);
        mmdp = findViewById(R.id.editText);
        premi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        Mon_compte.this,
                        Premium_1.class
                );
                startActivityFromChild(Mon_compte.this,intent,4545445);
            }
        });
        mmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.toString().equals("")){
                    email.setEnabled(false);
                    mmail.setError("Un nouveau mail est requis !");
                }
                else{
                    email.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        mmdp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.toString().equals("")){
                    mdp.setEnabled(false);
                    mmdp.setError("Un nouveau mot de passe est requis !");
                }
                else{
                    mdp.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Mon_compte.this, "Le mail a été modifié",
                        Toast.LENGTH_LONG).show();
            }
        });
        mdp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Mon_compte.this, "Le mot de passe a été modifié",
                        Toast.LENGTH_LONG).show();
            }
        });
    }
}
