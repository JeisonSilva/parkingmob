package com.jsonapp.parkingmob.Parking;

import android.content.Context;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class ParkingRepositoryImpl implements ParkingRepository {
    private Context context;

    public ParkingRepositoryImpl() {
    }

    @Override
    public void registrar(CarDto car) throws IOException, ClassNotFoundException {
        List<CarDto> list = getListCars();

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(this.context.openFileOutput("cars.data", Context.MODE_PRIVATE));
        objectOutputStream.writeObject(car);
    }

    private List<CarDto> getListCars() throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(this.context.openFileInput("cars.data"));
        return (List<CarDto>)objectInputStream.readObject();
    }

    @Override
    public void setContext(Context context) {
        this.context =context;
    }
}
