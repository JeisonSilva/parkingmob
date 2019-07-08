package com.jsonapp.parkingmob.Parking;

import java.util.List;

public interface ParkingDal {
    void requestListCar();
    List<CarDto> getCars();

    int checkWritePermission();

    int checkReadPermission();

    void requestPermission(String[] permission);

    void finish();

    void screenProfileUser(String name, String email);
}
