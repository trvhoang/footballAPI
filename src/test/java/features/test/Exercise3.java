package features.test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasItems;

public class Exercise3 {
    @BeforeTest
    void setup(){
        RestAssured.baseURI = "http://restapi.demoqa.com";
        RestAssured.basePath = "/utilities/books/getallbooks";
    }

    @Test
    void getBookAuthor(){
        Response response =
                given().get()
                .then().log().all()
                        .extract().response();

        List<String> authors = response.path("books.author");
        System.out.println(authors);

        String[] expectedResult = {"Marijn Haverbeke, Addy Osmani, Axel Rauschmayer"};
        //compare expected result to author list
    }

    @Test
    void getBookPages() {
        Response response =
                given().
                        get()
                        .then().log().all()
                        .extract().response();

        List<String> titles2 = response.path("books.findAll {it.pages >= 500}.title");
        System.out.println(titles2);

//        List<Integer> pages = response.path("books.pages");
//        List<String> titles = response.path("books.title");
//        for (int i = 0; i < pages.size(); i++){
//            if(pages.get(i) > 500)
//                System.out.println(titles.get(i));
//        }

    }


    @Test
    void hasAuthor(){
        String[] checkList = {"Marijn Haverbeke", "Addy Osmani", "Axel Rauschmayer"};

        Response response =
                given().get()
                .then().log().all()
                        .extract().response();

        List<String> authorList = response.path("books.author");
        assertThat(authorList, hasItems(checkList));

    }

    @Test
    void checkMinMaxPage(){

        Response response =
                    given().get()
                    .then().log().all()
                            .extract().response();


        Integer pageMax = response.path("books.pages.max()"); //lamda
        Integer pageMin = response.path("books.pages.min()");


        List<Integer> page = response.path("books.pages");
        List<String> title = response.path("books.title");



        for (int i = 0; i < page.size(); i++){

            if (pageMax.equals(page.get(i))){

                System.out.println("Page Max: " +title.get(i));
            } else if (pageMin.equals(page.get(i))){

                System.out.println("Page Min: " +title.get(i));
            }

        }




    }


}
