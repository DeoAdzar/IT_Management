package com.example.it_management.ui.Inventory.Assets;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
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
import com.example.it_management.ui.AddActivity.AddAsset;
import com.example.it_management.ui.Clients.ClientsModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AssetsFragment extends Fragment {
    private RecyclerView rvAssets;
    private RecyclerView.Adapter adAssets;
    private RecyclerView.LayoutManager lmAssets;
    private List<AssetsModel> listAssets = new ArrayList<>();
    private SwipeRefreshLayout swipeRefreshLayout;
    private FloatingActionButton fabAssets;
    private TextView totalAssets;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_assets, container, false);
        rvAssets = v.findViewById(R.id.rv_asset);
        swipeRefreshLayout = v.findViewById(R.id.sr_assets);
        fabAssets = v.findViewById(R.id.fab_add_assets);
        totalAssets = v.findViewById(R.id.totalAsset);
        lmAssets = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        rvAssets.setLayoutManager(lmAssets);
        tampilDataAssets();
        hilang();
        addAssets();
        refresh();
        return v;
    }

    private void refresh() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                tampilDataAssets();
                swipeRefreshLayout.setRefreshing(false);
                Toast.makeText(getActivity(), "Data have been updated", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        tampilDataAssets();
    }

    private void hilang(){
        rvAssets.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0 && fabAssets.getVisibility() == View.VISIBLE) {
                    fabAssets.hide();
                } else if (dy < 0 && fabAssets.getVisibility() != View.VISIBLE) {
                    fabAssets.show();
                }
            }
        });
    }
    private void addAssets(){
        fabAssets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), AddAsset.class);
                startActivity(i);
                onStop();
            }
        });
    }

    private void tampilDataAssets(){
        BaseApiService mApiService = UtilsApi.getApiService();
        Call<AssetsResponseModel> tampilData = mApiService.basGetAssets();
        tampilData.enqueue(new Callback<AssetsResponseModel>() {
            @Override
            public void onResponse(Call<AssetsResponseModel> call, Response<AssetsResponseModel> response) {
                listAssets = response.body().getData();
                adAssets = new AssetsAdapterData(getContext(),listAssets);
                totalAssets.setText(String.valueOf(adAssets.getItemCount()+" assets"));
                rvAssets.setAdapter(adAssets);
                adAssets.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<AssetsResponseModel> call, Throwable t) {
                Toast.makeText(getActivity(), "Gagal Menghubungkan Server pesan : "+t, Toast.LENGTH_SHORT).show();
            }
        });
    }
}