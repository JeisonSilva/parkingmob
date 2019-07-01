package com.jsonapp.parkingmob.login;

import android.content.Context;
import android.content.Intent;

public class ParkingScreen {
    private final Context context;
    Intent intent;

    public ParkingScreen(Context context, Intent intent) {
        this.intent = intent;
        this.context = context;
    }

    public void display(){
        context.startActivity(intent);
    }
}
