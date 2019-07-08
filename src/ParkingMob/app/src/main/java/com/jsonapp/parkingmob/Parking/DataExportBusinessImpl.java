package com.jsonapp.parkingmob.Parking;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.jsonapp.parkingmob.login.LoginDto;
import com.jsonapp.parkingmob.login.LoginRepository;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class DataExportBusinessImpl implements DataExportBusiness {
    private final DataExportRepository dataExportRepository;
    private final LoginRepository loginRepository;
    private final ParkingRepository parkingRepository;
    private final ParkingDal parkingDal;


    public DataExportBusinessImpl(
            ParkingDal parkingDal,
            LoginRepository loginRepository,
            ParkingRepository parkingRepository,
            DataExportRepository dataExportRepository) {

        this.loginRepository = loginRepository;
        this.parkingRepository = parkingRepository;
        this.dataExportRepository = dataExportRepository;

        this.parkingDal = parkingDal;
        this.loginRepository.setContext((Context) parkingDal);
        this.parkingRepository.setContext((Context)parkingDal);
        this.dataExportRepository.setContext((Context)parkingDal);

    }

    @Override
    public void exportDataAndKeepData() throws IOException, ClassNotFoundException {
        exportData();
    }


    @Override
    public void exportDataWithoutKeepingThem() throws IOException, ClassNotFoundException {
        exportData();
    }

    private void exportData() throws IOException, ClassNotFoundException {
        ParkingDto parkingDto = new ParkingDto();
        LoginDto loginDto = this.loginRepository.getLoginCurrent();
        List<CarDto> carDtos = this.parkingRepository.getCars();

        parkingDto.setUserDto(loginDto);
        parkingDto.setCars(carDtos);

        this.dataExportRepository.export(parkingDto);
    }

    @Override
    public boolean verifyPermissions() {

        int isWritePermission = this.parkingDal.checkWritePermission();
        int isReadPermission = this.parkingDal.checkReadPermission();

        if(isWritePermission == PackageManager.PERMISSION_GRANTED && isReadPermission == PackageManager.PERMISSION_GRANTED)
            return true;

        return false;
    }

    @Override
    public void requestPermission() {
        String[] permission = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
        };

        this.parkingDal.requestPermission(permission);
    }
}
