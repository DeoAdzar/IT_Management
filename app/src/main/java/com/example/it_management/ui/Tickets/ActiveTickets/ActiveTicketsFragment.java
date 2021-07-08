package com.example.it_management.ui.Tickets.ActiveTickets;

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
import com.example.it_management.ui.Tickets.ActiveTickets.ActiveTicketsModel;
import com.example.it_management.ui.Tickets.ActiveTickets.ActiveTicketsAdapterData;
import com.example.it_management.ui.Tickets.ActiveTickets.ActiveTicketsResponseModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ActiveTicketsFragment extends Fragment {
    private RecyclerView rvActiveTickets;
    private RecyclerView.Adapter adActiveTickets;
    private RecyclerView.LayoutManager lmActiveTickets;
    private List<ActiveTicketsModel> ActiveTicketsModelList = new ArrayList<>();
    private SwipeRefreshLayout srActiveTickets;
    private FloatingActionButton fabActiveTickets;
    private TextView totalActiveTickets,nodata;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_active_tickets, container, false);
        rvActiveTickets = v.findViewById(R.id.rv_active_tickets);
        lmActiveTickets = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        rvActiveTickets.setLayoutManager(lmActiveTickets);
        srActiveTickets = v.findViewById(R.id.sr_active_tickets);
        fabActiveTickets = v.findViewById(R.id.fab_add_active_tickets);
        totalActiveTickets = v.findViewById(R.id.total_active_tickets);
        nodata = v.findViewById(R.id.no_data_active_tickets);
        nodata.setVisibility(View.GONE);
        tampilDataActiveTickets();
        refresh();
        return v;
    }
    private void refresh(){
        srActiveTickets.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                tampilDataActiveTickets();
                srActiveTickets.setRefreshing(false);
                Toast.makeText(getActivity(), "Data have been updated", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void tampilDataActiveTickets() {
        BaseApiService mApiService = UtilsApi.getApiService();
        Call<ActiveTicketsResponseModel> tampil = mApiService.basGetActiveTickets();
        tampil.enqueue(new Callback<ActiveTicketsResponseModel>() {
            @Override
            public void onResponse(Call<ActiveTicketsResponseModel> call, Response<ActiveTicketsResponseModel> response) {
                ActiveTicketsModelList = response.body().getData();
                if (ActiveTicketsModelList.isEmpty()){
                    rvActiveTickets.setVisibility(View.GONE);
                    nodata.setVisibility(View.VISIBLE);
                }else {
                    adActiveTickets = new ActiveTicketsAdapterData(getContext(), ActiveTicketsModelList);
                    totalActiveTickets.setText(String.valueOf(adActiveTickets.getItemCount())+ " Active");
                    rvActiveTickets.setAdapter(adActiveTickets);
                    adActiveTickets.notifyDataSetChanged();

                    rvActiveTickets.setVisibility(View.VISIBLE);
                    nodata.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<ActiveTicketsResponseModel> call, Throwable t) {
                Toast.makeText(getActivity(), "Gagal Menghubungkan Server pesan : "+t, Toast.LENGTH_SHORT).show();
            }
        });
    }
}