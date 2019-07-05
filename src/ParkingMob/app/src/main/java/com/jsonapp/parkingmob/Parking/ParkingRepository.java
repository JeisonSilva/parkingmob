package com.jsonapp.parkingmob.Parking;

import android.content.Context;

import java.io.IOException;

public interface ParkingRepository {
    void registrar(CarDto car) throws IOException, ClassNotFoundException;

    void setContext(Context context);
}
