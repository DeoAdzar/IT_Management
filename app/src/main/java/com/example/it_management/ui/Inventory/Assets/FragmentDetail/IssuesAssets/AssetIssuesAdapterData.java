package com.example.it_management.ui.Inventory.Assets.FragmentDetail.IssuesAssets;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.it_management.R;
import com.example.it_management.ui.Clients.FragmentDetail.ClientIssues.ClientsIssuesModel;
import com.example.it_management.ui.EditActivity.EditIssues;

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
        holder.tvclientid.setText(String.valueOf(cim.getClientid()));
        holder.tvadminid.setText(String.valueOf(cim.getAdminid()));
        holder.tvassetid.setText(String.valueOf(cim.getAssetid()));
        holder.tvprojectid.setText(String.valueOf(cim.getProjectid()));
        switch (cim.getIssuetype()){
            case "Task":
                holder.tvissuetype.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(ctx,R.drawable.task),null,null,null);
                holder.tvissuetype.setText(cim.getIssuetype());
                holder.tvvaltype.setText("0");
                break;
            case "Maintenance":
                holder.tvissuetype.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(ctx,R.drawable.maintenance),null,null,null);
                holder.tvissuetype.setText(cim.getIssuetype());
                holder.tvvaltype.setText("1");
                break;
            case "Bug":
                holder.tvissuetype.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(ctx,R.drawable.bug),null,null,null);
                holder.tvissuetype.setText(cim.getIssuetype());
                holder.tvvaltype.setText("2");
                break;
            case "Improvement":
                holder.tvissuetype.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(ctx,R.drawable.improvement),null,null,null);
                holder.tvissuetype.setText(cim.getIssuetype());
                holder.tvvaltype.setText("3");
                break;
            case "New Feature":
                holder.tvissuetype.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(ctx,R.drawable.newfeature),null,null,null);
                holder.tvissuetype.setText(cim.getIssuetype());
                holder.tvvaltype.setText("4");
                break;
            case "Story":
                holder.tvissuetype.setCompoundDrawablesRelative(ContextCompat.getDrawable(ctx,R.drawable.story),null,null,null);
                holder.tvissuetype.setText(cim.getIssuetype());
                holder.tvvaltype.setText("5");
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
        holder.tvdes.setText(cim.getDescription());
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
        private TextView tvid,tvissuetype,tvpriority,tvstatus,tvname,tvduedate
                ,tvclientid,tvassetid,tvprojectid,tvadminid,tvdes,tvvaltype;
        ImageButton btnEdit,btnDelete;
        public HolderDataAssetIssues(@NonNull View itemView) {
            super(itemView);
            tvid = itemView.findViewById(R.id.tv_id_issues_asset);
            tvclientid = itemView.findViewById(R.id.tv_idclient_issues_asset);
            tvassetid =itemView.findViewById(R.id.tv_idasset_issues_asset);
            tvadminid=itemView.findViewById(R.id.tv_adminid_issues_asset);
            tvprojectid = itemView.findViewById(R.id.tv_projectid_issues_asset);
            tvdes = itemView.findViewById(R.id.tv_description_issues_asset);
            tvissuetype = itemView.findViewById(R.id.tv_type_issues_asset);
            tvpriority = itemView.findViewById(R.id.tv_priority_issues_asset);
            tvstatus = itemView.findViewById(R.id.tv_status_issues_asset);
            tvname = itemView.findViewById(R.id.tv_name_issues_asset);
            tvduedate = itemView.findViewById(R.id.tv_duedate_issues_asset);
            tvvaltype = itemView.findViewById(R.id.tv_valType_issues_asset);
            btnEdit = itemView.findViewById(R.id.btn_edit_issues_asset);
            btnDelete = itemView.findViewById(R.id.btn_delete_issues_asset);
            btnEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(ctx, EditIssues.class);
                    i.putExtra("idIssues",tvid.getText().toString());
                    i.putExtra("idAdmin",tvadminid.getText().toString());
                    i.putExtra("idProject",tvprojectid.getText().toString());
                    i.putExtra("idAssets",tvassetid.getText().toString());
                    i.putExtra("idClient",tvclientid.getText().toString());
                    i.putExtra("type",tvissuetype.getText().toString());
                    i.putExtra("valtype", tvvaltype.getText().toString());
                    i.putExtra("prio",tvpriority.getText().toString());
                    i.putExtra("status",tvstatus.getText().toString());
                    i.putExtra("name",tvname.getText().toString());
                    i.putExtra("des",tvdes.getText().toString());
                    i.putExtra("duedate",tvduedate.getText().toString());

                    ctx.startActivity(i);
                }
            });
        }
    }
}
