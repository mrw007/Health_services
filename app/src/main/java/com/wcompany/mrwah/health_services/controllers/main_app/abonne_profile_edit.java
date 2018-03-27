package com.wcompany.mrwah.health_services.controllers.main_app;

import android.app.DatePickerDialog;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
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
import com.wcompany.mrwah.health_services.Entities.Abonne;
import com.wcompany.mrwah.health_services.Entities.Medecin;
import com.wcompany.mrwah.health_services.Entities.Session;
import com.wcompany.mrwah.health_services.R;

import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static com.basgeekball.awesomevalidation.ValidationStyle.COLORATION;

public class abonne_profile_edit extends AppCompatActivity {

    Toolbar edit_profile_toolbar;
    EditText firstname, lastname, date_ness, email, phone, adress;
    private AwesomeValidation firstname_R, lastname_R, date_ness_R, email_R, phone_R, adress_R;
    Calendar Cal_date_naiss;
    private Gson json;
    private Session session;
    private Abonne ab;
    RequestQueue requestQueue;
    String baseUrl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abonne_profile_edit);
        edit_profile_toolbar = findViewById(R.id.profile_toolbar);
        setSupportActionBar(edit_profile_toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        init();
        //Calendar
        Cal_date_naiss = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Cal_date_naiss.set(Calendar.YEAR, year);
                Cal_date_naiss.set(Calendar.MONTH, monthOfYear);
                Cal_date_naiss.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };
        getInfos();
        validationConfig();
        date_ness.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    new DatePickerDialog(abonne_profile_edit.this, date, 1994, 01, 01).show();
                }
            }
        });
        requestQueue = Volley.newRequestQueue(this);
        baseUrl = getString(R.string.server_link);
    }

    private void validationConfig() {
        // Step 1: designate a style
        firstname_R = new AwesomeValidation(COLORATION);
        lastname_R = new AwesomeValidation(COLORATION);
        date_ness_R = new AwesomeValidation(COLORATION);
        email_R = new AwesomeValidation(COLORATION);
        phone_R = new AwesomeValidation(COLORATION);
        adress_R = new AwesomeValidation(COLORATION);

        // optional, default color is RED if not set
        firstname_R.setColor(R.color.colorAccent);
        lastname_R.setColor(R.color.colorAccent);
        date_ness_R.setColor(R.color.colorAccent);
        email_R.setColor(R.color.colorAccent);
        phone_R.setColor(R.color.colorAccent);
        adress_R.setColor(R.color.colorAccent);

        // Step 2: add validations

        firstname_R.addValidation(firstname, "[a-zA-Z\\s]+", "Champs Requis");
        lastname_R.addValidation(lastname, "[a-zA-Z\\s]+", "Champs Requis");
        email_R.addValidation(email, Patterns.EMAIL_ADDRESS, "Champs requis");
        phone_R.addValidation(phone, Patterns.PHONE, "Champs requis");
        adress_R.addValidation(adress, "[a-zA-Z0-9\\s]+", "Champs requis");
        date_ness_R.addValidation(date_ness, new SimpleCustomValidation() {
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

    }

    private void getInfos() {
        //Retrieve informations from session
        json = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        session = new Session(getApplicationContext());
        String acc = session.getAccount();
        ab = json.fromJson(acc, Abonne.class);

        //setting the fields
        firstname.setText(ab.getPrenom());
        lastname.setText(ab.getNom());
        email.setText(ab.getMail());
        phone.setText(ab.getTel());
        adress.setText(ab.getAdresse());
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
        String dateN = DATE_FORMAT.format(ab.getDateNaissance());
        date_ness.setText(dateN);
    }

    private void init() {
        firstname = findViewById(R.id.firstname);
        lastname = findViewById(R.id.lastname);
        date_ness = findViewById(R.id.date_ness);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        adress = findViewById(R.id.adress);
    }
    private void updateLabel() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.FRENCH);
        date_ness.setText(sdf.format(Cal_date_naiss.getTime()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.abonne_edit_profile_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.done:
                if (firstname_R.validate() && lastname_R.validate() && date_ness_R.validate() && email_R.validate()  && phone_R.validate() && adress_R.validate()) {

                    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.FRENCH);
                    Date daten = null;
                    java.sql.Date sqlDate = null;
                    try {
                        daten = dateFormat.parse(date_ness.getText().toString());
                        sqlDate = new java.sql.Date(daten.getTime());
                        Abonne abn = new Abonne (ab.getId(), ab.getLogin(), ab.getPassword(), lastname.getText().toString(), firstname.getText().toString(), email.getText().toString(), phone.getText().toString(), adress.getText().toString(), sqlDate);
                        updateRequest(json.toJson(abn));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                return true;
        }
        return super.onOptionsItemSelected(item);

    }

    private void updateRequest(final String acct) {
        JsonObjectRequest arrReq = new JsonObjectRequest(Request.Method.PUT, baseUrl + "/updateAbonne", acct,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        session.setAccount("account",acct);
                        Toast toast = Toast.makeText(getApplicationContext(), "Modification a été effectué avec succès", Toast.LENGTH_SHORT);
                        toast.show();
                        dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_BACK));
                        dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_BACK));
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
