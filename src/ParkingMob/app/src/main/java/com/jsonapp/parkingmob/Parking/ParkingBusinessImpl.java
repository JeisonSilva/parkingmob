package com.jsonapp.parkingmob.Parking;

import android.content.Context;

import com.jsonapp.parkingmob.ui.ParkingActivity;

import java.io.IOException;
import java.util.List;

public class ParkingBusinessImpl implements ParkingBusiness {
    private final ParkingDal parkingDal;
    private final ParkingRepository parkingRepository;
    private final ParkingDto parking;

    public ParkingBusinessImpl(ParkingDal parkingDal, ParkingRepository parkingRepository) {
        this.parkingDal = parkingDal;
        this.parkingRepository =parkingRepository;
        this.parking = new ParkingDto();

        this.parkingRepository.setContext((Context)this.parkingDal);
    }

    @Override
    public void addCar(String plate, String custumerName) throws IOException, ClassNotFoundException {
        CarDto car = new CarDto();
        car.setPlate(plate);
        car.setCustomerName(custumerName);

        this.parking.addCar(car);
        this.parkingRepository.registrar(car);
        this.parkingDal.requestListCar();
    }

    @Override
    public List<CarDto> getCars() {
        return this.parking.getCars();
    }

    @Override
    public void exportDataAndKeepData() {

    }

    @Override
    public void exportDataWithoutKeepingThem() {

    }
}
