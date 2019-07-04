package com.jsonapp.parkingmob.Parking;

import java.util.List;

public interface ParkingDal {
    void requestListCar();
    List<CarDto> getCars();
}
