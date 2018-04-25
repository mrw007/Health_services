package com.wcompany.mrwah.health_services.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wcompany.mrwah.health_services.Entities.Abonne;
import com.wcompany.mrwah.health_services.Entities.Medecin;
import com.wcompany.mrwah.health_services.Entities.Publication;
import com.wcompany.mrwah.health_services.Entities.Reponse;
import com.wcompany.mrwah.health_services.Entities.Session;
import com.wcompany.mrwah.health_services.R;
import com.wcompany.mrwah.health_services.adapters.reponseAdapter;
import com.wcompany.mrwah.health_services.controllers.main_app.conseil_medical.cons_med_state1;

import org.json.JSONObject;

import me.gujun.android.taggroup.TagGroup;

/**
 * Created by mac on 22/04/2018.
 */

public class detail_pub extends AppCompatActivity {

    TextView description;
    TextView textViewusername;
    TagGroup tagGroup;
    Button send;
    EditText edittext_reponse;


    RequestOptions option;
    ImageView image_r;

    private Session session;
    private Medecin med;


    RecyclerView recyclerView;
    private reponseAdapter adapter;

    Gson gson;
    RequestQueue requestQueue;

     Gson json = new Gson();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_publication);
        Toolbar toolbar =(Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Détail Publication");

        requestQueue = Volley.newRequestQueue(this);
        final Intent intent = getIntent();
        //Publication pub = (Publication) getIntent().getSerializableExtra("Publication");

        String pubJson=intent.getStringExtra("Publication");

        final Publication pub ;
        pub= json.fromJson(pubJson, Publication.class);

        tagGroup=findViewById(R.id.tag_group);
        tagGroup.setTags(pub.getZone().split(";", -1));

        description=findViewById(R.id.description);
        description.setText(pub.getDescription());

        textViewusername=findViewById(R.id.textViewusername);
        textViewusername.setText(pub.getAbonne().getNom()+" "+pub.getAbonne().getPrenom());

        image_r = findViewById(R.id.profile_image);

        getImage(pub.getAbonne().getImage_src());



        recyclerView = (RecyclerView) findViewById(R.id.reponse_list);
        recyclerView.setHasFixedSize(true);

        adapter = new reponseAdapter(pub.getReponses());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);


         send = findViewById(R.id.button_chatbox_send);
        edittext_reponse = findViewById(R.id.edittext_reponse);

        send.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {



                //Retrieve informations from session
                json = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
                session = new Session(getApplicationContext());
                String acc = session.getAccount();
                med = json.fromJson(acc, Medecin.class);


                String message=edittext_reponse.getText().toString();

                //Medecin med = json.fromJson(med, Medecin.class);
                long millis = System.currentTimeMillis();
                java.sql.Date date = new java.sql.Date(millis);

                Publication p= new Publication(pub.getId(), pub.getDescription(),pub.getZone(), pub.getDatePub(), pub.getPub_mode(), pub.isConsultation_domicile(), pub.getPosition_long(), pub.getPosition_lat(),pub.getReponses(),pub.getAbonne());

                Reponse rep = new Reponse(message, date, p, med);
                gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
                sendMessage(gson.toJson(rep));

                edittext_reponse.setText(gson.toJson(rep));

            }
        });

        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back_black_24dp));
        setSupportActionBar(toolbar);


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void getImage(String url) {

        if ( url != null) {
            String baseUrl = getString(R.string.server_link);
            //Glide.with(getApplicationContext()).load(baseUrl + "/" + url).apply(option).into(image_r);
        }
    }


    private void sendMessage(final String cnx) {
        JsonObjectRequest arrReq = new JsonObjectRequest(Request.Method.POST, getString(R.string.server_link) + "/addReponse", cnx,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

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
