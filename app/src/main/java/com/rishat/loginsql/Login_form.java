package com.rishat.loginsql;


import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Login_form extends AppCompatActivity {

    EditText mTextEmail;
    EditText mTextPassword;
    Button mButtonLogin;
    Button mButtonRegister;
    DatabaseHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_form);

        db = new DatabaseHelper(this);
        mTextEmail = (EditText)findViewById(R.id.textEmail1);
        mTextPassword = (EditText)findViewById(R.id.textPassword1);
        mButtonLogin = (Button)findViewById(R.id.btnLogin1);
        mButtonRegister = (Button)findViewById(R.id.btnRegister1);

        mButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(Login_form.this, Signup_form.class);
                startActivity(registerIntent);
            }
        });

        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = mTextEmail.getText().toString().trim();
                String pwd = mTextPassword.getText().toString().trim();
                Boolean res = db.checkUser(email, pwd);
                if(res == true)
                {
                    String name= mTextEmail.getText().toString().trim();
                    Calendar calendar = Calendar.getInstance();
                    SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
                    String time =" " +format.format(calendar.getTime());
                    db.addTime(name,time);
                    Intent LoginScreen = new Intent(Login_form.this, MainActivity.class);
                    startActivity(LoginScreen);
                    //Toast.makeText(Login_form.this, "Success", Toast.LENGTH_LONG).show();

                }
                else
                {
                    Toast.makeText(Login_form.this, "Login Failed!!", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
