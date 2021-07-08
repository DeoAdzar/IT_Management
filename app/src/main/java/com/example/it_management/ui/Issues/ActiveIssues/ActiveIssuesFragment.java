package com.example.it_management.ui.Issues.ActiveIssues;

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
import com.example.it_management.ui.Tickets.ActiveTickets.ActiveTicketsAdapterData;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActiveIssuesFragment extends Fragment {
    private RecyclerView rvActiveIssues;
    private RecyclerView.Adapter adIssuesActive;
    private RecyclerView.LayoutManager lmActiveIssues;
    private List<ActiveIssuesModel> activeIssuesModelList = new ArrayList<>();
    private SwipeRefreshLayout srActiveIssues;
    private FloatingActionButton fabActiveIssues;
    private TextView totalActiveIssues,nodata;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_active_issues, container, false);
        rvActiveIssues = v.findViewById(R.id.rv_active_issues);
        lmActiveIssues = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        rvActiveIssues.setLayoutManager(lmActiveIssues);
        srActiveIssues = v.findViewById(R.id.sr_active_issues);
        fabActiveIssues = v.findViewById(R.id.fab_add_active_issues);
        totalActiveIssues = v.findViewById(R.id.total_active_issues);
        nodata = v.findViewById(R.id.no_data_active_issues);
        nodata.setVisibility(View.GONE);
        tampilDataActiveIssues();
        refresh();
        return v;
    }
    private void refresh(){
        srActiveIssues.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                tampilDataActiveIssues();
                srActiveIssues.setRefreshing(false);
                Toast.makeText(getActivity(), "Data have been updated", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void tampilDataActiveIssues() {
        BaseApiService mApiservice = UtilsApi.getApiService();
        Call<ActiveIssuesResponseModel> tampil = mApiservice.basGetActiveIssues();
        tampil.enqueue(new Callback<ActiveIssuesResponseModel>() {
            @Override
            public void onResponse(Call<ActiveIssuesResponseModel> call, Response<ActiveIssuesResponseModel> response) {
                activeIssuesModelList = response.body().getData();
                if (activeIssuesModelList.isEmpty()){
                    rvActiveIssues.setVisibility(View.GONE);
                    nodata.setVisibility(View.VISIBLE);
                }else {
                    adIssuesActive = new ActiveIssuesAdapterData(getContext(), activeIssuesModelList);
                    totalActiveIssues.setText(String.valueOf(adIssuesActive.getItemCount())+ " Active");
                    rvActiveIssues.setAdapter(adIssuesActive);
                    adIssuesActive.notifyDataSetChanged();

                    rvActiveIssues.setVisibility(View.VISIBLE);
                    nodata.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<ActiveIssuesResponseModel> call, Throwable t) {
                Toast.makeText(getActivity(), "Gagal Menghubungkan Server pesan : "+t, Toast.LENGTH_SHORT).show();
            }
        });
    }
}