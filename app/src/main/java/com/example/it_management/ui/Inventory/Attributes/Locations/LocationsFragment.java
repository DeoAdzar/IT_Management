package com.example.it_management.ui.Inventory.Attributes.Locations;

import android.os.Bundle;

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
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LocationsFragment extends Fragment {
    private RecyclerView rvlocation;
    private RecyclerView.Adapter adlocation;
    private RecyclerView.LayoutManager lmlocation;
    private List<LocationsModel> locationModelList = new ArrayList<>();
    private SwipeRefreshLayout srlocation;
    private FloatingActionButton fablocation;
    private TextView totallocation;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_locations, container, false);
        rvlocation = v.findViewById(R.id.rv_location);
        lmlocation = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        rvlocation.setLayoutManager(lmlocation);
        srlocation = v.findViewById(R.id.sr_location);
        fablocation = v.findViewById(R.id.fab_add_location);
        totallocation = v.findViewById(R.id.totallocation);
        tampillocation();
        refresh();
        return v;
    }
    private void refresh(){
        srlocation.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                tampillocation();
                srlocation.setRefreshing(false);
                Toast.makeText(getActivity(), "Data have been updated", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void tampillocation() {
        BaseApiService mApiService = UtilsApi.getApiService();
        Call<LocationsResponseModel> tampil = mApiService.basGetLocations();
        tampil.enqueue(new Callback<LocationsResponseModel>() {
            @Override
            public void onResponse(Call<LocationsResponseModel> call, Response<LocationsResponseModel> response) {
                locationModelList = response.body().getData();
                adlocation = new LocationsAdapter(getContext(), locationModelList);
                totallocation.setText(String.valueOf(adlocation.getItemCount())+ " Locations");
                rvlocation.setAdapter(adlocation);
                adlocation.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<LocationsResponseModel> call, Throwable t) {
                Toast.makeText(getActivity(), "Gagal Menghubungkan Server pesan : "+t, Toast.LENGTH_SHORT).show();
            }
        });
    }
}