package com.wcompany.mrwah.health_services;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wcompany.mrwah.health_services.adapters.accountListAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import Entities.Medecin;
import Entities.Session;

public class adminHome extends AppCompatActivity {
    RequestQueue requestQueue;
    String baseUrl;
    private JsonArrayRequest get_medecins_request;
    private List<Medecin> medecinList;
    private RecyclerView accountList;
    Toolbar adminToolBar;
    Gson json = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
    Session session;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        adminToolBar = findViewById(R.id.adminToolBar);
        setSupportActionBar(adminToolBar);

        setContentView(R.layout.activity_admin_home);
        baseUrl = getString(R.string.server_link);
        medecinList = new ArrayList<>();
        accountList = findViewById(R.id.account_list);
        session = new Session(getApplicationContext());
        jsonRequest();
        if (session.getAccount().equals("")) {
            Intent login = new Intent(getBaseContext(), login.class);
            login.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(login);
        }
        Toast toast = Toast.makeText(getApplicationContext(), session.getAccount(), Toast.LENGTH_LONG);
        toast.show();

    }

    private void jsonRequest() {
        get_medecins_request = new JsonArrayRequest(Request.Method.GET, baseUrl + "/getMedecinByValidation", new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        Medecin med = json.fromJson(jsonObject.toString(), Medecin.class);
                        medecinList.add(med);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                setupListAccountRecyclerView(medecinList);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue = Volley.newRequestQueue(adminHome.this);
        requestQueue.add(get_medecins_request);
    }

    private void setupListAccountRecyclerView(List<Medecin> medecinList) {
        accountListAdapter listAdapter = new accountListAdapter(this, medecinList);
        accountList.setLayoutManager(new LinearLayoutManager(this));
        accountList.setAdapter(listAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.admin_menu, menu);
        return true;
    }
}
