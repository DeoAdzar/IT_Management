package com.example.it_management.ui.Clients.FragmentDetail.ClientIssues;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.it_management.R;

import java.util.List;

public class ClientsIssuesAdapterData extends RecyclerView.Adapter<ClientsIssuesAdapterData.HolderDataClientIssues>{
    private Context ctx;
    private List<ClientsIssuesModel> clientsIssuesModelList;

    public ClientsIssuesAdapterData(Context ctx, List<ClientsIssuesModel> clientsIssuesModelList) {
        this.ctx = ctx;
        this.clientsIssuesModelList = clientsIssuesModelList;
    }

    @NonNull
    @Override
    public HolderDataClientIssues onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_client_issues,parent,false);
        HolderDataClientIssues holder = new HolderDataClientIssues(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderDataClientIssues holder, int position) {
        ClientsIssuesModel cim = clientsIssuesModelList.get(position);
        holder.tvid.setText(String.valueOf(cim.getId()));
        holder.tvclientid.setText(String.valueOf(cim.getClientid()));
        holder.tvadminid.setText(String.valueOf(cim.getAdminid()));
        holder.tvassetid.setText(String.valueOf(cim.getAssetid()));
        holder.tvprojectid.setText(String.valueOf(cim.getProjectid()));
        holder.tvmilestoneid.setText(String.valueOf(cim.getMilestoneid()));
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
        holder.tvname.setText(cim.getName());
        holder.tvdescription.setText(cim.getDescription());
        if (cim.getDuedate().equals("")){
            holder.tvduedate.setTextColor(ContextCompat.getColor(ctx,R.color.none));
            holder.tvduedate.setText(" none ");
        }else{
            holder.tvduedate.setText(cim.getDuedate());
        }
        holder.tvtimespent.setText(String.valueOf(cim.getTimespent()));
        holder.tvdateadded.setText(cim.getDateadded());
    }

    @Override
    public int getItemCount() {
        return clientsIssuesModelList.size();
    }


    public class HolderDataClientIssues extends RecyclerView.ViewHolder{
        private TextView tvid,tvclientid,tvassetid,tvprojectid,tvadminid,tvmilestoneid,tvissuetype
                ,tvpriority,tvstatus,tvname,tvdescription,tvduedate,tvtimespent,tvdateadded;
        ImageButton btnEdit,btnDelete;
        public HolderDataClientIssues(@NonNull View itemView) {
            super(itemView);
            tvid = itemView.findViewById(R.id.tv_id_issues_client);
            tvclientid = itemView.findViewById(R.id.tv_idclient_client_issues);
            tvassetid = itemView.findViewById(R.id.tv_idasset_client_issues);
            tvadminid = itemView.findViewById(R.id.tv_adminid_client_issues);
            tvprojectid = itemView.findViewById(R.id.tv_projectid_client_issues);
            tvmilestoneid = itemView.findViewById(R.id.tv_milestoneid_client_issues);
            tvissuetype = itemView.findViewById(R.id.tv_type_issues_client);
            tvpriority = itemView.findViewById(R.id.tv_priority_issues_client);
            tvstatus = itemView.findViewById(R.id.tv_status_issues_client);
            tvname = itemView.findViewById(R.id.tv_name_issues_client);
            tvdescription = itemView.findViewById(R.id.tv_description__client_issues);
            tvduedate = itemView.findViewById(R.id.tv_duedate_issues_client);
            tvtimespent = itemView.findViewById(R.id.tv_timespent_client_issues);
            tvdateadded = itemView.findViewById(R.id.tv_dateadded_client_issues);
            btnEdit = itemView.findViewById(R.id.btn_edit_issues_client);
            btnDelete = itemView.findViewById(R.id.btn_delete_issues_client);
        }
    }
}
