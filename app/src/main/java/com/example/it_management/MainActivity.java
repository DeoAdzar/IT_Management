package com.example.it_management;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.it_management.API.BaseApiService;
import com.example.it_management.API.UtilsApi;
import com.example.it_management.ui.Clients.ClientsResponseModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import dmax.dialog.SpotsDialog;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    EditText etEmail, etPassword;
    Context context;
    BaseApiService mApiService;
    ProgressBar progressBar;
    Button btnLogin;
    private AlertDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        init();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestLogin();
            }
        });

    }

    private void RequestLogin() {
        final String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        progressDialog.show();
        mApiService.loginRequest(email, password)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            try {
                                JSONObject jsonResult = new JSONObject(response.body().string());
                                progressDialog.hide();
                                if (jsonResult.getString("error").equals("false")) {
                                    String name = jsonResult.getJSONObject("login").getString("name");
                                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                                    intent.putExtra("result_name", name);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    String error_msg = jsonResult.getString("error_msg");
                                    Toast.makeText(context, error_msg, Toast.LENGTH_SHORT).show();
                                    progressDialog.hide();
                                }
                            } catch (JSONException e) {
                                progressDialog.hide();
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Toast.makeText(context, "GAGAL", Toast.LENGTH_SHORT).show();
                            progressDialog.hide();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e("debug","OnFailure : Error -> "+t.getMessage());

                        Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                        progressDialog.hide();
                    }
                });
    }

    private void init() {
        progressDialog = new SpotsDialog(MainActivity.this, R.style.Custom);
        etEmail = findViewById(R.id.editTextEmail);
        etPassword = findViewById(R.id.editTextPassword);
        btnLogin = findViewById(R.id.cirLoginButton);
        context = MainActivity.this;
        mApiService = UtilsApi.getApiService();
    }


}