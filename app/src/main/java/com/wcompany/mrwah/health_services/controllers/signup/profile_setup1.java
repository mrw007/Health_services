package com.wcompany.mrwah.health_services.controllers.signup;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.wcompany.mrwah.health_services.R;

import net.gotev.uploadservice.MultipartUploadRequest;
import net.gotev.uploadservice.UploadNotificationConfig;

import java.io.IOException;
import java.util.UUID;

import static com.basgeekball.awesomevalidation.ValidationStyle.COLORATION;

public class profile_setup1 extends AppCompatActivity {
    String filename=null;
    Button next_btn;
    Switch ifMed;
    EditText nom, prenom;
    AwesomeValidation nom_R, prenom_R;
    private ImageView imageview;
    private static final int STORAGE_PERMISSION_CODE = 1242;
    private static final int SELECT_PICTURE = 1;
    private Uri filePath;
    private Bitmap bitmap;
    RequestQueue requestQueue;
    String baseUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_setup1);
        requestStoragePermission();
        requestQueue = Volley.newRequestQueue(this);
        baseUrl = getString(R.string.server_link);

        next_btn = findViewById(R.id.next_btn);
        ifMed = findViewById(R.id.ifMed);
        nom = findViewById(R.id.lastname);
        prenom = findViewById(R.id.firstname);
        imageview = findViewById(R.id.image);
        next_btn.setOnClickListener(next_action);


        // Step 1: designate a style
        nom_R = new AwesomeValidation(COLORATION);
        prenom_R = new AwesomeValidation(COLORATION);
        nom_R.setColor(R.color.colorAccent);  // optional, default color is RED if not set
        prenom_R.setColor(R.color.colorAccent);
        // Step 2: add validations
        nom_R.addValidation(nom, "[a-zA-Z\\s]+", "Champs Requis");
        prenom_R.addValidation(prenom, "[a-zA-Z\\s]+", "Champs Requis");

        imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFileChooser();
            }
        });

    }

    private void requestStoragePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            return;
        }
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show();
            } else Toast.makeText(this, "Permission not granted", Toast.LENGTH_SHORT).show();

        }
    }

    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SELECT_PICTURE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                imageview.setImageBitmap(bitmap);
            } catch (IOException e) {

            }
            uploadImage();
        }
    }

    private String getPath(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        String document_id = cursor.getString(0);

        document_id = document_id.substring(document_id.lastIndexOf(":") + 1);
        cursor.close();
        cursor = getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null, MediaStore.Images.Media._ID + " = ? ", new String[]{document_id}, null
        );
        cursor.moveToFirst();
        String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
        cursor.close();
        return path;
    }
    public String getFileName(Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                cursor.close();
            }
        }
        if (result == null) {
            result = uri.getPath();
            int cut = result.lastIndexOf('/');
            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        return result;
    }

    private void uploadImage() {
        String path = getPath(filePath);
        filename= getFileName(filePath);
        try {
            String uploadid = UUID.randomUUID().toString();
            new MultipartUploadRequest(this, uploadid, baseUrl + "/fileupload")
                    .addFileToUpload(path, "file")
                    .setNotificationConfig(new UploadNotificationConfig())
                    .setMaxRetries(2)
                    .startUpload();
        } catch (Exception e) {

        }
    }

    OnClickListener next_action = new OnClickListener() {
        @Override
        public void onClick(View view) {
            if (nom_R.validate() && prenom_R.validate()) {
                if (ifMed.isChecked()) {
                    Intent Doctor = new Intent(view.getContext(), profile_setup2_2.class);
                    Doctor.putExtra("nom", nom.getText().toString());
                    Doctor.putExtra("prenom", prenom.getText().toString());
                    Doctor.putExtra("username", getIntent().getStringExtra("username"));
                    Doctor.putExtra("pass", getIntent().getStringExtra("pass"));
                    if (filename!=null)
                    Doctor.putExtra("imageName",filename);
                    startActivity(Doctor);
                } else {
                    Intent User = new Intent(view.getContext(), profile_setup2.class);
                    User.putExtra("nom", nom.getText().toString());
                    User.putExtra("prenom", prenom.getText().toString());
                    User.putExtra("username", getIntent().getStringExtra("username"));
                    User.putExtra("pass", getIntent().getStringExtra("pass"));
                    if (filename!=null)
                        User.putExtra("imageName",filename);
                    startActivity(User);
                }
            }
        }
    };
}
