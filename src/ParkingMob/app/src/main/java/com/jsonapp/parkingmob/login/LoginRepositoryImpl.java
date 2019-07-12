package com.jsonapp.parkingmob.login;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class LoginRepositoryImpl implements LoginRepository {
    private Context context;

    @Override
    public Account getAccount(String email) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.context);
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
        setRemember(true);
    }

    private void setRemember(boolean valeu) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("remember", valeu);
        editor.apply();
    }

    @Override
    public boolean isRemember() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.context);
        Boolean isRemember = sharedPreferences.getBoolean("remember", false);
        return isRemember;
    }

    @Override
    public LoginDto getLoginCurrent() {
        LoginDto loginDto = new LoginDto();

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.context);
        String name = String.valueOf(sharedPreferences.getString("userName", ""));
        String email = String.valueOf(sharedPreferences.getString("email", ""));
        String password = String.valueOf(sharedPreferences.getString("password", ""));
        boolean remember = Boolean.valueOf(sharedPreferences.getBoolean("remember", false));

        loginDto.setName(name);
        loginDto.setEmail(email);
        loginDto.setPassword(password);
        loginDto.setRemember(remember);
        return loginDto;
    }

    @Override
    public void noRememberUser() {
        setRemember(false);
    }
}
