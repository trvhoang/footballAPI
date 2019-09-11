package features.test;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class MockAPITest {
    @BeforeTest
    void setup(){
        RestAssured.baseURI = "http://jsonplaceholder.typicode.com";
        RestAssured.basePath = "/posts";
    }

    @Test
    void getRequestTest(){
        given()
                .then() //validation expected results
                .statusCode(200);
    }

    @Test
    void postRequestTest(){
        given().baseUri("http://jsonplaceholder.typicode.com").log().all() //pre-condition
                .param("title","foo")
                .param("body", "bodar")
                .param("userID", 1)
                .when()
                .post("/posts")
                .then().log().all()
                .statusCode(200);

    }

    @Test
    void putRequestTest(){
        given().baseUri("http://jsonplaceholder.typicode.com").log().all()
                .param("title", "foo")
                .when()
                .put("/posts/1")
                .then().log().all()
                .statusCode(200);
    }

    @Test
    void delRequestTest(){
        given().baseUri("http://jsonplaceholder.typicode.com").log().all()
                .when()
                .delete("/posts/2")
                .then().log().all()
                .statusCode(200)
                .time(lessThan(2000L));

    }

}
