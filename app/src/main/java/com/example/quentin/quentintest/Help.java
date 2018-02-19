package com.example.quentin.quentintest;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;

public class Help extends Activity {
    Button compris;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        compris = findViewById(R.id.button_understand);

        compris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        Help.this,
                        MainActivity.class
                );
                startActivityFromChild(Help.this,intent,8888);
            }
        });
    }
}
