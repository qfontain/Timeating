package com.example.quentin.quentintest;

/**
 * Created by sebas on 05/06/2018.
 */

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ru.whalemare.sheetmenu.SheetMenu;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    public static List<Restaurants> restaurantsList = new ArrayList<>();
    private String content;
    public static double latitudeRetour;
    public static double longitudeRetour;
    private final static int FIRST_CALL_ID = 12;
    ScrollingActivity scrollingActivity = new ScrollingActivity();
    Context context = scrollingActivity.context;
    Context context1;
    RecyclerView recyclerView = scrollingActivity.recyclerView;
    View view;
    LinearLayout linearLayout;


    public RecyclerAdapter(List<Restaurants> restaurantsList, Context context) {
        this.restaurantsList = restaurantsList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurant_vue,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Restaurants restaurants = restaurantsList.get(position);

        String url1 = "";
        String food_type = ActivityFilter._utfValue ;

        //String url = "http://www.mpizza-nice.fr/img/photos/zoom/accueil-03.jpg";


        if(food_type == "pizza") {url1 = "http://www.mpizza-nice.fr/img/photos/zoom/accueil-03.jpg";}
        else if(food_type == "bakery") {url1 = "https://lh3.googleusercontent.com/-ldH4dMCpO48/ViE15jwk9HI/AAAAAAABF4U/ECNYUJ29y3o/s640/blogger-image-881916915.jpg";}
        else if(food_type == "restaurant") {url1 = "https://restaurant.michelin.fr/sites/mtpb2c_fr/files/Framb4.jpg";}
        else if(food_type == "bar") {url1 = "http://barrua.ie/sites/default/files/parallax-back13_0_0.jpg";}
        else if(food_type == "cafe") {url1 = "https://viejas.com/wp-content/uploads/2018/01/Cafe_Patio_detail-1.jpg";}
        else if(food_type == "pates") {url1 = "http://cache.marieclaire.fr/data/photo/w1000_c17/cuisine/4e/pateaucitronal62-fotolia.jpg";}
        else if(food_type == "fastfood") {url1 = "http://s2.lemde.fr/image/2015/07/08/534x0/4674872_6_c70d_aux-etats-unis-la-chaine-shake-shak-sert-des_9356e55517ed263c1e9bd9f78d6421aa.jpg";}
        else if(food_type == "japonais") {url1 = "https://thumbs.dreamstime.com/z/ensemble-de-sushi-nourriture-japonaise-traditionnelle-39298049.jpg";}
        else if(food_type == "creperie") {url1 = "http://cache.marieclaire.fr/data/photo/w1000_ci/4z/creperie-bisou.jpg";}
        //String url = "http://www.mpizza-nice.fr/img/photos/zoom/accueil-03.jpg";


        Picasso.get().load(url1).into(holder.Album); //--> url1
        holder.Name.setText(restaurants.getName());
        holder.Adress.setText("Adresse : "+restaurants.getAddress());
        double temp = restaurants.getRating();
        float f = (float) temp;
        holder.Rating.setRating(f);  // double --> float
        holder.Phone_Number.setText("Téléphone : "+restaurants.getInternationalPhoneNumber());
        Drawable drawable = holder.Rating.getProgressDrawable();
        drawable.setColorFilter(Color.parseColor("#00BB61"), PorterDuff.Mode.SRC_ATOP);
        if(restaurants.getTotalTime()== null)
            holder.Temps_Attente.setText("Temps d'attente indispo.");
        else
            holder.Temps_Attente.setText(String.valueOf(restaurants.getTotalTime())+" min pour le trajet au total");
        if(restaurants.getTravelTime()==null)
            holder.Temps_Trajet.setText("Temps de trajet indispo.");
        else
            holder.Temps_Trajet.setText(restaurants.getTravelTime()+" min de trajet");
        context1 =  holder.Album.getContext();
    }

    @Override
    public int getItemCount() {
        return restaurantsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView Album;
        TextView Name;
        TextView Adress;
        RatingBar Rating;
        TextView Phone_Number;
        Button Temps_Attente;
        Button Temps_Trajet;

        public ViewHolder(final View itemView) {
            super(itemView);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.linearLayout);
            Album = itemView.findViewById(R.id.album);
            Rating = itemView.findViewById(R.id.rating);
            Name = itemView.findViewById(R.id.name);
            Adress = itemView.findViewById(R.id.adress);
            Phone_Number = itemView.findViewById(R.id.phone_number);
            Temps_Attente = itemView.findViewById(R.id.temps_attente);
            Temps_Trajet = itemView.findViewById(R.id.temps_trajet);

            /*
            Album.setClickable(true);
            Album.setFocusableInTouchMode(true);
            Album.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    CharSequence nom = restaurantsList.get(position).name + " " + restaurantsList.get(position).address;
                    latitudeRetour = restaurantsList.get(position).getCoordinates().lat;
                    longitudeRetour = restaurantsList.get(position).getCoordinates().lng;
                    Toast.makeText(itemView.getContext(), "Position: " + Integer.toString(getAdapterPosition()), Toast.LENGTH_LONG).show();
                    //nom = nom.replaceAll("[�]+","é");
                    showMenu(nom);
                }
            });*/

            Album.setClickable(true);
            Album.setFocusableInTouchMode(true);
            Album.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CharSequence nom = restaurantsList.get(getAdapterPosition()).name + " " + restaurantsList.get(getAdapterPosition()).address;
                    latitudeRetour = restaurantsList.get(getAdapterPosition()).getCoordinates().lat;
                    longitudeRetour = restaurantsList.get(getAdapterPosition()).getCoordinates().lng;
                    Toast.makeText(v.getContext(), "Position: " + Integer.toString(getAdapterPosition()), Toast.LENGTH_LONG).show();
                    //nom = nom.replaceAll("[�]+","é");
                    showMenu(nom);

                }
            });
        }
    }

    // Méthodes permettant de faire une notification avec un délai dans le temps en millisecondes
    public void scheduleNotification(Notification notification, int delay) {

        Intent notificationIntent = new Intent(context, NotificationPublisher.class);
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION_ID, 1);
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION, notification);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        long futureInMillis = SystemClock.elapsedRealtime() + delay;
        AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis, pendingIntent);
    }
    public Notification getNotification(String content) {
        Notification.Builder builder = new Notification.Builder(context);
        builder.setContentTitle("En route pour une nouvelle aventure !");
        builder.setContentText(content);
        builder.setSmallIcon(R.drawable.logo_ping);
        builder.setVibrate(new long[] { 1000, 1000, 1000, 1000, 1000 });
        return builder.build();
    }
    // Méthode permettant de montrer le menu du bas et qui gère
    private void showMenu(CharSequence name)
    {
        content = (String)name;
        SheetMenu.with(context1)
                .setTitle("Sélectionnez une application de transport :")
                .setMenu(R.menu.sheet_menu)
                .setClick(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        int delay_notification = 3; // Délai en secondes avant que la notification apparaisse
                        if(menuItem.getItemId() == R.id.City)
                        {
                            Toast.makeText(context1, "Citymapper !", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(
                                    context1,
                                    Navigation_Citymapper.class
                            );
                            // Permettront de montrer à titre d'exemple dans l'activité suivante que ça marche
                            intent.putExtra("latitudeRetour",latitudeRetour);
                            intent.putExtra("longitudeRetour",longitudeRetour);
                            scheduleNotification(getNotification("Il est temps de partir du resto \uD83D\uDE09"), delay_notification*1000);
                            context.startActivity(intent);
                        }
                        else
                        {
                            Toast.makeText(context1, "Maps !", Toast.LENGTH_SHORT).show();
                            Uri gmmIntentUri = Uri.parse("google.navigation:q="+content+",+France&mode=w");
                            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                            mapIntent.setPackage("com.google.android.apps.maps");
                            scheduleNotification(getNotification("Il est temps de partir du resto \uD83D\uDE09"), delay_notification*1000);
                            context1.startActivity(mapIntent);
                        }
                        return false;
                    }
                }).show();
    }

}

