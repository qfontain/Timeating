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
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ru.whalemare.sheetmenu.SheetMenu;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private List<Restaurants> restaurantsList = new ArrayList<>();
    private String content;
    private double latitudeDepart;
    private double longitudeDepart;
    private final static int FIRST_CALL_ID = 12;


    public RecyclerAdapter(List<Restaurants> restaurantsList) {
        this.restaurantsList = restaurantsList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurant_vue,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
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

    }

    @Override
    public int getItemCount() {
        return restaurantsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView Album;
        TextView Name;
        TextView Adress;
        RatingBar Rating;
        TextView Phone_Number;
        Button Temps_Attente;
        Button Temps_Trajet;

        public ViewHolder(View itemView) {
            super(itemView);
            Album= itemView.findViewById(R.id.album);
            Rating = itemView.findViewById(R.id.rating);
            Name = itemView.findViewById(R.id.name);
            Adress = itemView.findViewById(R.id.adress);
            Phone_Number = itemView.findViewById(R.id.phone_number);
            Temps_Attente = itemView.findViewById(R.id.temps_attente);
            Temps_Trajet = itemView.findViewById(R.id.temps_trajet);
        }
    }

}

