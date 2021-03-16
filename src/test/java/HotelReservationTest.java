import com.bridgelabz.hotelreservationsystem.Hotel;
import com.bridgelabz.hotelreservationsystem.HotelReservation;
import static com.bridgelabz.hotelreservationsystem.HotelReservation.hotels;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HotelReservationTest {

    HotelReservation hotelReservation;

    Hotel lakewood = new Hotel("LakeWood", 110);
    Hotel bridgewood = new Hotel("BridgeWood", 150);
    Hotel ridgewood = new Hotel("RidgeWood", 220);

    @BeforeEach
    public void setUp(){
        hotelReservation = new HotelReservation();
    }

    @Test
    public void givenHotel_ToAddInHotelReservation_ShouldReturnTrueIfItContain(){
        hotelReservation.addHotel(lakewood);
        hotelReservation.addHotel(bridgewood);
        hotelReservation.addHotel(ridgewood);

        Assertions.assertTrue(hotels.contains(lakewood));
        Assertions.assertTrue(hotels.contains(bridgewood));
        Assertions.assertTrue(hotels.contains(ridgewood));

        hotels.forEach(System.out::println);
    }
}
