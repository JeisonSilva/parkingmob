package com.jsonapp.parkingmob.Parking;

import com.jsonapp.parkingmob.ui.ParkingActivity;

public class ParkingBusinessImpl implements ParkingBusiness {
    private final ParkingDal parkingDal;
    private final ParkingRepository parkingRepository;

    public ParkingBusinessImpl(ParkingDal parkingDal, ParkingRepository parkingRepository) {
        this.parkingDal = parkingDal;
        this.parkingRepository =parkingRepository;
    }
}
