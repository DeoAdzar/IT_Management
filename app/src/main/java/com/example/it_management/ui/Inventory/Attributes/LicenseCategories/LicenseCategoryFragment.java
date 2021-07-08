package com.example.it_management.ui.Inventory.Attributes.LicenseCategories;

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


public class LicenseCategoryFragment extends Fragment {
    private RecyclerView rvLicenseCategory;
    private RecyclerView.Adapter adLicenseCategory;
    private RecyclerView.LayoutManager lmLicenseCategory;
    private List<LicenseCategoryModel> LicenseCategoryModelList = new ArrayList<>();
    private SwipeRefreshLayout srLicenseCategory;
    private FloatingActionButton fabLicenseCategory;
    private TextView totalLicenseCategory;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_license_categories, container, false);
        rvLicenseCategory = v.findViewById(R.id.rv_LicenseCategory);
        lmLicenseCategory = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        rvLicenseCategory.setLayoutManager(lmLicenseCategory);
        srLicenseCategory = v.findViewById(R.id.sr_LicenseCategory);
        fabLicenseCategory = v.findViewById(R.id.fab_add_LicenseCategory);
        totalLicenseCategory = v.findViewById(R.id.totalLicenseCategory);
        tampilLicenseCategory();
        refresh();
        return v;
    }
    private void refresh(){
        srLicenseCategory.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                tampilLicenseCategory();
                srLicenseCategory.setRefreshing(false);
                Toast.makeText(getActivity(), "Data have been updated", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void tampilLicenseCategory() {
        BaseApiService mApiService = UtilsApi.getApiService();
        Call<LicenseCategoryResponseModel> tampil = mApiService.basGetLicenseCategory();
        tampil.enqueue(new Callback<LicenseCategoryResponseModel>() {
            @Override
            public void onResponse(Call<LicenseCategoryResponseModel> call, Response<LicenseCategoryResponseModel> response) {
                LicenseCategoryModelList = response.body().getData();
                adLicenseCategory = new LicenseCategoryAdapter(getContext(), LicenseCategoryModelList);
                totalLicenseCategory.setText(String.valueOf(adLicenseCategory.getItemCount())+ " Category");
                rvLicenseCategory.setAdapter(adLicenseCategory);
                adLicenseCategory.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<LicenseCategoryResponseModel> call, Throwable t) {
                Toast.makeText(getActivity(), "Gagal Menghubungkan Server pesan : "+t, Toast.LENGTH_SHORT).show();
            }
        });
    }
}