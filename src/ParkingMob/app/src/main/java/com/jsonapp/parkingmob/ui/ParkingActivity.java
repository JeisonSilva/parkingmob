package com.jsonapp.parkingmob.ui;

import android.Manifest;
import android.net.Uri;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.jsonapp.parkingmob.Parking.CarDto;
import com.jsonapp.parkingmob.Parking.DataExportBusiness;
import com.jsonapp.parkingmob.Parking.DataExportBusinessImpl;
import com.jsonapp.parkingmob.Parking.DataExportRepository;
import com.jsonapp.parkingmob.Parking.DataExportRepositoryImpl;
import com.jsonapp.parkingmob.Parking.ParkingBusiness;
import com.jsonapp.parkingmob.Parking.ParkingBusinessImpl;
import com.jsonapp.parkingmob.Parking.ParkingDal;
import com.jsonapp.parkingmob.Parking.ParkingDto;
import com.jsonapp.parkingmob.Parking.ParkingRepository;
import com.jsonapp.parkingmob.Parking.ParkingRepositoryImpl;
import com.jsonapp.parkingmob.R;

import com.jsonapp.parkingmob.login.LoginBusiness;
import com.jsonapp.parkingmob.login.LoginBusinessImpl;
import com.jsonapp.parkingmob.login.LoginRepository;
import com.jsonapp.parkingmob.login.LoginRepositoryImpl;
import com.jsonapp.parkingmob.ui.dialogs.ExportDataDialogImpl;

import com.jsonapp.parkingmob.ui.fragments.ParkingManangerFragment;
import com.jsonapp.parkingmob.ui.fragments.RequestCarDataFragment;

import java.io.IOException;
import java.util.List;

public class ParkingActivity extends AppCompatActivity
        implements ParkingDal,
        ParkingManangerFragment.OnFragmentInteractionListener,
        RequestCarDataFragment.OnFragmentInteractionListener, ExportDataDialogImpl.ExportDataDialog {

    private static final int REQUEST_PERMISSION = 1;
    private Toolbar toolbar;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private ParkingBusiness parkingBusiness;
    private DataExportBusiness dataExportBusiness;

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
        DataExportRepository dataExportRepository =new DataExportRepositoryImpl();
        LoginRepository loginRepository = new LoginRepositoryImpl();

        this.parkingBusiness = new ParkingBusinessImpl(this, parkingRepository);

        this.dataExportBusiness = new DataExportBusinessImpl(
                this,
                loginRepository,
                parkingRepository,
                dataExportRepository);
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
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.mnu_export_file:{
                ExportDataDialogImpl exportDataDialog = ExportDataDialogImpl.newDialog();
                exportDataDialog.openDialog(getSupportFragmentManager());
            }
            case R.id.mnu_logout:{

            }
        }

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
    public int checkWritePermission() {
        return ContextCompat.checkSelfPermission(ParkingActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }

    @Override
    public int checkReadPermission() {
        return ContextCompat.checkSelfPermission(ParkingActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE);
    }

    @Override
    public void requestPermission(String[] permission) {
        ActivityCompat.requestPermissions(ParkingActivity.this, permission, REQUEST_PERMISSION);
    }

    @Override
    public void exportData(int opcao) {
        try {
            boolean isPermissionEnable = this.dataExportBusiness.verifyPermissions();

            if(!isPermissionEnable){
                this.dataExportBusiness.requestPermission();
                Toast.makeText(this, "Deverá refazer a exportação de dados", Toast.LENGTH_LONG).show();
                return;
            }

            if(opcao == ExportDataDialogImpl.EXPORT_DATA_AND_KEEP)
                this.dataExportBusiness.exportDataAndKeepData();
            else
                this.dataExportBusiness.exportDataWithoutKeepingThem();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
