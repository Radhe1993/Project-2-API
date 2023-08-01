package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class PostsExtractionTest {


    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {

        RestAssured.baseURI = "https://gorest.co.in";
        //  RestAssured.port = 3030;
        response = given()
                .when()
                .get("/public/v2/posts?page=1&per_page=25")
                .then().statusCode(200);
        response.log().all();

    }

    //1. Extract the title
    @Test
    public void title()
    {
        List<String> title = response.extract().path("title");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of title is : " + title);
        System.out.println("------------------End of Test-----------------------------");
    }

    //2. Extract the total number of record

    @Test
    public void total()
    {
        List<String> total = response.extract().path("id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of total size  is : " + total.size());
        System.out.println("------------------End of Test-----------------------------");
    }

    //3. Extract the body of 15th record

    @Test
    public void body()
    {
        List< HashMap<String ,?>> body = response.extract().path("findAll{it.id==56955}.body");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of total size  is : " + body);
        System.out.println("------------------End of Test-----------------------------");
    }

    //4. Extract the user_id of all the records

    @Test
    public void userid()
    {

        List< HashMap<String ,?>> userid = response.extract().path("user_id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of user_id  is : " + userid);
        System.out.println("------------------End of Test-----------------------------");
    }

    //5. Extract the title of all the records
    @Test
    public void title1()
    {
        List<String> title = response.extract().path("title");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of title is : " + title);
        System.out.println("------------------End of Test-----------------------------");
    }

    //6. Extract the title of all records whose user_id = 5456
    @Test
    public void records()
    {
        List<HashMap<String,?>> userid = response.extract().path("findAll{it.user_id==4040693}.title");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of user_id  is : " + userid);
        System.out.println("------------------End of Test-----------------------------");
    }

    //7. Extract the body of all records whose id = 2671

    @Test
    public void body1()
    {
        List< HashMap<String ,?>> body = response.extract().path("findAll{it.id==56965}.body");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of total body  is : " + body);
        System.out.println("------------------End of Test-----------------------------");
    }
}
