package com.example.quentin.quentintest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

import java.util.Date;

public class Premium_3 extends AppCompatActivity {
    TimePicker timePicker2;
    Button button2;
    private final static int SECOND_CALL_ID = 1234;
    DatePicker datepicker;

    private double latitudeRetour;
    private double longitudeRetour;
    private String idRetour;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_premium_3);


// Liaisons
        button2 = findViewById(R.id.button2); // Fait la liaison avec le code xml et l'"id" du bouton 2 qui est button2 (voir code XML) android:id
        timePicker2 = findViewById(R.id.timePicker2);
        timePicker2.setIs24HourView(true); // Met le timepicker au format européen
        datepicker = (DatePicker) findViewById(R.id.datePicker);
        datepicker.setMinDate(new Date().getTime());
        // Autocompletion Google
        PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);
        autocompleteFragment.setBoundsBias(new LatLngBounds(
                new LatLng(48.644522, 1.522519),
                new LatLng(49.154395, 3.100239)));

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // Get info about the selected place.
                idRetour = place.getId();
                latitudeRetour = place.getLatLng().latitude;
                longitudeRetour = place.getLatLng().longitude;
            }

            @Override
            public void onError(Status status) {
                // Handle the error.

            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        Premium_3.this,
                        Premium_4.class
                );
                intent.putExtra("month",datepicker.getMonth()+1);
                intent.putExtra("year",datepicker.getYear());
                intent.putExtra("day",datepicker.getDayOfMonth());
                intent.putExtra("hour",timePicker2.getHour());
                intent.putExtra("minute",timePicker2.getMinute());
                startActivityFromChild(Premium_3.this,intent,SECOND_CALL_ID);
            }
        });
    }
}
