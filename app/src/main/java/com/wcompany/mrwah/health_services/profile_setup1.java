package com.wcompany.mrwah.health_services;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import com.basgeekball.awesomevalidation.AwesomeValidation;

import java.util.regex.Pattern;

import static com.basgeekball.awesomevalidation.ValidationStyle.COLORATION;

public class profile_setup1 extends AppCompatActivity {

    Button next_btn;
    Switch ifMed;
    EditText nom, prenom;
    AwesomeValidation nom_R, prenom_R;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_setup1);
        next_btn = findViewById(R.id.next_btn);
        ifMed = findViewById(R.id.ifMed);
        nom = findViewById(R.id.lastname);
        prenom = findViewById(R.id.firstname);
        next_btn.setOnClickListener(next_action);


        // Step 1: designate a style
        nom_R = new AwesomeValidation(COLORATION);
        prenom_R = new AwesomeValidation(COLORATION);
        nom_R.setColor(R.color.colorAccent);  // optional, default color is RED if not set
        prenom_R.setColor(R.color.colorAccent);
        // Step 2: add validations
        nom_R.addValidation(nom, "[a-zA-Z\\s]+", "Champs Requis");
        prenom_R.addValidation(prenom, "[a-zA-Z\\s]+", "Champs Requis");
    }

    OnClickListener next_action = new OnClickListener() {
        @Override
        public void onClick(View view) {
            if (nom_R.validate() && prenom_R.validate()) {
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
        }
    };
}
