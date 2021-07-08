package com.example.it_management.ui.Clients.FragmentDetail.ClientLicense;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.it_management.API.BaseApiService;
import com.example.it_management.API.UtilsApi;
import com.example.it_management.R;
import com.example.it_management.ui.AddActivity.AddAsset;
import com.example.it_management.ui.AddActivity.AddLicense;
import com.example.it_management.ui.Clients.FragmentDetail.ClientCredential.ClientCredentialAdapterData;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClientsLicenseFragment extends Fragment {
    private RecyclerView rvLicense;
    private RecyclerView.Adapter adLicense;
    private RecyclerView.LayoutManager lmLicense;
    private List<ClientsLicenseModel>   clientsLicenseModelList = new ArrayList<>();
    private int id;
    FloatingActionButton fabAddLicense;
    private TextView nodata;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_clients_license, container, false);
        rvLicense = v.findViewById(R.id.rv_client_license);
        lmLicense = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        rvLicense.setLayoutManager(lmLicense);
        id = Integer.parseInt(getArguments().getString("idClient"));
        fabAddLicense = v.findViewById(R.id.fab_add_license_client);
        nodata = v.findViewById(R.id.no_data_license);
        nodata.setVisibility(View.GONE);
        hilang();
        tampilDataLicense();
        addLicense();
        return v;
    }

    private void addLicense() {
        fabAddLicense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), AddLicense.class);
                startActivity(i);
                onStop();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        tampilDataLicense();
    }
    private void hilang(){
        rvLicense.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0 && fabAddLicense.getVisibility() == View.VISIBLE) {
                    fabAddLicense.hide();
                } else if (dy < 0 && fabAddLicense.getVisibility() != View.VISIBLE) {
                    fabAddLicense.show();
                }
            }
        });
    }
    private void tampilDataLicense() {
        BaseApiService mApiService = UtilsApi.getApiService();
        Call<ClientsLicenseResponseModel> tampildata = mApiService.basGetLicenseClient(id);
        tampildata.enqueue(new Callback<ClientsLicenseResponseModel>() {
            @Override
            public void onResponse(Call<ClientsLicenseResponseModel> call, Response<ClientsLicenseResponseModel> response) {
                clientsLicenseModelList = response.body().getData();
                if (clientsLicenseModelList.isEmpty()){
                    rvLicense.setVisibility(View.GONE);
                    nodata.setVisibility(View.VISIBLE);
                }else {
                    adLicense = new ClientsLicenseAdaptorData(getContext(),clientsLicenseModelList);
                    rvLicense.setAdapter(adLicense);
                    adLicense.notifyDataSetChanged();

                    rvLicense.setVisibility(View.VISIBLE);
                    nodata.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<ClientsLicenseResponseModel> call, Throwable t) {
                Toast.makeText(getActivity(), "Gagal Menghubungkan Server pesan : "+t, Toast.LENGTH_SHORT).show();
            }
        });
    }
}