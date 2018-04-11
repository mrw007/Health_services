package com.wcompany.mrwah.health_services.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.wcompany.mrwah.health_services.Entities.mailing;
import com.wcompany.mrwah.health_services.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wcompany.mrwah.health_services.Entities.Medecin;

import org.json.JSONObject;

import static com.wcompany.mrwah.health_services.R.*;

/**
 * Created by mrwah on 3/20/2018.
 */

public class accountListAdapter extends RecyclerView.Adapter<accountListAdapter.ViewHolder> {

    public static accountListAdapter adapter;
    private Context mContext;
    private List<Medecin> medecinList;
    RequestOptions option;


    public accountListAdapter(Context mContext, List<Medecin> medecinList) {
        this.mContext = mContext;
        this.medecinList = medecinList;
        adapter = this; //This is an important line, you need this line to keep track the adapter variable
        //Request option for Glide
        option = new RequestOptions().centerCrop().placeholder(R.drawable.user).error(R.drawable.user);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(layout.profile_list, parent, false);
        return new ViewHolder(view, mContext, medecinList, parent);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String name = medecinList.get(position).getPrenom() + " " + medecinList.get(position).getNom();
        holder.user_name.setText(name);
        holder.user_spec.setText(medecinList.get(position).getSpecialite());

        //Glide holder
        String baseUrl = mContext.getString(R.string.server_link);
        if (medecinList.get(position).getImage_src() != null)
            Glide.with(mContext).load(baseUrl + "/" + medecinList.get(position).getImage_src()).apply(option).into(holder.image_r);
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
        RequestOptions option;
        ImageView image_r, profile_image_r;
        private RequestQueue requestQueue;


        public ViewHolder(View itemView, Context mContext, List<Medecin> medecins, ViewGroup parent) {
            super(itemView);
            itemView.setOnClickListener(this);
            user_name = itemView.findViewById(id.profile_name);
            user_spec = itemView.findViewById(id.profile_spec);
            image_r = itemView.findViewById(id.profile_image);

            this.medecins = medecins;
            this.mcontext = mContext;
            this.parent = parent;

        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            Medecin med = medecins.get(position);
            showPopup(view, med, medecins);
        }

        public void showPopup(View view, final Medecin med, final List<Medecin> medecins) {
            View popupView = LayoutInflater.from(mcontext).inflate(layout.medecin_list_popup, null);
            popupView.startAnimation(AnimationUtils.loadAnimation(mcontext, android.R.anim.fade_in));

            final PopupWindow popupWindow = new PopupWindow(popupView, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
            dissmiss = popupWindow.getContentView().findViewById(id.dissmiss);
            profile_name = popupWindow.getContentView().findViewById(id.profile_name);
            profile_spec = popupWindow.getContentView().findViewById(id.profile_spec);
            date_ness = popupWindow.getContentView().findViewById(id.date_ness);
            phone = popupWindow.getContentView().findViewById(id.phone);
            adress = popupWindow.getContentView().findViewById(id.adress);
            profile_image_r = popupWindow.getContentView().findViewById(id.image_u);
            Button accepterBtn = popupWindow.getContentView().findViewById(id.accepter_btn);
            Button refuserBtn = popupWindow.getContentView().findViewById(id.refuser_btn);
            requestQueue = Volley.newRequestQueue(mcontext);
            String baseUrl = mcontext.getString(R.string.server_link);
            //Request option for Glide
            option = new RequestOptions().centerCrop().placeholder(R.drawable.user).error(R.drawable.user);
            if (med.getImage_src() != null)
                Glide.with(mcontext).load(baseUrl + "/" + med.getImage_src()).apply(option).into(profile_image_r);

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
            accepterBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Long id = med.getId();
                    String to = med.getMail();
                    acceptRequest(id, view, to, medecins);
                }


                private void acceptRequest(final Long id, View view, final String to, final List<Medecin> medecins) {
                    String baseUrl = mcontext.getString(R.string.server_link);
                    final StringRequest arrReq = new StringRequest(Request.Method.PUT, baseUrl + "/acceptmedecin",
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    try {
                                        if (response.equals("1")) {
                                            Toast toast = Toast.makeText(mcontext, "La demande est acceptée ", Toast.LENGTH_SHORT);
                                            toast.show();
                                            String text = "Votre compte est accepté, vous pouvez maintenant accèder à Caritus";
                                            String subject = "Compte Caritus";
                                            mailing mail = new mailing(to, text, subject);
                                            Gson json = new Gson();
                                            verif_mail(json.toJson(mail));
                                            popupWindow.dismiss();
                                            medecins.remove(med); //Actually change your list of items here
                                            adapter.notifyDataSetChanged(); //notify for change
                                        } else {
                                            Toast toast = Toast.makeText(mcontext, "Something is Wrong, Please try again", Toast.LENGTH_SHORT);
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
                                    Toast toast = Toast.makeText(mcontext, "Something is Wrong, Please try again", Toast.LENGTH_SHORT);
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
            });
            refuserBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Long id = med.getId();
                    String to = med.getMail();
                    refuseRequest(id, view, to, medecins);
                }

                private void refuseRequest(final Long id, View view, final String to, final List<Medecin> medecins) {
                    String baseUrl = mcontext.getString(R.string.server_link);
                    final StringRequest arrReq = new StringRequest(Request.Method.PUT, baseUrl + "/refusermedecin",
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    try {
                                        if (response.equals("true")) {
                                            Toast toast = Toast.makeText(mcontext, "La demande est refusée ", Toast.LENGTH_SHORT);
                                            toast.show();
                                            String text = "Votre compte est refusé, veuillez verifier vous coordonnées.";
                                            String subject = "Compte Caritus";
                                            mailing mail = new mailing(to, text, subject);
                                            Gson json = new Gson();
                                            verif_mail(json.toJson(mail));
                                            popupWindow.dismiss();
                                            medecins.remove(med); //Actually change your list of items here
                                            adapter.notifyDataSetChanged(); //notify for change
                                        } else {
                                            Toast toast = Toast.makeText(mcontext, "Something is Wrong, Please try again", Toast.LENGTH_SHORT);
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
                                    Toast toast = Toast.makeText(mcontext, "Something is Wrong, Please try again", Toast.LENGTH_SHORT);
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
            });

            popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);

        }

        private void verif_mail(final String mail) {
            String baseUrl = mcontext.getString(R.string.server_link);
            JsonObjectRequest arrReq = new JsonObjectRequest(Request.Method.POST, baseUrl + "/simpleemail", mail,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                   
                        }
                    });
            requestQueue.add(arrReq);
        }
    }


}
