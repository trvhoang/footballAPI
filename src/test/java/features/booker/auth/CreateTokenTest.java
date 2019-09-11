package features.booker.auth;

import com.google.gson.JsonObject;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

public class CreateTokenTest {

    private static RequestSpecification bookingSpec;
    @BeforeClass
    public static void createRequestSpecification() {

        JsonObject loginCredentials = new JsonObject();
        loginCredentials.addProperty("username", "admin");
        loginCredentials.addProperty("password", "password123");

        bookingSpec = new RequestSpecBuilder().
                setBaseUri("https://restful-booker.herokuapp.com").
                setContentType("application/json").
                setBasePath("auth").
                setBody(loginCredentials.toString()).
                build();
    }

    @Test
    public static void getToken(){
        given().
                spec(bookingSpec).
                when().
                post().
                then().log().all().
                assertThat().
                statusCode(200).
                assertThat().body("token", is(String.class));
    }
}
