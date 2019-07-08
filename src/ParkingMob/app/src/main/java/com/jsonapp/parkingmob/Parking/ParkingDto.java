package com.jsonapp.parkingmob.Parking;

import com.jsonapp.parkingmob.login.LoginDto;
import com.jsonapp.parkingmob.login.UserDto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ParkingDto implements Serializable {
    private List<CarDto> carDtos;
    private LoginDto loginDto;

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

    public LoginDto getUserDto() {
        return loginDto;
    }

    public void setUserDto(LoginDto userDto) {
        this.loginDto = userDto;
    }

    public void setCars(List<CarDto> carDtos) {
        this.carDtos = carDtos;
    }
}
