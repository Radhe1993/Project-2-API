package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class UserExtractionTest {


    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in";
        //  RestAssured.port = 3030;
        response = given()
                .when()
                .get("/public/v2/users?page=1&per_page=20")
                .then().statusCode(200);
        response.log().all();


    }

    //Extract the All Ids
    @Test
    public void Ids()
    {
       List<Integer> ids =  response.extract().path("id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of id : " + ids);
        System.out.println("------------------End of Test---------------------------");

    }

    //2. Extract the all Names
    @Test
    public void name()
    {
        List<String> name =  response.extract().path("name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of name : " +name);
        System.out.println("------------------End of Test---------------------------");
    }

    //3. Extract the name of 5th object
    @Test
    public void object()
    {
        List<String> name =  response.extract().path("name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of name : " +name.get(4));
        System.out.println("------------------End of Test---------------------------");
    }

    //4. Extract the names of all object whose status = inactive
    @Test
    public void status()
    {
        List<HashMap<String ,?>> name = response.extract().path("findAll{it.status=='inactive'}.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of name : " +name);
        System.out.println("------------------End of Test---------------------------");
    }

    //5. Extract the gender of all the object whose status = active

    @Test
    public void gender()
    {
        List<HashMap<String ,?>> gender = response.extract().path("findAll{it.status=='active'}.gender");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of gender : " +gender);
        System.out.println("------------------End of Test---------------------------");
    }

    //6. Print the names of the object whose gender = female
    @Test
    public void name1()
    {
        List<HashMap<String ,?>> names = response.extract().path("findAll{it.gender=='female'}.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of name : " +names);
        System.out.println("------------------End of Test---------------------------");
    }

    //7. Get all the emails of the object where status = inactive
    @Test
    public void emails()
    {
        List<HashMap<String ,?>> emails = response.extract().path("findAll{it.status=='inactive'}.email");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of email : " +emails);
        System.out.println("------------------End of Test---------------------------");
    }

    //8. Get the ids of the object where gender = male
    @Test
    public void ids()
    {
        List<HashMap<String ,?>> ids = response.extract().path("findAll{it.gender=='male'}.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of ids : " +ids);
        System.out.println("------------------End of Test---------------------------");
    }

    //9. Get all the status

    @Test
    public void status1()
    {
        List<HashMap<String ,?>> status = response.extract().path("status");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of ids : " +status);
        System.out.println("------------------End of Test---------------------------");
    }

    //10. Get email of the object where name = Karthik Dubashi IV
    @Test
    public void email1()
    {
        List<String>  email = response.extract().path("findAll{it.name=='Saraswati Dhawan'}.email");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of email : " +email);
        System.out.println("------------------End of Test---------------------------");
    }

    //11. Get gender of id = 5471
    @Test
    public void id()
    {
       List<String>  gender = response.extract().path("findAll{it.id==4040685}.gender");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of gender : " +gender);
        System.out.println("------------------End of Test---------------------------");
    }


}
