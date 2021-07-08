package com.example.it_management.ui.Inventory.Attributes.Locations;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.it_management.R;

import java.util.List;

public class LocationsAdapter extends RecyclerView.Adapter<LocationsAdapter.HolderDataLocation>{
    private Context context;
    private List<LocationsModel> locationsModelList;

    public LocationsAdapter(Context context, List<LocationsModel> locationsModelList) {
        this.context = context;
        this.locationsModelList = locationsModelList;
    }

    @NonNull
    @Override
    public HolderDataLocation onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_location,parent,false);
       HolderDataLocation holder = new HolderDataLocation(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderDataLocation holder, int position) {
        LocationsModel lm = locationsModelList.get(position);
        holder.tvid.setText(String.valueOf(lm.getId()));
        holder.tvclient.setText(lm.getNamaClient());
        holder.tvname.setText(lm.getName());
    }

    @Override
    public int getItemCount() {
        return locationsModelList.size();
    }

    public class HolderDataLocation extends RecyclerView.ViewHolder{
        TextView tvid,tvclient,tvname;
        public HolderDataLocation(@NonNull View itemView) {
            super(itemView);
            tvid = itemView.findViewById(R.id.tv_id_location);
            tvclient = itemView.findViewById(R.id.tv_client_location);
            tvname = itemView.findViewById(R.id.tv_nama_location);
        }
    }
}
