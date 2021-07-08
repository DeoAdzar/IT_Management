package com.example.it_management.ui.Inventory.Attributes.AssetsModels;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.it_management.R;

import java.util.List;

public class AssetsModelAdapter extends RecyclerView.Adapter<AssetsModelAdapter.HolderDataAssetsModel>{
    private Context ctx;
    private List<AssetsModelModel> assetsModelModelList;

    public AssetsModelAdapter(Context ctx, List<AssetsModelModel> assetsModelModelList) {
        this.ctx = ctx;
        this.assetsModelModelList = assetsModelModelList;
    }

    @NonNull
    @Override
    public HolderDataAssetsModel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_assets_model,parent,false);
        HolderDataAssetsModel holder = new HolderDataAssetsModel(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderDataAssetsModel holder, int position) {
        AssetsModelModel acm = assetsModelModelList.get(position);
        holder.id.setText(String.valueOf(acm.getId()));
        holder.nama.setText(acm.getName());
    }

    @Override
    public int getItemCount() {
        return assetsModelModelList.size();
    }

    public class HolderDataAssetsModel extends RecyclerView.ViewHolder{
        private TextView id,nama;
        public HolderDataAssetsModel(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.tv_id_assetsModel);
            nama = itemView.findViewById(R.id.tv_nama_assetsModel);
        }
    }
}
