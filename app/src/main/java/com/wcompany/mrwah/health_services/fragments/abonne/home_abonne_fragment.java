package com.wcompany.mrwah.health_services.fragments.abonne;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.wcompany.mrwah.health_services.R;
import com.wcompany.mrwah.health_services.controllers.main_app.conseil_medical.cons_med_girl;
import com.wcompany.mrwah.health_services.controllers.main_app.conseil_medical.cons_med_state1;

/**
 * Created by mrwah on 3/25/2018.
 */

public class home_abonne_fragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home_abn, null);
        CardView btn = rootView.findViewById(R.id.btn_test);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent test = new Intent(((AppCompatActivity) getActivity()), cons_med_state1.class);
                startActivity(test);
            }
        });
        return rootView;
    }
}
