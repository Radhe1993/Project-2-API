package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UserAssertionTest
{
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

    //1. Verify the if the total record is 20
    @Test
    public void total()
    {
        response.body("id.size()",equalTo(20));
    }

    //2. Verify the if the name of id = 5487 is equal to ”Hamsini Trivedi”

    @Test
    public void name()
    {
        response.body("name[5]",equalTo("Jagmeet Jha"));
    }

    //3. Check the single ‘Name’ in the Array list (Subhashini Talwar)

    @Test
    public void singleName()
    {
        response.body("name[2]",equalTo("Sharmila Embranthiri DC"));
    }

    //4. Check the multiple ‘Names’ in the ArrayList (Mrs. Menaka Bharadwaj, Msgr. Bodhan
    //Guha, Karthik Dubashi IV)
    @Test
    public void multipleNAme()
    {
        response.body("name",hasItems("Tanushri Nayar","Niranjan Gill","Sarala Menon"));
    }

    //5. Verify the emai of userid = 5471 is equal “adiga_aanjaneya_rep@jast.org”

    @Test
    public void userid()
    {
        response.body("email[0]",equalTo("lakshmi_chaturvedi_pres@howell-leannon.test"));
    }

    //6. Verify the status is “Active” of user name is “Shanti Bhat V”
    @Test
    public void stative()
    {
        response.body("name",hasItem("Tanushri Nayar") );
    }

    //7. Verify the Gender = male of user name is “Niro Prajapat”

    @Test
    public void Gender()
    {
        response.body("name[1]",equalTo("Bandhul Gill"));
    }
}
