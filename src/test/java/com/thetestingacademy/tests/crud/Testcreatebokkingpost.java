package com.thetestingacademy.tests.crud;

import com.thetestingacademy.base.BaseTest;
import com.thetestingacademy.endpoints.APIConstraints;
import com.thetestingacademy.pojos.bookingresponse;
import com.thetestingacademy.utilitis.PropertyReader;
import io.qameta.allure.*;
import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.*;

public class Testcreatebokkingpost extends BaseTest {


    @Link(name = "Link to TC", url = "https://bugz.atlassian.net/browse/RBT-4")
    @Issue("JIRA_RBT-4")
    @TmsLink("RBT-4")
    @Owner("Promode")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Verify that the Post request working fine")
    @Test
    public void testVerifycreateBokkingPost(){

        requestSpecification.basePath(APIConstraints.CREATE_UPDATE_BOOKING_URL);
        response = RestAssured.given(requestSpecification)
                        .when().body(payloadManager.createpayloadbookingasstring()).post();
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(Integer.parseInt(PropertyReader.readkey("booking.post.statuscode.success")));

        bookingresponse bookingresponse = payloadManager.bookingResponseJava(response.asString());

        assertThat(bookingresponse.getBooking().getFirstname()).isNotNull();
        assertThat(bookingresponse.getBooking().getFirstname()).isEqualTo("James");
        assertThat(bookingresponse.getBooking().getFirstname()).isEqualTo(PropertyReader.readkey("booking.post.fisrtname"));





        Assert.assertEquals(true,true);
    }


}
