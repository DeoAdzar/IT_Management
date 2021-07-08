package com.example.it_management.ui.Inventory.Attributes.AssetsCategories;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
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
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AssetsCategoryFragment extends Fragment {
    private RecyclerView rvAssetsCategory;
    private RecyclerView.Adapter adAssetsCategory;
    private RecyclerView.LayoutManager lmAssetsCategory;
    private List<AssetsCategoryModel> assetsCategoryModelList = new ArrayList<>();
    private SwipeRefreshLayout srAssetsCategory;
    private FloatingActionButton fabAssetsCategory;
    private TextView totalAssetsCategory;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_assets_category, container, false);
        rvAssetsCategory = v.findViewById(R.id.rv_assetsCategory);
        lmAssetsCategory = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        rvAssetsCategory.setLayoutManager(lmAssetsCategory);
        srAssetsCategory = v.findViewById(R.id.sr_assetsCategory);
        fabAssetsCategory = v.findViewById(R.id.fab_add_assetsCategory);
        totalAssetsCategory = v.findViewById(R.id.totalAssetCategory);
        tampilAssetsCategory();
        refresh();
        return v;
    }
    private void refresh(){
        srAssetsCategory.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                tampilAssetsCategory();
                srAssetsCategory.setRefreshing(false);
                Toast.makeText(getActivity(), "Data have been updated", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void tampilAssetsCategory() {
        BaseApiService mApiService = UtilsApi.getApiService();
        Call<AssetsCategoryResponseModel> tampil = mApiService.basGetAssetsCategory();
        tampil.enqueue(new Callback<AssetsCategoryResponseModel>() {
            @Override
            public void onResponse(Call<AssetsCategoryResponseModel> call, Response<AssetsCategoryResponseModel> response) {
                assetsCategoryModelList = response.body().getData();
                adAssetsCategory = new AssetsCategoryAdapter(getContext(), assetsCategoryModelList);
                totalAssetsCategory.setText(String.valueOf(adAssetsCategory.getItemCount())+ " Category");
                rvAssetsCategory.setAdapter(adAssetsCategory);
                adAssetsCategory.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<AssetsCategoryResponseModel> call, Throwable t) {
                Toast.makeText(getActivity(), "Gagal Menghubungkan Server pesan : "+t, Toast.LENGTH_SHORT).show();
            }
        });
    }
}