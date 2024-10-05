package com.thetestingacademy.tests.ddt.vwo;

import com.thetestingacademy.utilitis.UtlisExcel;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class vwologinAPIddt {

    RequestSpecification requestSpecification;
    Response response;
    ValidatableResponse validatableResponse;

    Integer id;

    @Test(dataProvider = "getData", dataProviderClass = UtlisExcel.class)
    public void testVWOLogin(String email, String password){

        System.out.println(" -- Login API Testing");
        System.out.println(email);
        System.out.println(password);

    }

}
