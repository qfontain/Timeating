package com.example.quentin.quentintest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Splash_Screen extends AppCompatActivity {
    private int SLEEP_TIMER  =3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash__screen);
        LogoLauncher logoLauncher = new LogoLauncher();
        logoLauncher.start();
    }
    private class LogoLauncher extends Thread{
    public void run(){
        try{
            sleep(1000*SLEEP_TIMER);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
        Intent intent = new Intent(Splash_Screen.this,Connexion.class);
        startActivity(intent);
        Splash_Screen.this.finish();
    }
    }
}

