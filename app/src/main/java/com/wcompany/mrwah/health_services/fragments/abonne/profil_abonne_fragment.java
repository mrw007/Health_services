package com.wcompany.mrwah.health_services.fragments.abonne;

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
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wcompany.mrwah.health_services.Entities.Abonne;
import com.wcompany.mrwah.health_services.Entities.Session;
import com.wcompany.mrwah.health_services.R;
import com.wcompany.mrwah.health_services.adapters.abonneProfileDetailsListAdapter;

/**
 * Created by mrwah on 3/25/2018.
 */

public class profil_abonne_fragment extends Fragment {
    RecyclerView recyclerView;
    abonneProfileDetailsListAdapter adapter;
    Abonne abn;
    Session session;
    Gson json = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
    Toolbar abonne_profile_toolbar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profil_abonne, null);
        if (container != null) {
            abonne_profile_toolbar = rootView.findViewById(R.id.profile_toolbar);
            ((AppCompatActivity) getActivity()).setSupportActionBar(abonne_profile_toolbar);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
            setHasOptionsMenu(true);
            recyclerView = rootView.findViewById(R.id.abonne_profile_details_list);
            recyclerView.setHasFixedSize(true);
            session = new Session(getActivity().getApplicationContext());
            String acc = session.getAccount();
            abn = json.fromJson(acc, Abonne.class);
            String name = abn.getPrenom() + " " + abn.getNom();
            TextView nom = rootView.findViewById(R.id.profile_name);
            nom.setText(name);
            adapter = new abonneProfileDetailsListAdapter(rootView.getContext(), abn);
            recyclerView.setLayoutManager(new LinearLayoutManager(rootView.getContext()));
            recyclerView.setAdapter(adapter);
        }
        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        ((AppCompatActivity) getActivity()).getMenuInflater().inflate(R.menu.abonne_profile_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.edit:
                Intent edit = new Intent(((AppCompatActivity)getActivity()), com.wcompany.mrwah.health_services.controllers.main_app.abonne_profile_edit.class);
                edit.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(edit);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
