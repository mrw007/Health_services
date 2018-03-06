package com.wcompany.mrwah.health_services;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class login extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        EditText mail= findViewById(R.id.mail);
        EditText password=findViewById(R.id.pass);
        Button login_button = findViewById(R.id.login);


    }
    public void onClick(View v) {
            //going for signup
        Intent signup_itnt =new Intent(this,signup.class);
        startActivity(signup_itnt);


    }

}
