package com.jsonapp.parkingmob.signUp;

import android.content.Context;

public interface SignUpRepository {
    void register(SubscriptionDto subscriptionDto);

    void setContext(Context signUpDal);
}
