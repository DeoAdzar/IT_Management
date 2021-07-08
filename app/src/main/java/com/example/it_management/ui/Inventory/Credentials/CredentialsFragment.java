package com.example.it_management.ui.Inventory.Credentials;

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
import com.example.it_management.ui.Inventory.Licences.LicensesAdapterData;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CredentialsFragment extends Fragment {
    private RecyclerView rvCredential;
    private RecyclerView.Adapter adCredential;
    private RecyclerView.LayoutManager lmCredential;
    private List<CredentialsModel> credentialsModelList = new ArrayList<>();
    private SwipeRefreshLayout srCredential;
    private FloatingActionButton fabCredential;
    private TextView totalCredential,nodata;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_credentials, container, false);
        rvCredential = v.findViewById(R.id.rv_credential);
        lmCredential = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        rvCredential.setLayoutManager(lmCredential);
        srCredential = v.findViewById(R.id.sr_credential);
        fabCredential = v.findViewById(R.id.fab_add_credential);
        totalCredential = v.findViewById(R.id.totalCredential);
        nodata = v.findViewById(R.id.no_data_credentials);
        nodata.setVisibility(View.GONE);
        tampilDataCredential();
        refresh();
        return v;
    }
    private void refresh(){
        srCredential.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                tampilDataCredential();
                srCredential.setRefreshing(false);
                Toast.makeText(getActivity(), "Data have been updated", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void tampilDataCredential() {
        BaseApiService mApiService = UtilsApi.getApiService();
        Call<CredentialsResponseModel> tampil = mApiService.basGetCredential();
        tampil.enqueue(new Callback<CredentialsResponseModel>() {
            @Override
            public void onResponse(Call<CredentialsResponseModel> call, Response<CredentialsResponseModel> response) {
                credentialsModelList = response.body().getData();
                if (credentialsModelList.isEmpty()){
                    rvCredential.setVisibility(View.GONE);
                    nodata.setVisibility(View.VISIBLE);
                }else {
                    adCredential = new CredentialsAdapterData(getContext(), credentialsModelList);
                    totalCredential.setText(String.valueOf(adCredential.getItemCount())+ " Licenses");
                    rvCredential.setAdapter(adCredential);
                    adCredential.notifyDataSetChanged();

                    rvCredential.setVisibility(View.VISIBLE);
                    nodata.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<CredentialsResponseModel> call, Throwable t) {
                Toast.makeText(getActivity(), "Gagal Menghubungkan Server pesan : "+t, Toast.LENGTH_SHORT).show();
            }
        });
    }
}