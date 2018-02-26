package com.wcompany.mrwah.health_services;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
    public void onClick(View v) {
            //going for signup
        Intent signup_itnt =new Intent(this,signup.class);
        startActivity(signup_itnt);
    }
}
