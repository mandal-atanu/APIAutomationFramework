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
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Testintegrationflow2 extends BaseTest {

    @Test(groups = "integration", priority = 1)
    @Owner("Promode")
    @Description("TC#INT1 - Step 1. Verify that the Booking can be Created")
    public void testcreatebooking(ITestContext iTestContext){



        requestSpecification.basePath(APIConstraints.CREATE_UPDATE_BOOKING_URL);
        response = RestAssured.given(requestSpecification)
                .when().body(payloadManager.createpayloadbookingasstring()).post();
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(Integer.parseInt(PropertyReader.readkey("booking.post.statuscode.success")));

        bookingresponse bookingresponse = payloadManager.bookingResponseJava(response.asString());

        assertThat(bookingresponse.getBooking().getFirstname()).isNotNull();
        assertThat(bookingresponse.getBooking().getFirstname()).isEqualTo("James");
        assertThat(bookingresponse.getBooking().getFirstname()).isEqualTo(PropertyReader.readkey("booking.post.fisrtname"));

        iTestContext.setAttribute("bookingid", bookingresponse.getBookingid());

    }


}
