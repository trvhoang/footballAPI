package features.test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class RequestSpecificationTest {

    private static RequestSpecification requestSpec;
    @BeforeClass
    //pre-conditions
    //chỉ set điều kiện, ko run API => chỉ có hàm set
    public static void createRequestSpec() {
        requestSpec =
                new RequestSpecBuilder().
                        setBaseUri("http://ergast.com").
                        setBasePath("/api/f1").
                        build();
    }

    @Test
    public void useRequestSpec() {
        given().
                spec(requestSpec).
                when().
                get("/circuits/monza.json").
                then().
                assertThat().
                statusCode(200);
    }

}
