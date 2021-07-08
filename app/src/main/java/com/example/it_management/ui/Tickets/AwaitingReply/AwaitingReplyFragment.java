package com.example.it_management.ui.Tickets.AwaitingReply;

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

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AwaitingReplyFragment extends Fragment {
    private RecyclerView rvAwaitingReply;
    private RecyclerView.Adapter adAwaitingReply;
    private RecyclerView.LayoutManager lmAwaitingReply;
    private List<AwaitingReplyModel> AwaitingReplyModelList = new ArrayList<>();
    private SwipeRefreshLayout srAwaitingReply;
    private FloatingActionButton fabAwaitingReply;
    private TextView totalAwaitingReply,nodata;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_awaiting_reply, container, false);
        rvAwaitingReply = v.findViewById(R.id.rv_awaiting_reply);
        lmAwaitingReply = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        rvAwaitingReply.setLayoutManager(lmAwaitingReply);
        srAwaitingReply = v.findViewById(R.id.sr_awaiting_reply);
        fabAwaitingReply = v.findViewById(R.id.fab_add_awaiting_reply);
        totalAwaitingReply = v.findViewById(R.id.totalAwaitingReply);
        nodata = v.findViewById(R.id.no_data_awaiting_reply);
        nodata.setVisibility(View.GONE);
        tampilDataAwaitingReply();
        refresh();
        return v;
    }
    private void refresh(){
        srAwaitingReply.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                tampilDataAwaitingReply();
                srAwaitingReply.setRefreshing(false);
                Toast.makeText(getActivity(), "Data have been updated", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void tampilDataAwaitingReply() {
        BaseApiService mApiService = UtilsApi.getApiService();
        Call<AwaitingReplyResponseModel> tampil = mApiService.basGetAwaitingReply();
        tampil.enqueue(new Callback<AwaitingReplyResponseModel>() {
            @Override
            public void onResponse(Call<AwaitingReplyResponseModel> call, Response<AwaitingReplyResponseModel> response) {
                AwaitingReplyModelList = response.body().getData();
                if (AwaitingReplyModelList.isEmpty()){
                    rvAwaitingReply.setVisibility(View.GONE);
                    nodata.setVisibility(View.VISIBLE);
                }else {
                    adAwaitingReply = new AwaitingReplyAdapterData(getContext(), AwaitingReplyModelList);
                    totalAwaitingReply.setText(String.valueOf(adAwaitingReply.getItemCount())+ " Reply");
                    rvAwaitingReply.setAdapter(adAwaitingReply);
                    adAwaitingReply.notifyDataSetChanged();

                    rvAwaitingReply.setVisibility(View.VISIBLE);
                    nodata.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<AwaitingReplyResponseModel> call, Throwable t) {
                Toast.makeText(getActivity(), "Gagal Menghubungkan Server pesan : "+t, Toast.LENGTH_SHORT).show();
            }
        });
    }
}