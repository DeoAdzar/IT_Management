package com.example.it_management.ui.People.Users;

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


public class UsersFragment extends Fragment {
    private RecyclerView rvUser;
    private RecyclerView.Adapter adUser;
    private RecyclerView.LayoutManager lmUser;
    private List<UsersModel> userModelList = new ArrayList<>();
    private SwipeRefreshLayout srUser;
    private FloatingActionButton fabUser;
    private TextView totalUser,nodata;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_users, container, false);
        rvUser = v.findViewById(R.id.rv_user);
        lmUser = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        rvUser.setLayoutManager(lmUser);
        srUser = v.findViewById(R.id.sr_user);
        fabUser = v.findViewById(R.id.fab_add_user);
        totalUser = v.findViewById(R.id.total_user);
        nodata = v.findViewById(R.id.no_data_user);
        nodata.setVisibility(View.GONE);
        tampilDataUser();
        refresh();
        return v;
    }
    private void refresh(){
        srUser.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                tampilDataUser();
                srUser.setRefreshing(false);
                Toast.makeText(getActivity(), "Data have been updated", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void tampilDataUser() {
        BaseApiService mApiSevice = UtilsApi.getApiService();
        Call<UsersResponseModel> tampil = mApiSevice.basGetUser();
        tampil.enqueue(new Callback<UsersResponseModel>() {
            @Override
            public void onResponse(Call<UsersResponseModel> call, Response<UsersResponseModel> response) {
                userModelList = response.body().getData();
                if (userModelList.isEmpty()){
                    rvUser.setVisibility(View.GONE);
                    nodata.setVisibility(View.VISIBLE);
                }else {
                    adUser = new UsersAdapterData(getContext(), userModelList);
                    totalUser.setText(String.valueOf(adUser.getItemCount())+ " User");
                    rvUser.setAdapter(adUser);
                    adUser.notifyDataSetChanged();

                    rvUser.setVisibility(View.VISIBLE);
                    nodata.setVisibility(View.GONE);
                }

            }

            @Override
            public void onFailure(Call<UsersResponseModel> call, Throwable t) {
                Toast.makeText(getActivity(), "Gagal Menghubungkan Server pesan : "+t, Toast.LENGTH_SHORT).show();

            }
        });
    }
}