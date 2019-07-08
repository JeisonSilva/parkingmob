package com.jsonapp.parkingmob.Parking;

import android.content.Context;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ParkingRepositoryImpl implements ParkingRepository {
    private Context context;

    public ParkingRepositoryImpl() {
    }

    @Override
    public void registrar(CarDto car) throws IOException, ClassNotFoundException {
        List<CarDto> list = getListCars();

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(this.context.openFileOutput("cars.data", Context.MODE_PRIVATE));
        list.add(car);
        objectOutputStream.writeObject(list);
    }

    private List<CarDto> getListCars(){
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(this.context.openFileInput("cars.data"));
            return (List<CarDto>)objectInputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }

    }

    @Override
    public void setContext(Context context) {
        this.context =context;
    }

    @Override
    public List<CarDto> getCars(){
        return this.getListCars();
    }

    @Override
    public void clear() throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(this.context.openFileOutput("cars.data", Context.MODE_PRIVATE));
        objectOutputStream.writeObject(new ArrayList<CarDto>());
    }
}
