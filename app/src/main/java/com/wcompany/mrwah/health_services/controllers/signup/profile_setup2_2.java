package com.wcompany.mrwah.health_services.controllers.signup;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.utility.custom.SimpleCustomValidation;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import com.wcompany.mrwah.health_services.Entities.Medecin;
import com.wcompany.mrwah.health_services.R;

import static android.app.ProgressDialog.show;
import static com.basgeekball.awesomevalidation.ValidationStyle.COLORATION;

public class profile_setup2_2 extends AppCompatActivity {
    EditText date_naiss, tel, email, adresse;
    AwesomeValidation date_naiss_R, tel_R, email_R, adresse_R;
    Button finish_btn;
    RequestQueue requestQueue;
    String baseUrl;
    Gson gson;
    Spinner specialite;
    Calendar Cal_date_naiss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_setup2_2);
        requestQueue = Volley.newRequestQueue(this);
        baseUrl = getString(R.string.server_link);
        init();
    }

    private void init() {
        //Spinner
        specialite = findViewById(R.id.specialite);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.planets_array,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        specialite.setAdapter(adapter);
        date_naiss = findViewById(R.id.date_naiss);
        tel = findViewById(R.id.phone);
        email = findViewById(R.id.email);
        adresse = findViewById(R.id.adress);
        finish_btn = findViewById(R.id.finsh_btn);
        finish_btn.setOnClickListener(finish_action);
        Cal_date_naiss = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // TODO Auto-generated method stub
                Cal_date_naiss.set(Calendar.YEAR, year);
                Cal_date_naiss.set(Calendar.MONTH, monthOfYear);
                Cal_date_naiss.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };
        date_naiss.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    new DatePickerDialog(profile_setup2_2.this, date, 1994, 01, 01).show();
                }
            }
        });
        // Step 1: designate a style
        date_naiss_R = new AwesomeValidation(COLORATION);
        tel_R = new AwesomeValidation(COLORATION);
        email_R = new AwesomeValidation(COLORATION);
        adresse_R = new AwesomeValidation(COLORATION);
        date_naiss_R.setColor(R.color.colorAccent);  // optional, default color is RED if not set
        tel_R.setColor(R.color.colorAccent);
        email_R.setColor(R.color.colorAccent);
        adresse_R.setColor(R.color.colorAccent);
        // Step 2: add validations
        date_naiss_R.addValidation(date_naiss, new SimpleCustomValidation() {
            @Override
            public boolean compare(String input) {
                try {
                    Calendar calendarBirthday = Calendar.getInstance();
                    Calendar calendarToday = Calendar.getInstance();
                    calendarBirthday.setTime(new SimpleDateFormat("dd/MM/yyyy", Locale.FRENCH).parse(input));
                    int yearOfToday = calendarToday.get(Calendar.YEAR);
                    int yearOfBirthday = calendarBirthday.get(Calendar.YEAR);
                    if (yearOfToday - yearOfBirthday > 18) {
                        return true;
                    } else if (yearOfToday - yearOfBirthday == 18) {
                        int monthOfToday = calendarToday.get(Calendar.MONTH);
                        int monthOfBirthday = calendarBirthday.get(Calendar.MONTH);
                        if (monthOfToday > monthOfBirthday) {
                            return true;
                        } else if (monthOfToday == monthOfBirthday) {
                            if (calendarToday.get(Calendar.DAY_OF_MONTH) >= calendarBirthday.get(Calendar.DAY_OF_MONTH)) {
                                return true;
                            }
                        }
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return false;
            }
        }, "Vérifier Date");
        tel_R.addValidation(tel, Patterns.PHONE, "Champs requis");
        email_R.addValidation(email, Patterns.EMAIL_ADDRESS, "Champs requis");
        adresse_R.addValidation(adresse, adresse, "Les deux champs ne sont pas conformes");
    }

    private void updateLabel() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.FRENCH);
        date_naiss.setText(sdf.format(Cal_date_naiss.getTime()));
    }

    OnClickListener finish_action = new OnClickListener() {
        @Override
        public void onClick(View view) {
            if (date_naiss_R.validate() && tel_R.validate() && email_R.validate() && adresse_R.validate()) {
                String nom = getIntent().getStringExtra("nom");
                String prenom = getIntent().getStringExtra("prenom");
                String username = getIntent().getStringExtra("username");
                String pass = getIntent().getStringExtra("pass");
                String spec = specialite.getSelectedItem().toString();
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.FRENCH);
                Date date = null;
                java.sql.Date sqlDate = null;
                try {
                    date = dateFormat.parse(date_naiss.getText().toString());
                    sqlDate = new java.sql.Date(date.getTime());
                    Medecin med = new Medecin(username, pass, nom, prenom, email.getText().toString(), tel.getText().toString(), spec, tel.getText().toString(), adresse.getText().toString(), sqlDate);
                    gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
                    register_req(gson.toJson(med), view);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
        }
    };

    private void register_req(final String cnx, final View view) {
        JsonObjectRequest arrReq = new JsonObjectRequest(Request.Method.POST, baseUrl + "/signupMedecin", cnx,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast toast = Toast.makeText(getApplicationContext(), "Inscription est terminé avec succès, Bienvenue à bord !", Toast.LENGTH_SHORT);
                        toast.show();
                        Intent login = new Intent(view.getContext(), com.wcompany.mrwah.health_services.controllers.login.login.class);
                        startActivity(login);
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
