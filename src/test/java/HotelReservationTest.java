import com.bridgelabz.hotelreservationsystem.Hotel;
import com.bridgelabz.hotelreservationsystem.HotelReservation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

import static com.bridgelabz.hotelreservationsystem.HotelReservation.hotels;

public class HotelReservationTest {

    HotelReservation hotelReservation;

    Hotel lakewood = new Hotel("LakeWood", 110, 90, 3);
    Hotel bridgewood = new Hotel("BridgeWood", 150, 50, 4);
    Hotel ridgewood = new Hotel("RidgeWood", 220, 150, 5);

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
    public void givenDateRange_ShouldPrintCheapestHotels() {
        LocalDate startDate = LocalDate.of(2020, Month.SEPTEMBER, 11);
        LocalDate lastDate = LocalDate.of(2020, Month.SEPTEMBER, 12);

        hotelReservation.findCheapestHotels(startDate, lastDate);
    }

    @Test
    public void givenDateRange_ShouldPrintCheapestHotelByRating() {
        LocalDate startDate = LocalDate.of(2020, Month.SEPTEMBER, 11);
        LocalDate lastDate = LocalDate.of(2020, Month.SEPTEMBER, 12);

        hotelReservation.findCheapestHotelByRatings(startDate, lastDate);
    }

    @Test
    public void givenDateRange_ShouldReturnBestRatedHotel() {
        LocalDate startDate = LocalDate.of(2020, Month.SEPTEMBER, 11);
        LocalDate lastDate = LocalDate.of(2020, Month.SEPTEMBER, 12);

        Hotel bestRatedHotel = hotelReservation.findBestRatedHotel(startDate, lastDate);
        Assertions.assertEquals("RidgeWood", bestRatedHotel.name);
    }
}
