package com.bridgelabz.hotelreservationsystem;

public class Hotel {

    public String name;
    public int weekDayRate;
    public int weekEndRate;
    public int totalRate;
    public int rating;

    public Hotel(String name, int weekDayRate, int weekEndRate, int rating) {
        this.name = name;
        this.weekDayRate = weekDayRate;
        this.weekEndRate = weekEndRate;
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Hotel Name: " + name + " " + "\nWeekDayRate: " + weekDayRate + "\nWeekEndRate: " + weekEndRate + "\nRating: " + rating + "";
    }
}
