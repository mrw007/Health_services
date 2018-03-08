package com.wcompany.mrwah.health_services;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

public class profile_setup1 extends AppCompatActivity {

    Button next_btn;
    Switch ifMed;
    EditText nom, prenom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_setup1);
        next_btn = findViewById(R.id.next_btn);
        ifMed = findViewById(R.id.ifMed);
        nom = findViewById(R.id.lastname);
        prenom = findViewById(R.id.firstname);
        next_btn.setOnClickListener(next_action);

    }

    OnClickListener next_action = new OnClickListener() {
        @Override
        public void onClick(View view) {
            if (ifMed.isChecked()) {
                Intent Doctor = new Intent(view.getContext(), profile_setup2_2.class);
                Doctor.putExtra("nom", nom.getText().toString());
                Doctor.putExtra("prenom", prenom.getText().toString());
                Doctor.putExtra("username", getIntent().getStringExtra("username"));
                Doctor.putExtra("pass", getIntent().getStringExtra("pass"));

                startActivity(Doctor);
            } else {
                Intent User = new Intent(view.getContext(), profile_setup2.class);
                User.putExtra("nom", nom.getText().toString());
                User.putExtra("prenom", prenom.getText().toString());
                User.putExtra("username", getIntent().getStringExtra("username"));
                User.putExtra("pass", getIntent().getStringExtra("pass"));

                startActivity(User);
            }
        }
    };
}
