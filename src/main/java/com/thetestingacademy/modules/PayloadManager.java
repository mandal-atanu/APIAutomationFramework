package com.thetestingacademy.modules;

import com.github.javafaker.Faker;
import com.google.gson.Gson;
import com.thetestingacademy.pojos.BookingDates;
import com.thetestingacademy.pojos.auth;
import com.thetestingacademy.pojos.booking;
import com.thetestingacademy.pojos.bookingresponse;
import com.thetestingacademy.pojos.tokenresponse;

public class PayloadManager {

    // converting the java object to the string

    public  String createpayloadbookingasstring(){
        booking booking = new booking();

        booking.setFirstname("James");
        booking.setLastname("Brown");
        booking.setTotalprice(111);
        booking.setDepositpaid(true);

        BookingDates bookingdates = new BookingDates();
        bookingdates.setCheckin("2024-02-01");
        bookingdates.setCheckout("2024-02-01");

        booking.setBookingdates(bookingdates);
        booking.setAdditionalneeds("Breakfast");

        System.out.println(booking);
        // Java Object -> JSON String (byteStream) - Serlization
        Gson gson = new Gson();
        String jsonStringpayload = gson.toJson(booking);
        System.out.println(jsonStringpayload);

        return jsonStringpayload;




    }

    public String createpayloadbookingasstringFaker(){

        Faker faker = new Faker();
        booking booking = new booking();

        booking.setFirstname(faker.name().firstName());
        booking.setLastname(faker.name().lastName());
        booking.setTotalprice(faker.random().nextInt(100));
        booking.setDepositpaid(true);

        BookingDates bookingdates = new BookingDates();
        bookingdates.setCheckin("2024-02-01");
        bookingdates.setCheckout("2024-02-01");

        booking.setBookingdates(bookingdates);
        booking.setAdditionalneeds("Breakfast");

        System.out.println(booking);
        // Java Object -> JSON String (byteStream) - Serlization
        Gson gson = new Gson();
        String jsonStringpayload = gson.toJson(booking);
        System.out.println(jsonStringpayload);
        return jsonStringpayload;



    }

    public  bookingresponse bookingResponseJava(String responseString) {
        Gson gson = new Gson();
        bookingresponse bookingResponse = gson.fromJson(responseString, bookingresponse.class);
        return bookingResponse;
    }

    // create token

    public String setauthpayload(){

        auth auth = new auth();

        auth.setUsername("admin");
        auth.setPassword("password123");

        Gson gson = new Gson();

        String jsonpayloadString = gson.toJson(auth);
        System.out.println("Payload set to the--->"+jsonpayloadString);

        return jsonpayloadString;

    }

    public  String getTokenfromjson(String tokenresponse){
        Gson gson = new Gson();

        tokenresponse tokenresponse1 = gson.fromJson(tokenresponse, tokenresponse.class);

        return tokenresponse1.getToken();
    }

    //deserialization of booking

    public booking getresponsefromjson(String getresponse){
        Gson gson = new Gson();
        booking booking = gson.fromJson(getresponse, com.thetestingacademy.pojos.booking.class);

        return booking;
    }

    public String fullUpdatePayloadAsString() {
        booking booking = new booking();
        booking.setFirstname("Atanu");
        booking.setLastname("Mandal");
        booking.setTotalprice(112);
        booking.setDepositpaid(true);

        BookingDates bookingdates = new BookingDates();
        bookingdates.setCheckin("2024-02-01");
        bookingdates.setCheckout("2024-02-05");
        booking.setBookingdates(bookingdates);
        booking.setAdditionalneeds("Breakfast");

        Gson gson = new Gson();



        return gson.toJson(booking);
    }
}
