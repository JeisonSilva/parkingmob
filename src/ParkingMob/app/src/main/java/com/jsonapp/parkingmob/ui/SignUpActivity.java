package com.jsonapp.parkingmob.ui;

import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.jsonapp.parkingmob.R;
import com.jsonapp.parkingmob.signUp.SignUpBusiness;
import com.jsonapp.parkingmob.signUp.SignUpBusinessimpl;
import com.jsonapp.parkingmob.signUp.SignUpDal;
import com.jsonapp.parkingmob.signUp.SignUpRepository;
import com.jsonapp.parkingmob.signUp.SignUpRepositoryImpl;
import com.jsonapp.parkingmob.signUp.SubscriptionDto;

public class SignUpActivity extends AppCompatActivity implements SignUpDal {

    private TextInputLayout input_layout_userName;
    private TextInputLayout input_layout_email;
    private TextInputLayout input_layout_password;
    private TextInputLayout input_layout_confirmationPassword;
    private TextInputEditText input_edit_userName;
    private TextInputEditText input_edit_email;
    private TextInputEditText input_edit_password;
    private TextInputEditText input_edit_confirmationPassword;
    private AppCompatButton compat_btn_register;
    private SignUpBusiness signUpBusiness;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(this.toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        input_layout_userName = findViewById(R.id.input_layout_userName);
        input_layout_email = findViewById(R.id.input_layout_email);
        input_layout_password = findViewById(R.id.input_layout_password);
        input_layout_confirmationPassword = findViewById(R.id.input_layout_confirmationPassword);

        input_edit_userName = findViewById(R.id.input_edit_userName);
        input_edit_email = findViewById(R.id.input_edit_email);
        input_edit_password = findViewById(R.id.input_edit_password);
        input_edit_confirmationPassword = findViewById(R.id.input_edit_confirmationPassword);

        compat_btn_register = findViewById(R.id.compat_btn_register);

        compat_btn_register.setOnClickListener(registerOnClickListener);

        SignUpRepository signUpRepository = new SignUpRepositoryImpl();
        signUpBusiness = new SignUpBusinessimpl(this, signUpRepository);
    }

    View.OnClickListener registerOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            signUpBusiness.register();
        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{
                onBackPressed();
            }
        }

        return true;
    }

    @Override
    public SubscriptionDto getSubscription() {
        String userName = "";
        String email="";
        String password ="";
        String confirmationPassword = "";
        SubscriptionDto subscriptionDto = new SubscriptionDto();

        if(input_edit_userName instanceof TextInputEditText)
            userName = String.valueOf(input_edit_userName.getText());

        if(input_edit_email instanceof TextInputEditText)
            email = String.valueOf(input_edit_email.getText());

        if(input_edit_password instanceof TextInputEditText)
            password = String.valueOf(input_edit_password.getText());

        if(input_edit_confirmationPassword instanceof TextInputEditText)
            confirmationPassword = String.valueOf(input_edit_confirmationPassword.getText());

        SubscriptionDto subscriptionDto1 = new SubscriptionDto();
        subscriptionDto.setUserName(userName);
        subscriptionDto.setEmail(email);
        subscriptionDto.setPassword(password);
        subscriptionDto.setConfirmationPassword(confirmationPassword);

        return subscriptionDto;
    }

    @Override
    public void register() {
        signUpBusiness.register();
    }

    @Override
    public boolean isInputValid(SubscriptionDto subscriptionDto) {
        input_layout_userName.setError("");
        input_layout_email.setError("");
        input_layout_password.setError("");
        input_layout_confirmationPassword.setError("");

        if("".equals(subscriptionDto.getUserName())){
            input_layout_userName.setError("Usuário inválido");
            return false;
        }

        if("".equals(subscriptionDto.getEmail())){
            input_layout_email.setError("Email inválido");
            return false;
        }

        if("".equals(subscriptionDto.getPassword())){
            input_layout_password.setError("Senha inválida");
            return false;
        }

        if("".equals(subscriptionDto.getConfirmationPassword())){
            input_layout_confirmationPassword.setError("Confirmação de senha inválida");
            return false;
        }

        if(!subscriptionDto.getPassword().equals(subscriptionDto.getConfirmationPassword())){
            input_layout_confirmationPassword.setEnabled(true);
            input_layout_confirmationPassword.setError("Confirmação de senha inválida");
            return false;
        }

        return true;
    }
}
