package com.jsonapp.parkingmob.login;

public interface LoginDal {

    UserDto getUser();
    boolean isRemember();
    void login();
    void signUp();
    ParkingScreen getParkingScreen();

    void displayInvalidAuthenticationMessage();
}
