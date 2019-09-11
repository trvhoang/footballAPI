package features.booker.booking;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetBookingIdsTest {

    private static RequestSpecification boockingSpec;
    @BeforeClass
    public static void createRequestSpecification() {

                boockingSpec = new RequestSpecBuilder().
                setBaseUri("https://restful-booker.herokuapp.com").
                setBasePath("booking").
                build();
    }

    @Test
    public void getAllIDs(){
        given().
                spec(boockingSpec).
                when().
                get().
                then().
                assertThat().
                statusCode(200);
    }

    @Test
    public void filterByName(){
        given().
                spec(boockingSpec).
                queryParam("firstname", "Sally").
                queryParam("lastname", "Jackson").
                when().
                get().
                then().log().all().
                assertThat().
                statusCode(200);
    }

    @Test
    public void filterByDate(){
        given().
                spec(boockingSpec).
                queryParam("checkin", "2014-03-13").
                queryParam("checkout", "2014-05-21").
                when().
                get().
                then().log().all().
                assertThat().
                statusCode(200);
    }
}
