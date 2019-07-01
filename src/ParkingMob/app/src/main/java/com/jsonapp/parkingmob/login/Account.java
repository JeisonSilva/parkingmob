package com.jsonapp.parkingmob.login;

public class Account {
    private String password;

    public Account(String password) {
        this.password = password;
    }

    public void authorize(User user) throws NotAuthorizedException {
        if(this.password.equals("INVALIDO"))
            throw new NotAuthorizedException("Usuário não autorizado");

        if(!password.equals(user.getPassword())){
            throw new NotAuthorizedException("Usuário não autorizado");
        }
    }
}
