package com.wcompany.mrwah.health_services.controllers.main_app.conseil_medical;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import com.wcompany.mrwah.health_services.R;

public class cons_med_girl extends AppCompatActivity {
    RadioButton z1, z2, z3, z4, z5, z6, z7, z8, z9, z10, z11, z12, z13, z14, z15, z16, z17, z18, z19, z20, z21, z22, z23, z24, z25;
    Button next_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cons_med_girl);
        next_btn=findViewById(R.id.next_btn);
        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent desc = new Intent(view.getContext(), cons_med_state2.class);
                startActivity(desc);
            }
        });
        buttonz();
    }

    public void buttonz() {
        z1 = findViewById(R.id.radioButton);
        z2 = findViewById(R.id.radioButton1);
        z3 = findViewById(R.id.radioButton2);
        z4 = findViewById(R.id.radioButton3);
        z5 = findViewById(R.id.radioButton4);
        z6 = findViewById(R.id.radioButton5);
        z7 = findViewById(R.id.radioButton6);
        z8 = findViewById(R.id.radioButton7);
        z9 = findViewById(R.id.radioButton8);
        z10 = findViewById(R.id.radioButton9);
        z11 = findViewById(R.id.radioButton10);
        z12 = findViewById(R.id.radioButton11);
        z13 = findViewById(R.id.radioButton12);
        z14 = findViewById(R.id.radioButton13);
        z15 = findViewById(R.id.radioButton14);
        z16 = findViewById(R.id.radioButton15);
        z17 = findViewById(R.id.radioButton16);
        z18 = findViewById(R.id.radioButton17);
        z19 = findViewById(R.id.radioButton18);
        z20 = findViewById(R.id.radioButton19);
        z21 = findViewById(R.id.radioButton20);
        z22 = findViewById(R.id.radioButton21);
        z23 = findViewById(R.id.radioButton22);
        z24 = findViewById(R.id.radioButton23);
        z25 = findViewById(R.id.radioButton24);
        //colorise_buttons();
    }

    public void colorise_buttons() {
        if (Build.VERSION.SDK_INT >= 21) {

            ColorStateList colorStateList = new ColorStateList(
                    new int[][]{

                            new int[]{-android.R.attr.state_enabled}, //disabled
                            new int[]{android.R.attr.state_enabled} //enabled
                    },
                    new int[]{

                            Color.WHITE //disabled
                            , Color.rgb(254, 89, 57) //enabled

                    }
            );
            z1.setButtonTintList(colorStateList);//set the color tint list
            z1.invalidate(); //could not be necessary
            z2.setButtonTintList(colorStateList);//set the color tint list
            z2.invalidate(); //could not be necessary
            z3.setButtonTintList(colorStateList);//set the color tint list
            z3.invalidate(); //could not be necessary
            z4.setButtonTintList(colorStateList);//set the color tint list
            z4.invalidate(); //could not be necessary
            z5.setButtonTintList(colorStateList);//set the color tint list
            z5.invalidate(); //could not be necessary
            z6.setButtonTintList(colorStateList);//set the color tint list
            z6.invalidate(); //could not be necessary
            z7.setButtonTintList(colorStateList);//set the color tint list
            z7.invalidate(); //could not be necessary
            z8.setButtonTintList(colorStateList);//set the color tint list
            z8.invalidate(); //could not be necessary
            z9.setButtonTintList(colorStateList);//set the color tint list
            z9.invalidate(); //could not be necessary
            z10.setButtonTintList(colorStateList);//set the color tint list
            z10.invalidate(); //could not be necessary
            z11.setButtonTintList(colorStateList);//set the color tint list
            z11.invalidate(); //could not be necessary
            z12.setButtonTintList(colorStateList);//set the color tint list
            z12.invalidate(); //could not be necessary
            z13.setButtonTintList(colorStateList);//set the color tint list
            z13.invalidate(); //could not be necessary
            z14.setButtonTintList(colorStateList);//set the color tint list
            z14.invalidate(); //could not be necessary
            z15.setButtonTintList(colorStateList);//set the color tint list
            z15.invalidate(); //could not be necessary
            z16.setButtonTintList(colorStateList);//set the color tint list
            z16.invalidate(); //could not be necessary
            z17.setButtonTintList(colorStateList);//set the color tint list
            z17.invalidate(); //could not be necessary
            z18.setButtonTintList(colorStateList);//set the color tint list
            z18.invalidate(); //could not be necessary
            z19.setButtonTintList(colorStateList);//set the color tint list
            z19.invalidate(); //could not be necessary
            z20.setButtonTintList(colorStateList);//set the color tint list
            z20.invalidate(); //could not be necessary
            z21.setButtonTintList(colorStateList);//set the color tint list
            z21.invalidate(); //could not be necessary
            z22.setButtonTintList(colorStateList);//set the color tint list
            z22.invalidate(); //could not be necessary
            z23.setButtonTintList(colorStateList);//set the color tint list
            z23.invalidate(); //could not be necessary
            z24.setButtonTintList(colorStateList);//set the color tint list
            z24.invalidate(); //could not be necessary
            z25.setButtonTintList(colorStateList);//set the color tint list
            z25.invalidate(); //could not be necessary
        }
    }
}
