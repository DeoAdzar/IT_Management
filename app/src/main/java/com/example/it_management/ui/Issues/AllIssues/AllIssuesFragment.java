package com.example.it_management.ui.Issues.AllIssues;

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
import com.example.it_management.ui.AddActivity.AddIssues;
import com.example.it_management.ui.Issues.ActiveIssues.ActiveIssuesAdapterData;
import com.example.it_management.ui.Issues.AllIssues.AllIssuesModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AllIssuesFragment extends Fragment {
    private RecyclerView rvAllIssues;
    private RecyclerView.Adapter adIssuesAll;
    private RecyclerView.LayoutManager lmAllIssues;
    private List<AllIssuesModel> allIssuesModelList = new ArrayList<>();
    private SwipeRefreshLayout srAllIssues;
    private FloatingActionButton fabAllIssues;
    private TextView totalAllIssues,nodata;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_all_isues, container, false);
        rvAllIssues = v.findViewById(R.id.rv_all_issues);
        lmAllIssues = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        rvAllIssues.setLayoutManager(lmAllIssues);
        srAllIssues = v.findViewById(R.id.sr_all_issues);
        fabAllIssues = v.findViewById(R.id.fab_add_all_issues);
        totalAllIssues = v.findViewById(R.id.total_all_issues);
        nodata = v.findViewById(R.id.no_data_all_issues);
        nodata.setVisibility(View.GONE);
        tampilDataAllIssues();
        hilang();
        addIssues();
        refresh();
        return v;

    }
    @Override
    public void onResume() {
        super.onResume();
        tampilDataAllIssues();
    }

    private void hilang(){
        rvAllIssues.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0 && fabAllIssues.getVisibility() == View.VISIBLE) {
                    fabAllIssues.hide();
                } else if (dy < 0 && fabAllIssues.getVisibility() != View.VISIBLE) {
                    fabAllIssues.show();
                }
            }
        });
    }
    private void addIssues(){
        fabAllIssues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), AddIssues.class);
                startActivity(i);
                onStop();
            }
        });
    }
    private void refresh(){
        srAllIssues.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                tampilDataAllIssues();
                srAllIssues.setRefreshing(false);
                Toast.makeText(getActivity(), "Data have been updated", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void tampilDataAllIssues() {
        BaseApiService mApiService = UtilsApi.getApiService();
        Call<AllIssuesResponseModel> tampil = mApiService.basGetAllIssues();
        tampil.enqueue(new Callback<AllIssuesResponseModel>() {
            @Override
            public void onResponse(Call<AllIssuesResponseModel> call, Response<AllIssuesResponseModel> response) {
                allIssuesModelList = response.body().getData();
                if (allIssuesModelList.isEmpty()){
                    rvAllIssues.setVisibility(View.GONE);
                    nodata.setVisibility(View.VISIBLE);
                }else {
                    adIssuesAll = new AllIssuesAdapterData(getContext(), allIssuesModelList);
                    totalAllIssues.setText(String.valueOf(adIssuesAll.getItemCount())+ " Issues");
                    rvAllIssues.setAdapter(adIssuesAll);
                    adIssuesAll.notifyDataSetChanged();

                    rvAllIssues.setVisibility(View.VISIBLE);
                    nodata.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<AllIssuesResponseModel> call, Throwable t) {
                Toast.makeText(getActivity(), "Gagal Menghubungkan Server pesan : "+t, Toast.LENGTH_SHORT).show();

            }
        });
    }
}