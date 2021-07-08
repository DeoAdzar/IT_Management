package com.example.it_management.ui.Issues.ActiveIssues;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.it_management.R;
import com.example.it_management.ui.Clients.FragmentDetail.ClientIssues.ClientsIssuesAdapterData;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ActiveIssuesAdapterData extends RecyclerView.Adapter<ActiveIssuesAdapterData.HolderDataIssues>{
    private Context ctx;
    private List<ActiveIssuesModel> activeIssuesModelList;

    public ActiveIssuesAdapterData(Context ctx, List<ActiveIssuesModel> activeIssuesModelList) {
        this.ctx = ctx;
        this.activeIssuesModelList = activeIssuesModelList;
    }

    @NonNull
    @NotNull
    @Override
    public HolderDataIssues onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_active_issues,parent,false);
        HolderDataIssues holder = new HolderDataIssues(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull HolderDataIssues holder, int position) {
        ActiveIssuesModel aim = activeIssuesModelList.get(position);
        holder.tvid.setText(String.valueOf(aim.getId()));
        switch (aim.getIssuetype()){
            case "Task":
                holder.tvissuetype.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(ctx,R.drawable.task),null,null,null);
                holder.tvissuetype.setText(aim.getIssuetype());
                break;
            case "Maintenance":
                holder.tvissuetype.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(ctx,R.drawable.maintenance),null,null,null);
                holder.tvissuetype.setText(aim.getIssuetype());
                break;
            case "Bug":
                holder.tvissuetype.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(ctx,R.drawable.bug),null,null,null);
                holder.tvissuetype.setText(aim.getIssuetype());
                break;
            case "Improvement":
                holder.tvissuetype.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(ctx,R.drawable.improvement),null,null,null);
                holder.tvissuetype.setText(aim.getIssuetype());
                break;
            case "New Feature":
                holder.tvissuetype.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(ctx,R.drawable.newfeature),null,null,null);
                holder.tvissuetype.setText(aim.getIssuetype());
                break;
            case "Story":
                holder.tvissuetype.setCompoundDrawablesRelative(ContextCompat.getDrawable(ctx,R.drawable.story),null,null,null);
                holder.tvissuetype.setText(aim.getIssuetype());
                break;
            default:
                holder.tvissuetype.setCompoundDrawablesWithIntrinsicBounds(null,null,null,null);
                holder.tvissuetype.setText("none");
                break;
        }
        switch (aim.getPriority()){
            case "Low":
                holder.tvpriority.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(ctx,R.drawable.low_prio),null,null,null);
                holder.tvpriority.setText(aim.getPriority());
                break;
            case "Normal":
                holder.tvpriority.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(ctx,R.drawable.normal_prio),null,null,null);
                holder.tvpriority.setText(aim.getPriority());
                break;
            case "High":
                holder.tvpriority.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(ctx,R.drawable.high_prio),null,null,null);
                holder.tvpriority.setText(aim.getPriority());
                break;
            default:
                holder.tvpriority.setCompoundDrawablesWithIntrinsicBounds(null,null,null,null);
                holder.tvpriority.setText(aim.getPriority());
                break;
        }
        switch (aim.getStatus()){
            case "To Do":
                holder.tvstatus.setBackground(ContextCompat.getDrawable(ctx,R.drawable.bg_status_pending));
                holder.tvstatus.setText(aim.getStatus());
                break;
            case "In Progress":
                holder.tvstatus.setBackground(ContextCompat.getDrawable(ctx,R.drawable.bg_status_deployed));
                holder.tvstatus.setText(aim.getStatus());
                break;
            case "In Review":
                holder.tvstatus.setBackground(ContextCompat.getDrawable(ctx,R.drawable.bg_status_broken));
                holder.tvstatus.setText(aim.getStatus());
                break;
            case "Done":
                holder.tvstatus.setBackground(ContextCompat.getDrawable(ctx,R.drawable.bg_status_done));
                holder.tvstatus.setTextColor(ContextCompat.getColor(ctx,R.color.black));
                holder.tvstatus.setText(aim.getStatus());
                break;
            default:
                holder.tvstatus.setBackground(null);
                holder.tvstatus.setText("none");
        }
        holder.tvstatus.setText(aim.getStatus());
        holder.tvname.setText(aim.getName());
        if (aim.getDuedate().equals("")){
            holder.tvduedate.setTextColor(ContextCompat.getColor(ctx,R.color.none));
            holder.tvduedate.setText(" none ");
        }else{
            holder.tvduedate.setText(aim.getDuedate());
        }

    }

    @Override
    public int getItemCount() {
        return activeIssuesModelList.size();
    }

    public class HolderDataIssues extends RecyclerView.ViewHolder{
        private TextView tvid,tvissuetype,tvpriority,tvstatus,tvname,tvduedate;
        public HolderDataIssues(@NonNull @NotNull View itemView) {
            super(itemView);
            tvid = itemView.findViewById(R.id.tv_id_issues_active);
            tvname = itemView.findViewById(R.id.tv_name_issues_active);
            tvduedate = itemView.findViewById(R.id.tv_duedate_issues_active);
            tvissuetype = itemView.findViewById(R.id.tv_type_issues_active);
            tvpriority = itemView.findViewById(R.id.tv_priority_issues_active);
            tvstatus = itemView.findViewById(R.id.tv_status_issues_active);
        }
    }
}