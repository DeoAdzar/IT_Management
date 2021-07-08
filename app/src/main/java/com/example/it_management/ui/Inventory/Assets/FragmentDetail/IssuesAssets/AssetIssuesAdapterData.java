package com.example.it_management.ui.Inventory.Assets.FragmentDetail.IssuesAssets;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.it_management.R;
import com.example.it_management.ui.Clients.FragmentDetail.ClientIssues.ClientsIssuesModel;

import java.util.List;

public class AssetIssuesAdapterData extends RecyclerView.Adapter<AssetIssuesAdapterData.HolderDataAssetIssues>{
    private Context ctx;
    private List<AssetIssuesModel> assetIssuesModelList;

    public AssetIssuesAdapterData(Context ctx, List<AssetIssuesModel> assetIssuesModelList) {
        this.ctx = ctx;
        this.assetIssuesModelList = assetIssuesModelList;
    }

    @NonNull
    @Override
    public HolderDataAssetIssues onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_asset_issues,parent,false);
        HolderDataAssetIssues holder = new HolderDataAssetIssues(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderDataAssetIssues holder, int position) {
        AssetIssuesModel cim = assetIssuesModelList.get(position);
        holder.tvid.setText(String.valueOf(cim.getId()));
        switch (cim.getIssuetype()){
            case "Task":
                holder.tvissuetype.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(ctx,R.drawable.task),null,null,null);
                holder.tvissuetype.setText(cim.getIssuetype());
                break;
            case "Maintenance":
                holder.tvissuetype.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(ctx,R.drawable.maintenance),null,null,null);
                holder.tvissuetype.setText(cim.getIssuetype());
                break;
            case "Bug":
                holder.tvissuetype.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(ctx,R.drawable.bug),null,null,null);
                holder.tvissuetype.setText(cim.getIssuetype());
                break;
            case "Improvement":
                holder.tvissuetype.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(ctx,R.drawable.improvement),null,null,null);
                holder.tvissuetype.setText(cim.getIssuetype());
                break;
            case "New Feature":
                holder.tvissuetype.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(ctx,R.drawable.newfeature),null,null,null);
                holder.tvissuetype.setText(cim.getIssuetype());
                break;
            case "Story":
                holder.tvissuetype.setCompoundDrawablesRelative(ContextCompat.getDrawable(ctx,R.drawable.story),null,null,null);
                holder.tvissuetype.setText(cim.getIssuetype());
                break;
            default:
                holder.tvissuetype.setCompoundDrawablesWithIntrinsicBounds(null,null,null,null);
                holder.tvissuetype.setText("none");
                break;
        }
        switch (cim.getPriority()){
            case "Low":
                holder.tvpriority.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(ctx,R.drawable.low_prio),null,null,null);
                holder.tvpriority.setText(cim.getPriority());
                break;
            case "Normal":
                holder.tvpriority.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(ctx,R.drawable.normal_prio),null,null,null);
                holder.tvpriority.setText(cim.getPriority());
                break;
            case "High":
                holder.tvpriority.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(ctx,R.drawable.high_prio),null,null,null);
                holder.tvpriority.setText(cim.getPriority());
                break;
            default:
                holder.tvpriority.setCompoundDrawablesWithIntrinsicBounds(null,null,null,null);
                holder.tvpriority.setText(cim.getPriority());
                break;
        }
        switch (cim.getStatus()){
            case "To Do":
                holder.tvstatus.setBackground(ContextCompat.getDrawable(ctx,R.drawable.bg_status_pending));
                holder.tvstatus.setText(cim.getStatus());
                break;
            case "In Progress":
                holder.tvstatus.setBackground(ContextCompat.getDrawable(ctx,R.drawable.bg_status_deployed));
                holder.tvstatus.setText(cim.getStatus());
                break;
            case "In Review":
                holder.tvstatus.setBackground(ContextCompat.getDrawable(ctx,R.drawable.bg_status_broken));
                holder.tvstatus.setText(cim.getStatus());
                break;
            case "Done":
                holder.tvstatus.setBackground(ContextCompat.getDrawable(ctx,R.drawable.bg_status_done));
                holder.tvstatus.setTextColor(ContextCompat.getColor(ctx,R.color.black));
                holder.tvstatus.setText(cim.getStatus());
                break;
            default:
                holder.tvstatus.setBackground(null);
                holder.tvstatus.setText("none");
        }
        holder.tvstatus.setText(cim.getStatus());
        holder.tvname.setText(cim.getName());
        if (cim.getDuedate().equals("")){
            holder.tvduedate.setTextColor(ContextCompat.getColor(ctx,R.color.none));
            holder.tvduedate.setText(" none ");
        }else{
            holder.tvduedate.setText(cim.getDuedate());
        }
    }

    @Override
    public int getItemCount() {
        return assetIssuesModelList.size();
    }


    public class HolderDataAssetIssues extends RecyclerView.ViewHolder{
        private TextView tvid,tvissuetype,tvpriority,tvstatus,tvname,tvduedate;
        public HolderDataAssetIssues(@NonNull View itemView) {
            super(itemView);
            tvid = itemView.findViewById(R.id.tv_id_issues_asset);
            tvissuetype = itemView.findViewById(R.id.tv_type_issues_asset);
            tvpriority = itemView.findViewById(R.id.tv_priority_issues_asset);
            tvstatus = itemView.findViewById(R.id.tv_status_issues_asset);
            tvname = itemView.findViewById(R.id.tv_name_issues_asset);
            tvduedate = itemView.findViewById(R.id.tv_duedate_issues_asset);
        }
    }
}
