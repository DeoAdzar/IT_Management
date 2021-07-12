package com.example.it_management.ui.Inventory.Licences;

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
import com.example.it_management.ui.AddActivity.AddLicense;
import com.example.it_management.ui.Clients.ClientsAdapterData;
import com.example.it_management.ui.Clients.FragmentDetail.ClientAssets.ClientsAssetAdapterData;
import com.example.it_management.ui.Clients.FragmentDetail.ClientLicense.ClientsLicenseAdaptorData;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LicencesFragment extends Fragment {
    private RecyclerView rvlicense;
    private RecyclerView.Adapter adlicense;
    private RecyclerView.LayoutManager lmlicense;
    private List<LicencesModel> licencesModelList = new ArrayList<>();
    private SwipeRefreshLayout srlicense;
    private FloatingActionButton fablicense;
    private TextView totalLicense, nodata;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_licences, container, false);
        rvlicense = v.findViewById(R.id.rv_license);
        srlicense = v.findViewById(R.id.sr_license);
        fablicense = v.findViewById(R.id.fab_add_license);
        lmlicense = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        rvlicense.setLayoutManager(lmlicense);
        totalLicense = v.findViewById(R.id.totalLicense);
        nodata = v.findViewById(R.id.no_data_licenses);
        nodata.setVisibility(View.GONE);
        addLicense();
        hilang();
        tampilDataLicense();
        refresh();
        return v;
    }
    private void addLicense() {
        fablicense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), AddLicense.class);
                startActivity(i);
                onStop();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        tampilDataLicense();
    }
    private void hilang(){
        rvlicense.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0 && fablicense.getVisibility() == View.VISIBLE) {
                    fablicense.hide();
                } else if (dy < 0 && fablicense.getVisibility() != View.VISIBLE) {
                    fablicense.show();
                }
            }
        });
    }
    private void refresh(){
        srlicense.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                tampilDataLicense();
                srlicense.setRefreshing(false);
                Toast.makeText(getActivity(), "Data have been updated", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void tampilDataLicense() {
        BaseApiService mApiService = UtilsApi.getApiService();
        Call<LicensesResponseModel> tampildata = mApiService.basGetLicense();
        tampildata.enqueue(new Callback<LicensesResponseModel>() {
            @Override
            public void onResponse(Call<LicensesResponseModel> call, Response<LicensesResponseModel> response) {
                    licencesModelList = response.body().getData();

                if (licencesModelList.isEmpty()){
                    rvlicense.setVisibility(View.GONE);
                    nodata.setVisibility(View.VISIBLE);
                }else {
                    adlicense = new LicensesAdapterData(getContext(), licencesModelList);
                    totalLicense.setText(String.valueOf(adlicense.getItemCount())+ " Licenses");
                    rvlicense.setAdapter(adlicense);
                    adlicense.notifyDataSetChanged();

                    rvlicense.setVisibility(View.VISIBLE);
                    nodata.setVisibility(View.GONE);
                }

            }

            @Override
            public void onFailure(Call<LicensesResponseModel> call, Throwable t) {
                Toast.makeText(getActivity(), "Gagal Menghubungkan Server pesan : "+t, Toast.LENGTH_SHORT).show();
            }
        });
    }
}