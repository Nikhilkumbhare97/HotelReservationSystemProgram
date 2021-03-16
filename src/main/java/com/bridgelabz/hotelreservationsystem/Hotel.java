package com.bridgelabz.hotelreservationsystem;

public class Hotel{
    public String name;
    public int rate;

    public Hotel(String name, int rate){
        this.name = name;
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "Hotel Name: " + name + " " +"\nRate: " + rate;
    }
}
