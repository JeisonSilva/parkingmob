package com.jsonapp.parkingmob.Parking;

import com.jsonapp.parkingmob.login.LoginRepository;

import java.io.IOException;
import java.util.List;

public interface ParkingBusiness {
    void addCar(String plate, String custumerName) throws IOException, ClassNotFoundException;

    List<CarDto> getCars();

    void loadStorageInternalToMemory();

    void loadProfileUser(LoginRepository loginRepository);
}
