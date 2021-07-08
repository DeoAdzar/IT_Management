package com.example.it_management.ui.Inventory.Assets.FragmentDetail.TicketsAssets;

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
import com.example.it_management.ui.Clients.FragmentDetail.ClientTickets.ClientsTicketsModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TicketsAssetsFragment extends Fragment {
    private RecyclerView rvTicketAsset;
    private RecyclerView.Adapter adTicketAsset;
    private RecyclerView.LayoutManager lmTicketAsset;
    private List<AssetTicketsModel> assetTicketsModelList = new ArrayList<>();
    private int id;
    private TextView nodata;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_assets_ticket, container, false);
        rvTicketAsset = v.findViewById(R.id.rv_asset_ticket);
        lmTicketAsset = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        rvTicketAsset.setLayoutManager(lmTicketAsset);
        id = Integer.parseInt(getArguments().getString("idAssets"));
        nodata = v.findViewById(R.id.no_data_ticket_asset);
        nodata.setVisibility(View.GONE);
        TampilTicket();
        return v;
    }

    private void TampilTicket() {
        BaseApiService mApiService = UtilsApi.getApiService();
        Call<AssetTicketsResponseModel> tampil = mApiService.basGetTicketAssets(id);
        tampil.enqueue(new Callback<AssetTicketsResponseModel>() {
            @Override
            public void onResponse(Call<AssetTicketsResponseModel> call, Response<AssetTicketsResponseModel> response) {
                assetTicketsModelList = response.body().getData();
                if (assetTicketsModelList.isEmpty()){
                    rvTicketAsset.setVisibility(View.GONE);
                    nodata.setVisibility(View.VISIBLE);
                }else {
                    adTicketAsset = new AssetTicketsAdapterData(getContext(),assetTicketsModelList);
                    rvTicketAsset.setAdapter(adTicketAsset);
                    adTicketAsset.notifyDataSetChanged();

                    rvTicketAsset.setVisibility(View.VISIBLE);
                    nodata.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<AssetTicketsResponseModel> call, Throwable t) {
                Toast.makeText(getActivity(), "Gagal Menghubungkan Server pesan : "+t, Toast.LENGTH_SHORT).show();
            }
        });
    }
}