package com.bridgelabz.hotelreservationsystem;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

public class HotelReservation {

    public static List<Hotel> hotels = new ArrayList<>();
    Map<String,Integer> minRateInAll = new HashMap<>();

    public void addHotel(Hotel hotel) {
        hotels.add(hotel);
    }

    public Hotel getCheapestHotel(LocalDate startDate, LocalDate lastDate) {
        long daysBetween = ChronoUnit.DAYS.between(startDate, lastDate);
        int cheapRate;
        Hotel cheapest = Collections.min(hotels, Comparator.comparing(hotel -> hotel.weekDayRate));
        cheapRate = (int) ((daysBetween + 1) * cheapest.weekDayRate);
        System.out.println("Cheapest Hotel Name: " + cheapest.getName() + "\nTotal Rate: " + cheapRate);
        return cheapest;
    }

    public int findCheapestHotels(LocalDate startdate, LocalDate lastdate){

        List<LocalDate> dateList =  startdate.datesUntil(lastdate).collect(Collectors.toList());
        dateList.add(lastdate);

        for (LocalDate localDate : dateList) {
            DayOfWeek dayOfWeek2 = DayOfWeek.from(localDate);
            if (dayOfWeek2.equals(DayOfWeek.SATURDAY) || dayOfWeek2.equals(DayOfWeek.SUNDAY)) {
                for (Hotel hotel : hotels) {
                    hotel.totalRate += hotel.weekEndRate;
                }
            } else {
                for (Hotel hotel : hotels) {
                    hotel.totalRate += hotel.weekDayRate;
                }
            }
        }

        for (Hotel hotel: hotels) {
            System.out.println(hotel.getName()+" "+hotel.totalRate);
        }

        Hotel result = hotels.get(0);
        for (Hotel hotel : hotels) {
            if (result.totalRate > hotel.totalRate) {
                result = hotel;
                minRateInAll.put(hotel.getName(), hotel.totalRate);
            }
        }
        minRateInAll.put(result.getName(), result.totalRate);
        for (Hotel hotel: hotels) {
            if (hotel.totalRate == result.totalRate) {
                minRateInAll.put(hotel.getName(), hotel.totalRate);
            }
        }
        minRateInAll.forEach((key,value) -> System.out.println("Hotel Name: "+ key + ", Total Rate: " + value));
        return result.totalRate;
    }
}
