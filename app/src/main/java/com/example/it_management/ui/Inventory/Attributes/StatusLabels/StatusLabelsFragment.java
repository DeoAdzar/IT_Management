package com.example.it_management.ui.Inventory.Attributes.StatusLabels;

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
import com.example.it_management.ui.Inventory.Attributes.StatusLabels.StatusLabelsAdapter;
import com.example.it_management.ui.Inventory.Attributes.StatusLabels.StatusLabelsResponseModel;
import com.example.it_management.ui.Inventory.Attributes.StatusLabels.StatusLabelsModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class StatusLabelsFragment extends Fragment {
    private RecyclerView rvStatusLabels;
    private RecyclerView.Adapter adStatusLabels;
    private RecyclerView.LayoutManager lmStatusLabels;
    private List<StatusLabelsModel> StatusLabelsModelList = new ArrayList<>();
    private SwipeRefreshLayout srStatusLabels;
    private FloatingActionButton fabStatusLabels;
    private TextView totalStatusLabels;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       
        View v = inflater.inflate(R.layout.fragment_status_label, container, false);
        rvStatusLabels = v.findViewById(R.id.rv_StatusLabels);
        lmStatusLabels = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        rvStatusLabels.setLayoutManager(lmStatusLabels);
        srStatusLabels = v.findViewById(R.id.sr_StatusLabels);
        fabStatusLabels = v.findViewById(R.id.fab_add_StatusLabels);
        totalStatusLabels = v.findViewById(R.id.totalStatusLabels);
        tampilStatusLabels();
        refresh();
        return v;
    }
    private void refresh(){
        srStatusLabels.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                tampilStatusLabels();
                srStatusLabels.setRefreshing(false);
                Toast.makeText(getActivity(), "Data have been updated", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void tampilStatusLabels() {
        BaseApiService mApiService = UtilsApi.getApiService();
        Call<StatusLabelsResponseModel> tampil = mApiService.basGetLabels();
        tampil.enqueue(new Callback<StatusLabelsResponseModel>() {
            @Override
            public void onResponse(Call<StatusLabelsResponseModel> call, Response<StatusLabelsResponseModel> response) {
                StatusLabelsModelList = response.body().getData();
                adStatusLabels = new StatusLabelsAdapter(getContext(), StatusLabelsModelList);
                totalStatusLabels.setText(String.valueOf(adStatusLabels.getItemCount())+ " StatusLabels");
                rvStatusLabels.setAdapter(adStatusLabels);
                adStatusLabels.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<StatusLabelsResponseModel> call, Throwable t) {
                Toast.makeText(getActivity(), "Gagal Menghubungkan Server pesan : "+t, Toast.LENGTH_SHORT).show();
            }
        });
    }
}