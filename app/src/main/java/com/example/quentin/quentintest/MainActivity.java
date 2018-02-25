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
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TimePicker;
import android.os.Bundle;
import android.view.MenuItem;


// Le code XML situé en haut permet de faire l'interface graphique
// On peut designer avec la partie design en glissant les boutons etc mais attention à mettre un bon layout !
// Regarder bien le code XML car on va lier le code XML et le code en dessous avec findViewbyId
public class MainActivity extends AppCompatActivity  {
    private DrawerLayout mDrawerLayout;
    private final static int SECOND_CALL_ID = 1234;
    ImageButton navigationbar;
    Button button2; // Déclaration d'une variable bouton
    ListView header_layout_2;
    MenuItem item;
    MenuItem account_item;
    NavigationView navigation;
    TimePicker timePicker2; // Déclaration d'une variable EditText c'est à dire qui reçoit un texte de l'utilisateur
    AutoCompleteTextView Lieu; // Déclaration d'une variable EditText c'est à dire qui reçoit un texte de l'utilisateur

    @Override // Lance ce qu'il y en a en dessous (mis par défaut)
    protected void onCreate(Bundle savedInstanceState) { // Mis par défaut (c'est grosso modo le main() )
        super.onCreate(savedInstanceState); // Mis par défaut
        setContentView(R.layout.activity_main); // Lit l'activité principale par rapport au layout (l'affichage)

        String[] Places = getResources().getStringArray(R.array.Lieux); // Récupère le tableau du strings.xml dans res values





        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        navigationbar = findViewById(R.id.imagebutton);

        navigationbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrawerLayout.openDrawer(Gravity.START);
initInstances();

            }
        });




        button2 = findViewById(R.id.button2); // Fait la liaison avec le code xml et l'"id" du bouton 2 qui est button2 (voir code XML) android:id
        timePicker2 = findViewById(R.id.timePicker2); // Fait la liaison avec le code xml et l'heure de retour
        timePicker2.setIs24HourView(true); // Met le timepicker au format européen
        Lieu = findViewById(R.id.Lieu); // Idem avec le lieu

        //On crée la liste d'autocompletion à partir de notre tableau de string appelé Places
        //android.R.layout.simple_dropdown_item_1line permet de définir le style d'affichage de la liste
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_dropdown_item_1line, Places);
        //On affecte cette liste d'autocompletion à notre objet d'autocompletion
        Lieu.setAdapter(adapter);

        button2.setEnabled(false);
        // Permet de désactiver le bouton lorsque rien n'est rentré
        Lieu.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.toString().equals("")){
                    button2.setEnabled(false);
                    Lieu.setError("Un lieu est requis !");
                }
                else{
                    button2.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        button2.setOnClickListener(new View.OnClickListener() { // Détection que l'utilisateur a appuyé sur le bouton
            @Override
            public void onClick(View view) { // mis automatiquement quand on écrit button2.setOnClickListener
                Intent intent = new Intent( // On déclare l'intention de faire une action et plus précisement de passer à une autre activité
                        MainActivity.this, // On part de ça d'où le .this
                        ScrollingActivity.class // Pour aller vers cette classe
                );
                startActivityFromChild(MainActivity.this,intent,SECOND_CALL_ID); // On avait l'intention de le faire, maintenant on la commence de l'enfant (activité actuelle)
                // Second Call Id est une sorte de TAG pas vraiment important mais il faut le mettre
                // Définit après les import de packages
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