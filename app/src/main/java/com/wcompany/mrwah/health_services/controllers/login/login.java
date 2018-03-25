package com.wcompany.mrwah.health_services.controllers.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import com.wcompany.mrwah.health_services.Entities.Connexion;
import com.wcompany.mrwah.health_services.Entities.Session;
import com.wcompany.mrwah.health_services.R;
import com.wcompany.mrwah.health_services.controllers.main_app.abonneHome;
import com.wcompany.mrwah.health_services.controllers.main_app.adminHome;
import com.wcompany.mrwah.health_services.controllers.main_app.home_medecin;
import com.wcompany.mrwah.health_services.controllers.signup.signup;

import static com.basgeekball.awesomevalidation.ValidationStyle.*;

public class login extends AppCompatActivity {

    RequestQueue requestQueue;
    String baseUrl;
    Button login_btn;
    EditText username, password;
    AwesomeValidation username_R;
    Gson json = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        requestQueue = Volley.newRequestQueue(this);
        baseUrl = getString(R.string.server_link);
        login_btn = findViewById(R.id.login_btn);
        username = findViewById(R.id.username);
        password = findViewById(R.id.pass);
        login_btn.setOnClickListener(login_action);
        // Step 1: designate a style
        username_R = new AwesomeValidation(COLORATION);
        username_R.setColor(R.color.colorAccent);  // optional, default color is RED if not set
        // Step 2: add validations
        username_R.addValidation(username, "[a-zA-Z0-9\\s]+", "Nom d'utilisateur est requis");
    }


    OnClickListener login_action = new OnClickListener() {

        @Override
        public void onClick(View view) {
            if (username_R.validate()) {
                Connexion cnx = new Connexion(username.getText().toString(), password.getText().toString());
                login_req(json.toJson(cnx), view);
            }
        }
    };

    public void onClick(View v) {
        Intent signup_itnt = new Intent(this, signup.class);
        startActivity(signup_itnt);
    }

    private void login_req(String cnx, final View view) {

        final JsonArrayRequest arrReq = new JsonArrayRequest(Request.Method.POST, baseUrl + "/signin", cnx, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response.length() > 0) {
                    try {
                        String type = response.getString(0);
                        switch (type) {
                            case "admin": {
                                Session session;
                                session = new Session(getApplicationContext());
                                session.setAccount(response.getJSONObject(1).toString(), type);
                                Intent admin = new Intent(view.getContext(), adminHome.class);
                                startActivity(admin);
                                break;
                            }
                            case "abonne": {
                                Session session;
                                session = new Session(getApplicationContext());
                                session.setAccount(response.getJSONObject(1).toString(), type);
                                Intent abonne = new Intent(view.getContext(), abonneHome.class);
                                startActivity(abonne);
                            }
                            case "medecin": {
                                Session session;
                                session = new Session(getApplicationContext());
                                session.setAccount(response.getJSONObject(1).toString(), type);
                                Intent medecin = new Intent(view.getContext(), home_medecin.class);
                                startActivity(medecin);
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Toast toast = Toast.makeText(getApplicationContext(), "Nom d'utilisateur ou mot de passe est incorrect", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        requestQueue.add(arrReq);
    }
}
