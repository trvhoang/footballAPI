package features.test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class MockServerTest {

    private static RequestSpecification requestSpec;
    @BeforeClass
    public static void createRequestSpecification() {

        requestSpec = new RequestSpecBuilder().
                setBaseUri("http://localhost").
                setPort(9876).
                setContentType("application/json").
//                setBasePath("/api/f1").
                build();
    }

    @Test
    public static void getAddress(){
        given().
                spec(requestSpec).
                when().
                get("/address").
                then().log().all().
                statusCode(200);
    }
    @Test
    public void deserializeJsonToAddress() {

        Address myAddress =
                given().
                        spec(requestSpec).
                        when().
                        get("/address").
                        as(Address.class);

        Assert.assertEquals("Amsterdam", myAddress.getCity());
    }

    @Test
    public void serializeAddressToJson() {

        Address myAddress = new Address("my name", 10,10, "HCM");

        given().
                body(myAddress).
                when().
                post("http://localhost:9876/address").
                then().
                assertThat().
                statusCode(200);
    }

}
