package com.jsonapp.parkingmob.Parking;

import java.util.ArrayList;
import java.util.List;

public class ParkingDto {
    private List<CarDto> carDtos;

    public ParkingDto() {
        this.carDtos = new ArrayList<>();
    }

    public void addCar(CarDto car) {

        if(car != null)
            this.carDtos.add(car);
    }

    public List<CarDto> getCars() {
        return this.carDtos;
    }
}
