package com.jsonapp.parkingmob.login;

import android.content.Context;

public interface LoginRepository {
    Account getAccount(String email);
    void setContext(Context context);

    void rememberUser();

    boolean isRemember();

    LoginDto getLoginCurrent();

    void noRememberUser();
}
