package com.example.it_management.ui.Clients.FragmentDetail.ClientTimeLog;

import android.os.Bundle;

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
import com.example.it_management.ui.Clients.FragmentDetail.ClientTickets.ClientsTicketsAdapterData;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClientsTimeLogFragment extends Fragment {
    private RecyclerView rvTimeLog;
    private RecyclerView.Adapter adTimeLog;
    private RecyclerView.LayoutManager lmTimeLog;
    private List<ClientsTimeLogModel> clientsTimeLogModelList = new ArrayList<>();
    private int id;
    private TextView noData;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_clients_time_log, container, false);
        rvTimeLog = v.findViewById(R.id.rv_client_timelog);
        lmTimeLog = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        rvTimeLog.setLayoutManager(lmTimeLog);
        id = Integer.parseInt(getArguments().getString("idClient"));
        noData = v.findViewById(R.id.no_data_timelog);
        noData.setVisibility(View.GONE);
        tampildata();
        return v;
    }

    private void tampildata() {
        BaseApiService mApiService = UtilsApi.getApiService();
        Call<ClientsTimeLogResponseModel> tampil = mApiService.basGetTimeLogClient(id);
        tampil.enqueue(new Callback<ClientsTimeLogResponseModel>() {
            @Override
            public void onResponse(Call<ClientsTimeLogResponseModel> call, Response<ClientsTimeLogResponseModel> response) {
                clientsTimeLogModelList = response.body().getData();
                if (clientsTimeLogModelList.isEmpty()){
                    rvTimeLog.setVisibility(View.GONE);
                    noData.setVisibility(View.VISIBLE);
                }else {
                    adTimeLog = new ClientsTimeLogAdapterData(getContext(),clientsTimeLogModelList);
                    rvTimeLog.setAdapter(adTimeLog);
                    adTimeLog.notifyDataSetChanged();

                    rvTimeLog.setVisibility(View.VISIBLE);
                    noData.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<ClientsTimeLogResponseModel> call, Throwable t) {
                Toast.makeText(getActivity(), "Gagal Menghubungkan Server pesan : "+t, Toast.LENGTH_SHORT).show();

            }
        });
    }
}