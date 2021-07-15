package com.example.it_management.ui.Clients.FragmentDetail.ClientProjects;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.it_management.API.BaseApiService;
import com.example.it_management.API.UtilsApi;
import com.example.it_management.R;
import com.example.it_management.ui.AddActivity.AddProject;
import com.example.it_management.ui.Clients.FragmentDetail.ClientLicense.ClientsLicenseAdaptorData;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClientsProjectsFragment extends Fragment {
    private RecyclerView rvProject;
    private RecyclerView.Adapter adProject;
    private RecyclerView.LayoutManager lmProject;
    private List<ClientsProjectsModel> clientsProjectsModelList = new ArrayList<>();
    private int id;
    FloatingActionButton fabAddClientProject;
    private TextView noData;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_clients_projects, container, false);
        rvProject = v.findViewById(R.id.rv_client_project);
        lmProject = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        rvProject.setLayoutManager(lmProject);
        fabAddClientProject = v.findViewById(R.id.fab_add_project_client);
        id = Integer.parseInt(getArguments().getString("idClient"));
        noData = v.findViewById(R.id.no_data_project);
        noData.setVisibility(View.GONE);
        addProject();
        hilang();
        TampilDataProject();
        return v;
    }
    private void addProject() {
        fabAddClientProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), AddProject.class);
                startActivity(i);
                onStop();
            }
        });
    }

    private void hilang(){
        rvProject.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0 && fabAddClientProject.getVisibility() == View.VISIBLE) {
                    fabAddClientProject.hide();
                } else if (dy < 0 && fabAddClientProject.getVisibility() != View.VISIBLE) {
                    fabAddClientProject.show();
                }
            }
        });
    }
    @Override
    public void onResume() {
        super.onResume();
        TampilDataProject();
    }

    private void TampilDataProject() {
        BaseApiService mApiService = UtilsApi.getApiService();
        Call<ClientsProjectsResponseModel> tampil = mApiService.basGetProjectClient(id);
        tampil.enqueue(new Callback<ClientsProjectsResponseModel>() {
            @Override
            public void onResponse(Call<ClientsProjectsResponseModel> call, Response<ClientsProjectsResponseModel> response) {
                clientsProjectsModelList = response.body().getData();
                if (clientsProjectsModelList.isEmpty()){
                    rvProject.setVisibility(View.GONE);
                    noData.setVisibility(View.VISIBLE);
                }else {
                    adProject = new ClientsProjectsAdapterData(getContext(),clientsProjectsModelList);
                    rvProject.setAdapter(adProject);
                    adProject.notifyDataSetChanged();

                    rvProject.setVisibility(View.VISIBLE);
                    noData.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<ClientsProjectsResponseModel> call, Throwable t) {
                Toast.makeText(getActivity(), "Gagal Menghubungkan Server pesan : "+t, Toast.LENGTH_SHORT).show();
            }
        });
    }
}