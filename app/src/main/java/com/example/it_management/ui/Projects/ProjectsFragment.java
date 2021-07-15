package com.example.it_management.ui.Projects;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.it_management.API.BaseApiService;
import com.example.it_management.API.UtilsApi;
import com.example.it_management.R;
import com.example.it_management.ui.AddActivity.AddLicense;
import com.example.it_management.ui.AddActivity.AddProject;
import com.example.it_management.ui.Inventory.Licences.LicensesAdapterData;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProjectsFragment extends Fragment {
    private RecyclerView rvProjects;
    private RecyclerView.Adapter adProjects;
    private RecyclerView.LayoutManager lmProjects;
    private List<ProjectsModel> projectsModelList = new ArrayList<>();
    private SwipeRefreshLayout refreshLayout;
    private FloatingActionButton addProjects;
    private TextView totalProject, nodata;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_projects, container, false);
        refreshLayout = v.findViewById(R.id.sr_projects);
        rvProjects = v.findViewById(R.id.rv_project);
        addProjects = v.findViewById(R.id.fab_add_project);
        lmProjects = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        rvProjects.setLayoutManager(lmProjects);
        totalProject= v.findViewById(R.id.totalProject);
        nodata = v.findViewById(R.id.no_data_project);
        nodata.setVisibility(View.GONE);
        TampilDataProjects();
        addProject();
        hilang();
        refresh();
        return v;
    }
    private void addProject() {
        addProjects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), AddProject.class);
                startActivity(i);
                onStop();
            }
        });
    }

    private void hilang(){
        rvProjects.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0 && addProjects.getVisibility() == View.VISIBLE) {
                    addProjects.hide();
                } else if (dy < 0 && addProjects.getVisibility() != View.VISIBLE) {
                    addProjects.show();
                }
            }
        });
    }
    @Override
    public void onResume() {
        super.onResume();
        TampilDataProjects();
    }

        private void refresh(){
            refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    TampilDataProjects();
                    refreshLayout.setRefreshing(false);
                    Toast.makeText(getActivity(), "Data have been updated", Toast.LENGTH_SHORT).show();
                }
            });
        }

    private void TampilDataProjects() {
        BaseApiService mApiService = UtilsApi.getApiService();
        Call<ProjectsResponseModel> tampilData = mApiService.basGetProjects();
        tampilData.enqueue(new Callback<ProjectsResponseModel>() {
            @Override
            public void onResponse(Call<ProjectsResponseModel> call, Response<ProjectsResponseModel> response) {
                projectsModelList = response.body().getData();
                if (projectsModelList.isEmpty()){
                    rvProjects.setVisibility(View.GONE);
                    nodata.setVisibility(View.VISIBLE);
                }else {
                    adProjects = new ProjectsAdapterData(getContext(), projectsModelList);
                    totalProject.setText(String.valueOf(adProjects.getItemCount())+ " Project");
                    rvProjects.setAdapter(adProjects);
                    adProjects.notifyDataSetChanged();
                    rvProjects.setVisibility(View.VISIBLE);
                    nodata.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<ProjectsResponseModel> call, Throwable t) {
                Toast.makeText(getActivity(), "Gagal Menghubungkan Server pesan : "+t, Toast.LENGTH_SHORT).show();
            }
        });
    }
}