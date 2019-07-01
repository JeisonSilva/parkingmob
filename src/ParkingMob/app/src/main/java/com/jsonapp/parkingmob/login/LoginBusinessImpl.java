package com.jsonapp.parkingmob.login;

import android.content.Context;

public class LoginBusinessImpl implements LoginBusiness {

    private final LoginDal loginDal;
    private final LoginRepository loginRepository;

    public LoginBusinessImpl(LoginDal loginDal, LoginRepository loginRepository) {
        this.loginDal = loginDal;
        this.loginRepository = loginRepository;

        this.loginRepository.setContext((Context) this.loginDal);
    }

    @Override
    public void Login() {
        try {
            UserDto userDto = loginDal.getUser();
            User user = new User(userDto.getEmail(), userDto.getPassword());
            Account account = loginRepository.getAccount(userDto.getEmail());

            account.authorize(user);

            if(loginDal.isRemember())
                loginRepository.rememberUser();

            displayParkingScreen();
        } catch (NotAuthorizedException e) {
            e.printStackTrace();

            loginDal.displayInvalidAuthenticationMessage();
        }
    }

    @Override
    public void signUp() {
        loginDal.signUp();
    }

    @Override
    public boolean isRemember() {
        return this.loginRepository.isRemember();
    }

    private void displayParkingScreen() {
        ParkingScreen parkingScreen = this.loginDal.getParkingScreen();
        parkingScreen.display();
    }
}
