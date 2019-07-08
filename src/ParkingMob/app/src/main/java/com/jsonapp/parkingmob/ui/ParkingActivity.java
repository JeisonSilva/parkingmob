package com.jsonapp.parkingmob.ui;

import android.net.Uri;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Toast;

import com.jsonapp.parkingmob.Parking.CarDto;
import com.jsonapp.parkingmob.Parking.ParkingBusiness;
import com.jsonapp.parkingmob.Parking.ParkingBusinessImpl;
import com.jsonapp.parkingmob.Parking.ParkingDal;
import com.jsonapp.parkingmob.Parking.ParkingDto;
import com.jsonapp.parkingmob.Parking.ParkingRepository;
import com.jsonapp.parkingmob.Parking.ParkingRepositoryImpl;
import com.jsonapp.parkingmob.R;
import com.jsonapp.parkingmob.ui.dialogs.RegisterCarParkingDialogFragment;
import com.jsonapp.parkingmob.ui.fragments.ParkingManangerFragment;
import com.jsonapp.parkingmob.ui.fragments.RequestCarDataFragment;

import java.io.IOException;
import java.util.List;

public class ParkingActivity extends AppCompatActivity
        implements ParkingDal,
        ParkingManangerFragment.OnFragmentInteractionListener,
        RegisterCarParkingDialogFragment.RegisterCarInterface{

    private Toolbar toolbar;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private ParkingBusiness parkingBusiness;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking);

        toolbar = findViewById(R.id.toolbar);
        drawer = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navigation_view);

        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer,toolbar, R.string.open_drawer, R.string.close_drawer);

        drawer.addDrawerListener(toggle);
        toggle.syncState();

        ParkingRepository parkingRepository = new ParkingRepositoryImpl();
        this.parkingBusiness = new ParkingBusinessImpl(this, parkingRepository);
    }

    @Override
    protected void onStart() {
        super.onStart();

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager
                .beginTransaction()
                .replace(R.id.fragment_layout, ParkingManangerFragment.newInstance(this.parkingBusiness.getCars()))
                .commit();
    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_parking, menu);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void requestCarData() {
        RegisterCarParkingDialogFragment registerCarParkingDialogFragment = RegisterCarParkingDialogFragment.newInstance();
        registerCarParkingDialogFragment.openDialog(getSupportFragmentManager());

    }

    @Override
    public void requestListCar() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager
                .beginTransaction()
                .replace(R.id.fragment_layout, ParkingManangerFragment.newInstance(this.parkingBusiness.getCars()))
                .commit();
    }

    @Override
    public List<CarDto> getCars() {
        return this.parkingBusiness.getCars();
    }

    @Override
    public void addCar(String plate, String consumerName) {
        try {
            this.parkingBusiness.addCar(plate, consumerName);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
