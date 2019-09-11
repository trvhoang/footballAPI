package features.test;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;

public class Exercise1 {

    @BeforeTest
    void setup() {
        RestAssured.baseURI = "http://ergast.com";
    }

    @DataProvider(name = "driver")
    Object[][] driver(){
        return new Object[][]{
                new Object[]{"max_verstappen", 200, 6000L, "Verstappen"},
                new Object[]{"hamilton", 200, 6000L, "Hamilton"}
        };
    }


    @Test(dataProvider = "driver")
    void driver1Test(
            String driverName,
            int responseCode,
            long responseTime,
            String familyName) {
        given().log().all()
                .pathParam("driver_name", driverName)
                .get("/api/f1/drivers/{driver_name}.json")
                .then().log().all()
                .statusCode(responseCode)
                .time(lessThan(responseTime))
                .assertThat().body("MRData.DriverTable.Drivers[0].familyName", is(familyName));
    }

    @Test
    void driver2Test() {
        get("/api/f1/drivers/hamilton.json")
                .then().log().all()
                .statusCode(200)
                .time(lessThan(6000L));
    }

}
