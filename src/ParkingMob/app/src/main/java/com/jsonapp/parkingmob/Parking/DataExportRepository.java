package com.jsonapp.parkingmob.Parking;

import android.content.Context;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface DataExportRepository {
    void export(ParkingDto parkingDto) throws IOException;

    void setContext(Context context);
}
