package com.bridgelabz.hotelreservationsystem;

public class Hotel {

    public String name;
    public int weekDayRate;
    public int weekEndRate;
    public int totalRate;

    public Hotel(String name, int weekDayRate, int weekEndRate) {
        this.name = name;
        this.weekDayRate = weekDayRate;
        this.weekEndRate = weekEndRate;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Hotel Name: " + name + " " + "\nWeekDayRate: " + weekDayRate + "\nWeekEndRate: " + weekEndRate;
    }
}
