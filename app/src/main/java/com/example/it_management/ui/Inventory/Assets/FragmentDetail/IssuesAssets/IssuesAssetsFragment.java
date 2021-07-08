package com.example.it_management.ui.Inventory.Assets.FragmentDetail.IssuesAssets;

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
import com.example.it_management.ui.Clients.FragmentDetail.ClientIssues.ClientsIssuesAdapterData;
import com.example.it_management.ui.Clients.FragmentDetail.ClientIssues.ClientsIssuesModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class IssuesAssetsFragment extends Fragment {
    private RecyclerView rvIssuesAsset;
    private RecyclerView.Adapter adIssuesAsset;
    private RecyclerView.LayoutManager lmIssuesAsset;
    private TextView noData;
    private List<AssetIssuesModel> assetIssuesModelList = new ArrayList<>();
    private int id;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_assets_issues, container, false);
        rvIssuesAsset = v.findViewById(R.id.rv_asset_issues);
        lmIssuesAsset = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        rvIssuesAsset.setLayoutManager(lmIssuesAsset);
        id = Integer.parseInt(getArguments().getString("idAssets"));
        noData = v.findViewById(R.id.no_data_issues_asset);
        noData.setVisibility(View.GONE);
        TampilDataIssues();
        return v;
    }

    private void TampilDataIssues() {
        BaseApiService mApiService = UtilsApi.getApiService();
        Call<AssetIssuesResponseModel> tampil = mApiService.basGetIssuesAssets(id);
        tampil.enqueue(new Callback<AssetIssuesResponseModel>() {
            @Override
            public void onResponse(Call<AssetIssuesResponseModel> call, Response<AssetIssuesResponseModel> response) {
                assetIssuesModelList = response.body().getData();
                if (assetIssuesModelList.isEmpty()){
                    rvIssuesAsset.setVisibility(View.GONE);
                    noData.setVisibility(View.VISIBLE);
                }else {
                    adIssuesAsset = new AssetIssuesAdapterData(getContext(),assetIssuesModelList);
                    rvIssuesAsset.setAdapter(adIssuesAsset);
                    adIssuesAsset.notifyDataSetChanged();

                    rvIssuesAsset.setVisibility(View.VISIBLE);
                    noData.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<AssetIssuesResponseModel> call, Throwable t) {
                Toast.makeText(getActivity(), "Gagal Menghubungkan Server pesan : "+t, Toast.LENGTH_SHORT).show();
            }
        });
    }
}