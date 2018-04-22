package com.wcompany.mrwah.health_services.adapters;



import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
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
import com.wcompany.mrwah.health_services.Entities.Abonne;
import com.wcompany.mrwah.health_services.Entities.Session;
import com.wcompany.mrwah.health_services.R;

import java.util.HashMap;
import java.util.Map;

        import com.wcompany.mrwah.health_services.Entities.Reponse;

        import java.util.List;

public class reponseAdapter extends RecyclerView.Adapter<reponseAdapter.MyViewHolder> {

    private List<Reponse> reponseList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView dateRep, reponse, text_message_name;

        public MyViewHolder(View view) {
            super(view);
            dateRep = (TextView) view.findViewById(R.id.dateRep);
            reponse = (TextView) view.findViewById(R.id.reponse);
            text_message_name = (TextView) view.findViewById(R.id.text_message_name);
        }
    }


    public reponseAdapter(List<Reponse> reponseList) {
        this.reponseList = reponseList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.comment_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Reponse reponse = reponseList.get(position);
        holder.reponse.setText(reponse.getMessage());
        holder.dateRep.setText(reponse.getDateRep().toString());
        holder.text_message_name.setText(reponse.getMedecin().getNom()+" "+reponse.getMedecin().getPrenom());
    }

    @Override
    public int getItemCount() {
        return reponseList.size();
    }
}
