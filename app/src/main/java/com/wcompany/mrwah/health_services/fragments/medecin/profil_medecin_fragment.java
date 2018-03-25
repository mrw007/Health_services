package com.wcompany.mrwah.health_services.fragments.medecin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wcompany.mrwah.health_services.Entities.Medecin;
import com.wcompany.mrwah.health_services.Entities.Session;
import com.wcompany.mrwah.health_services.R;
import com.wcompany.mrwah.health_services.adapters.medecinProfileDetailsListAdapter;
import com.wcompany.mrwah.health_services.controllers.login.login;
import com.wcompany.mrwah.health_services.controllers.main_app.home_medecin;

import org.json.JSONObject;

/**
 * Created by mrwah on 3/25/2018.
 */

public class profil_medecin_fragment extends Fragment {
    RecyclerView recyclerView;
    medecinProfileDetailsListAdapter adapter;
    Medecin med;
    Session session;
    Gson json = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
    Toolbar medecin_profile_toolbar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profil_medecin, null);
        if (container != null) {
            medecin_profile_toolbar = rootView.findViewById(R.id.profile_toolbar);
            ((AppCompatActivity)getActivity()).setSupportActionBar(medecin_profile_toolbar);
            ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
            setHasOptionsMenu(true);
            recyclerView = rootView.findViewById(R.id.medecin_profile_details_list);
            recyclerView.setHasFixedSize(true);
            session = new Session(getActivity().getApplicationContext());
            String acc = session.getAccount();
            med = json.fromJson(acc, Medecin.class);
            String name = med.getPrenom() + " " + med.getNom();
            TextView nom = rootView.findViewById(R.id.profile_name);
            nom.setText(name);
            TextView spec = rootView.findViewById(R.id.profile_spec);
            spec.setText(med.getSpecialite());
            adapter = new medecinProfileDetailsListAdapter(rootView.getContext(), med);
            recyclerView.setLayoutManager(new LinearLayoutManager(rootView.getContext()));
            recyclerView.setAdapter(adapter);
        }
        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        ((AppCompatActivity)getActivity()).getMenuInflater().inflate(R.menu.medcin_profile_menu, menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.edit:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
