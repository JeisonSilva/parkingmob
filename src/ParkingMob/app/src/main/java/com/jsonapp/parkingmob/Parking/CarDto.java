package com.jsonapp.parkingmob.Parking;

import java.io.Serializable;

public class CarDto implements Serializable {
    private String plate;
    private String customerName;

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
