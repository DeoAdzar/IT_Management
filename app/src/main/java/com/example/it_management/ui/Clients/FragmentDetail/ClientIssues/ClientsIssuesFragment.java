package com.example.it_management.ui.Clients.FragmentDetail.ClientIssues;

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
import com.example.it_management.ui.Clients.FragmentDetail.ClientFiles.ClientsFilesAdapterData;

import java.util.ArrayList;
import java.util.List;

import okhttp3.internal.Util;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClientsIssuesFragment extends Fragment {
    private RecyclerView rvIssues;
    private RecyclerView.Adapter adIssues;
    private RecyclerView.LayoutManager lmIssues;
    private TextView noData;
    private List<ClientsIssuesModel> clientsIssuesModelList = new ArrayList<>();
    private int id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_clients_issues, container, false);
        rvIssues = v.findViewById(R.id.rv_client_issues);
        lmIssues = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        rvIssues.setLayoutManager(lmIssues);
        id = Integer.parseInt(getArguments().getString("idClient"));
        noData = v.findViewById(R.id.no_data_issues);
        noData.setVisibility(View.GONE);
        TampilDataIssues();
        return v;
    }

    private void TampilDataIssues() {
        BaseApiService mApiService = UtilsApi.getApiService();
        Call<ClientsIssuesResponseModel> tampildata = mApiService.basGetIssuesClient(id);
        tampildata.enqueue(new Callback<ClientsIssuesResponseModel>() {
            @Override
            public void onResponse(Call<ClientsIssuesResponseModel> call, Response<ClientsIssuesResponseModel> response) {
                clientsIssuesModelList = response.body().getData();
                if (clientsIssuesModelList.isEmpty()){
                    rvIssues.setVisibility(View.GONE);
                    noData.setVisibility(View.VISIBLE);
                }else {
                    adIssues = new ClientsIssuesAdapterData(getContext(),clientsIssuesModelList);
                    rvIssues.setAdapter(adIssues);
                    adIssues.notifyDataSetChanged();

                    rvIssues.setVisibility(View.VISIBLE);
                    noData.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<ClientsIssuesResponseModel> call, Throwable t) {
                Toast.makeText(getActivity(), "Gagal Menghubungkan Server pesan : "+t, Toast.LENGTH_SHORT).show();
            }
        });
    }
}