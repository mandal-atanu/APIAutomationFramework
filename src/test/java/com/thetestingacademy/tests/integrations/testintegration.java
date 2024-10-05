package com.thetestingacademy.tests.integrations;

import com.google.gson.Gson;
import com.thetestingacademy.base.BaseTest;
import com.thetestingacademy.endpoints.APIConstraints;
import com.thetestingacademy.pojos.booking;
import com.thetestingacademy.pojos.bookingresponse;
import com.thetestingacademy.utilitis.PropertyReader;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class testintegration extends BaseTest {

    @Test(groups = "integration", priority = 1)
    @Owner("Atanu")
    @Description("TC#INT1 - Step 1. Verify that the Booking can be Created")
    public void createbooking(ITestContext iTestContext){
        requestSpecification.basePath(APIConstraints.CREATE_UPDATE_BOOKING_URL);
        response = RestAssured.given(requestSpecification)
                .when().body(payloadManager.createpayloadbookingasstring()).post();

        validatableResponse = response.then().log().all();

        validatableResponse.statusCode(200);

        bookingresponse bookingresponse = payloadManager.bookingResponseJava(response.asString());

        Integer bookingid = bookingresponse.getBookingid();
        System.out.println(bookingid);

        assertThat(bookingresponse.getBooking().getFirstname()).isEqualTo("James");

        iTestContext.setAttribute("bookingid", bookingresponse.getBookingid());


    }

    @Test(groups = "integration",priority = 2)
    @Owner("Atanu")
    @Description("TC#INT1 - Step 2. Verify that the token can be created")
    public void createtoken(ITestContext iTestContext){

        Integer bookingid = (Integer) iTestContext.getAttribute("bookingid");



        requestSpecification.basePath(APIConstraints.AUTH_URL);
        response = RestAssured.given(requestSpecification)
                .when().contentType(ContentType.JSON).body(payloadManager.setauthpayload()).post();

        validatableResponse = response.then().log().all();

        validatableResponse.statusCode(200);

        String token = payloadManager.getTokenfromjson(response.asString());

        System.out.println(token);

        iTestContext.setAttribute("token", getToken());





    }

    @Test(groups = "integration", priority = 3)
    @Owner("Atanu")
    @Description("TC#INT1 - Step 4. Delete the Booking by ID")
    public void testDeleteBookingById(ITestContext iTestContext){

        String token = (String) iTestContext.getAttribute("token");

        Integer bookingid = (Integer) iTestContext.getAttribute("bookingid");

        String delete_path = APIConstraints.CREATE_UPDATE_BOOKING_URL + "/" + bookingid;

        requestSpecification.basePath(delete_path).cookie("token", token);
        response = RestAssured.given(requestSpecification)
                .when().delete();

        validatableResponse = response.then().log().all();

        validatableResponse.statusCode(201);


    }

    @Test(groups = "integration", priority = 4)
    @Owner("Promode")
    @Description("TC#INT1 - Step 2. Verify that the Booking By ID")
    public void testVerifyBookingId(ITestContext iTestContext){
        Integer bookingid = (Integer) iTestContext.getAttribute("bookingid");

        String basepath = APIConstraints.CREATE_UPDATE_BOOKING_URL + "/" + bookingid;

        requestSpecification.basePath(basepath);
        response = RestAssured
                .given(requestSpecification)
                .when().get();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(404);






    }







}
