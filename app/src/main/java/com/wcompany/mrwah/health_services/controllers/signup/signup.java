package com.wcompany.mrwah.health_services.controllers.signup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import com.wcompany.mrwah.health_services.Entities.Connexion;
import com.wcompany.mrwah.health_services.R;
import com.wcompany.mrwah.health_services.controllers.login.login;

import static com.basgeekball.awesomevalidation.ValidationStyle.COLORATION;

public class signup extends AppCompatActivity {
    Button SignUp_btn;
    EditText username, pass, verif_pass;
    AwesomeValidation username_R, password_R, verif_pass_R;
    RequestQueue requestQueue;
    String baseUrl;
    Gson json = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        username = findViewById(R.id.username);
        pass = findViewById(R.id.pass);
        verif_pass = findViewById(R.id.verif_pass);
        SignUp_btn = findViewById(R.id.signup_btn);
        baseUrl = getString(R.string.server_link);
        requestQueue = Volley.newRequestQueue(this);
        SignUp_btn.setOnClickListener(SignUp_action);

        // Step 1: designate a style
        username_R = new AwesomeValidation(COLORATION);
        password_R = new AwesomeValidation(COLORATION);
        verif_pass_R = new AwesomeValidation(COLORATION);
        username_R.setColor(R.color.colorAccent);  // optional, default color is RED if not set
        password_R.setColor(R.color.colorAccent);
        verif_pass_R.setColor(R.color.colorAccent);
        // Step 2: add validations
        username_R.addValidation(username, "[a-zA-Z0-9._-]{4,}", "Nom d'utilisateur est requis et ne doit pas contenir des espaces");
        password_R.addValidation(pass, "(?=.*[a-z])(?=.*[A-Z])(?=.*[\\d])(?=.*[~`!@#\\$%\\^&\\*\\(\\)\\-_\\+=\\{\\}\\[\\]\\|\\;:\"<>,./\\?]).{8,}", "Mot de passe doit être au moins 8 caractères et contient au moins une lettre minuscule, une lettre majiscule et un caractère spécial");
        verif_pass_R.addValidation(verif_pass, pass, "Les deux champs ne sont pas conformes");
    }

    public void onClick(View v) {
        //going for login
        Intent login_itnt = new Intent(this, login.class);
        startActivity(login_itnt);
    }

    OnClickListener SignUp_action = new OnClickListener() {
        @Override
        public void onClick(View view) {
            if (username_R.validate() && password_R.validate() && verif_pass_R.validate()) {
                Connexion cnx = new Connexion(username.getText().toString());
                verif_username(view, json.toJson(cnx));
            }
        }
    };

    private void verif_username(final View view, final String cnx) {
        final StringRequest arrReq = new StringRequest(Request.Method.POST, baseUrl + "/verifUsername",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            if (response.equals("false")) {
                                Intent profile_setup = new Intent(view.getContext(), profile_setup1.class);
                                profile_setup.putExtra("username", username.getText().toString());
                                profile_setup.putExtra("pass", pass.getText().toString());
                                startActivity(profile_setup);

                            } else if (response.equals("true")) {
                                Toast toast = Toast.makeText(getApplicationContext(), "Nom d'utulisateur déja existe, essayez un autre", Toast.LENGTH_SHORT);
                                toast.show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast toast = Toast.makeText(getApplicationContext(), "Something is Wrong, Please try again", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Content-Type", "application/json");
                return params;
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                return cnx.getBytes();
            }
        };
        requestQueue.add(arrReq);
    }
}
