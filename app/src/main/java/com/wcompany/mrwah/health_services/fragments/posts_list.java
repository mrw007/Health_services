package com.wcompany.mrwah.health_services.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wcompany.mrwah.health_services.Entities.Abonne;
import com.wcompany.mrwah.health_services.Entities.Medecin;
import com.wcompany.mrwah.health_services.Entities.Publication;
import com.wcompany.mrwah.health_services.R;
import com.wcompany.mrwah.health_services.adapters.RecyclerTouchListener;
import com.wcompany.mrwah.health_services.adapters.accountListAdapter;
import com.wcompany.mrwah.health_services.adapters.posts_list_adapter;
import com.wcompany.mrwah.health_services.controllers.main_app.adminHome;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mrwah on 4/17/2018.
 */

public class posts_list extends Fragment {
    private RecyclerView postsList;
    RequestQueue requestQueue;
    String baseUrl;
    Gson json = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
    posts_list_adapter posts_adapter;
    List<Publication> publicationList;
    Toolbar posts_ToolBar;
    private JsonArrayRequest get_all_posts;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_posts_list, null);
        if (container != null) {
            posts_ToolBar = rootView.findViewById(R.id.posts_toolbar);
            ((AppCompatActivity) getActivity()).setSupportActionBar(posts_ToolBar);
            posts_ToolBar.setTitle("Publications");

            baseUrl = getString(R.string.server_link);
            publicationList = new ArrayList<>();
            postsList = rootView.findViewById(R.id.posts_list);
            jsonRequest();
        }
        return rootView;
    }

    private void jsonRequest() {
        get_all_posts = new JsonArrayRequest(Request.Method.GET, baseUrl + "/getAllPublication", new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        Publication publication = json.fromJson(jsonObject.toString(), Publication.class);
                        publicationList.add(publication);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                setupListPostsRecyclerView(publicationList);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(get_all_posts);
    }

    private void setupListPostsRecyclerView(List<Publication> publicationList) {
        final List<Publication> pub=publicationList;
        posts_adapter = new posts_list_adapter(getActivity(), publicationList);
        postsList.setLayoutManager(new LinearLayoutManager(getActivity()));
        postsList.setAdapter(posts_adapter);

        postsList.addOnItemTouchListener(new  RecyclerTouchListener(getContext(), postsList, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Publication p = pub.get(position);

                Intent detail_pub = new Intent(getContext(), detail_pub.class);

                Gson json = new Gson();
                String params = json.toJson(p);
                detail_pub.putExtra("Publication", params);
                getContext().startActivity(detail_pub);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

    }
}