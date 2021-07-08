package com.example.it_management.ui.Inventory.Attributes.AssetsCategories;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.it_management.R;

import java.util.List;

public class AssetsCategoryAdapter extends RecyclerView.Adapter<AssetsCategoryAdapter.HolderDataAssetsCategory>{
    private Context ctx;
    private List<AssetsCategoryModel> assetsCategoryModelList;

    public AssetsCategoryAdapter(Context ctx, List<AssetsCategoryModel> assetsCategoryModelList) {
        this.ctx = ctx;
        this.assetsCategoryModelList = assetsCategoryModelList;
    }

    @NonNull
    @Override
    public HolderDataAssetsCategory onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_assets_category,parent,false);
        HolderDataAssetsCategory holder = new HolderDataAssetsCategory(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderDataAssetsCategory holder, int position) {
        AssetsCategoryModel acm = assetsCategoryModelList.get(position);
        holder.id.setText(String.valueOf(acm.getId()));
        holder.nama.setText(acm.getName());
    }

    @Override
    public int getItemCount() {
        return assetsCategoryModelList.size();
    }

    public class HolderDataAssetsCategory extends RecyclerView.ViewHolder{
        private TextView id,nama;
        public HolderDataAssetsCategory(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.tv_id_assetsCategory);
            nama = itemView.findViewById(R.id.tv_nama_assetsCategory);
        }
    }
}
