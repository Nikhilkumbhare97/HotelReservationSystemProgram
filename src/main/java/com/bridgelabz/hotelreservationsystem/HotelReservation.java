package com.bridgelabz.hotelreservationsystem;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

public class HotelReservation {

    public static List<Hotel> hotels = new ArrayList<>();
    Map<String, Integer> minRateInRegular = new HashMap<>();
    Map<String, Integer> minRateInReward = new HashMap<>();
    Map<String, Integer> minRegularRateAndBestRating = new HashMap<>();
    Map<String, Integer> minRewardRateAndBestRating = new HashMap<>();
    Hotel result1;
    Hotel result2;

    public void dateValidation (String startDate, String lastDate) throws HotelReservationException {
        String pattern = "^([0-9]{4})-(1[0-2]|0[1-9])-(3[01]|[12][0-9]|0[1-9])$";
        if (startDate.matches(pattern) && lastDate.matches(pattern)) {
            System.out.println("Valid Dates Pattern");
        }else{
            throw new HotelReservationException("Invalid Date Pattern");
        }
    }

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
                    hotel.totalRegularRate += hotel.regularWeekDayRate;
                    hotel.totalRewardRate += hotel.rewardWeekDayRate;
                }
            } else {
                for (Hotel hotel : hotels) {
                    hotel.totalRegularRate += hotel.regularWeekEndRate;
                    hotel.totalRewardRate += hotel.rewardWeekEndRate;
                }
            }
        }

        result1 = hotels.get(0);
        for (Hotel hotel : hotels) {
            if (result1.totalRegularRate > hotel.totalRegularRate) {
                result1 = hotel;
                minRateInRegular.put(hotel.name, hotel.totalRegularRate);
            }
        }
        minRateInRegular.put(result1.name, result1.totalRegularRate);
        for (Hotel hotel : hotels) {
            if (hotel.totalRegularRate == result1.totalRegularRate) {
                minRateInRegular.put(hotel.name, hotel.totalRegularRate);
                minRegularRateAndBestRating.put(hotel.name, hotel.rating);
            }
        }

        result2 = hotels.get(0);
        for (Hotel hotel : hotels) {
            if (result2.totalRewardRate > hotel.totalRewardRate) {
                result2 = hotel;
                minRateInReward.put(hotel.name, hotel.totalRewardRate);
            }
        }
        minRewardRateAndBestRating.put(result2.name, result2.totalRewardRate);
        for (Hotel hotel : hotels) {
            if (hotel.totalRewardRate == result2.totalRewardRate) {
                minRateInReward.put(hotel.name, hotel.totalRewardRate);
                minRewardRateAndBestRating.put(hotel.name, hotel.rating);
            }
        }
    }

    public void findCheapestRegularHotels(LocalDate startDate, LocalDate lastDate) {
        hotelData(startDate, lastDate);
        System.out.println("Min Rate Hotels");
        minRateInRegular.forEach((key, value) -> System.out.println("Hotel Name: " + key + "\nTotal Rate: " + value));
    }

    public Integer findCheapestRegualarHotelByRatings(String startdate, String lastdate) throws HotelReservationException {
        dateValidation(startdate,lastdate);
        LocalDate startDate = LocalDate.parse(startdate);
        LocalDate lastDate = LocalDate.parse(lastdate);
        findCheapestRegularHotels(startDate, lastDate);
        Integer maxRating = (Collections.max(minRegularRateAndBestRating.values()));
        System.out.println("Hotel with Minimum Total Rate And Best Rating");
        minRegularRateAndBestRating.entrySet().stream().filter(p -> p.getValue().equals(maxRating))
                .forEach(p -> System.out.println(p.getKey() + "\nRating: " + p.getValue() + "\nTotal Rate: " + result1.totalRegularRate));
        return maxRating;
    }

    public Hotel findBestRegularRatedHotel(LocalDate startDate, LocalDate lastDate) {
        hotelData(startDate, lastDate);
        Hotel bestRated = Collections.max(hotels, Comparator.comparing(hotel -> hotel.rating));
        System.out.println("Best Rated Hotel");
        System.out.println("Hotel Name: " + bestRated.name + "\nTotal Rate: " + bestRated.totalRegularRate);
        return bestRated;
    }

    public void findCheapestRewardHotels(LocalDate startDate, LocalDate lastDate) {
        hotelData(startDate, lastDate);
        System.out.println("Min Rate Hotels");
        minRateInReward.forEach((key, value) -> System.out.println("Hotel Name: " + key + "\nTotal Rate: " + value));
    }

    public Integer findCheapestRewardHotelByRatings(String startdate, String lastdate) throws HotelReservationException {
        dateValidation(startdate,lastdate);
        LocalDate startDate = LocalDate.parse(startdate);
        LocalDate lastDate = LocalDate.parse(lastdate);
        findCheapestRewardHotels(startDate, lastDate);
        Integer maxRating = (Collections.max(minRewardRateAndBestRating.values()));
        System.out.println("Hotel with Minimum Total Rate And Best Rating");
        minRewardRateAndBestRating.entrySet().stream().filter(p -> p.getValue().equals(maxRating))
                .forEach(p -> System.out.println(p.getKey() + "\nRating: " + p.getValue() + "\nTotal Rate: " + result2.totalRewardRate));
        return maxRating;
    }
}