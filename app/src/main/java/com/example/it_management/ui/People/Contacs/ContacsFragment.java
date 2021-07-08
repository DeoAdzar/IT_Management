package com.example.it_management.ui.People.Contacs;

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
import com.example.it_management.ui.Issues.ActiveIssues.ActiveIssuesAdapterData;
import com.example.it_management.ui.Projects.ProjectsModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ContacsFragment extends Fragment {
    private RecyclerView rvContacts;
    private RecyclerView.Adapter adContacts;
    private RecyclerView.LayoutManager lmContacts;
    private List<ContacsModel> contactsModelList = new ArrayList<>();
    private SwipeRefreshLayout srContacts;
    private FloatingActionButton fabContacts;
    private TextView totalContacts,nodata;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_contacs, container, false);
        rvContacts = v.findViewById(R.id.rv_contacts);
        lmContacts = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        rvContacts.setLayoutManager(lmContacts);
        srContacts = v.findViewById(R.id.sr_contacts);
        fabContacts = v.findViewById(R.id.fab_add_contacts);
        totalContacts = v.findViewById(R.id.total_contacts);
        nodata = v.findViewById(R.id.no_data_contacts);
        nodata.setVisibility(View.GONE);
        tampilDataContacts();
        refresh();
        return v;
    }
    private void refresh(){
        srContacts.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                tampilDataContacts();
                srContacts.setRefreshing(false);
                Toast.makeText(getActivity(), "Data have been updated", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void tampilDataContacts() {
        BaseApiService mApiService = UtilsApi.getApiService();
        Call<ContacsResponseModel> tampil = mApiService.basGetContacs();
        tampil.enqueue(new Callback<ContacsResponseModel>() {
            @Override
            public void onResponse(Call<ContacsResponseModel> call, Response<ContacsResponseModel> response) {
                contactsModelList = response.body().getData();
                if (contactsModelList.isEmpty()){
                    rvContacts.setVisibility(View.GONE);
                    nodata.setVisibility(View.VISIBLE);
                }else {
                    adContacts = new ContacsAdapterData(getContext(), contactsModelList);
                    totalContacts.setText(String.valueOf(adContacts.getItemCount())+ " Contacts");
                    rvContacts.setAdapter(adContacts);
                    adContacts.notifyDataSetChanged();

                    rvContacts.setVisibility(View.VISIBLE);
                    nodata.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<ContacsResponseModel> call, Throwable t) {
                Toast.makeText(getActivity(), "Gagal Menghubungkan Server pesan : "+t, Toast.LENGTH_SHORT).show();
            }
        });
    }
}