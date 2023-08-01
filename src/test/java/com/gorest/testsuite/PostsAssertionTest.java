package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PostsAssertionTest {


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

    //1. Verify the if the total record is 25
    @Test
    public void total()
    {
        response.body("id.size()",equalTo(25));
    }

    //2. Verify the if the title of id = 2730 is equal to ”Ad ipsa coruscus ipsam eos demitto
    //centum.”

    @Test
    public void id()
    {
        response.body("title[16]",equalTo("Autem cultura baiulus tonsor ancilla tremo."));
    }

    //3. Check the single user_id in the Array list (5522)
    @Test
    public void singleUserId()
    {
        response.body("user_id[7]",equalTo(4040697));
    }

    //4. Check the multiple ids in the ArrayList (2693, 2684,2681)
    @Test
    public void multiplIds()
    {
        response.body("user_id",hasItems(4040734,4040719,4040692));
    }

    //5. Verify the body of userid = 2678 is equal “Carus eaque voluptatem. Calcar
    //spectaculum coniuratio. Abstergo consequatur deleo. Amiculum advenio dolorem.
    //Sollers conservo adiuvo. Concedo campana capitulus. Adfectus tibi truculenter.
    //Canto temptatio adimpleo. Ter degenero animus. Adeo optio crapula. Abduco et
    //antiquus. Chirographum baiulus spoliatio. Suscipit fuga deleo. Comburo aequus
    //cuppedia. Crur cuppedia voluptates. Argentum adduco vindico. Denique undique

    @Test
    public void body()
    {
        response.body("body[2]",equalTo("Denego aptus arma. Placeat decet subvenio. Tabernus unde officia. Abundans amplus vulnero. Aegre error anser. Quod vaco thymum. Comptus triginta agnitio. Quia versus tenus. Est cito ut. Pecus clamo venustas. Peior deficio tripudio. Textus clamo stultus. Caelestis nihil demergo."));
    }
}
