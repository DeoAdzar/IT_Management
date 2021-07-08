package com.example.it_management.ui.Clients;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.it_management.API.BaseApiService;
import com.example.it_management.API.UtilsApi;
import com.example.it_management.MainActivity;
import com.example.it_management.R;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddClientsActivity extends AppCompatActivity {
    private Button btnBack,btnCreate;
    private EditText etName, etAsset_tag_prefix, etLicense_tag_prefix,etNotes;
    private String name,asset_tag_prefix,license_tag_prefix,notes,asset_tag,license_tag;
    private AlertDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_clients);
        init();
        btnClick();

    }
    private void init(){
        progressDialog = new SpotsDialog(AddClientsActivity.this, R.style.Custom);

        btnCreate = findViewById(R.id.btn_addClient);
        btnBack = findViewById(R.id.backClients);
        etName = findViewById(R.id.et_new_name);
        etAsset_tag_prefix = findViewById(R.id.et_new_asset_tag_prefix);
        etLicense_tag_prefix = findViewById(R.id.et_new_license_tag_prefix);
        etNotes = findViewById(R.id.et_new_notes);
    }
    private void btnClick(){
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = etName.getText().toString();
                asset_tag = etAsset_tag_prefix.getText().toString();
                license_tag = etLicense_tag_prefix.getText().toString();
                notes = etNotes.getText().toString();
                asset_tag_prefix = asset_tag + "-";
                license_tag_prefix = license_tag + "-";
                if(name.trim().equals("")){
                    etName.setError("Nama Harus Diisi");
                }else if(asset_tag.trim().equals("")){
                    etAsset_tag_prefix.setError("Asset Tag Harus Diisi");
                }else if(license_tag_prefix.trim().equals("")){
                    etLicense_tag_prefix.setError("License Tag Harus Diisi");
                }else {
                    createClients();
                }
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void createClients(){
        progressDialog.show();
        BaseApiService mApiService = UtilsApi.getApiService();
        Call<ClientsResponseModel> createDAta = mApiService.basInsertClients(name,asset_tag_prefix,license_tag_prefix,notes);
        createDAta.enqueue(new Callback<ClientsResponseModel>() {
            @Override
            public void onResponse(Call<ClientsResponseModel> call, Response<ClientsResponseModel> response) {
                String message = response.body().getMessage();
                Toast.makeText(AddClientsActivity.this, "response : "+message, Toast.LENGTH_SHORT).show();
                finish();
                progressDialog.hide();
            }

            @Override
            public void onFailure(Call<ClientsResponseModel> call, Throwable t) {
                Toast.makeText(AddClientsActivity.this, "Gagal Menghubungi Server, response : "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}