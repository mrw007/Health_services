package com.wcompany.mrwah.health_services.controllers.main_app.conseil_medical;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wcompany.mrwah.health_services.Entities.Abonne;
import com.wcompany.mrwah.health_services.Entities.Publication;
import com.wcompany.mrwah.health_services.Entities.Session;
import com.wcompany.mrwah.health_services.R;
import com.wcompany.mrwah.health_services.controllers.main_app.abonneHome;

import org.json.JSONObject;

import java.sql.Date;

public class cons_med_state2 extends AppCompatActivity {
    Button next_btn;
    Switch cons_swc;
    EditText description;
    RadioGroup pub;
    private Session session;
    Gson json = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
    private String baseUrl;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cons_med_state2);
        description = findViewById(R.id.description);
        pub = findViewById(R.id.pub);
        next_btn = findViewById(R.id.next_btn);
        cons_swc = findViewById(R.id.ifCons);
        baseUrl = getString(R.string.server_link);
        session = new Session(getApplicationContext());
        requestQueue = Volley.newRequestQueue(this);


        cons_swc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cons_swc.isChecked())
                    next_btn.setText("continuer");
                else {
                    next_btn.setText("terminé");

                }
            }
        });
        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cons_swc.isChecked())
                    send_to_map(view);
                else
                    generate_query();
            }
        });
    }

    private String mode_pub() {
        int selectedId = pub.getCheckedRadioButtonId();
        RadioButton pub_mode = findViewById(selectedId);
        return pub_mode.getText().toString();
    }

    private void generate_query() {
        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
        String pub_mode = mode_pub();
        String acc = session.getAccount();
        Abonne abn = json.fromJson(acc, Abonne.class);
        boolean cons_dom = false;
        String zones = getIntent().getStringExtra("zones");
        Publication p = new Publication(description.getText().toString(), zones, date, pub_mode, cons_dom, abn);
        post(json.toJson(p));
    }

    private void send_to_map(View view) {
        String pub_mode = mode_pub();
        String acc = session.getAccount();
        Intent map = new Intent(view.getContext(), cons_med_location.class);
        String zones = getIntent().getStringExtra("zones");
        map.putExtra("zones", zones);
        map.putExtra("pub_mode", pub_mode);
        map.putExtra("description", description.getText().toString());
        map.putExtra("abonne", acc);
        startActivity(map);
    }

    private void post(final String cnx) {
        JsonObjectRequest arrReq = new JsonObjectRequest(Request.Method.POST, baseUrl + "/addPublication", cnx,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast toast = Toast.makeText(cons_med_state2.this, "publication est terminé avec succès", Toast.LENGTH_SHORT);
                        toast.show();
                        Intent refresh = new Intent(cons_med_state2.this, abonneHome.class);
                        startActivity(refresh);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast toast = Toast.makeText(getApplicationContext(), "Something is Wrong, Please try again", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                });
        requestQueue.add(arrReq);
    }
}
