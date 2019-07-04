package com.jsonapp.parkingmob.Parking;

import java.util.List;

public interface ParkingBusiness {
    void addCar(String plate, String custumerName);

    List<CarDto> getCars();
}
