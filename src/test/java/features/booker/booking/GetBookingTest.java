package features.booker.booking;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetBookingTest {

    private static RequestSpecification boockingSpec;
    @BeforeClass
    public static void createRequestSpecification() {

        boockingSpec = new RequestSpecBuilder().
                setBaseUri("https://restful-booker.herokuapp.com").
                setBasePath("/booking").
                setContentType("application/json").
                build();
    }

    @Test
    public static void specificId(){
        BookingId bookingId = given().
                spec(boockingSpec).
                when().log().all().
                get("/1").
                as(BookingId.class);

        Assert.assertEquals("Jim", bookingId.getFirstname());
    }
}
