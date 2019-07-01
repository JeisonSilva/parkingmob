package com.jsonapp.parkingmob.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.jsonapp.parkingmob.R;
import com.jsonapp.parkingmob.login.LoginBusiness;
import com.jsonapp.parkingmob.login.LoginBusinessImpl;
import com.jsonapp.parkingmob.login.LoginDal;
import com.jsonapp.parkingmob.login.LoginRepository;
import com.jsonapp.parkingmob.login.LoginRepositoryImpl;
import com.jsonapp.parkingmob.login.ParkingScreen;
import com.jsonapp.parkingmob.login.UserDto;

public class MainActivity extends AppCompatActivity implements LoginDal {

    private Toolbar toolbar;
    private TextInputLayout input_layout_user;
    private TextInputEditText input_edit_layout_user;
    private TextInputLayout input_layout_password;
    private TextInputEditText input_edit_layout_password;
    private AppCompatCheckBox compat_chk_relembrar;
    private AppCompatButton compat_btn_login;
    private LoginBusiness loginBusiness;
    private AppCompatTextView compat_text_signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        input_layout_user = findViewById(R.id.input_layout_user);
        input_edit_layout_user = findViewById(R.id.input_edit_layout_user);
        input_layout_password = findViewById(R.id.input_layout_password);
        input_edit_layout_password = findViewById(R.id.input_edit_layout_password);
        compat_chk_relembrar = findViewById(R.id.compat_chk_remember_password);
        compat_btn_login = findViewById(R.id.compat_btn_login);
        compat_text_signUp = findViewById(R.id.compat_text_signUp);

        setSupportActionBar(toolbar);
        compat_btn_login.setOnClickListener(loginOnClickListener);
        compat_text_signUp.setOnClickListener(signUpOnClickListener);

        LoginRepository loginRepository = new LoginRepositoryImpl();
        this.loginBusiness = new LoginBusinessImpl(this, loginRepository);
    }

    View.OnClickListener loginOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            login();
        }
    };


    View.OnClickListener signUpOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            loginBusiness.signUp();
        }
    };


    @Override
    protected void onStart() {
        super.onStart();

        if(loginBusiness.isRemember()){
            ParkingScreen parkingScreen = this.getParkingScreen();
            parkingScreen.display();
        }

    }

    @Override
    public UserDto getUser() {
        UserDto userDto = new UserDto();
        String email ="";
        String password = "";

        if(input_edit_layout_user instanceof TextInputEditText)
            email = String.valueOf(input_edit_layout_user.getText());

        if(input_edit_layout_password instanceof TextInputEditText)
            password = String.valueOf(input_edit_layout_password.getText());

        userDto.setEmail(email);
        userDto.setPassword(password);
        return userDto;
    }

    @Override
    public boolean isRemember() {
        if(compat_chk_relembrar instanceof AppCompatCheckBox){
            boolean isChecked = compat_chk_relembrar.isChecked();
            return isChecked;
        }
        return false;
    }

    @Override
    public void login() {
        this.loginBusiness.Login();
    }

    @Override
    public void signUp() {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }

    @Override
    public ParkingScreen getParkingScreen() {
        Intent intent = new Intent(this,ParkingActivity.class);
        return new ParkingScreen(this, intent);
    }

    @Override
    public void displayInvalidAuthenticationMessage() {
        AlertDialog alertDialog;

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Autenticação");
        builder.setMessage("Autenticação inválida");
        builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        alertDialog = builder.create();
        alertDialog.show();
    }
}
