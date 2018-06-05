package com.example.quentin.quentintest;

/**
 * Created by sebas on 05/06/2018.
 */

public class Restaurants{

    public String address;
    public Coordinates coordinates = new Coordinates();
    public String id;
    public String international_phone_number;
    public String name;
    public Integer Actual_Populartime;
    public Double rating;
    public Time_Spent time_spent = new Time_Spent();
    public Integer TravelTime;
    public Double TotalTime;

    public Restaurants(String address, Coordinates coordinates, String international_phone_number,
                       String name, Integer Actual_Populartime, Double rating, Time_Spent time_spent,
                       Integer TravelTime, Double TotalTime) {
        this.address = address;
        this.coordinates = coordinates;
        this.international_phone_number = international_phone_number;
        this.name = name;
        this.Actual_Populartime = Actual_Populartime;
        this.rating = rating;
        this.time_spent = time_spent;
        this.TravelTime = TravelTime;
        this.TotalTime = TotalTime;
    }

    public String getAddress() {
        return address;
    }
    public Coordinates getCoordinates() {
        return coordinates;
    }
    public String getInternationalPhoneNumber() {
        return international_phone_number;
    }
    public String getName() {
        return name;
    }
    public Integer getActual_Populartime() {
        return Actual_Populartime;
    }
    public Double getRating() {
        return rating;
    }
    public Time_Spent getTime_spent() {
        return time_spent;
    }
    public Integer getTravelTime() {
        return TravelTime;
    }
    public Double getTotalTime() {
        return TotalTime;
    }
    public void setTotalTime(Double totalTime) {
        TotalTime = totalTime;
    }
}

class Coordinates {

    public Double lat;
    public Double lng;
    public Double getLat() {
        return lat;
    }
    public Double getLng() {
        return lng;
    }
}

class Time_Spent {

    public Double time_min;
    public Double time_max;
    public Double getTime_min() {return time_min;}
    public Double getTime_max() {return time_max;}
}
