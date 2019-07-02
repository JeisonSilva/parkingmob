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
import android.view.View;
import android.widget.Toast;

import com.jsonapp.parkingmob.Parking.ParkingBusiness;
import com.jsonapp.parkingmob.Parking.ParkingBusinessImpl;
import com.jsonapp.parkingmob.Parking.ParkingDal;
import com.jsonapp.parkingmob.Parking.ParkingDto;
import com.jsonapp.parkingmob.Parking.ParkingRepository;
import com.jsonapp.parkingmob.Parking.ParkingRepositoryImpl;
import com.jsonapp.parkingmob.R;
import com.jsonapp.parkingmob.ui.fragments.ParkingManangerFragment;
import com.jsonapp.parkingmob.ui.fragments.RequestCarDataFragment;

public class ParkingActivity extends AppCompatActivity
        implements ParkingDal,
        ParkingManangerFragment.OnFragmentInteractionListener,
        RequestCarDataFragment.OnFragmentInteractionListener {

    private Toolbar toolbar;
    private DrawerLayout drawer;
    private NavigationView navigationView;

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
        ParkingBusiness parkingBusiness = new ParkingBusinessImpl(this, parkingRepository);
    }

    @Override
    protected void onStart() {
        super.onStart();

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager
                .beginTransaction()
                .replace(R.id.fragment_layout, ParkingManangerFragment.newInstance())
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
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void requestCarData() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager
                .beginTransaction()
                .replace(R.id.fragment_layout, RequestCarDataFragment.newInstance())
                .commit();
    }

    @Override
    public void addNewCar(String plate, String custumerName) {
        Toast.makeText(this, String.format("Placa:%s  do cliente: %s", plate, custumerName),Toast.LENGTH_LONG).show();
    }
}
