package com.example.it_management.ui.Clients.FragmentDetail.ClientProjects;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.it_management.R;
import com.example.it_management.ui.EditActivity.EditProject;
import com.example.it_management.ui.Projects.ProjectsModel;

import java.util.List;

public class ClientsProjectsAdapterData extends RecyclerView.Adapter<ClientsProjectsAdapterData.HolderDataClientProjects>{
    private Context ctx;
    private List<ClientsProjectsModel> projectsModelList ;

    public ClientsProjectsAdapterData(Context ctx, List<ClientsProjectsModel> projectsModelList) {
        this.ctx = ctx;
        this.projectsModelList = projectsModelList;
    }

    @NonNull
    @Override
    public HolderDataClientProjects onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_client_project,parent,false);
        HolderDataClientProjects holder = new HolderDataClientProjects(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderDataClientProjects holder, int position) {
        ClientsProjectsModel cpm = projectsModelList.get(position);
        holder.tvid.setText(String.valueOf(cpm.getId()));
        holder.tvidclient.setText(String.valueOf(cpm.getclientid()));
        holder.tvprogress.setText(String.valueOf(cpm.getProgress()));
        holder.tvnama.setText(cpm.getName());
        holder.tvnotes.setText(cpm.getNotes());
        holder.tvdescription.setText(cpm.getDescription());
        holder.tvstartdate.setText(cpm.getStartdate());
        holder.tvtag.setText(cpm.getTag());
        holder.tvprogresbar.setProgress(cpm.getProgress());
        if (cpm.getDeadline().equals("")){
            holder.tvduedate.setText(" - ");
        }else{
            holder.tvduedate.setText(cpm.getDeadline());
        }
    }

    @Override
    public int getItemCount() {
        return projectsModelList.size();
    }

    public class HolderDataClientProjects extends RecyclerView.ViewHolder{
        private TextView tvprogress,tvtag,tvnama,tvduedate,tvid,
                tvnotes,tvdescription,tvstartdate,tvidclient;
        private ProgressBar tvprogresbar;
        private ImageButton btnHapus,btnEdit;
        public HolderDataClientProjects(@NonNull View itemView) {
            super(itemView);
            init(itemView);
            btnEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(ctx, EditProject.class);
                    i.putExtra("idProject",tvid.getText().toString());
                    i.putExtra("idClient",tvidclient.getText().toString());
                    i.putExtra("tag",tvtag.getText().toString());
                    i.putExtra("progress",tvprogress.getText().toString());
                    i.putExtra("nama",tvnama.getText().toString());
                    i.putExtra("duedate",tvduedate.getText().toString());
                    i.putExtra("notes",tvnotes.getText().toString());
                    i.putExtra("startdate",tvstartdate.getText().toString());
                    ctx.startActivity(i);
                }
            });
        }
        private void init(@NonNull View itemView) {
        tvprogresbar = itemView.findViewById(R.id.progress_client_project);
        tvid = itemView.findViewById(R.id.tv_idprojects_client_project);
        tvprogress = itemView.findViewById(R.id.tv_client_progress);
        tvtag = itemView.findViewById(R.id.tv_tag_client_projects);
        tvidclient = itemView.findViewById(R.id.tv_idclient_client_projects);
        tvnama = itemView.findViewById(R.id.tv_nama_client_projects);
        tvduedate = itemView.findViewById(R.id.tv_due_date_client_project);
        tvnotes = itemView.findViewById(R.id.tv_notes_client_projects);
        tvdescription = itemView.findViewById(R.id.tv_description_client_projects);
        tvstartdate = itemView.findViewById(R.id.tv_start_date_client_project);
        btnHapus = itemView.findViewById(R.id.btn_delete_client_project);
        btnEdit = itemView.findViewById(R.id.btn_edit_client_project);
        }
    }


}
