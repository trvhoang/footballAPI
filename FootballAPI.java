import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.collections.Lists;
import org.testng.collections.Sets;

import java.sql.Array;
import java.util.*;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

public class FootballAPI {
    @BeforeTest
    public void setUpRestAssured() {
        RestAssured.baseURI = "https://raw.githubusercontent.com";
        RestAssured.basePath = "/openfootball/world-cup.json/master/2018/worldcup.json";
    }


    @Test
    void getAllRounds(){
        Response response =
                given().get()
                        .then()
                        .extract().response();
        List<Object> getRounds = response.jsonPath().get("rounds.matches.group");
        int rounds = getRounds.size();
        System.out.println(rounds);
        System.out.println(getRounds);
    }

    @Test
    public void getTeam(){
        Response response =
                given().get()
                        .then()
                        .extract().response();

        List<List<String>> getTeam1 = response.jsonPath().get("rounds.matches.team1.name");
        List<List<String>> getTeam2 = response.jsonPath().get("rounds.matches.team2.name");
        List<List<String>> getGroup = response.jsonPath().get("rounds.matches.group");
        // List<List<String>>

        int round =0;

        for (int i = 0; i < getGroup.size(); i++){
            for (int j = 0; j < getGroup.get(i).size(); j++) {
                    if (round > 15){
                        break;
                    } else System.out.format("%-32s %-32s %-32s\n",getGroup.get(i).get(j), getTeam1.get(i).get(j), getTeam2.get(i).get(j));
                    //system.out.format("%32s %-32s %-32s\n",)
                    round++;
                }
        }
    }

    @Test
    public void champGoals(){
        Response response =
                given().get()
                        .then()
                        .extract().response();

        List<Integer> getGoals1 = response.jsonPath().get("rounds.matches[-1].score1");
        List<Integer> getGoals2 = response.jsonPath().get("rounds.matches[-1].score2");
        List<String> champT1 = response.jsonPath().get("rounds.matches[-1].team1.name");
        List<String> champT2 = response.jsonPath().get("rounds.matches[-1].team2.name");

        String champName = "";

        if (getGoals1.get(0) > getGoals2.get(0)){
            champName = champT1.get(0);

        } else
            champName = champT2.get(0);

        System.out.println("Champion team is: " + champName);

        int goal = 0;
        List<Object> getMatches = response.jsonPath().get("rounds.matches");
        List<List<String>> getTeam1 = response.jsonPath().get("rounds.matches.team1.name");
        List<List<String>> getTeam2 = response.jsonPath().get("rounds.matches.team2.name");
        List<List<Integer>> goalTeam1 = response.jsonPath().get("rounds.matches.score1");
        List<List<Integer>> goalTeam2 = response.jsonPath().get("rounds.matches.score2");

        for (int i = 0; i < getMatches.size(); i++){
            for (int j = 0; j < getTeam1.get(i).size(); j++){
                if(champName.equals(getTeam1.get(i).get(j))){
                    goal = goal + goalTeam1.get(i).get(j);
                } else if (champName.equals(getTeam2.get(i).get(j))){
                    goal = goal + goalTeam2.get(i).get(j);
                }
            }
        }
        System.out.println("Total goals of champion team is: " + goal);

    }

    @Test
    public void goldenShoes(){
        Response response =
                        given(). get()
                        .then()
                        .extract().response();

        List<List<List<String>>> goalTeam1 = response.jsonPath().get("rounds.matches.goals1.name");
        List<List<List<String>>> goalTeam2 = response.jsonPath().get("rounds.matches.goals2.name");
        List<String> goalList = new ArrayList<>();

        for (int i = 0; i < goalTeam1.size(); i ++){
            for (int j = 0; j < goalTeam1.get(i).size(); j++){
                for (int k = 0; k < goalTeam1.get(i).get(j).size(); k++){
                    goalList.add(goalTeam1.get(i).get(j).get(k));
                }
            }
        }

        for (int m = 0; m < goalTeam2.size(); m++){
            for (int n = 0; n < goalTeam2.get(m).size(); n++){
                for (int l = 0; l < goalTeam2.get(m).get(n).size(); l++){
                    goalList.add(goalTeam2.get(m).get(n).get(l));
                }
            }
        }
        goalList.removeAll(Collections.singleton(new ArrayList<>()));
//        System.out.println(goalList);

        List<String> goalListDistinct = Lists.newArrayList(Sets.newHashSet(goalList));
//        System.out.println(goalListDistinct);

        List<Integer> goalListNo = new ArrayList<>();

        int goalOfPlayer = 0;
        for (int x = 0; x < goalListDistinct.size(); x++){
            for (int y = 0; y < goalList.size(); y++){
                if (goalListDistinct.get(x).contains(goalList.get(y))){
                    goalOfPlayer = goalOfPlayer + 1;
                }
            }
            goalListNo.add(goalOfPlayer);
            goalOfPlayer = 0;

        }

        int temp = goalListNo.indexOf(Collections.max(goalListNo));
        System.out.println("The owner of golden shoes award is: " + goalListDistinct.get(temp));
        System.out.println("He scored: " + goalListNo.get(temp) + " goals");

    }





}
