package com.jsonapp.parkingmob.login;

public class User {
    private String email;
    private String password;
    private String userNmae;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setUserName(String userName) {
        this.userNmae = userName;
    }

    public boolean confirmationPassword(String confirmationPassword) {
        return this.password.equals(confirmationPassword);
    }
}
