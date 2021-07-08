package com.example.it_management.ui.Inventory.Attributes.Suppliers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.it_management.R;

import java.util.List;

public class SuppliersAdapter extends RecyclerView.Adapter<SuppliersAdapter.HolderDataSuppliersModel>{
    private Context context;
    private List<SuppliersModel> SuppliersModelList;

    public SuppliersAdapter(Context context, List<SuppliersModel> SuppliersModelList) {
        this.context = context;
        this.SuppliersModelList = SuppliersModelList;
    }

    @NonNull
    @Override
    public HolderDataSuppliersModel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_suppliers,parent,false);
       HolderDataSuppliersModel holder = new HolderDataSuppliersModel(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderDataSuppliersModel holder, int position) {
        SuppliersModel slm = SuppliersModelList.get(position);
        holder.tvid.setText(String.valueOf(slm.getId()));
        holder.tvname.setText(slm.getName());
    }

    @Override
    public int getItemCount() {
        return SuppliersModelList.size();
    }

    public class HolderDataSuppliersModel extends RecyclerView.ViewHolder{
        TextView tvid,tvname;
        public HolderDataSuppliersModel(@NonNull View itemView) {
            super(itemView);
            tvid = itemView.findViewById(R.id.tv_id_suppliers);
            tvname = itemView.findViewById(R.id.tv_nama_suppliers);
        }
    }
}
