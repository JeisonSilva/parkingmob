package com.jsonapp.parkingmob.Parking;

import android.content.Context;
import android.os.Environment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class DataExportRepositoryImpl implements DataExportRepository {
    private Context context;

    @Override
    public void export(ParkingDto parkingDto) throws IOException {
        FileOutputStream fileOutputStream = getFileOutputStream();

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);

        objectOutputStream.writeObject(parkingDto);
        fileOutputStream.write(byteArrayOutputStream.toByteArray());
    }

    private FileOutputStream getFileOutputStream() throws FileNotFoundException {
        String baseDir =  Environment.getExternalStorageDirectory().getAbsolutePath();
        String pathFile = String.format("%s%s%s",baseDir, File.separator, "dataexport.data");
        File file = new File(pathFile);
        FileOutputStream fileOutputStream = new FileOutputStream(file);

        return fileOutputStream;
    }

    @Override
    public void setContext(Context context) {
        this.context = context;
    }
}
