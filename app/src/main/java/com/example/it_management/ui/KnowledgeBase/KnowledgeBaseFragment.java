package com.example.it_management.ui.KnowledgeBase;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
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
import com.example.it_management.ui.Clients.ClientsModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class KnowledgeBaseFragment extends Fragment {
    private RecyclerView rvKbCategory;
    private RecyclerView.Adapter adKbCategory;
    private RecyclerView.LayoutManager lmKbCategory;
    private List<KnowledgeBaseCategoryModel> categoryModelList = new ArrayList<>();
    private SwipeRefreshLayout swipeRefreshLayout;
    private FloatingActionButton fabKbCategory;
    private TextView totalKbCategory;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_knowledge_base_category, container, false);
        rvKbCategory = v.findViewById(R.id.rv_kb_category);
        swipeRefreshLayout = v.findViewById(R.id.sr_kb_category);
        fabKbCategory = v.findViewById(R.id.fab_add_kb_category);
        lmKbCategory = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        rvKbCategory.setLayoutManager(lmKbCategory);
        totalKbCategory = v.findViewById(R.id.totalKbCategory);
        tampilDataKbCategory();
        refresh();
        return v;
    }
    private void refresh(){
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                tampilDataKbCategory();
                swipeRefreshLayout.setRefreshing(false);
                Toast.makeText(getActivity(), "Data have been updated", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void tampilDataKbCategory() {
        BaseApiService mApiService = UtilsApi.getApiService();
        Call<KnowledgeBaseCategoryResponseModel> tampildata = mApiService.basGetKbCategory();
        tampildata.enqueue(new Callback<KnowledgeBaseCategoryResponseModel>() {
            @Override
            public void onResponse(Call<KnowledgeBaseCategoryResponseModel> call, Response<KnowledgeBaseCategoryResponseModel> response) {
                categoryModelList = response.body().getData();
                adKbCategory = new KBCategoryAdapterData(getContext(),categoryModelList);
                totalKbCategory.setText(String.valueOf(adKbCategory.getItemCount()+ " Category"));
                rvKbCategory.setAdapter(adKbCategory);
                adKbCategory.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<KnowledgeBaseCategoryResponseModel> call, Throwable t) {
                Toast.makeText(getActivity(), "Gagal Menghubungkan Server pesan : "+t, Toast.LENGTH_SHORT).show();

            }
        });
    }
}