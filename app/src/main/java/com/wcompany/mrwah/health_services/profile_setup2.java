package com.wcompany.mrwah.health_services;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class profile_setup2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_setup2);
        String nom = getIntent().getStringExtra("nom");
        String prenom=getIntent().getStringExtra("prenom");

    }
}