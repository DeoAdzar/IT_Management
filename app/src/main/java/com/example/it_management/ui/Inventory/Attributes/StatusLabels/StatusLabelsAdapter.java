package com.example.it_management.ui.Inventory.Attributes.StatusLabels;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.it_management.R;
import com.example.it_management.ui.Inventory.Attributes.StatusLabels.StatusLabelsModel;

import java.util.List;

public class StatusLabelsAdapter extends RecyclerView.Adapter<StatusLabelsAdapter.HolderDataStatusLabels>{
    private Context context;
    private List<StatusLabelsModel> StatusLabelsModelList;

    public StatusLabelsAdapter(Context context, List<StatusLabelsModel> StatusLabelsModelList) {
        this.context = context;
        this.StatusLabelsModelList = StatusLabelsModelList;
    }

    @NonNull
    @Override
    public HolderDataStatusLabels onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_status_labels,parent,false);
       HolderDataStatusLabels holder = new HolderDataStatusLabels(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderDataStatusLabels holder, int position) {
        StatusLabelsModel slm = StatusLabelsModelList.get(position);
        holder.tvid.setText(String.valueOf(slm.getId()));
        holder.tvname.setText(slm.getName());
    }

    @Override
    public int getItemCount() {
        return StatusLabelsModelList.size();
    }

    public class HolderDataStatusLabels extends RecyclerView.ViewHolder{
        TextView tvid,tvname;
        public HolderDataStatusLabels(@NonNull View itemView) {
            super(itemView);
            tvid = itemView.findViewById(R.id.tv_id_StatusLabels);
            tvname = itemView.findViewById(R.id.tv_nama_StatusLabels);
        }
    }
}
