package com.example.it_management.ui.Inventory.Attributes.Manufacturers;

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


public class ManufacturersFragment extends Fragment {
    private RecyclerView rvmanufacturers;
    private RecyclerView.Adapter admanufacturers;
    private RecyclerView.LayoutManager lmmanufacturers;
    private List<ManufacturersModel> manufacturersModelList = new ArrayList<>();
    private SwipeRefreshLayout srmanufacturers;
    private FloatingActionButton fabmanufacturers;
    private TextView totalmanufacturers;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_manufacturers, container, false);
        rvmanufacturers = v.findViewById(R.id.rv_manufacturers);
        lmmanufacturers = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        rvmanufacturers.setLayoutManager(lmmanufacturers);
        srmanufacturers = v.findViewById(R.id.sr_manufacturers);
        fabmanufacturers = v.findViewById(R.id.fab_add_manufacturers);
        totalmanufacturers = v.findViewById(R.id.totalmanufacturers);
        tampilmanufacturers();
        refresh();
        return v;
    }
    private void refresh(){
        srmanufacturers.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                tampilmanufacturers();
                srmanufacturers.setRefreshing(false);
                Toast.makeText(getActivity(), "Data have been updated", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void tampilmanufacturers() {
        BaseApiService mApiService = UtilsApi.getApiService();
        Call<ManufacturersResponseModel> tampil = mApiService.basGetManufacturers();
        tampil.enqueue(new Callback<ManufacturersResponseModel>() {
            @Override
            public void onResponse(Call<ManufacturersResponseModel> call, Response<ManufacturersResponseModel> response) {
                manufacturersModelList = response.body().getData();
                admanufacturers = new ManufacturersAdapter(getContext(), manufacturersModelList);
                totalmanufacturers.setText(String.valueOf(admanufacturers.getItemCount())+ " Manufacturerss");
                rvmanufacturers.setAdapter(admanufacturers);
                admanufacturers.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ManufacturersResponseModel> call, Throwable t) {
                Toast.makeText(getActivity(), "Gagal Menghubungkan Server pesan : "+t, Toast.LENGTH_SHORT).show();
            }
        });
    }
}