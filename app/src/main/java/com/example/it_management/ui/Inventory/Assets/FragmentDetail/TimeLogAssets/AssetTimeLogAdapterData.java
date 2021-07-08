package com.example.it_management.ui.Inventory.Assets.FragmentDetail.TimeLogAssets;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.it_management.R;
import com.example.it_management.ui.Clients.FragmentDetail.ClientTimeLog.ClientsTimeLogModel;

import java.util.List;


public class AssetTimeLogAdapterData extends RecyclerView.Adapter<AssetTimeLogAdapterData.HolderDataAssetTimeLog>{
    private Context ctx;
    private List<AssetTimeLogModel> assetTimeLogModelList;

    public AssetTimeLogAdapterData(Context ctx, List<AssetTimeLogModel> assetTimeLogModelList) {
        this.ctx = ctx;
        this.assetTimeLogModelList = assetTimeLogModelList;
    }

    @NonNull
    @Override
    public HolderDataAssetTimeLog onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_asset_timelog,parent,false);
        HolderDataAssetTimeLog holder = new HolderDataAssetTimeLog(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderDataAssetTimeLog holder, int position) {
        AssetTimeLogModel tlm = assetTimeLogModelList.get(position);
        holder.tvid.setText(String.valueOf(tlm.getId()));
        holder.tvasset.setText(tlm.getNama());
        holder.tvdate.setText(tlm.getDate());
        holder.tvtime.setText(tlm.getStart()+ " --> "+tlm.getEnd());
        holder.tvdescription.setText(Html.fromHtml(tlm.getDescription()));
    }

    @Override
    public int getItemCount() {
        return assetTimeLogModelList.size();
    }

    public class HolderDataAssetTimeLog extends RecyclerView.ViewHolder{
        private TextView tvid,tvdate,tvtime,tvdescription,tvasset;
        public HolderDataAssetTimeLog(@NonNull View itemView) {
            super(itemView);
            tvid = itemView.findViewById(R.id.tv_id_timelog_asset);
            tvdate = itemView.findViewById(R.id.tv_date_timelog_asset);
            tvtime = itemView.findViewById(R.id.tv_time_timelog_asset);
            tvdescription = itemView.findViewById(R.id.tv_description_timelog_asset);
            tvasset = itemView.findViewById(R.id.tv_asset_timelog_asset);

        }
    }
}
