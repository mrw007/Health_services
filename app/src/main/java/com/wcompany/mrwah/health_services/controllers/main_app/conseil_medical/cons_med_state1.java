package com.wcompany.mrwah.health_services.controllers.main_app.conseil_medical;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;

import com.wcompany.mrwah.health_services.R;
import com.wcompany.mrwah.health_services.controllers.main_app.abonneHome;
import com.wcompany.mrwah.health_services.controllers.main_app.home_medecin;

public class cons_med_state1 extends AppCompatActivity {
    Switch if_sexe;
    ImageView sexe_ab;
    Button next_btn;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cons_med_state1);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        if_sexe = findViewById(R.id.if_sexe);
        sexe_ab = findViewById(R.id.sexe_ab);
        next_btn = findViewById(R.id.next_btn);
        if_sexe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (if_sexe.isChecked())
                    sexe_ab.setImageResource(R.drawable.ic_woman);
                else
                    sexe_ab.setImageResource(R.drawable.ic_man);
            }
        });
        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (if_sexe.isChecked()) {
                    Intent girl = new Intent(view.getContext(), cons_med_girl.class);
                    startActivity(girl);
                } else {
                    Intent boy = new Intent(view.getContext(), cons_med_boy.class);
                    startActivity(boy);
                }
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Intent refresh = new Intent(this, abonneHome.class);
            startActivity(refresh);
            this.finish();
        }
    }

}
