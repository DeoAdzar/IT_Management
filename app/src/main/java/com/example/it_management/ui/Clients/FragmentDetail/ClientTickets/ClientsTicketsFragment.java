package com.example.it_management.ui.Clients.FragmentDetail.ClientTickets;

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
import com.example.it_management.ui.Clients.FragmentDetail.ClientProjects.ClientsProjectsAdapterData;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClientsTicketsFragment extends Fragment {
    private RecyclerView rvTicket;
    private RecyclerView.Adapter adTicket;
    private RecyclerView.LayoutManager lmTicket;
    private List<ClientsTicketsModel> clientsTicketsModelList = new ArrayList<>();
    private int id;
    private TextView nodata;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_clients_tickets, container, false);
        rvTicket = v.findViewById(R.id.rv_client_ticket);
        lmTicket = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        rvTicket.setLayoutManager(lmTicket);
        id = Integer.parseInt(getArguments().getString("idClient"));
        nodata = v.findViewById(R.id.no_data_ticket);
        nodata.setVisibility(View.GONE);
        TampilTicket();
        return v;
    }

    private void TampilTicket() {
        BaseApiService mApiService = UtilsApi.getApiService();
        Call<ClientsTicketsResponseModel> tampil = mApiService.basGetTicketClient(id);
        tampil.enqueue(new Callback<ClientsTicketsResponseModel>() {
            @Override
            public void onResponse(Call<ClientsTicketsResponseModel> call, Response<ClientsTicketsResponseModel> response) {
                clientsTicketsModelList = response.body().getData();
                if (clientsTicketsModelList.isEmpty()){
                    rvTicket.setVisibility(View.GONE);
                    nodata.setVisibility(View.VISIBLE);
                }else {
                    adTicket = new ClientsTicketsAdapterData(getContext(),clientsTicketsModelList);
                    rvTicket.setAdapter(adTicket);
                    adTicket.notifyDataSetChanged();

                    rvTicket.setVisibility(View.VISIBLE);
                    nodata.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<ClientsTicketsResponseModel> call, Throwable t) {
                Toast.makeText(getActivity(), "Gagal Menghubungkan Server pesan : "+t, Toast.LENGTH_SHORT).show();
            }
        });
    }
}