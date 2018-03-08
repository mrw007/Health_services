package com.wcompany.mrwah.health_services;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
        Spinner specialite = (Spinner) findViewById(R.id.specialite);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.planets_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        specialite.setAdapter(adapter);
        requestQueue = Volley.newRequestQueue(this);
        baseUrl = getString(R.string.server_link);
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
            Medecin med = new Medecin((long) 0, username, pass,  nom,  prenom, email.getText().toString(),tel.getText().toString(), "testing", tel.getText().toString() , adresse.getText().toString());
            Context context = getApplicationContext();
            CharSequence text = json.toJson(med);
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            register_req(json.toJson(med));
        }
    };
    private void register_req(String cnx) {

        JsonArrayRequest arrReq = new JsonArrayRequest(Request.Method.POST, baseUrl + "/signupMedecin", cnx, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Context context = getApplicationContext();
                CharSequence text = response.toString();
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
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
