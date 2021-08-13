package com.example.it_management.ui.Issues.AllIssues;

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
import com.example.it_management.ui.EditActivity.EditIssues;
import com.example.it_management.ui.Issues.ActiveIssues.ActiveIssuesModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AllIssuesAdapterData extends RecyclerView.Adapter<AllIssuesAdapterData.HolderDataIssues>{
    private Context ctx;
    private List<AllIssuesModel> allIssuesModelList;

    public AllIssuesAdapterData(Context ctx, List<AllIssuesModel> allIssuesModelList) {
        this.ctx = ctx;
        this.allIssuesModelList = allIssuesModelList;
    }

    @NonNull
    @NotNull
    @Override
    public HolderDataIssues onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_all_issues,parent,false);
        HolderDataIssues holder = new HolderDataIssues(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull HolderDataIssues holder, int position) {
        AllIssuesModel aim = allIssuesModelList.get(position);
        holder.tvid.setText(String.valueOf(aim.getId()));
        holder.tvclientid.setText(String.valueOf(aim.getClientid()));
        holder.tvadminid.setText(String.valueOf(aim.getAdminid()));
        holder.tvassetid.setText(String.valueOf(aim.getAssetid()));
        holder.tvprojectid.setText(String.valueOf(aim.getProjectid()));
        switch (aim.getIssuetype()){
            case "Task":
                holder.tvissuetype.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(ctx,R.drawable.task),null,null,null);
                holder.tvissuetype.setText(aim.getIssuetype());
                holder.tvvaltype.setText("0");
                break;
            case "Maintenance":
                holder.tvissuetype.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(ctx,R.drawable.maintenance),null,null,null);
                holder.tvissuetype.setText(aim.getIssuetype());
                holder.tvvaltype.setText("1");
                break;
            case "Bug":
                holder.tvissuetype.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(ctx,R.drawable.bug),null,null,null);
                holder.tvissuetype.setText(aim.getIssuetype());
                holder.tvvaltype.setText("2");
                break;
            case "Improvement":
                holder.tvissuetype.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(ctx,R.drawable.improvement),null,null,null);
                holder.tvissuetype.setText(aim.getIssuetype());
                holder.tvvaltype.setText("3");
                break;
            case "New Feature":
                holder.tvissuetype.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(ctx,R.drawable.newfeature),null,null,null);
                holder.tvissuetype.setText(aim.getIssuetype());
                holder.tvvaltype.setText("4");
                break;
            case "Story":
                holder.tvissuetype.setCompoundDrawablesRelative(ContextCompat.getDrawable(ctx,R.drawable.story),null,null,null);
                holder.tvissuetype.setText(aim.getIssuetype());
                holder.tvvaltype.setText("5");
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
        holder.tvdes.setText(aim.getDescription());
        if (aim.getDuedate().equals("")){
            holder.tvduedate.setTextColor(ContextCompat.getColor(ctx,R.color.none));
            holder.tvduedate.setText(" none ");
        }else{
            holder.tvduedate.setText(aim.getDuedate());
        }

    }

    @Override
    public int getItemCount() {
        return allIssuesModelList.size();
    }

    public class HolderDataIssues extends RecyclerView.ViewHolder{
        private TextView tvid,tvissuetype,tvpriority,tvstatus,tvname,tvduedate
                ,tvclientid,tvassetid,tvprojectid,tvadminid,tvdes,tvvaltype;
        ImageButton btnEdit,btnDelete;
        public HolderDataIssues(@NonNull @NotNull View itemView) {
            super(itemView);
            tvid = itemView.findViewById(R.id.tv_id_issues_all);
            tvclientid = itemView.findViewById(R.id.tv_idclient_issues_all);
            tvassetid =itemView.findViewById(R.id.tv_idasset_issues_all);
            tvadminid=itemView.findViewById(R.id.tv_adminid_issues_all);
            tvprojectid = itemView.findViewById(R.id.tv_projectid_issues_all);
            tvdes = itemView.findViewById(R.id.tv_description_issues_all);
            tvname = itemView.findViewById(R.id.tv_name_issues_all);
            tvduedate = itemView.findViewById(R.id.tv_duedate_issues_all);
            tvissuetype = itemView.findViewById(R.id.tv_type_issues_all);
            tvpriority = itemView.findViewById(R.id.tv_priority_issues_all);
            tvstatus = itemView.findViewById(R.id.tv_status_issues_all);
            tvvaltype = itemView.findViewById(R.id.tv_valType_issues_all);
            btnEdit = itemView.findViewById(R.id.btn_edit_issues_all);
            btnDelete = itemView.findViewById(R.id.btn_delete_issues_all);
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
