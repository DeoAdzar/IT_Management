package com.example.it_management.ui.Inventory.Attributes.Manufacturers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.it_management.R;

import java.util.List;

public class ManufacturersAdapter extends RecyclerView.Adapter<ManufacturersAdapter.HolderDatamanufacturers>{
    private Context context;
    private List<ManufacturersModel> ManufacturersModelList;

    public ManufacturersAdapter(Context context, List<ManufacturersModel> ManufacturersModelList) {
        this.context = context;
        this.ManufacturersModelList = ManufacturersModelList;
    }

    @NonNull
    @Override
    public HolderDatamanufacturers onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_manufacturers,parent,false);
       HolderDatamanufacturers holder = new HolderDatamanufacturers(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderDatamanufacturers holder, int position) {
        ManufacturersModel mm = ManufacturersModelList.get(position);
        holder.tvid.setText(String.valueOf(mm.getId()));
        holder.tvname.setText(mm.getName());
    }

    @Override
    public int getItemCount() {
        return ManufacturersModelList.size();
    }

    public class HolderDatamanufacturers extends RecyclerView.ViewHolder{
        TextView tvid,tvname;
        public HolderDatamanufacturers(@NonNull View itemView) {
            super(itemView);
            tvid = itemView.findViewById(R.id.tv_id_manufacturers);
            tvname = itemView.findViewById(R.id.tv_nama_manufacturers);
        }
    }
}
