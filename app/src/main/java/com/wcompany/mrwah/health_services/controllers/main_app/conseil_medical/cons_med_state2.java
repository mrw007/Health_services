package com.wcompany.mrwah.health_services.controllers.main_app.conseil_medical;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

import com.wcompany.mrwah.health_services.R;

public class cons_med_state2 extends AppCompatActivity {
    Button next_btn;
    Switch cons_swc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cons_med_state2);
        next_btn = findViewById(R.id.next_btn);
        cons_swc = findViewById(R.id.ifCons);
        cons_swc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cons_swc.isChecked())
                    next_btn.setText("continuer");
                else
                    next_btn.setText("terminé");
            }
        });
        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cons_swc.isChecked()){
                    Intent map = new Intent(view.getContext(), cons_med_mocation.class);
                    startActivity(map);
                }

            }
        });
    }

}
