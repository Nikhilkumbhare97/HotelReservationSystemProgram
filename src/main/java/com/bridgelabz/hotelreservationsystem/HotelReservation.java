package com.bridgelabz.hotelreservationsystem;

import java.util.ArrayList;
import java.util.List;

public class HotelReservation{

    public static List<Hotel> hotels =  new ArrayList<>();

    public void addHotel(Hotel hotel) {
        hotels.add(hotel);
    }
}
