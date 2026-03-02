package com.decathlon.testng.test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class APIValidator{

    @Test(priority = 1)
    public void decathlonLoadAPITest(){
        RestAssured.useRelaxedHTTPSValidation();
        int statusCode = RestAssured.given().when()
                .get("https://www.decathlon.in/")
                .then()
                .extract().statusCode();
        Assert.assertEquals(statusCode, 200);
        System.out.println("Status Code for Decathlon Home Page Load - "+ statusCode);
    }

    @Test(priority = 2)
    public void validateProductSearchByQueryParam(){
        RestAssured.useRelaxedHTTPSValidation();

        RequestSpecification requestSpec = new RequestSpecBuilder()
                .setBaseUri("https://www.decathlon.in")
                .setContentType(ContentType.JSON)
                .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64)")
                .build();

        // 2. Execute Search (GET request with query parameters)
        Response response = RestAssured.given()
                .spec(requestSpec)
                .queryParam("query", "shoes") // This adds ?query=shoes to the URL
                .when()
                .get("/search")
                .then()
                .extract().response();

        // 3. Validation
        System.out.println("Status Code for Query Shoe Search: " + response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertTrue(response.getBody().asString().contains("shoes"));
    }

}
