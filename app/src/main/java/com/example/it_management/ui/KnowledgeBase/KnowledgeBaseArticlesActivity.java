package com.example.it_management.ui.KnowledgeBase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class KnowledgeBaseArticlesActivity extends AppCompatActivity {
    private RecyclerView rvArticles;
    private RecyclerView.Adapter adArticles;
    private RecyclerView.LayoutManager lmArticles;
    private List<KnowledgeBaseArticlesModel> articlesModelList = new ArrayList<>();
    private SwipeRefreshLayout swipeRefreshLayout;
    private FloatingActionButton fabKbArticles;
    private TextView totalKbArticles;
    private TextView idCategory,tvnama;
    private Button back;
    private Integer id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_knowledge_base_articles);
        rvArticles = findViewById(R.id.rv_kb_articles);
        swipeRefreshLayout = findViewById(R.id.sr_kb_articles);
        fabKbArticles = findViewById(R.id.fab_add_kb_articles);
        lmArticles = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rvArticles.setLayoutManager(lmArticles);
        totalKbArticles = findViewById(R.id.totalKbArticles);
        idCategory = findViewById(R.id.idCategory);
        tvnama = findViewById(R.id.tv_CategoryName);
        back = findViewById(R.id.back_kb_articles);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvnama.setText(getIntent().getStringExtra("namaCategory"));
        idCategory.setText(getIntent().getStringExtra("idCategory"));
        tampilDataKbArticles();
        refresh();
    }
    private void refresh(){
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                tampilDataKbArticles();
                swipeRefreshLayout.setRefreshing(false);
                Toast.makeText(KnowledgeBaseArticlesActivity.this, "Data have been updated", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void tampilDataKbArticles() {
        id = Integer.parseInt(idCategory.getText().toString());
        BaseApiService mApiService = UtilsApi.getApiService();
        Call<KnowledgeBaseArticlesResponseModel> tampilData = mApiService.basGetKbArticles(id);
        tampilData.enqueue(new Callback<KnowledgeBaseArticlesResponseModel>() {
            @Override
            public void onResponse(Call<KnowledgeBaseArticlesResponseModel> call, Response<KnowledgeBaseArticlesResponseModel> response) {
                articlesModelList = response.body().getData();
                adArticles = new KBArticlesAdapterData(KnowledgeBaseArticlesActivity.this,articlesModelList);
                totalKbArticles.setText(String.valueOf(adArticles.getItemCount()+ " Articles"));
                rvArticles.setAdapter(adArticles);
                adArticles.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<KnowledgeBaseArticlesResponseModel> call, Throwable t) {
                Toast.makeText(KnowledgeBaseArticlesActivity.this, "Gagal Menghubungkan Server pesan : "+t, Toast.LENGTH_SHORT).show();

            }
        });
    }
}