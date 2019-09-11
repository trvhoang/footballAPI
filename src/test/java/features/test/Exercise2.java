package features.test;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;

public class Exercise2 {
    @BeforeTest
    void setup(){
        RestAssured.baseURI = "http://swapi.co";
    }



    @Test
    void queryParam(){
        given()
                .queryParam("search", 2)
                .get("/api/people")
                .then().log().all()
                .statusCode(200)
                .time(lessThan(6000L))
                .assertThat().body("results[0].height", is("96"));
    }

}
