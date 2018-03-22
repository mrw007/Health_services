package com.wcompany.mrwah.health_services.adapters;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.wcompany.mrwah.health_services.R;

import java.util.ArrayList;
import java.util.List;

import Entities.Medecin;

import static android.content.ContentValues.TAG;

/**
 * Created by mrwah on 3/20/2018.
 */

public class accountListAdapter extends RecyclerView.Adapter<accountListAdapter.ViewHolder> {

    private Context mContext;
    private List<Medecin> medecinList;

    public accountListAdapter(Context mContext, List<Medecin> medecinList) {
        this.mContext = mContext;
        this.medecinList = medecinList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.profile_list, parent, false);
        return new ViewHolder(view, mContext, medecinList, parent);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String name = medecinList.get(position).getPrenom() + " " + medecinList.get(position).getNom();
        holder.user_name.setText(name);
        holder.user_spec.setText(medecinList.get(position).getSpecialite());
    }

    @Override
    public int getItemCount() {
        return medecinList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener

    {
        private final List<Medecin> medecins;
        private final Context mcontext;
        private final ViewGroup parent;
        TextView user_name;
        TextView user_spec;
        TextView dissmiss, profile_name, profile_spec, date_ness, phone, adress;

        public ViewHolder(View itemView, Context mContext, List<Medecin> medecins, ViewGroup parent) {
            super(itemView);
            itemView.setOnClickListener(this);
            user_name = itemView.findViewById(R.id.profile_name);
            user_spec = itemView.findViewById(R.id.profile_spec);
            this.medecins = medecins;
            this.mcontext = mContext;
            this.parent = parent;
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            Medecin med = medecins.get(position);
            showPopup(view, med);
        }

        public void showPopup(View view, Medecin med) {
            View popupView = LayoutInflater.from(mcontext).inflate(R.layout.medecin_list_popup, null);
            final PopupWindow popupWindow = new PopupWindow(popupView, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
            dissmiss = popupWindow.getContentView().findViewById(R.id.dissmiss);
            profile_name = popupWindow.getContentView().findViewById(R.id.profile_name);
            profile_spec = popupWindow.getContentView().findViewById(R.id.profile_spec);
            date_ness = popupWindow.getContentView().findViewById(R.id.date_ness);
            phone = popupWindow.getContentView().findViewById(R.id.phone);
            adress = popupWindow.getContentView().findViewById(R.id.adress);

            String name = med.getPrenom() + " " + med.getNom();
            profile_name.setText(name);
            profile_spec.setText(med.getSpecialite());
            date_ness.setText(med.getDateNaissance().toString());
            phone.setText(med.getTelCabinet());
            adress.setText(med.getAdresseCabinet());
            dissmiss.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    popupWindow.dismiss();
                }
            });
            popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);

        }
    }

}
