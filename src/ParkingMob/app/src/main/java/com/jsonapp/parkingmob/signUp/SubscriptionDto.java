package com.jsonapp.parkingmob.signUp;

public class SubscriptionDto {
    private String userName;
    private String email;
    private String password;
    private String confirmationPassword;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userNmae) {
        this.userName = userNmae;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmationPassword() {
        return confirmationPassword;
    }

    public void setConfirmationPassword(String confirmationPassword) {
        this.confirmationPassword = confirmationPassword;
    }
}
