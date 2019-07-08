package com.jsonapp.parkingmob.login;

import android.content.Context;
import android.content.SharedPreferences;

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
        setRemember(true);
    }

    private void setRemember(boolean valeu) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("authorization.data",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("remember", valeu);
        editor.apply();
    }

    @Override
    public boolean isRemember() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("authorization.data",Context.MODE_PRIVATE);
        Boolean isRemember = sharedPreferences.getBoolean("remember", false);
        return isRemember;
    }

    @Override
    public LoginDto getLoginCurrent() {
        LoginDto loginDto = new LoginDto();

        SharedPreferences sharedPreferences = context.getSharedPreferences("authorization.data",Context.MODE_PRIVATE);
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
