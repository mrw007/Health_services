package com.wcompany.mrwah.health_services.adapters;


import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.wcompany.mrwah.health_services.Entities.Medecin;
import com.wcompany.mrwah.health_services.Entities.Session;
import com.wcompany.mrwah.health_services.R;
import com.wcompany.mrwah.health_services.controllers.login.login;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mrwah on 3/25/2018.
 */

public class medecinProfileDetailsListAdapter extends RecyclerView.Adapter<medecinProfileDetailsListAdapter.medecinProfileDetailsListViewHolder> {

    private Context context;
    private Medecin medecin;
    private Session session;
    private Gson json = new Gson();
    private RequestQueue requestQueue;


    public medecinProfileDetailsListAdapter(Context context, Medecin medecin) {
        this.context = context;
        this.medecin = medecin;
    }

    @Override
    public medecinProfileDetailsListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.medecin_profile_details_list_item, null);
        session = new Session(context.getApplicationContext());
        requestQueue = Volley.newRequestQueue(context);
        return new medecinProfileDetailsListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(medecinProfileDetailsListViewHolder holder, int position) {
        holder.username.setText(medecin.getLogin());
        holder.date_ness.setText(medecin.getDateNaissance().toString());
        holder.email.setText(medecin.getMail());
        holder.pro_tel.setText(medecin.getTelCabinet());
        holder.phone.setText(medecin.getTel());
        holder.adress.setText(medecin.getAdresseCabinet());


    }

    @Override
    public int getItemCount() {
        return 1;
    }


    class medecinProfileDetailsListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView username, date_ness, email, pro_tel, phone, adress;
        private LinearLayout logout, del_acc;

        public medecinProfileDetailsListViewHolder(View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.username);
            date_ness = itemView.findViewById(R.id.date_ness);
            email = itemView.findViewById(R.id.email);
            pro_tel = itemView.findViewById(R.id.pro_tel);
            phone = itemView.findViewById(R.id.phone);
            adress = itemView.findViewById(R.id.adress);
            logout = itemView.findViewById(R.id.logout);
            del_acc = itemView.findViewById(R.id.delete_acc);
            logout.setOnClickListener(this);
            del_acc.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            if (view == logout) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setMessage("Etes-vous sûr de vouloir se déconecter?")
                        .setPositiveButton("Se déconnecter", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                session.deleteAccount();
                                Intent login = new Intent(context, com.wcompany.mrwah.health_services.controllers.login.login.class);
                                login.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                Toast.makeText(context,"Vous êtes maintenant déconnecté",Toast.LENGTH_LONG).show();
                                context.startActivity(login);
                            }
                        })
                        .setNegativeButton("Non", null);
                AlertDialog alertDialog =builder.create();
                alertDialog.show();

            } else if (view == del_acc) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Etes-vous sûr de vouloir supprimer ce compte? Toutes vos données serons définitivement supprimées.")
                        .setPositiveButton("Supprimer Compte", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Medecin m = new Medecin();

                                deleteAccount(medecin.getId());

                            }
                        })
                        .setNegativeButton("Non", null);
                AlertDialog alertDialog =builder.create();
                alertDialog.show();
            }
        }

        private void deleteAccount(final Long id) {
            String baseUrl = context.getString(R.string.server_link);
            final StringRequest arrReq = new StringRequest(Request.Method.PUT, baseUrl + "/deleteMedecin",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                if (response.equals("true")) {
                                    session.deleteAccount();
                                    Intent login = new Intent(context, com.wcompany.mrwah.health_services.controllers.login.login.class);
                                    login.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    Toast.makeText(context,"Votre compte a été supprimé avec succès",Toast.LENGTH_LONG).show();
                                    context.startActivity(login);
                                } else {
                                    Toast toast = Toast.makeText(context, "Something is Wrong, Please try again", Toast.LENGTH_SHORT);
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
                            Toast toast = Toast.makeText(context, "Something is Wrong, Please try again", Toast.LENGTH_SHORT);
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
                    Medecin medecin = new Medecin(id);
                    Gson json = new Gson();
                    String params = json.toJson(medecin);
                    return params.getBytes();
                }
            };
            requestQueue.add(arrReq);
        }

    }

}
