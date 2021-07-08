package com.example.it_management.ui.Clients.FragmentDetail.ClientUser;

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
import com.example.it_management.ui.Clients.FragmentDetail.ClientTimeLog.ClientsTimeLogAdapterData;

import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClientsUserFragment extends Fragment {
    private RecyclerView rvUser;
    private RecyclerView.Adapter adUser;
    private RecyclerView.LayoutManager lmUser;
    private List<ClientsUserModel> clientsUserModelList = new ArrayList<>();
    private int id;
    private TextView nodata;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_clients_user, container, false);
        rvUser = v.findViewById(R.id.rv_client_user);
        lmUser = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        rvUser.setLayoutManager(lmUser);
        id = Integer.parseInt(getArguments().getString("idClient"));
        nodata = v.findViewById(R.id.no_data_user);
        nodata.setVisibility(View.GONE);
        tampilData();
        return v;
    }

    private void tampilData() {
        BaseApiService mApiService = UtilsApi.getApiService();
        Call<ClientsUserResponseModel> tampil = mApiService.basGetUserClient(id);
        tampil.enqueue(new Callback<ClientsUserResponseModel>() {
            @Override
            public void onResponse(Call<ClientsUserResponseModel> call, Response<ClientsUserResponseModel> response) {
                clientsUserModelList = response.body().getData();
                if (clientsUserModelList.isEmpty()){
                    rvUser.setVisibility(View.GONE);
                    nodata.setVisibility(View.VISIBLE);
                }else {
                    adUser = new ClientsUserAdapterData(getContext(),clientsUserModelList);
                    rvUser.setAdapter(adUser);
                    adUser.notifyDataSetChanged();

                    rvUser.setVisibility(View.VISIBLE);
                    nodata.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<ClientsUserResponseModel> call, Throwable t) {
                Toast.makeText(getActivity(), "Gagal Menghubungkan Server pesan : "+t, Toast.LENGTH_SHORT).show();

            }
        });
    }
}