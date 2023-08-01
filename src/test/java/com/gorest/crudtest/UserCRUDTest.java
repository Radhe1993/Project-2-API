package com.gorest.crudtest;

import com.gorest.model.UserPojo;
import com.gorest.testbase.TestBase;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class UserCRUDTest extends TestBase
{
    String token = "f8f231911770bd29394ba6350b92e3e88b00d7f482a4b763d244d3d42f395d19";
        int userId;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in";
    }

    @Test()
    public void verifyUserCreatedSuccessfully() {

        //  String email = TestUtils.getRandomValue() + "prime@gmail.com";
        UserPojo userPojo = new UserPojo();
        userPojo.setName("Ram");
        userPojo.setEmail("primetesting@gmail.com");
        userPojo.setGender("male");
        userPojo.setStatus("active");
// make request to server to create new user
        Response response = given()
                .headers("Content-Type", "application/json", "Authorization", "Bearer " + token)
                .when()
                .body(userPojo)
                .post("/public/v2/users/");
//To fetch response from server
        response.then().log().all().statusCode(201);
        String responseBody = response.getBody().asString();
        JsonPath jsonPath = new JsonPath(responseBody);
        int userId = jsonPath.getInt("id");
        System.out.println("user id " + userId);

    }

    @Test
    public void verifyUserUpdateSuccessfully() {
        //  String email = TestUtils.getRandomValue() + "prime@gmail.com";
        UserPojo userPojo = new UserPojo();
        userPojo.setName("Ram");
        userPojo.setEmail("primetesting@gmail.com");
        userPojo.setGender("male");
        userPojo.setStatus("active");
// make request to server to create new user
        Response response = given()

                .headers("Content-Type", "application/json", "Authorization", "Bearer " + token)
                .when()
                .body(userPojo)
                .patch("/public/v2/users/" + userId);
//To fetch response from server
        response.then().log().all().statusCode(200);
        String responseBody = response.getBody().asString();
        JsonPath jsonPath = new JsonPath(responseBody);
        int userId = jsonPath.getInt("id");
        System.out.println("user id " + userId);

        }

        @Test
        public void VerifyUserDeleteSuccessfully () {

            given()
                    .headers("Content-Type", "application/json", "Authorization", "Bearer " + token)
                    .pathParam("id", userId);

        }
    }


