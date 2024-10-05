package com.thetestingacademy.tests.integrations;

import com.thetestingacademy.base.BaseTest;
import com.thetestingacademy.endpoints.APIConstraints;
import com.thetestingacademy.listners.RetryAnalyzer;
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
@Test(retryAnalyzer = RetryAnalyzer.class)
public class TCIntegrationFlowRetryAnalyzer extends BaseTest {

    // Create A Booking, Create a Token
    // Get booking
    // Update the Booking
    // Delete the Booking

    @Test(groups = "integration", priority = 1)
    @Owner("Promode")
    @Description("TC#INT1 - Step 1. Verify that the Booking can be Created")
    public void testcreatebooking(ITestContext iTestContext){

        iTestContext.setAttribute("token", getToken());

        requestSpecification.basePath(APIConstraints.CREATE_UPDATE_BOOKING_URL);
        response = RestAssured.given(requestSpecification)
                .when().body(payloadManager.createpayloadbookingasstring()).post();
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(Integer.parseInt(PropertyReader.readkey("booking.post.statuscode.success")));

        bookingresponse bookingresponse = payloadManager.bookingResponseJava(response.asString());

        assertThat(bookingresponse.getBooking().getFirstname()).isNotNull();
        assertThat(bookingresponse.getBooking().getFirstname()).isEqualTo("James");
        assertThat(bookingresponse.getBooking().getFirstname()).isEqualTo(PropertyReader.readkey("booking.post.fisrtname"));

        iTestContext.setAttribute("bookingid",bookingresponse.getBookingid());

    }


    @Test(groups = "integration", priority = 2)
    @Owner("Promode")
    @Description("TC#INT1 - Step 2. Verify that the Booking By ID")
    public void testVerifyBookingId(ITestContext iTestContext) {

        Integer bookingid = (Integer) iTestContext.getAttribute("bookingid");

        String basepath = APIConstraints.CREATE_UPDATE_BOOKING_URL + "/" + bookingid;

        requestSpecification.basePath(basepath);
        response = RestAssured
                .given(requestSpecification)
                .when().get();

        validatableResponse.statusCode(200);

        booking booking = payloadManager.getresponsefromjson(response.asString());

        assertThat(booking.getFirstname()).isNotNull();
        assertThat(booking.getFirstname()).isEqualTo(PropertyReader.readkey("booking.get.firstname"));


    }

    @Test(groups = "integration", priority = 3)
    @Owner("Promode")
    @Description("TC#INT1 - Step 3. Verify Updated Booking by ID")
    public void testUpdateBookingByID(ITestContext iTestContext){

        System.out.println("Token - " + iTestContext.getAttribute("token"));
        String token = (String) iTestContext.getAttribute("token");

        Integer bookingid = (Integer) iTestContext.getAttribute("bookingid");

        String basePathPUTPATCH = APIConstraints.CREATE_UPDATE_BOOKING_URL + "/" + bookingid;
        System.out.println(basePathPUTPATCH);

        requestSpecification.basePath(basePathPUTPATCH);
        response = RestAssured
                .given(requestSpecification).cookie("token", token)
                .when().body(payloadManager.fullUpdatePayloadAsString()).put();
        validatableResponse = response.then().log().all();
        // Validatable Assertion
        validatableResponse.statusCode(200);

        booking booking = payloadManager.getresponsefromjson(response.asString());

        assertThat(booking.getFirstname()).isEqualTo(PropertyReader.readkey("booking.put.firstname"));





    }

    @Test(groups = "integration", priority = 4)
    @Owner("Promode")
    @Description("TC#INT1 - Step 4. Delete the Booking by ID")
    public void testDeleteBookingById(ITestContext iTestContext) {

        String token = (String) iTestContext.getAttribute("token");
        Assert.assertTrue(true);

        Integer bookingid = (Integer) iTestContext.getAttribute("bookingid");
        String basePathDELETE = APIConstraints.CREATE_UPDATE_BOOKING_URL + "/" + bookingid;
        System.out.println(basePathDELETE);

        requestSpecification.basePath(basePathDELETE).cookie("token", token);
        validatableResponse = RestAssured.given().spec(requestSpecification)
                .when().delete().then().log().all();
        validatableResponse.statusCode(200);

    }

}
