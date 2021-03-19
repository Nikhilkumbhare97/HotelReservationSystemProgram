package com.bridgelabz.hotelreservationsystem;

public class Hotel {

    public String name;
    public int regularWeekDayRate;
    public int regularWeekEndRate;
    public int rewardWeekDayRate;
    public int rewardWeekEndRate;
    public int totalRegularRate;
    public int totalRewardRate;
    public int rating;

    public Hotel(String name, int regularWeekDayRate, int regularWeekEndRate, int rewardWeekDayRate, int rewardWeekEndRate, int rating) {
        this.name = name;
        this.regularWeekDayRate = regularWeekDayRate;
        this.regularWeekEndRate = regularWeekEndRate;
        this.rewardWeekDayRate = rewardWeekDayRate;
        this.rewardWeekEndRate = rewardWeekEndRate;
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Hotel Name: " + name + " " + "\nRegularWeekDayRate: " + regularWeekDayRate + "\nRegularWeekEndRate: " + regularWeekEndRate + " " + "\nRewardWeekDayRate: " + rewardWeekDayRate + "\nRewardWeekEndRate: " + rewardWeekEndRate + "\nRating: " + rating;
    }
}
