package com.example.it_management.ui.Tickets.AllTickets;

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
import com.example.it_management.ui.Tickets.AllTickets.AllTicketsAdapterData;
import com.example.it_management.ui.Tickets.AllTickets.AllTicketsResponseModel;
import com.example.it_management.ui.Tickets.AllTickets.AllTicketsModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AllTicketsFragment extends Fragment {
    private RecyclerView rvAllTickets;
    private RecyclerView.Adapter adAllTickets;
    private RecyclerView.LayoutManager lmAllTickets;
    private List<AllTicketsModel> AllTicketsModelList = new ArrayList<>();
    private SwipeRefreshLayout srAllTickets;
    private FloatingActionButton fabAllTickets;
    private TextView totalAllTickets,nodata;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_all_tickets, container, false);
        rvAllTickets = v.findViewById(R.id.rv_all_tickets);
        lmAllTickets = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        rvAllTickets.setLayoutManager(lmAllTickets);
        srAllTickets = v.findViewById(R.id.sr_all_tickets);
        fabAllTickets = v.findViewById(R.id.fab_add_all_tickets);
        totalAllTickets = v.findViewById(R.id.total_all_tickets);
        nodata = v.findViewById(R.id.no_data_all_tickets);
        nodata.setVisibility(View.GONE);
        tampilDataAllTickets();
        refresh();
        return v;
    }
    private void refresh(){
        srAllTickets.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                tampilDataAllTickets();
                srAllTickets.setRefreshing(false);
                Toast.makeText(getActivity(), "Data have been updated", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void tampilDataAllTickets() {
        BaseApiService mApiService = UtilsApi.getApiService();
        Call<AllTicketsResponseModel> tampil = mApiService.basGetAllTickets();
        tampil.enqueue(new Callback<AllTicketsResponseModel>() {
            @Override
            public void onResponse(Call<AllTicketsResponseModel> call, Response<AllTicketsResponseModel> response) {
                AllTicketsModelList = response.body().getData();
                if (AllTicketsModelList.isEmpty()){
                    rvAllTickets.setVisibility(View.GONE);
                    nodata.setVisibility(View.VISIBLE);
                }else {
                    adAllTickets = new AllTicketsAdapterData(getContext(), AllTicketsModelList);
                    totalAllTickets.setText(String.valueOf(adAllTickets.getItemCount())+ " Tickets");
                    rvAllTickets.setAdapter(adAllTickets);
                    adAllTickets.notifyDataSetChanged();

                    rvAllTickets.setVisibility(View.VISIBLE);
                    nodata.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<AllTicketsResponseModel> call, Throwable t) {
                Toast.makeText(getActivity(), "Gagal Menghubungkan Server pesan : "+t, Toast.LENGTH_SHORT).show();
            }
        });
    }
}