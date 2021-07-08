package com.example.it_management.ui.Clients.FragmentDetail.ClientAssets;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.it_management.API.BaseApiService;
import com.example.it_management.API.UtilsApi;
import com.example.it_management.R;
import com.example.it_management.ui.AddActivity.AddAsset;
import com.example.it_management.ui.Clients.AddClientsActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClientsAssetsFragment extends Fragment {
    private RecyclerView rvClientAsset;
    private RecyclerView.Adapter adClientAsset;
    private RecyclerView.LayoutManager lmClientAsset;
    TextView tvnodata;
    private List<ClientsAssetModel> listClientAsset = new ArrayList<>();
    private Integer id;
    FloatingActionButton fabAddAssetClient;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_clients_assets, container, false);
        rvClientAsset = v.findViewById(R.id.rv_client_asset);
        lmClientAsset = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false);
        rvClientAsset.setLayoutManager(lmClientAsset);
        fabAddAssetClient = v.findViewById(R.id.fab_add_assets_client);
        id = Integer.parseInt(getArguments().getString("idClient"));
        tvnodata = v.findViewById(R.id.no_data);
        tvnodata.setVisibility(View.GONE);
        hilang();
        tampilDataClientAsset();
        addAssets();
        return  v;
    }
    @Override
    public void onResume() {
        super.onResume();
        tampilDataClientAsset();
    }

    private void hilang(){
        rvClientAsset.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0 && fabAddAssetClient.getVisibility() == View.VISIBLE) {
                    fabAddAssetClient.hide();
                } else if (dy < 0 && fabAddAssetClient.getVisibility() != View.VISIBLE) {
                    fabAddAssetClient.show();
                }
            }
        });
    }
    private void addAssets(){
        fabAddAssetClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), AddAsset.class);
                startActivity(i);
                onStop();
            }
        });
    }

    private void tampilDataClientAsset() {
        BaseApiService mApiService = UtilsApi.getApiService();
        Call<ClientAssetResponseModel> tampilDataAsset = mApiService.basGetAssetsClient(id);
        tampilDataAsset.enqueue(new Callback<ClientAssetResponseModel>() {
            @Override
            public void onResponse(Call<ClientAssetResponseModel> call, Response<ClientAssetResponseModel> response) {
                listClientAsset = response.body().getData();
                if (listClientAsset.isEmpty()){
                    rvClientAsset.setVisibility(View.GONE);
                    tvnodata.setVisibility(View.VISIBLE);
                }else {
                    adClientAsset = new ClientsAssetAdapterData(getContext(), listClientAsset);
                    rvClientAsset.setAdapter(adClientAsset);
                    adClientAsset.notifyDataSetChanged();

                    rvClientAsset.setVisibility(View.VISIBLE);
                    tvnodata.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<ClientAssetResponseModel> call, Throwable t) {
                Toast.makeText(getActivity(), "Gagal Menghubungkan Server pesan : "+t, Toast.LENGTH_SHORT).show();
            }
        });
    }
}