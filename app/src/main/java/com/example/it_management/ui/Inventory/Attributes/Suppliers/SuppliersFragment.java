package com.example.it_management.ui.Inventory.Attributes.Suppliers;

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
import com.example.it_management.ui.Inventory.Attributes.Suppliers.SuppliersAdapter;
import com.example.it_management.ui.Inventory.Attributes.Suppliers.SuppliersResponseModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SuppliersFragment extends Fragment {
    private RecyclerView rvSuppliers;
    private RecyclerView.Adapter adSuppliers;
    private RecyclerView.LayoutManager lmSuppliers;
    private List<SuppliersModel> SuppliersModelList = new ArrayList<>();
    private SwipeRefreshLayout srSuppliers;
    private FloatingActionButton fabSuppliers;
    private TextView totalSuppliers;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_suppliers, container, false);
        rvSuppliers = v.findViewById(R.id.rv_suppliers);
        lmSuppliers = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        rvSuppliers.setLayoutManager(lmSuppliers);
        srSuppliers = v.findViewById(R.id.sr_suppliers);
        fabSuppliers = v.findViewById(R.id.fab_add_suppliers);
        totalSuppliers = v.findViewById(R.id.totalsuppliers);
        tampilSuppliers();
        refresh();
        return v;
    }
    private void refresh(){
        srSuppliers.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                tampilSuppliers();
                srSuppliers.setRefreshing(false);
                Toast.makeText(getActivity(), "Data have been updated", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void tampilSuppliers() {
        BaseApiService mApiService = UtilsApi.getApiService();
        Call<SuppliersResponseModel> tampil = mApiService.basGetSuppliers();
        tampil.enqueue(new Callback<SuppliersResponseModel>() {
            @Override
            public void onResponse(Call<SuppliersResponseModel> call, Response<SuppliersResponseModel> response) {
                SuppliersModelList = response.body().getData();
                adSuppliers = new SuppliersAdapter(getContext(), SuppliersModelList);
                totalSuppliers.setText(String.valueOf(adSuppliers.getItemCount())+ " Suppliers");
                rvSuppliers.setAdapter(adSuppliers);
                adSuppliers.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<SuppliersResponseModel> call, Throwable t) {
                Toast.makeText(getActivity(), "Gagal Menghubungkan Server pesan : "+t, Toast.LENGTH_SHORT).show();
            }
        });
    }
}