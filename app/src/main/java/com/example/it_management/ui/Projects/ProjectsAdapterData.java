package com.example.it_management.ui.Projects;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.it_management.R;

import java.util.List;

public class ProjectsAdapterData extends RecyclerView.Adapter<ProjectsAdapterData.HolderDataProjects>{
    private Context ctx;
    private List<ProjectsModel> projectsModelList ;

    public ProjectsAdapterData(Context ctx, List<ProjectsModel> projectsModelList) {
        this.ctx = ctx;
        this.projectsModelList = projectsModelList;
    }

    @NonNull
    @Override
    public HolderDataProjects onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_project,parent,false);
        HolderDataProjects holder = new HolderDataProjects(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderDataProjects holder, int position) {
        ProjectsModel pm = projectsModelList.get(position);
        holder.tvid.setText(String.valueOf(pm.getId()));
        holder.tvidclient.setText(String.valueOf(pm.getclientid()));
        holder.tvclient.setText(pm.getNama());
        holder.tvprogress.setText(String.valueOf(pm.getProgress())+"%");
        holder.tvnama.setText(pm.getName());
        holder.tvnotes.setText(pm.getNotes());
        holder.tvdescription.setText(pm.getDescription());
        holder.tvstartdate.setText(pm.getStartdate());
        holder.tvtag.setText(pm.getTag());
        holder.tvprogresbar.setProgress(pm.getProgress());
        if (pm.getDeadline().equals("")){
            holder.tvduedate.setText(" - ");
        }else{
            holder.tvduedate.setText(pm.getDeadline());
        }
    }

    @Override
    public int getItemCount() {
        return projectsModelList.size();
    }

    public class HolderDataProjects extends RecyclerView.ViewHolder{
        private TextView tvprogress,tvtag,tvnama,tvclient,tvduedate,tvid,
                tvnotes,tvdescription,tvstartdate,tvidclient;
        private ProgressBar tvprogresbar;
        private ImageButton btnHapus,btnEdit;
        public HolderDataProjects(@NonNull View itemView) {
            super(itemView);
            init(itemView);

        }
        private void init(@NonNull View itemView) {
        tvprogresbar = itemView.findViewById(R.id.progress_project);
        tvid = itemView.findViewById(R.id.tv_id_projects);
        tvprogress = itemView.findViewById(R.id.tv_progress);
        tvtag = itemView.findViewById(R.id.tv_tag_projects);
        tvidclient = itemView.findViewById(R.id.tv_id_client_projects);
        tvnama = itemView.findViewById(R.id.tv_nama_projects);
        tvclient = itemView.findViewById(R.id.tv_client_project);
        tvduedate = itemView.findViewById(R.id.tv_due_date);
        tvnotes = itemView.findViewById(R.id.tv_notes_projects);
        tvdescription = itemView.findViewById(R.id.tv_description_projects);
        tvstartdate = itemView.findViewById(R.id.tv_start_date_project);
        btnHapus = itemView.findViewById(R.id.btn_delete_project);
        btnEdit = itemView.findViewById(R.id.btn_edit_project);
        }
    }


}
