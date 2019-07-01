package com.jsonapp.parkingmob.login;

public class NotAuthorizedException extends Exception {
    public NotAuthorizedException(String message) {
        super(message);
    }
}
