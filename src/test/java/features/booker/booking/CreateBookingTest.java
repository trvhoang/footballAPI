package features.booker.booking;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class CreateBookingTest {
    private static RequestSpecification bookingSpec;
    @BeforeClass
    public static void createRequestSpecification() {

        bookingSpec = new RequestSpecBuilder().
                setBaseUri("https://restful-booker.herokuapp.com").
                setBasePath("/booking").
                setContentType("application/json").
                build();
    }

    @Test
    public static void createBooking(){
        BookingId createBooking = new BookingId(
                "Jim",
                "Brown",
                111,
                true,
                new BookingDates(
                        "2018-01-01",
                        "2019-01-01"),
                "Breakfast");

        given().
                spec(bookingSpec).
                body(createBooking).
                when().
                post().
                then().
                assertThat().
                statusCode(200);
    }
}
