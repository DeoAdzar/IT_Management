package com.example.it_management.ui.Inventory.Attributes.LicenseCategories;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.it_management.R;

import java.util.List;

public class LicenseCategoryAdapter extends RecyclerView.Adapter<LicenseCategoryAdapter.HolderDatalicenseCategory>{
    private Context ctx;
    private List<LicenseCategoryModel> licenseCategoryModelList;

    public LicenseCategoryAdapter(Context ctx, List<LicenseCategoryModel> licenseCategoryModelList) {
        this.ctx = ctx;
        this.licenseCategoryModelList = licenseCategoryModelList;
    }

    @NonNull
    @Override
    public HolderDatalicenseCategory onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_licenses_category,parent,false);
        HolderDatalicenseCategory holder = new HolderDatalicenseCategory(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderDatalicenseCategory holder, int position) {
        LicenseCategoryModel acm = licenseCategoryModelList.get(position);
        holder.id.setText(String.valueOf(acm.getId()));
        holder.nama.setText(acm.getName());
    }

    @Override
    public int getItemCount() {
        return licenseCategoryModelList.size();
    }

    public class HolderDatalicenseCategory extends RecyclerView.ViewHolder{
        private TextView id,nama;
        public HolderDatalicenseCategory(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.tv_id_licenseCategory);
            nama = itemView.findViewById(R.id.tv_nama_licenseCategory);
        }
    }
}
