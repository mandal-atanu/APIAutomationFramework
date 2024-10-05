package com.thetestingacademy.asserts;

import static org.testng.AssertJUnit.assertEquals;

public class assertactions {

    public void verifyResponseBody(String actual, String expected, String description) {
        assertEquals(actual, expected, description);
    }

//    public void verifyResponseBody(int actual, int expected, String description) {
//        assertEquals(actual, expected, description);
//    }
//
//    public void verifyStatusCode(Response response, Integer expected) {
//        assertEquals(response.getStatusCode(),expected);
//    }
}
