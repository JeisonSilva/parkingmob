package com.jsonapp.parkingmob.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.jsonapp.parkingmob.Parking.ParkingBusiness;
import com.jsonapp.parkingmob.Parking.ParkingBusinessImpl;
import com.jsonapp.parkingmob.Parking.ParkingDal;
import com.jsonapp.parkingmob.Parking.ParkingDto;
import com.jsonapp.parkingmob.Parking.ParkingRepository;
import com.jsonapp.parkingmob.Parking.ParkingRepositoryImpl;
import com.jsonapp.parkingmob.R;

public class ParkingActivity extends AppCompatActivity implements ParkingDal {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking);

        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        ParkingRepository parkingRepository = new ParkingRepositoryImpl();
        ParkingBusiness parkingBusiness = new ParkingBusinessImpl(this, parkingRepository);
    }
}
