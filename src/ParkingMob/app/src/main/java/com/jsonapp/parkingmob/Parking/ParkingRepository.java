package com.jsonapp.parkingmob.Parking;

import android.content.Context;

import java.io.IOException;
import java.util.List;

public interface ParkingRepository {
    void registrar(CarDto car) throws IOException, ClassNotFoundException;

    void setContext(Context context);

    List<CarDto> getCars() throws IOException, ClassNotFoundException;
}
