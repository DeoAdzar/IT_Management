package com.example.it_management.ui.KnowledgeBase;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.it_management.R;
import com.example.it_management.ui.Clients.ClientsAdapterData;

import java.util.List;

public class KBCategoryAdapterData extends RecyclerView.Adapter<KBCategoryAdapterData.HolderDataKbCategory>{
    private Context ctx;
    private List<KnowledgeBaseCategoryModel> categoryModelList;

    public KBCategoryAdapterData(Context ctx, List<KnowledgeBaseCategoryModel> categoryModelList) {
        this.ctx = ctx;
        this.categoryModelList = categoryModelList;
    }

    @NonNull
    @Override
    public HolderDataKbCategory onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_kb_category,parent,false);
        HolderDataKbCategory holder = new HolderDataKbCategory(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderDataKbCategory holder, int position) {
        KnowledgeBaseCategoryModel kcm = categoryModelList.get(position);

        holder.tvid.setText(String.valueOf(kcm.getId()));
        holder.tvidClient.setText(String.valueOf(kcm.getClients()));
        holder.tvnama.setText(kcm.getName());
    }

    @Override
    public int getItemCount() {
        return categoryModelList.size();
    }

    public class HolderDataKbCategory extends RecyclerView.ViewHolder{
        TextView tvnama,tvid,tvidClient;
        String id,nama;
        public HolderDataKbCategory(@NonNull View itemView) {
            super(itemView);
            init(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    id = tvid.getText().toString();
                    nama = tvnama.getText().toString();
                    Intent i = new Intent(ctx,KnowledgeBaseArticlesActivity.class);
                    i.putExtra("idCategory", id);
                    i.putExtra("namaCategory", nama);
                    ctx.startActivity(i);
                }
            });
        }
        private void init(@NonNull View itemView){
            tvid = itemView.findViewById(R.id.tv_id_kb_category);
            tvnama = itemView.findViewById(R.id.tv_nama_kb_category);
            tvidClient = itemView.findViewById(R.id.tv_id_client_kb_category);
        }

    }


}
