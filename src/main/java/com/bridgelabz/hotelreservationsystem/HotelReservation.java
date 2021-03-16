package com.bridgelabz.hotelreservationsystem;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class HotelReservation{

    public static List<Hotel> hotels =  new ArrayList<>();

    public void addHotel(Hotel hotel) {
        hotels.add(hotel);
    }

    public Hotel getCheapestHotel(LocalDate startDate, LocalDate lastDate ){
        long daysBetween = ChronoUnit.DAYS.between(startDate, lastDate) ;
        int cheapRate;
        Hotel cheapest = Collections.min(hotels, Comparator.comparing(hotel -> hotel.rate));
        cheapRate = (int) ((daysBetween + 1)*cheapest.getRate());
        System.out.println("Cheapest Hotel Name: "+ cheapest.getName() + "\nTotal Rate: "+cheapRate);
        return cheapest;
    }
}
