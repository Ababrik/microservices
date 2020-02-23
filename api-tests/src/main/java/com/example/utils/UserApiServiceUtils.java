package com.example.utils;

import com.example.model.AddressPayload;
import com.example.model.CardPayload;
import com.example.model.UserPayload;
import com.github.javafaker.Faker;
import java.util.concurrent.TimeUnit;

public class UserApiServiceUtils {

    private final Faker faker = new Faker();
    private UserPayload userPayload = new UserPayload();
    private AddressPayload addressPayload = new AddressPayload();
    private CardPayload cardPayload = new CardPayload();


    public UserPayload generateUserDetails() {
        String userName = faker.name().username();
        return userPayload
                .setUsername(userName)
                .setPassword(faker.numerify("a#b##b#a"))
                .setEmail(userName + "@example.com");
    }

    public CardPayload generateCardDetails(String userId) {
        return cardPayload
                .setLongNum(faker.numerify("##x##y##z###y###z##x####"))
                .setExpires(faker.date().future(1000, TimeUnit.DAYS).toString())
                .setCcv(faker.numerify("###"))
                .setUserID(userId);
    }

    public CardPayload setEmptyCardDetails(){
        return cardPayload
                .setLongNum("")
                .setExpires("")
                .setCcv("")
                .setUserID("");
    }

    public AddressPayload generateAddressDetails(String userId) {
        return addressPayload
                .setStreet(faker.address().streetName())
                .setNumber(faker.address().buildingNumber())
                .setCountry(faker.address().country())
                .setCity(faker.address().city())
                .setPostcode(faker.address().zipCode())
                .setUserID(userId);
    }

    public AddressPayload setEmptyAddressPayload(){
        return addressPayload
                .setStreet("")
                .setNumber("")
                .setCountry("")
                .setCity("")
                .setPostcode("")
                .setUserID("");
    }








}
