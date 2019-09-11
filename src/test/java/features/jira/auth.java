package features.jira;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class auth {

    private RequestSpecification authSpec;
    @BeforeClass
    public void setupURL() {
        authSpec = new RequestSpecBuilder().
                setBaseUri("https://testingvn.atlassian.net").
                setContentType("application/json").
                setAccept("application/json").
                build();
    }

    public String issueKey = "";

    @Test
    public void authLogin(){
          Response response = given().
                auth().preemptive().basic("api@testing.edu.vn","@pitesting!2345").
                spec(authSpec).
                    get("rest/auth/1/session").
                then().log().all().
           extract().response();

        System.out.println(response.toString());


    }


    @Test
    public String createNewIssue(){

            issueInfo createIssue = new issueInfo(
                   new issueField(
                           new issueProject(
                                   "API2"),
                           "Issue 3 description",
                           "[Hoang] Create new issue 3 on project API2",
                           new issueType(
                                   "Bug")
                   )
            );

        Response response =
                given().
                        auth().preemptive().basic("api@testing.edu.vn","@pitesting!2345").
                        spec(authSpec).
                        body(createIssue).
                        when().log().all().
                        post("rest/api/2/issue").
                        then().log().all().
                        statusCode(201).
                        extract().
                        response();

        System.out.println(response.jsonPath().get("key").toString());
        issueKey = response.jsonPath().getString("key");

        return issueKey;

    }

    @Test
    public void commentIssue(){
        String key = createNewIssue();
        String commentPath = "rest/api/2/issue/"+ key +"/comment";

        commentIssue comment = new commentIssue("3rd comment");

        Response response = given().
                auth().preemptive().basic("api@testing.edu.vn","@pitesting!2345").
                spec(authSpec).
                body(comment).
                when().log().all().
                post(commentPath).
                then().log().all().
                statusCode(201).
                extract().
                response();

    }

    @Test
    public void assignIssue(){
        String key = createNewIssue();
        String assignPath = "rest/api/2/issue/"+ key +"/assignee";
        assignIssue assignIssue = new assignIssue("api");

        Response response = given().
                auth().preemptive().basic("api@testing.edu.vn","@pitesting!2345").
                spec(authSpec).
                body(assignIssue).
                when().log().all().
                put(assignPath).
                then().log().all().
                statusCode(204).
                extract().
                response();

    }

    @Test
    public void changeStatus(){


        String key = createNewIssue();
        String statusPath = "rest/api/2/issue/" + key + "/transitions";

        transitionIssue transitionIssue = new transitionIssue(new transition("31"));

        Response response = given().
                auth().preemptive().basic("api@testing.edu.vn","@pitesting!2345").
                spec(authSpec).
                body(transitionIssue).
                when().log().all().
                post(statusPath).
                then().log().all().
                statusCode(204).
                extract().response();


    }

}

