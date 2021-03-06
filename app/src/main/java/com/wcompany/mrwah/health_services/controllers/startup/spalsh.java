package com.wcompany.mrwah.health_services.controllers.startup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.wcompany.mrwah.health_services.Entities.Session;
import com.wcompany.mrwah.health_services.R;
import com.wcompany.mrwah.health_services.controllers.main_app.abonneHome;
import com.wcompany.mrwah.health_services.controllers.main_app.adminHome;
import com.wcompany.mrwah.health_services.controllers.main_app.home_medecin;

public class spalsh extends AppCompatActivity {
    private ImageView logo;
    Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spalsh);
        logo = findViewById(R.id.splash_logo);
        Animation splash_animation = AnimationUtils.loadAnimation(this, R.anim.spalsh_transition);
        logo.startAnimation(splash_animation);
        final Intent walk_through = new Intent(this, walk_through.class);
        final Intent admin = new Intent(this, adminHome.class);
        final Intent abonne = new Intent(this, abonneHome.class);
        final Intent medecin = new Intent(this, home_medecin.class);
        Thread timer = new Thread() {
            public void run() {
                try {
                    sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    session = new Session(getApplicationContext());
                    switch (session.getType()) {
                        case "admin": {
                            startActivity(admin);
                            break;
                        }
                        case "abonne": {
                            startActivity(abonne);
                            break;
                        }
                        case "medecin": {
                            startActivity(medecin);
                            break;
                        }
                        default:
                            startActivity(walk_through);
                    }
                    finish();
                }
            }
        };
        timer.start();
    }
}
