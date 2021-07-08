package com.example.it_management.ui.Inventory.Assets.FragmentDetail.TimeLogAssets;

import android.os.Bundle;

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
import com.example.it_management.ui.Clients.FragmentDetail.ClientTimeLog.ClientsTimeLogAdapterData;
import com.example.it_management.ui.Clients.FragmentDetail.ClientTimeLog.ClientsTimeLogModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TimeLogAssets extends Fragment {
    private RecyclerView rvTimeLogAsset;
    private RecyclerView.Adapter adTimeLogAsset;
    private RecyclerView.LayoutManager lmTimeLogAsset;
    private List<AssetTimeLogModel> assetTimeLogModelList = new ArrayList<>();
    private int id;
    private TextView noData;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_assets_time_log, container, false);
        rvTimeLogAsset = v.findViewById(R.id.rv_asset_timelog);
        lmTimeLogAsset = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        rvTimeLogAsset.setLayoutManager(lmTimeLogAsset);
        id = Integer.parseInt(getArguments().getString("idAssets"));
        noData = v.findViewById(R.id.no_data_timelog_asset);
        noData.setVisibility(View.GONE);
        tampildata();
        return v;
    }

    private void tampildata() {
        BaseApiService mApiService = UtilsApi.getApiService();
        Call<AssetTimeLogResponseModel> tampil = mApiService.basGetTimeLogAssets(id);
        tampil.enqueue(new Callback<AssetTimeLogResponseModel>() {
            @Override
            public void onResponse(Call<AssetTimeLogResponseModel> call, Response<AssetTimeLogResponseModel> response) {
                assetTimeLogModelList = response.body().getData();
                if (assetTimeLogModelList.isEmpty()){
                    rvTimeLogAsset.setVisibility(View.GONE);
                    noData.setVisibility(View.VISIBLE);
                }else {
                    adTimeLogAsset = new AssetTimeLogAdapterData(getContext(),assetTimeLogModelList);
                    rvTimeLogAsset.setAdapter(adTimeLogAsset);
                    adTimeLogAsset.notifyDataSetChanged();

                    rvTimeLogAsset.setVisibility(View.VISIBLE);
                    noData.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<AssetTimeLogResponseModel> call, Throwable t) {
                Toast.makeText(getActivity(), "Gagal Menghubungkan Server pesan : "+t, Toast.LENGTH_SHORT).show();
            }
        });
    }
}