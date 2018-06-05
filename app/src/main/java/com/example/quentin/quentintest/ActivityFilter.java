package com.example.quentin.quentintest;

/**
 * Created by sebas on 05/06/2018.
 */

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityFilter extends AppCompatActivity {
    String url;
    int rayon;
    int budget;
    String food_type;
    public static String _utfValue = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filter_activity);

        //RECUPERER LES COORDS DE L'ACTIVITE DE QUENTIN

        //final double lat = 48.888822;
        //final double longi = 2.247635;

        final double lat = getIntent().getDoubleExtra("latitudeRetour",0);
        final double longi = getIntent().getDoubleExtra("longitudeRetour",0);


        // CHECKBOX
        final CheckBox checkRestaurant = findViewById(R.id.restaurantCheckBox);
        final CheckBox checkBar = findViewById(R.id.barCheckBox);
        final CheckBox checkCafe = findViewById(R.id.cafeCheckBox);
        final CheckBox checkBoulang = findViewById(R.id.boulangerieCheckBox);
        final CheckBox checkPates = findViewById(R.id.patesCheckBox);
        final CheckBox checkFastfood = findViewById(R.id.fastfoodCheckBox);
        final CheckBox checkPizza = findViewById(R.id.pizzaCheckBox);
        final CheckBox checkCreperie = findViewById(R.id.creperieCheckBox);
        final CheckBox checkJaponais = findViewById(R.id.japonaisCheckBox);

        //RAYON SEEKBAR
        SeekBar seekbarRayon = (SeekBar)findViewById(R.id.rayonSeekBar);
        final TextView textRayon = (TextView)findViewById(R.id.rayonTextView);
        final int rayonmin = 100;
        rayon = seekbarRayon.getProgress()+rayonmin;
        textRayon.setText(rayon+" mètres");
        seekbarRayon.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener(){
                    @Override
                    public void onProgressChanged(SeekBar seekbar, int progress, boolean fromUser){
                        rayon = progress+rayonmin;
                        textRayon.setText(rayon+" mètres");
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekbar){
                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekbar){
                    }
                }
        );

        //BUDGET SEEKBAR
        SeekBar seekbarBudget = (SeekBar)findViewById(R.id.budgetSeekBar);
        final TextView textBudget = (TextView)findViewById(R.id.budgetTextView);
        budget = seekbarBudget.getProgress();
        textBudget.setText("0€");
        seekbarBudget.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener(){

                    @Override
                    public void onProgressChanged(SeekBar seekbar, int progress, boolean fromUser){
                        budget = progress;
                        if(budget == 20)
                            textBudget.setText(">20€");
                        else textBudget.setText(budget+"€");
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekbar){
                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekbar){
                    }
                }
        );


        //RECHERCHER BUTTON
        Button rechercherButton = findViewById(R.id.rechercherButton);
        rechercherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int nivBudget = 0;
                if(budget>=5 && budget<10)
                    nivBudget = 1;
                if(budget>=10 && budget<15)
                    nivBudget = 2;
                if(budget>=15 && budget<20)
                    nivBudget = 3;
                if(budget == 20)
                    nivBudget = 4;


                if(checkRestaurant.isChecked()||checkBar.isChecked()||checkBoulang.isChecked()||checkCafe.isChecked()) {
                    url = "https://maps.googleapis.com/maps/api/place/radarsearch/json?location="+lat+","+longi+"&opennow&radius=" + rayon + "&maxprice=" + nivBudget + "&type=";
                    if (checkRestaurant.isChecked()) {
                        url += "restaurant";
                        food_type = "restaurant";
                    }
                    if (checkBar.isChecked()) {
                        url += "bar";
                        food_type = "bar";
                    }
                    if (checkBoulang.isChecked()) {
                        url += "bakery";
                        food_type = "bakery";
                    }
                    if (checkCafe.isChecked()) {
                        url += "cafe";
                        food_type = "cafe";
                    }
                }
                if(checkFastfood.isChecked()||checkCreperie.isChecked() || checkPates.isChecked() || checkPizza.isChecked() || checkJaponais.isChecked())
                {
                    url = "https://maps.googleapis.com/maps/api/place/textsearch/json?query=";
                    if (checkPates.isChecked()) {
                        url += "pates";
                        food_type = "pates";
                    }
                    if (checkPizza.isChecked()) {
                        url += "pizza";
                        food_type = "pizza";
                    }
                    if (checkCreperie.isChecked()) {
                        url += "creperie";
                        food_type = "creperie";
                    }
                    if (checkFastfood.isChecked()) {
                        url += "fastfood";
                        food_type = "fastfood";
                    }
                    if (checkJaponais.isChecked()) {
                        url += "japonais";
                        food_type = "japonais";
                    }
                    url+="&location="+lat+","+longi+"&opennow&radius=" + rayon /*+ "&maxprice=" + nivBudget;*/;
                }
                if (checkBar.isChecked() == false && checkBoulang.isChecked() == false
                        && checkCafe.isChecked() == false && checkRestaurant.isChecked() == false
                        && checkPates.isChecked() == false && checkFastfood.isChecked() == false
                        && checkCreperie.isChecked() == false && checkPizza.isChecked() == false && checkJaponais.isChecked() == false)
                {url = "https://maps.googleapis.com/maps/api/place/radarsearch/json?location="+lat+","+longi+"&opennow&radius=" + rayon + "&maxprice=" + nivBudget + "&type=food";}
                url+="&key=AIzaSyDw4zax9x0mauyrgDLcLcxgUkkd-xecj1g";

                _utfValue = food_type;

                Intent sendingIntent = new Intent(ActivityFilter.this,ScrollingActivity.class);
                sendingIntent.putExtra("url", url);
                startActivityFromChild(ActivityFilter.this,sendingIntent,999);


            }
        });

    }}
