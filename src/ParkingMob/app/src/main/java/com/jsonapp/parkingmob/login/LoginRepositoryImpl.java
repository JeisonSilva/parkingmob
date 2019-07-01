package com.jsonapp.parkingmob.login;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;

public class LoginRepositoryImpl implements LoginRepository {
    private Context context;

    @Override
    public Account getAccount(String email) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("authorization.data",Context.MODE_PRIVATE);
        String registeredEmal = sharedPreferences.getString("email", "");
        String password = sharedPreferences.getString("password", "");

        if(registeredEmal.equals(email))
            return new Account(password);

        return new Account("INVALIDO");
    }

    @Override
    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public void rememberUser() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("authorization.data",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("remember", true);
        editor.apply();
    }

    @Override
    public boolean isRemember() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("authorization.data",Context.MODE_PRIVATE);
        Boolean isRemember = sharedPreferences.getBoolean("remember", false);
        return isRemember;
    }
}
