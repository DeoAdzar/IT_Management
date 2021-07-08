package com.example.it_management.ui.Clients;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.it_management.API.BaseApiService;
import com.example.it_management.API.UtilsApi;
import com.example.it_management.R;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditClientsActivity extends AppCompatActivity {
    Button btnback,btnEdit;
    EditText etid,etname,etasset_tag,etlicense_tag,etnotes;
    String nName,nAsset_tag,nLicense_tag,nNotes;
    Integer nId;
    private AlertDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_clients);
        init();
        etid.setClickable(false);
        etid.setText(getIntent().getStringExtra("id"));
        etname.setText(getIntent().getStringExtra("name"));
        etasset_tag.setText(getIntent().getStringExtra("asset_tag"));
        etlicense_tag.setText(getIntent().getStringExtra("license_tag"));
        etnotes.setText(getIntent().getStringExtra("notes"));
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nId = Integer.parseInt(etid.getText().toString());
                nName = etname.getText().toString();
                nAsset_tag = etasset_tag.getText().toString();
                nLicense_tag = etlicense_tag.getText().toString();
                nNotes = etnotes.getText().toString();
                RequestUpdate();
            }
        });
    }
    private void init(){
        progressDialog = new SpotsDialog(EditClientsActivity.this, R.style.Custom);

        btnback = findViewById(R.id.backEditClients);
        etid = findViewById(R.id.id_edit_clients);
        etname = findViewById(R.id.et_edit_name);
        etasset_tag = findViewById(R.id.et_edit_asset_tag_prefix);
        etlicense_tag = findViewById(R.id.et_edit_license_tag_prefix);
        etnotes = findViewById(R.id.et_edit_notes);
        btnEdit = findViewById(R.id.btn_update);
    }
    private void RequestUpdate(){
        progressDialog.show();
        BaseApiService mApiService = UtilsApi.getApiService();
        Call<ClientsResponseModel> updateClients = mApiService.basUpdateClients(nId,nName,nAsset_tag,nLicense_tag,nNotes);
        updateClients.enqueue(new Callback<ClientsResponseModel>() {
            @Override
            public void onResponse(Call<ClientsResponseModel> call, Response<ClientsResponseModel> response) {
                String message = response.body().getMessage();
                Toast.makeText(EditClientsActivity.this, "response : "+message, Toast.LENGTH_SHORT).show();
                finish();
                progressDialog.hide();
            }

            @Override
            public void onFailure(Call<ClientsResponseModel> call, Throwable t) {
                Toast.makeText(EditClientsActivity.this, "Gagal menghubungi server | response : "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}