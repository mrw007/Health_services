package com.wcompany.mrwah.health_services;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Entities.Connexion;

import static com.basgeekball.awesomevalidation.ValidationStyle.*;

public class login extends AppCompatActivity {

    RequestQueue requestQueue;
    String baseUrl;
    Button login_btn;
    EditText username, password;
    AwesomeValidation username_R, password_R;
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
        password_R = new AwesomeValidation(COLORATION);
        username_R.setColor(R.color.colorAccent);  // optional, default color is RED if not set
        password_R.setColor(R.color.colorAccent);
        // Step 2: add validations
        username_R.addValidation(username, "[a-zA-Z0-9\\s]+", "Nom d'utilisateur est requis");
        password_R.addValidation(password, "(?=.*[a-z])(?=.*[A-Z])(?=.*[\\d])(?=.*[~`!@#\\$%\\^&\\*\\(\\)\\-_\\+=\\{\\}\\[\\]\\|\\;:\"<>,./\\?]).{8,}", "Mot de passe invalide");
    }


    OnClickListener login_action = new OnClickListener() {

        @Override
        public void onClick(View view) {
            if (username_R.validate() && password_R.validate()) {
                Connexion cnx = new Connexion(username.getText().toString(), password.getText().toString());
                //login_req(json.toJson(cnx));

                //Testing Variables and json object creation
                Context context = getApplicationContext();
                CharSequence text = json.toJson(cnx);
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();

                //Temporary Redirect
                switch (username.getText().toString()) {
                    case "1": {
                        Intent User = new Intent(context, MainActivity.class);
                        startActivity(User);
                        break;
                    }
                    case "2": {
                        Intent Doctor = new Intent(context, MainActivity.class);
                        startActivity(Doctor);
                        break;

                    }
                    case "3": {
                        Intent Admin = new Intent(context, MainActivity.class);
                        startActivity(Admin);
                        break;
                    }
                    default: {
                        Toast toasty = Toast.makeText(context, "Login ou Mot de passe incorrectes", duration);
                        toasty.show();
                    }
                }
            }
        }
    };

    public void onClick(View v) {
        Intent signup_itnt = new Intent(this, signup.class);
        startActivity(signup_itnt);
    }

    private void login_req(String cnx) {

        JsonArrayRequest arrReq = new JsonArrayRequest(Request.Method.POST, baseUrl + "/login", cnx, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response.length() > 0) {
                    try {
                        JSONObject jsonObj = response.getJSONObject(0);
                        String msg = jsonObj.get("type").toString();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
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
