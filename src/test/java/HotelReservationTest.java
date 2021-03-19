import com.bridgelabz.hotelreservationsystem.Hotel;
import com.bridgelabz.hotelreservationsystem.HotelReservation;
import com.bridgelabz.hotelreservationsystem.HotelReservationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

import static com.bridgelabz.hotelreservationsystem.HotelReservation.hotels;

@SuppressWarnings("ALL")
public class HotelReservationTest {

    HotelReservation hotelReservation;

    Hotel lakewood = new Hotel("LakeWood", 110, 90, 80, 80, 3);
    Hotel bridgewood = new Hotel("BridgeWood", 150, 50, 110, 50, 4);
    Hotel ridgewood = new Hotel("RidgeWood", 220, 150, 100, 40, 5);

    @BeforeEach
    public void setUp() {
        hotelReservation = new HotelReservation();
        hotelReservation.addHotel(lakewood);
        hotelReservation.addHotel(bridgewood);
        hotelReservation.addHotel(ridgewood);
    }

    @Test
    public void givenHotel_ToAddInHotelReservation_ShouldReturnTrueIfItContain() {
        Assertions.assertTrue(hotels.contains(lakewood));
        Assertions.assertTrue(hotels.contains(bridgewood));
        Assertions.assertTrue(hotels.contains(ridgewood));
        hotels.forEach(System.out::println);
    }

    @Test
    public void givenDateRange_ShouldReturnCheapestHotel() {
        LocalDate startDate = LocalDate.of(2020, Month.SEPTEMBER, 10);
        LocalDate lastDate = LocalDate.of(2020, Month.SEPTEMBER, 11);
        Hotel cheapestHotel = hotelReservation.getCheapestHotel(startDate, lastDate);
        Assertions.assertEquals("LakeWood", cheapestHotel.name);
    }

    @Test
    public void givenDateRange_ShouldPrintCheapestHotelsForRegularCostomer() {
        LocalDate startDate = LocalDate.of(2020, Month.SEPTEMBER, 11);
        LocalDate lastDate = LocalDate.of(2020, Month.SEPTEMBER, 12);
        hotelReservation.findCheapestRegularHotels(startDate, lastDate);
    }

    @Test
    public void givenDateRange_ShouldReturnCheapestHotelByRatingForRegularCostomer() throws HotelReservationException {
        String startDate = "2020-09-11";
        String lastDate = "2020-09-12";
        String pattern = "^([0-9]{4})-(1[0-2]|0[1-9])-(3[01]|[12][0-9]|0[1-9])$";
        if (startDate.matches(pattern) && lastDate.matches(pattern)) {
            try {
                Integer rating = hotelReservation.findCheapestRegualarHotelByRatings(startDate, lastDate);
                Assertions.assertEquals(4, rating);
            } catch (Exception e) {
                System.out.println(e);
            }
        }else{
            throw new HotelReservationException("Invalid Date Pattern");
        }
    }

    @Test
    public void givenDateRange_ShouldReturnBestRatedHotelForRegularCostomer() {
        LocalDate startDate = LocalDate.of(2020, Month.SEPTEMBER, 11);
        LocalDate lastDate = LocalDate.of(2020, Month.SEPTEMBER, 12);
        Hotel bestRatedHotel = hotelReservation.findBestRegularRatedHotel(startDate, lastDate);
        Assertions.assertEquals("RidgeWood", bestRatedHotel.name);
    }

    @Test
    public void givenDateRange_ShouldReturnBestRatedHotelForRewardCostomer() throws HotelReservationException {
        String startDate = "2020-09-11";
        String lastDate = "2020-09-12";
        String pattern = "^([0-9]{4})-(1[0-2]|0[1-9])-(3[01]|[12][0-9]|0[1-9])$";
        if (startDate.matches(pattern) && lastDate.matches(pattern)) {
            try {
                Integer rating = hotelReservation.findCheapestRewardHotelByRatings(startDate, lastDate);
                Assertions.assertEquals(5, rating);
            } catch (Exception e) {
                System.out.println(e);
            }
        }else{
            throw new HotelReservationException("Invalid Date Pattern");
        }
    }
}
