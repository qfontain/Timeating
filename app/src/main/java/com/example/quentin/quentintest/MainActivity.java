package com.example.quentin.quentintest;


import android.content.Intent;
import android.content.SyncStats;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuInflater;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TimePicker;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;


public class MainActivity extends AppCompatActivity  {
    private DrawerLayout mDrawerLayout;
    private final static int SECOND_CALL_ID = 1234;
    ImageButton navigationbar;
    Button button2; // Déclaration d'une variable bouton
    ListView header_layout_2;
    MenuItem item;
    MenuItem account_item;
    NavigationView navigation;
    TimePicker timePicker2;
    private double latitude;
    private double longitude;
    private String ID_resto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button2 = findViewById(R.id.button2); // Fait la liaison avec le code xml et l'"id" du bouton 2 qui est button2 (voir code XML) android:id
        timePicker2 = findViewById(R.id.timePicker2);
        timePicker2.setIs24HourView(true); // Met le timepicker au format européen
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        navigationbar = findViewById(R.id.imagebutton);

        navigationbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrawerLayout.openDrawer(Gravity.START);
                initInstances();
            }
        });
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
                ID_resto = place.getId();
                latitude = place.getLatLng().latitude;
                longitude = place.getLatLng().longitude;
                Toast.makeText(MainActivity.this,ID_resto,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Status status) {
                // Handle the error.

            }
        });

        button2.setOnClickListener(new View.OnClickListener() { // Détection que l'utilisateur a appuyé sur le bouton
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        MainActivity.this, // On part de ça d'où le .this
                        ScrollingActivity.class // Pour aller vers cette classe
                );
                startActivityFromChild(MainActivity.this,intent,SECOND_CALL_ID);
                intent.putExtra("ID",ID_resto);
                intent.putExtra("latitude",latitude);
                intent.putExtra("longitude",longitude);
            }
        });

    }
    private void initInstances() {


        navigation = (NavigationView) findViewById(R.id.navigation_view);
        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                int id = menuItem.getItemId();
                switch (id) {
                    case R.id.compte:
                        Intent i = new Intent(MainActivity.this, Mon_compte.class);
                        startActivity(i);

                        break;
                    case R.id.achievement:
                        Intent achi = new Intent(MainActivity.this, Reussite.class);
                        startActivity(achi);

                        break;
                    case R.id.logout:

                     //   Intent logout = new Intent(MainActivity.this, Mon_compte.class);
                      //  startActivity(logout);

                        break;
                    case R.id.help:

                         Intent help = new Intent(MainActivity.this, Help.class);
                         startActivity(help);

                        break;
                    case R.id.cgu:

                        Intent cgu = new Intent(MainActivity.this, CGU.class);
                        startActivity(cgu);
                        break;


                    case R.id.source:

                        Intent source = new Intent(MainActivity.this, Sources_des_datas.class);
                        startActivity(source);
                        break;
                    case R.id.contact:

                        Intent contact = new Intent(MainActivity.this, Contact.class);
                        startActivity(contact);
                        break;

                    case R.id.confidentiality:

                        Intent confidentiality = new Intent(MainActivity.this, Confidentiality.class);
                        startActivity(confidentiality);
                        break;


                    case R.id.settings:
                       // Intent settings = new Intent(MainActivity.this, Mon_compte.class);
                       // startActivity(settings);

                        break;
                    case R.id.time_to_eat:
                        Intent time_eat = new Intent(MainActivity.this, MainActivity.class);
                        startActivity(time_eat);

                        break;
                }
                return false;
            }
        });

    }

}