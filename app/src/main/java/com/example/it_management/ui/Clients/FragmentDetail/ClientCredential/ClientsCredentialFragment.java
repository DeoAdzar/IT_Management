package com.example.it_management.ui.Clients.FragmentDetail.ClientCredential;

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

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClientsCredentialFragment extends Fragment {
    private RecyclerView rvClientCredential;
    private RecyclerView.Adapter adClientCredential;
    private RecyclerView.LayoutManager lmClientCredential;
    private List<ClientCredentialModel> credentialModelList = new ArrayList<>();
    private Integer id;
    TextView tvnodata;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_clients_credential, container, false);
        rvClientCredential = v.findViewById(R.id.rv_client_credential);
        lmClientCredential  = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        rvClientCredential.setLayoutManager(lmClientCredential);
        id = Integer.parseInt(getArguments().getString("idClient"));
        tvnodata = v.findViewById(R.id.no_data_credential);
        tvnodata.setVisibility(View.GONE);
        tampilDataClientCredential();
        return v;
    }

    private void tampilDataClientCredential() {
        BaseApiService mApiService = UtilsApi.getApiService();
        Call<ClientCredentialResponseModel> tampilDataCredential = mApiService.basGetCredentialClient(id);
        tampilDataCredential.enqueue(new Callback<ClientCredentialResponseModel>() {
            @Override
            public void onResponse(Call<ClientCredentialResponseModel> call, Response<ClientCredentialResponseModel> response) {
                credentialModelList = response.body().getData();
                if (credentialModelList.isEmpty()){
                    rvClientCredential.setVisibility(View.GONE);
                    tvnodata.setVisibility(View.VISIBLE);
                }else {
                    adClientCredential = new ClientCredentialAdapterData(getContext(), credentialModelList);
                    rvClientCredential.setAdapter(adClientCredential);
                    adClientCredential.notifyDataSetChanged();

                    rvClientCredential.setVisibility(View.VISIBLE);
                    tvnodata.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<ClientCredentialResponseModel> call, Throwable t) {
                Toast.makeText(getActivity(), "Gagal Menghubungkan Server pesan : "+t, Toast.LENGTH_SHORT).show();
            }
        });
    }
}