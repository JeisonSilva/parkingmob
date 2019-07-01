package com.jsonapp.parkingmob.signUp;

import android.content.Context;

import com.jsonapp.parkingmob.login.User;

public class SignUpBusinessimpl implements SignUpBusiness {

    private final SignUpDal signUpDal;
    private final SignUpRepository signUpRepository;

    public SignUpBusinessimpl(SignUpDal signUpDal, SignUpRepository signUpRepository ) {
        this.signUpDal = signUpDal;
        this.signUpRepository = signUpRepository;

        this.signUpRepository.setContext((Context)this.signUpDal);
    }

    @Override
    public void register() {
        SubscriptionDto subscriptionDto = signUpDal.getSubscription();
        User user = new User(subscriptionDto.getEmail(), subscriptionDto.getPassword());
        user.setUserName(subscriptionDto.getUserName());

        if(!signUpDal.isInputValid(subscriptionDto))
            return;

        if(user.confirmationPassword(subscriptionDto.getConfirmationPassword())){
            this.signUpRepository.register(subscriptionDto);
            this.signUpDal.finish();
        }

    }
}
