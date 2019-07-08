package com.jsonapp.parkingmob.Parking;

import java.io.IOException;

public interface DataExportBusiness {
    void exportDataAndKeepData() throws IOException, ClassNotFoundException;

    void exportDataWithoutKeepingThem() throws IOException, ClassNotFoundException;

    boolean verifyPermissions();

    void requestPermission();
}
