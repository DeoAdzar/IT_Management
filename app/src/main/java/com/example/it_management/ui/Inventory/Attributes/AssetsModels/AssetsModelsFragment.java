package com.example.it_management.ui.Inventory.Attributes.AssetsModels;

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


public class AssetsModelsFragment extends Fragment {
    private RecyclerView rvAssetsModel;
    private RecyclerView.Adapter adAssetsModel;
    private RecyclerView.LayoutManager lmAssetsModel;
    private List<AssetsModelModel> assetsModelModelList = new ArrayList<>();
    private SwipeRefreshLayout srAssetsModel;
    private FloatingActionButton fabAssetsModel;
    private TextView totalAssetsModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_assets_models, container, false);
        rvAssetsModel = v.findViewById(R.id.rv_assetsModel);
        lmAssetsModel = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        rvAssetsModel.setLayoutManager(lmAssetsModel);
        srAssetsModel = v.findViewById(R.id.sr_assetsModel);
        fabAssetsModel = v.findViewById(R.id.fab_add_assetsModel);
        totalAssetsModel = v.findViewById(R.id.totalAssetModel);
        tampilAssetsModel();
        refresh();
        return v;
    }
    private void refresh(){
        srAssetsModel.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                tampilAssetsModel();
                srAssetsModel.setRefreshing(false);
                Toast.makeText(getActivity(), "Data have been updated", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void tampilAssetsModel() {
        BaseApiService mApiService = UtilsApi.getApiService();
        Call<AssetsModelResponseModel> tampil = mApiService.basGetAssetsModel();
        tampil.enqueue(new Callback<AssetsModelResponseModel>() {
            @Override
            public void onResponse(Call<AssetsModelResponseModel> call, Response<AssetsModelResponseModel> response) {
                assetsModelModelList = response.body().getData();
                adAssetsModel = new AssetsModelAdapter(getContext(), assetsModelModelList);
                totalAssetsModel.setText(String.valueOf(adAssetsModel.getItemCount())+ " Models");
                rvAssetsModel.setAdapter(adAssetsModel);
                adAssetsModel.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<AssetsModelResponseModel> call, Throwable t) {
                Toast.makeText(getActivity(), "Gagal Menghubungkan Server pesan : "+t, Toast.LENGTH_SHORT).show();
            }
        });
    }
}