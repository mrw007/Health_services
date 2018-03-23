package com.wcompany.mrwah.health_services;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;


import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;


public class Profile extends AppCompatActivity {
    private ImageView imageview;
    private static final int SELECT_PICTURE = 1;

    RequestQueue requestQueue;
    String baseUrl;
    Gson json = new Gson();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        requestQueue = Volley.newRequestQueue(this);
        baseUrl = getString(R.string.server_link);
        imageview = findViewById(R.id.image);
        imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,
                        "Select Picture"), SELECT_PICTURE);

            }
        });
    }

    ;

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                imageview.setImageBitmap(null);

                Uri mediaUri = data.getData();
                String mediaPath = getPath(mediaUri);
                Log.e("amalPath", mediaPath);

                File photo = new File(mediaPath);
                RequestParams params = new RequestParams();
                try {
                    params.put("file", photo);
                } catch(FileNotFoundException e) {}

                AsyncHttpClient client = new AsyncHttpClient();

                client.post(baseUrl + "/fileupload", params, new JsonHttpResponseHandler() {
                    ProgressDialog pd;
                    @Override
                    public void onStart() {
                        String uploadingMessage = "uploading";
                        pd = new ProgressDialog(Profile.this);
                        pd.setTitle("please_wait");
                        pd.setMessage(uploadingMessage);
                        pd.setIndeterminate(false);
                        pd.show();
                    }



                    @Override
                    public void onFinish() {
                        pd.dismiss();
                    }
                });


                //display the image
                try {
                    InputStream inputStream = getBaseContext().getContentResolver().openInputStream(mediaUri);
                    Bitmap bm = BitmapFactory.decodeStream(inputStream);

                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    byte[] byteArray = stream.toByteArray();

                    //register_req(json.toJson(byteArray));
                    imageview.setImageBitmap(bm);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String getPath(Uri uri) {
        // just some safety built in
        if (uri == null) {
            // TODO perform some logging or show user feedback
            return null;
        }
        // try to retrieve the image from the media store first
        // this will only work for images selected from gallery
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        if (cursor != null) {
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        }
        // this is our fallback here
        return uri.getPath();
    }


    private void register_req( final String cnx) {
        JsonObjectRequest arrReq = new JsonObjectRequest(Request.Method.POST, baseUrl + "/upload", cnx,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast toast = Toast.makeText(getApplicationContext(), " succès", Toast.LENGTH_SHORT);
                        toast.show();

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
