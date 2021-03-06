package com.wcompany.mrwah.health_services.controllers.main_app;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.wcompany.mrwah.health_services.Entities.Medecin;
import com.wcompany.mrwah.health_services.Entities.Session;
import com.wcompany.mrwah.health_services.R;
import com.wcompany.mrwah.health_services.fragments.abonne.dashboard_abonne_fragment;
import com.wcompany.mrwah.health_services.fragments.abonne.home_abonne_fragment;
import com.wcompany.mrwah.health_services.fragments.abonne.notifs_abonne_fragment;
import com.wcompany.mrwah.health_services.fragments.abonne.profil_abonne_fragment;
import com.wcompany.mrwah.health_services.fragments.posts_list;


public class abonneHome extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abonne_home);
        session = new Session(getApplicationContext());
        loadFragment(new home_abonne_fragment());
        if (session.getAccount().equals("")) {
            Intent login = new Intent(getBaseContext(), com.wcompany.mrwah.health_services.controllers.login.login.class);
            login.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(login);
        }
        BottomNavigationViewEx navigation = findViewById(R.id.navigation_abn);
        navigation.setOnNavigationItemSelectedListener(this);
        navigation.enableAnimation(true);
        navigation.enableShiftingMode(false);
        navigation.enableItemShiftingMode(true);
        navigation.setTextVisibility(true);
        navigation.setIconSize(20, 20);
    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {

            getSupportFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                    .replace(R.id.fragmentContainer_abn, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        switch (item.getItemId()) {
            // TODO: 3/25/2018 edit with our pages later
            case R.id.abonne_profile:
                fragment = new profil_abonne_fragment();
                break;
            case R.id.navigation_home:
                fragment = new home_abonne_fragment();
                break;
            case R.id.navigation_dashboard:
                fragment = new posts_list();
                break;
            case R.id.navigation_notifications:
                fragment = new notifs_abonne_fragment();
                break;
        }
        return loadFragment(fragment);
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Etes-vous sûr de vouloir quitter l'application?")
                .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("Non", null);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
