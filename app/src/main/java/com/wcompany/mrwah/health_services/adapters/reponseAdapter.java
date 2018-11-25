package com.wcompany.mrwah.health_services.adapters;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.wcompany.mrwah.health_services.Entities.Abonne;
import com.wcompany.mrwah.health_services.Entities.Session;
import com.wcompany.mrwah.health_services.R;

import java.util.HashMap;
import java.util.Map;

import com.wcompany.mrwah.health_services.Entities.Reponse;

import java.util.List;

public class reponseAdapter extends RecyclerView.Adapter<reponseAdapter.MyViewHolder> {
    private Context mContext;
    public static reponseAdapter adapter;

    private List<Reponse> reponseList;
    RequestOptions option;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView dateRep, reponse, text_message_name;
        ImageView image_message_profile;

        public MyViewHolder(View view) {
            super(view);
            dateRep = view.findViewById(R.id.dateRep);
            reponse = view.findViewById(R.id.reponse);
            text_message_name = view.findViewById(R.id.text_message_name);
            image_message_profile = view.findViewById(R.id.image_message_profile);

        }
    }


    public reponseAdapter(Context mContext, List<Reponse> reponseList) {
        this.reponseList = reponseList;
        this.mContext = mContext;
        adapter = this; //This is an important line, you need this line to keep track the adapter variable
        option = new RequestOptions().centerCrop().placeholder(R.drawable.user).error(R.drawable.user);

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.comment_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        try {
            Reponse reponse = reponseList.get(position);
            holder.reponse.setText(reponse.getMessage());
            String nom = reponse.getMedecin().getNom() + " " + reponse.getMedecin().getPrenom();
            holder.text_message_name.setText(nom);
            //Glide holder
            String baseUrl = mContext.getString(R.string.server_link);
            Glide.with(mContext).load(baseUrl + "/" + reponseList.get(position).getMedecin().getImage_src()).apply(option).into(holder.image_message_profile);
            holder.dateRep.setText(reponse.getDateRep().toString());
        } catch (Exception e)

        {
            e.fillInStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return reponseList.size();
    }
}
