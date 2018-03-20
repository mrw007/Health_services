package com.wcompany.mrwah.health_services.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wcompany.mrwah.health_services.R;

import java.util.List;

import Entities.Medecin;

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
        return new ViewHolder(view);
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

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView user_name;
        TextView user_spec;

        public ViewHolder(View itemView) {
            super(itemView);
            user_name = itemView.findViewById(R.id.profile_name);
            user_spec = itemView.findViewById(R.id.profile_spec);
        }
    }
}
