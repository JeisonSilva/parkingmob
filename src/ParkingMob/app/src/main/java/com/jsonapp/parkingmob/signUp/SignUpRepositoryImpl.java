package com.jsonapp.parkingmob.signUp;

import android.content.Context;
import android.content.SharedPreferences;

public class SignUpRepositoryImpl implements SignUpRepository {
    private Context context;

    @Override
    public void register(SubscriptionDto subscriptionDto) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("authorization.data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("userName", subscriptionDto.getUserName());
        editor.putString("email", subscriptionDto.getEmail());
        editor.putString("password", subscriptionDto.getPassword());
        editor.putBoolean("remember", false);
        editor.apply();
    }

    @Override
    public void setContext(Context signUpDal) {
        this.context = signUpDal;
    }
}
