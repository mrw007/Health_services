package com.wcompany.mrwah.health_services.controllers.main_app.conseil_medical;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import com.wcompany.mrwah.health_services.R;

public class cons_med_boy extends AppCompatActivity {
    RadioButton z1, z2, z3, z4, z5, z6, z7, z8, z9, z10, z11, z12, z13, z14, z15, z16, z17, z18, z19, z20, z21, z22, z23, z24;
    private Button next_btn;
    String zones = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cons_med_boy);
        buttonz();
        next_btn = findViewById(R.id.next_btn);
        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (z1.isChecked()) {
                    String z = "Pied gauche";
                    if (zones.equals("")) {
                        zones = z;
                    } else {
                        zones = zones + ";" + z;
                    }
                }
                if (z2.isChecked()) {
                    String z = "Pied gauche";
                    if (zones.equals("")) {
                        zones = z;
                    } else {
                        zones = zones + ";" + z;
                    }
                }
                if (z3.isChecked()) {
                    String z = "Genou droite";
                    if (zones.equals("")) {
                        zones = z;
                    } else {
                        zones = zones + ";" + z;
                    }
                }
                if (z4.isChecked()) {
                    String z = "Genou gauche";
                    if (zones.equals("")) {
                        zones = z;
                    } else {
                        zones = zones + ";" + z;
                    }
                }
                if (z5.isChecked()) {
                    String z = "Penis";
                    if (zones.equals("")) {
                        zones = z;
                    } else {
                        zones = zones + ";" + z;
                    }
                }
                if (z6.isChecked()) {
                    String z = "Main gauche";
                    if (zones.equals("")) {
                        zones = z;
                    } else {
                        zones = zones + ";" + z;
                    }
                }
                if (z7.isChecked()) {
                    String z = "Main droite";
                    if (zones.equals("")) {
                        zones = z;
                    } else {
                        zones = zones + ";" + z;
                    }
                }
                if (z8.isChecked()) {
                    String z = "Côté droit";
                    if (zones.equals("")) {
                        zones = z;
                    } else {
                        zones = zones + ";" + z;
                    }
                }
                if (z9.isChecked()) {
                    String z = "Côté droit";
                    if (zones.equals("")) {
                        zones = z;
                    } else {
                        zones = zones + ";" + z;
                    }
                }
                if (z10.isChecked()) {
                    String z = "Coude gauche";
                    if (zones.equals("")) {
                        zones = z;
                    } else {
                        zones = zones + ";" + z;
                    }
                }
                if (z11.isChecked()) {
                    String z = "Coude gauche";
                    if (zones.equals("")) {
                        zones = z;
                    } else {
                        zones = zones + ";" + z;
                    }
                }
                if (z12.isChecked()) {
                    String z = "Abdomen";
                    if (zones.equals("")) {
                        zones = z;
                    } else {
                        zones = zones + ";" + z;
                    }
                }
                if (z13.isChecked()) {
                    String z = "Sein droit";
                    if (zones.equals("")) {
                        zones = z;
                    } else {
                        zones = zones + ";" + z;
                    }
                }
                if (z14.isChecked()) {
                    String z = "Sein droit";
                    if (zones.equals("")) {
                        zones = z;
                    } else {
                        zones = zones + ";" + z;
                    }
                }
                if (z15.isChecked()) {
                    String z = "Cou";
                    if (zones.equals("")) {
                        zones = z;
                    } else {
                        zones = zones + ";" + z;
                    }
                }
                if (z16.isChecked()) {
                    String z = "Epaule gauche";
                    if (zones.equals("")) {
                        zones = z;
                    } else {
                        zones = zones + ";" + z;
                    }
                }
                if (z17.isChecked()) {
                    String z = "Epaule droite";
                    if (zones.equals("")) {
                        zones = z;
                    } else {
                        zones = zones + ";" + z;
                    }
                }
                if (z18.isChecked()) {
                    String z = "Bouche";
                    if (zones.equals("")) {
                        zones = z;
                    } else {
                        zones = zones + ";" + z;
                    }
                }
                if (z19.isChecked()) {
                    String z = "Nez";
                    if (zones.equals("")) {
                        zones = z;
                    } else {
                        zones = zones + ";" + z;
                    }
                }
                if (z20.isChecked()) {
                    String z = "Oeil gauche";
                    if (zones.equals("")) {
                        zones = z;
                    } else {
                        zones = zones + ";" + z;
                    }
                }
                if (z21.isChecked()) {
                    String z = "Oeil droite";
                    if (zones.equals("")) {
                        zones = z;
                    } else {
                        zones = zones + ";" + z;
                    }
                }
                if (z22.isChecked()) {
                    String z = "Oreille droite";
                    if (zones.equals("")) {
                        zones = z;
                    } else {
                        zones = zones + ";" + z;
                    }
                }
                if (z23.isChecked()) {
                    String z = "Oreille gauche";
                    if (zones.equals("")) {
                        zones = z;
                    } else {
                        zones = zones + ";" + z;
                    }
                }
                if (z24.isChecked()) {
                    String z = "Tête";
                    if (zones.equals("")) {
                        zones = z;
                    } else {
                        zones = zones + ";" + z;
                    }
                }
                if (!zones.equals("")) {
                    Intent desc = new Intent(view.getContext(), cons_med_state2.class);
                    desc.putExtra("zones", zones);
                    startActivity(desc);
                } else
                    Toast.makeText(cons_med_boy.this, "Veuillez sélectionner une zone", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void buttonz() {
        z1 = findViewById(R.id.radioButton); //right leg
        z2 = findViewById(R.id.radioButton1); // left leg
        z3 = findViewById(R.id.radioButton2); // right knee
        z4 = findViewById(R.id.radioButton3); // left knee
        z5 = findViewById(R.id.radioButton4); // penis
        z6 = findViewById(R.id.radioButton5); // left hand
        z7 = findViewById(R.id.radioButton6); // right hand
        z8 = findViewById(R.id.radioButton7); // left side
        z9 = findViewById(R.id.radioButton8); //right side
        z10 = findViewById(R.id.radioButton9); // right elbow
        z11 = findViewById(R.id.radioButton10); //left elbow
        z12 = findViewById(R.id.radioButton11); // abdomen
        z13 = findViewById(R.id.radioButton12); //left breast
        z14 = findViewById(R.id.radioButton13); // right breast
        z15 = findViewById(R.id.radioButton14); // neck
        z16 = findViewById(R.id.radioButton16);// left shoulder
        z17 = findViewById(R.id.radioButton17); // right shoulder
        z18 = findViewById(R.id.radioButton18);// mouth
        z19 = findViewById(R.id.radioButton19); // nose
        z20 = findViewById(R.id.radioButton20); // left eye
        z21 = findViewById(R.id.radioButton21); // right eye
        z22 = findViewById(R.id.radioButton22); // right ear
        z23 = findViewById(R.id.radioButton23); // left ear
        z24 = findViewById(R.id.radioButton24); // head
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
        }
    }
}
