package com.rishat.loginsql;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Signup_form extends AppCompatActivity {

    DatabaseHelper db;
    EditText mTextEmail;
    EditText mTextPassword;
    EditText mTextFullName;
    EditText mTextCPassword;
    EditText mTextPhone;
    Button mButtonLogin;
    Button mButtonRegister;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_form);


        db = new DatabaseHelper(this);
        mTextEmail = (EditText)findViewById(R.id.textEmail);
        mTextPassword = (EditText)findViewById(R.id.textPassword);
        mTextFullName = (EditText)findViewById(R.id.textFullName);
        mTextCPassword = (EditText)findViewById(R.id.textCPassword);
        mTextPhone = (EditText) findViewById(R.id.textPhone);
        mButtonLogin = (Button)findViewById(R.id.btnLogin);
        mButtonRegister = (Button)findViewById(R.id.btnRegister);

        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent LoginIntent = new Intent(Signup_form.this, Login_form.class);
                startActivity(LoginIntent);
            }
        });

        mButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fullname = mTextFullName.getText().toString().trim();
                String email = mTextEmail.getText().toString().trim();
                String pwd = mTextPassword.getText().toString().trim();
                String cpwd = mTextCPassword.getText().toString().trim();
                String phone = mTextPhone.getText().toString().trim();

                if (pwd.equals(cpwd)){

                    long val = db.addUser(fullname,email,pwd,phone);
                    if (val>0) {
                        Toast.makeText(Signup_form.this, "You have registered", Toast.LENGTH_LONG).show();
                        Intent moveToLogin = new Intent(Signup_form.this, Login_form.class);
                        startActivity(moveToLogin);
                    }
                    else
                    {
                        Toast.makeText(Signup_form.this, "Registration Error", Toast.LENGTH_LONG).show();
                    }

                }
                else
                {
                    Toast.makeText(Signup_form.this, "Password not Matched", Toast.LENGTH_LONG).show();
                }
            }
        });


    }
}
