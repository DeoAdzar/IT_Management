package com.example.it_management.ui.People.Staff;

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

import com.example.it_management.ui.People.Roles.RolesAdapterData;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class StaffFragment extends Fragment {
    private RecyclerView rvStaff;
    private RecyclerView.Adapter adStaff;
    private RecyclerView.LayoutManager lmStaff;
    private List<StaffModel> StaffModelList = new ArrayList<>();
    private SwipeRefreshLayout srStaff;
    private FloatingActionButton fabStaff;
    private TextView totalStaff,nodata;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_staff, container, false);
        rvStaff = v.findViewById(R.id.rv_staff);
        lmStaff = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        rvStaff.setLayoutManager(lmStaff);
        srStaff = v.findViewById(R.id.sr_staff);
        fabStaff = v.findViewById(R.id.fab_add_staff);
        totalStaff = v.findViewById(R.id.total_staff);
        nodata = v.findViewById(R.id.no_data_staff);
        nodata.setVisibility(View.GONE);
        tampilDataStaff();
        refresh();
        return v;
    }
    private void refresh(){
        srStaff.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                tampilDataStaff();
                srStaff.setRefreshing(false);
                Toast.makeText(getActivity(), "Data have been updated", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void tampilDataStaff() {
        BaseApiService mApiService = UtilsApi.getApiService();
        Call<StaffResponseModel> tampil = mApiService.basGetStaff();
        tampil.enqueue(new Callback<StaffResponseModel>() {
            @Override
            public void onResponse(Call<StaffResponseModel> call, Response<StaffResponseModel> response) {
                StaffModelList = response.body().getData();
                if (StaffModelList.isEmpty()){
                    rvStaff.setVisibility(View.GONE);
                    nodata.setVisibility(View.VISIBLE);
                }else {
                    adStaff = new StaffAdapterData(getContext(), StaffModelList);
                    totalStaff.setText(String.valueOf(adStaff.getItemCount())+ " Staff");
                    rvStaff.setAdapter(adStaff);
                    adStaff.notifyDataSetChanged();

                    rvStaff.setVisibility(View.VISIBLE);
                    nodata.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<StaffResponseModel> call, Throwable t) {
                Toast.makeText(getActivity(), "Gagal Menghubungkan Server pesan : "+t, Toast.LENGTH_SHORT).show();
            }
        });
    }
}