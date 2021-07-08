package com.example.it_management.ui.People.Roles;

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
import com.example.it_management.ui.People.Contacs.ContacsAdapterData;
import com.example.it_management.ui.People.Contacs.ContacsModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RolesFragment extends Fragment {
    private RecyclerView rvRoles;
    private RecyclerView.Adapter adRoles;
    private RecyclerView.LayoutManager lmRoles;
    private List<RolesModel> rolesModelList = new ArrayList<>();
    private SwipeRefreshLayout srRoles;
    private FloatingActionButton fabRoles;
    private TextView totalRoles,nodata;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_roles, container, false);
        rvRoles = v.findViewById(R.id.rv_roles);
        lmRoles = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        rvRoles.setLayoutManager(lmRoles);
        srRoles = v.findViewById(R.id.sr_roles);
        fabRoles = v.findViewById(R.id.fab_add_roles);
        totalRoles = v.findViewById(R.id.total_roles);
        nodata = v.findViewById(R.id.no_data_roles);
        nodata.setVisibility(View.GONE);
        tampilDataRoles();
        refresh();
        return v;
    }
    private void refresh(){
        srRoles.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                tampilDataRoles();
                srRoles.setRefreshing(false);
                Toast.makeText(getActivity(), "Data have been updated", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void tampilDataRoles() {
        BaseApiService mApiService = UtilsApi.getApiService();
        Call<RolesResponseModel> tampil = mApiService.basGetRoles();
        tampil.enqueue(new Callback<RolesResponseModel>() {
            @Override
            public void onResponse(Call<RolesResponseModel> call, Response<RolesResponseModel> response) {
                rolesModelList = response.body().getData();
                if (rolesModelList.isEmpty()){
                    rvRoles.setVisibility(View.GONE);
                    nodata.setVisibility(View.VISIBLE);
                }else {
                    adRoles = new RolesAdapterData(getContext(), rolesModelList);
                    totalRoles.setText(String.valueOf(adRoles.getItemCount())+ " Roles");
                    rvRoles.setAdapter(adRoles);
                    adRoles.notifyDataSetChanged();

                    rvRoles.setVisibility(View.VISIBLE);
                    nodata.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<RolesResponseModel> call, Throwable t) {
                Toast.makeText(getActivity(), "Gagal Menghubungkan Server pesan : "+t, Toast.LENGTH_SHORT).show();
            }
        });
    }
}