package com.bridgelabz.hotelreservationsystem;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

public class HotelReservation {

    public static List<Hotel> hotels = new ArrayList<>();
    Map<String, Integer> minRateInAll = new HashMap<>();
    Map<String, Integer> minRateAndBestRating = new HashMap<>();
    Hotel result;

    public void addHotel(Hotel hotel) {
        hotels.add(hotel);
    }

    public Hotel getCheapestHotel(LocalDate startDate, LocalDate lastDate) {
        long daysBetween = ChronoUnit.DAYS.between(startDate, lastDate);
        int cheapRate;
        Hotel cheapest = Collections.min(hotels, Comparator.comparing(hotel -> hotel.regularWeekDayRate));
        cheapRate = (int) ((daysBetween + 1) * cheapest.regularWeekDayRate);
        System.out.println("Cheapest Hotel Name: " + cheapest.name + "\nTotal Rate: " + cheapRate);
        return cheapest;
    }

    public void hotelData(LocalDate startDate, LocalDate lastDate) {

        List<LocalDate> dateList = startDate.datesUntil(lastDate).collect(Collectors.toList());
        dateList.add(lastDate);

        for (LocalDate localDate : dateList) {
            DayOfWeek dayOfWeek2 = DayOfWeek.from(localDate);
            if (dayOfWeek2.equals(DayOfWeek.SATURDAY) || dayOfWeek2.equals(DayOfWeek.SUNDAY)) {
                for (Hotel hotel : hotels) {
                    hotel.totalRate += hotel.regularWeekDayRate;
                }
            } else {
                for (Hotel hotel : hotels) {
                    hotel.totalRate += hotel.regularWeekEndRate;
                }
            }
        }

        result = hotels.get(0);
        for (Hotel hotel : hotels) {
            if (result.totalRate > hotel.totalRate) {
                result = hotel;
                minRateInAll.put(hotel.name, hotel.totalRate);
            }
        }
        minRateInAll.put(result.name, result.totalRate);
        for (Hotel hotel : hotels) {
            if (hotel.totalRate == result.totalRate) {
                minRateInAll.put(hotel.name, hotel.totalRate);
                minRateAndBestRating.put(hotel.name, hotel.rating);
            }
        }
    }

    public void findCheapestHotels(LocalDate startDate, LocalDate lastDate) {
        hotelData(startDate, lastDate);
        System.out.println("Min Rate Hotels");
        minRateInAll.forEach((key, value) -> System.out.println("Hotel Name: " + key + "\nTotal Rate: " + value));
    }
    public void findCheapestHotelByRatings(LocalDate startDate, LocalDate lastDate) {
        findCheapestHotels(startDate, lastDate);
        Integer maxRating = (Collections.max(minRateAndBestRating.values()));
        for (Map.Entry<String, Integer> entry : minRateAndBestRating.entrySet()) {
            if (entry.getValue().equals(maxRating)) {
                System.out.println("Hotel with Minimum Total Rate And Best Rating");
                System.out.println("Hotel Name: " + entry.getKey() + "\nRating: " + entry.getValue() + "\nTotal Rate: " + result.totalRate);
            }
        }
    }

    public Hotel findBestRatedHotel(LocalDate startDate, LocalDate lastDate) {
        hotelData(startDate, lastDate);
        Hotel bestRated = Collections.max(hotels, Comparator.comparing(hotel -> hotel.rating));
        System.out.println("Best Rated Hotel");
        System.out.println("Hotel Name: " + bestRated.name + "\nTotal Rate: " + bestRated.totalRate);
        return bestRated;
    }
}
