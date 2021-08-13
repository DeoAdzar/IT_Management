package com.example.it_management.ui.Clients;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.it_management.API.BaseApiService;
import com.example.it_management.API.RetrofitClient;
import com.example.it_management.API.UtilsApi;
import com.example.it_management.R;
import com.example.it_management.ui.Dashboard.DashboardFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClientsFragment extends Fragment {
    private RecyclerView rvClient;
    private RecyclerView.Adapter adClient;
    private RecyclerView.LayoutManager lmClient;
    private List<ClientsModel> listClient = new ArrayList<>();
    private SwipeRefreshLayout swipeRefreshLayout;
    private FloatingActionButton fabClients;
    private TextView totalClient;
    public static ClientsFragment cf;
    String a;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_clients, container, false);
        rvClient = v.findViewById(R.id.rv_client);
        swipeRefreshLayout = v.findViewById(R.id.sr_client);
        fabClients = v.findViewById(R.id.fab_add_client);
        lmClient = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        rvClient.setLayoutManager(lmClient);
        totalClient = v.findViewById(R.id.totalClient);
        cf=this;
        hilang();
        tampilDataClient();
        refresh();
        addClients();
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        tampilDataClient();
    }

    private void hilang(){
        rvClient.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0 && fabClients.getVisibility() == View.VISIBLE) {
                    fabClients.hide();
                } else if (dy < 0 && fabClients.getVisibility() != View.VISIBLE) {
                    fabClients.show();
                }
            }
        });
    }
    private void addClients(){
        fabClients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),AddClientsActivity.class);
                startActivity(i);
                onStop();
            }
        });
    }
    public void refresh(){
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                tampilDataClient();
                swipeRefreshLayout.setRefreshing(false);
                Toast.makeText(getActivity(), "Data have been updated", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void tampilDataClient(){
        BaseApiService mApiService = UtilsApi.getApiService();
        Call<ClientsResponseModel> tampilData = mApiService.basClientData();
        tampilData.enqueue(new Callback<ClientsResponseModel>() {
            @Override
            public void onResponse(Call<ClientsResponseModel> call, Response<ClientsResponseModel> response) {
                listClient = response.body().getdata();
                adClient = new ClientsAdapterData(getContext(), listClient);
                totalClient.setText(String.valueOf(adClient.getItemCount())+ " Clients");
                rvClient.setAdapter(adClient);
                adClient.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ClientsResponseModel> call, Throwable t) {
                Toast.makeText(getActivity(), "Gagal Menghubungkan Server pesan : "+t, Toast.LENGTH_SHORT).show();
            }
        });
    }
}