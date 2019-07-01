package com.jsonapp.parkingmob.signUp;

public interface SignUpDal {
    SubscriptionDto getSubscription();
    void register();

    boolean isInputValid(SubscriptionDto subscriptionDto);

    void finish();
}
