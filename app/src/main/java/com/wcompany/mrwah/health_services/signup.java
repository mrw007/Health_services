package com.wcompany.mrwah.health_services;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class signup extends AppCompatActivity {
    Button SignUp_btn;
    EditText username, pass,verif_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        username=findViewById(R.id.username);
        pass=findViewById(R.id.pass);
        verif_pass=findViewById(R.id.verif_pass);
        SignUp_btn=findViewById(R.id.signup_btn);
        SignUp_btn.setOnClickListener(SignUp_action);
    }

    public void onClick(View v) {
        //going for login
        Intent login_itnt = new Intent(this, login.class);
        startActivity(login_itnt);
    }
    OnClickListener SignUp_action = new OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent profile_setup = new Intent(view.getContext(), profile_setup1.class);
            profile_setup.putExtra("username", username.getText().toString());
            profile_setup.putExtra("pass", pass.getText().toString());
            startActivity(profile_setup);
        }
    };
}
