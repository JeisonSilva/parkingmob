package com.jsonapp.parkingmob.Parking;

import android.content.Context;

import com.jsonapp.parkingmob.login.LoginBusiness;
import com.jsonapp.parkingmob.login.LoginDto;
import com.jsonapp.parkingmob.login.LoginRepository;
import com.jsonapp.parkingmob.login.UserDto;

import java.util.List;

public class DataExportBusinessImpl implements DataExportBusiness {
    private final DataExportRepository dataExportRepository;
    private final LoginRepository loginRepository;
    private final ParkingRepository parkingRepository;


    public DataExportBusinessImpl(
            ParkingDal parkingDal,
            LoginRepository loginRepository,
            ParkingRepository parkingRepository,
            DataExportRepository dataExportRepository) {

        this.loginRepository = loginRepository;
        this.parkingRepository = parkingRepository;
        this.dataExportRepository = dataExportRepository;

        this.loginRepository.setContext((Context) parkingDal);
        this.parkingRepository.setContext((Context)parkingDal);
        this.dataExportRepository.setContext((Context)parkingDal);

    }

    @Override
    public void exportDataAndKeepData() {
        ParkingDto parkingDto = new ParkingDto();
        LoginDto loginDto = this.loginRepository.getLoginCurrent();
        List<CarDto> carDtos = this.parkingRepository.getCars();

        parkingDto.setUserDto(loginDto);
        parkingDto.setCars(carDtos);

        this.dataExportRepository.export(parkingDto);
    }

    @Override
    public void exportDataWithoutKeepingThem() {

    }
}
