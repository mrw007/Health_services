package com.wcompany.mrwah.health_services;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import Entities.Medecin;

public class profile_setup2_2 extends AppCompatActivity {
    EditText date_naiss, tel, email, adresse;
    Button finish_btn;
    RequestQueue requestQueue;
    String baseUrl;
    Gson json = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_setup2_2);

        requestQueue = Volley.newRequestQueue(this);
        baseUrl = getString(R.string.server_link);
        init();
    }

    private void init() {
        //Spinner
        Spinner specialite = (Spinner) findViewById(R.id.specialite);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.planets_array,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        specialite.setAdapter(adapter);

        date_naiss = findViewById(R.id.date_naiss);
        tel = findViewById(R.id.phone);
        email = findViewById(R.id.email);
        adresse = findViewById(R.id.adress);
        finish_btn = findViewById(R.id.finsh_btn);
        finish_btn.setOnClickListener(finish_action);
    }

    OnClickListener finish_action = new OnClickListener() {
        @Override
        public void onClick(View view) {

            String nom = getIntent().getStringExtra("nom");
            String prenom = getIntent().getStringExtra("prenom");
            String username = getIntent().getStringExtra("username");
            String pass = getIntent().getStringExtra("pass");

            Medecin med = new Medecin(username, pass, nom, prenom, email.getText().toString(), tel.getText().toString(), "testing", tel.getText().toString(), adresse.getText().toString());

           /* CharSequence text = json.toJson(med);
            Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
            toast.show();
*/
            register_req(json.toJson(med));
        }
    };

    private void register_req(final String cnx) {

        JsonArrayRequest arrReq = new JsonArrayRequest(Request.Method.POST, baseUrl + "/signupMedecin", cnx,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        VolleyLog.v("ferrrr",response.toString());
                        Toast toast = Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT);
                        toast.show();


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue.add(arrReq);
    }
}
