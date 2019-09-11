package features.booker.booking;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.basic;
import static io.restassured.RestAssured.given;

public class UpdateBooking {

    private static RequestSpecification bookingSpec;
    @BeforeClass
    public static void createRequestSpecification() {
        bookingSpec = new RequestSpecBuilder().
//                setBaseUri("https://restful-booker.herokuapp.com").
//                setBasePath("/booking").
                setContentType("application/json").
                setAccept("application/json").
                setAuth(basic("admin", "password123")).
                build();
    }

    @Test
    public void updateSpecificBookingId(){
        BookingId upDateBooking = new BookingId(
                "Jim",
                "Brown",
                111,
                true,
                new BookingDates(
                        "2018-01-01",
                        "2019-01-01"),
                "Breakfast");

        given().
                auth().preemptive().basic("admin", "password123").
                spec(bookingSpec).
                body(upDateBooking).
                when().log().all().
                put("https://restful-booker.herokuapp.com/booking/11").
                then().log().all().
                assertThat().
                statusCode(200);
    }
}
