package com.jsonapp.parkingmob.Parking;

import com.jsonapp.parkingmob.ui.ParkingActivity;

import java.util.List;

public class ParkingBusinessImpl implements ParkingBusiness {
    private final ParkingDal parkingDal;
    private final ParkingRepository parkingRepository;
    private final ParkingDto parking;

    public ParkingBusinessImpl(ParkingDal parkingDal, ParkingRepository parkingRepository) {
        this.parkingDal = parkingDal;
        this.parkingRepository =parkingRepository;
        this.parking = new ParkingDto();
    }

    @Override
    public void addCar(String plate, String custumerName) {
        CarDto car = new CarDto();
        car.setPlate(plate);
        car.setCustomerName(custumerName);

        this.parking.addCar(car);
        this.parkingDal.requestListCar();
    }

    @Override
    public List<CarDto> getCars() {
        return this.parking.getCars();
    }
}
