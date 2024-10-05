package com.thetestingacademy.base;

// common to all TestCase

import com.thetestingacademy.asserts.assertactions;
import com.thetestingacademy.endpoints.APIConstraints;
import com.thetestingacademy.modules.PayloadManager;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeTest;

public class BaseTest {

    public RequestSpecification requestSpecification;
    public assertactions assertActions;
    public PayloadManager payloadManager;
    public JsonPath jsonPath;
    public Response response;
    public ValidatableResponse validatableResponse;


    @BeforeTest
    public void setUp() {
        payloadManager = new PayloadManager();
        assertActions = new assertactions();
        requestSpecification = new RequestSpecBuilder()

                .setBaseUri(APIConstraints.BASE_URL)
                .addHeader("Content-Type", "application/json")
                .build().log().all();

//        requestSpecification = RestAssured.
//                given()
//                .baseUri(APIConstants.BASE_URL)
//                .contentType(ContentType.JSON)
//                .log().all();


    }

    public String getToken() {


        requestSpecification = RestAssured
                .given()
                .baseUri(APIConstraints.BASE_URL)
                .basePath(APIConstraints.AUTH_URL);

        String payload = payloadManager.setauthpayload();

        response = requestSpecification.contentType(ContentType.JSON).body(payload).when().post();

        // extract token

        String token = payloadManager.getTokenfromjson(response.asString());

        return token;



    }










}
