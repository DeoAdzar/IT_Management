package com.example.it_management.ui.Clients.FragmentDetail.ClientFiles;

import android.Manifest;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
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
import com.example.it_management.ui.Clients.FragmentDetail.ClientAssets.ClientsAssetAdapterData;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClientsFilesFragment extends Fragment {
    private RecyclerView rvClientFiles;
    private RecyclerView.Adapter adClientsFiles;
    private RecyclerView.LayoutManager lmClientsFiles;
    private TextView nodata;
    private List<ClientsFilesModel> clientsFilesModelList = new ArrayList<>();
    private int id;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_clients_files, container, false);
        ActivityCompat.requestPermissions(getActivity(), new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        }, 1);
        rvClientFiles = v.findViewById(R.id.rv_client_files);
        lmClientsFiles = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        rvClientFiles.setLayoutManager(lmClientsFiles);
        id = Integer.parseInt(getArguments().getString("idClient"));
        nodata = v.findViewById(R.id.no_data_files);
        nodata.setVisibility(View.GONE);
        tampilDataFiles();
        return v;
    }

    private void tampilDataFiles() {
        BaseApiService mApiService = UtilsApi.getApiService();
        Call<ClientsFilesResponseModel> tampilData = mApiService.basGetFilesClient(id);
        tampilData.enqueue(new Callback<ClientsFilesResponseModel>() {
            @Override
            public void onResponse(Call<ClientsFilesResponseModel> call, Response<ClientsFilesResponseModel> response) {
                clientsFilesModelList = response.body().getData();
                if (clientsFilesModelList.isEmpty()){
                    rvClientFiles.setVisibility(View.GONE);
                    nodata.setVisibility(View.VISIBLE);
                }else {
                    adClientsFiles = new ClientsFilesAdapterData(getContext(), clientsFilesModelList);
                    rvClientFiles.setAdapter(adClientsFiles);
                    adClientsFiles.notifyDataSetChanged();

                    rvClientFiles.setVisibility(View.VISIBLE);
                    nodata.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<ClientsFilesResponseModel> call, Throwable t) {
                Toast.makeText(getActivity(), "Gagal Menghubungkan Server pesan : "+t, Toast.LENGTH_SHORT).show();
            }
        });
    }
}