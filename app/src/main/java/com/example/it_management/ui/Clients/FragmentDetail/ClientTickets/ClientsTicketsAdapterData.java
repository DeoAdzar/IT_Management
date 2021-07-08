package com.example.it_management.ui.Clients.FragmentDetail.ClientTickets;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.it_management.R;
import com.example.it_management.ui.Clients.FragmentDetail.ClientProjects.ClientsProjectsModel;

import java.util.List;

public class ClientsTicketsAdapterData extends RecyclerView.Adapter<ClientsTicketsAdapterData.HolderDataClientTicket>{
    private Context ctx;
    private List<ClientsTicketsModel> clientsTicketsModelList ;

    public ClientsTicketsAdapterData(Context ctx, List<ClientsTicketsModel> clientsTicketsModelList) {
        this.ctx = ctx;
        this.clientsTicketsModelList = clientsTicketsModelList;
    }

    @NonNull
    @Override
    public HolderDataClientTicket onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_client_tickets,parent,false);
        HolderDataClientTicket holder = new HolderDataClientTicket(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderDataClientTicket holder, int position) {
        ClientsTicketsModel ctm = clientsTicketsModelList.get(position);
        holder.tvid.setText(String.valueOf(ctm.getId()));
        holder.tvadminid.setText(String.valueOf(ctm.getAdminid()));
        holder.tvdepartmentid.setText(String.valueOf(ctm.getDepartmentid()));
        holder.tvclientid.setText(String.valueOf(ctm.getClientid()));
        holder.tvuserid.setText(String.valueOf(ctm.getUserid()));
        holder.tvassetid.setText(String.valueOf(ctm.getAssetid()));
        holder.tvprojectid.setText(String.valueOf(ctm.getProjectid()));
        holder.tvdepartmentid.setText(String.valueOf(ctm.getDepartmentid()));
        holder.tvtimespent.setText(String.valueOf(ctm.getTimespent()));
        holder.tvnama.setText(ctm.getNama());
        holder.tvsubject.setText(ctm.getSubject());
        holder.tvsubmitter.setText(ctm.getEmail());
        switch (ctm.getPriority()){
            case "Normal":
                holder.tvticket.setText(String.valueOf(ctm.getTicket()));
                holder.tvticket.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(ctx,R.drawable.normal_prio),null,null,null);
                break;
            case "Low":
                holder.tvticket.setText(String.valueOf(ctm.getTicket()));
                holder.tvticket.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(ctx,R.drawable.low_prio),null,null,null);
                break;
            case "High":
                holder.tvticket.setText(String.valueOf(ctm.getTicket()));
                holder.tvticket.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(ctx,R.drawable.high_prio),null,null,null);
                break;
        }
        switch (ctm.getStatus()){
            case "Closed":
                holder.tvstatusname.setText(ctm.getStatus());
                holder.tvstatusname.setBackground(ContextCompat.getDrawable(ctx,R.drawable.bg_status_done));
                holder.tvstatusname.setTextColor(ContextCompat.getColor(ctx,R.color.black));
                break;
            case "In Progress":
                holder.tvstatusname.setText(ctm.getStatus());
                holder.tvstatusname.setBackground(ContextCompat.getDrawable(ctx,R.drawable.bg_status_broken));
                break;
            case "Answered":
                holder.tvstatusname.setText(ctm.getStatus());
                holder.tvstatusname.setBackground(ContextCompat.getDrawable(ctx,R.drawable.bg_status_deployed));
                break;
            case "Open":
                holder.tvstatusname.setText(ctm.getStatus());
                holder.tvstatusname.setBackground(ContextCompat.getDrawable(ctx,R.drawable.bg_status_pending));
                break;
            case "Reopened":
                holder.tvstatusname.setText(ctm.getStatus());
                holder.tvstatusname.setBackground(ContextCompat.getDrawable(ctx,R.drawable.bg_status_reopened));
                break;
            default:
                holder.tvstatusname.setText(" - ");
                break;
        }
        holder.tvtimestamp.setText(ctm.getTimestamp());
        holder.tvccs.setText(ctm.getCcs());
        holder.tvnotes.setText(ctm.getNotes());
    }

    @Override
    public int getItemCount() {
        return clientsTicketsModelList.size();
    }

    public class HolderDataClientTicket extends RecyclerView.ViewHolder{
        private TextView tvid,tvnama,tvticket,tvsubject,tvsubmitter,tvstatusname
                ,tvadminid,tvdepartmentid,tvclientid,tvuserid,tvassetid,tvprojectid,tvtimestamp
                ,tvnotes,tvccs,tvtimespent;
        private ImageButton btnHapus,btnEdit;
        public HolderDataClientTicket(@NonNull View itemView) {
            super(itemView);
            init(itemView);

        }
        private void init(@NonNull View itemView) {
            tvid = itemView.findViewById(R.id.tv_id_client_ticket);
            tvnama = itemView.findViewById(R.id.tv_assignedto_license_client);
            tvadminid = itemView.findViewById(R.id.tv_idadmin_client_ticket);
            tvticket = itemView.findViewById(R.id.tv_ticket_client_ticket);
            tvsubject = itemView.findViewById(R.id.tv_subject_ticket_client);
            tvsubmitter = itemView.findViewById(R.id.tv_submitter_license_client);
            tvstatusname = itemView.findViewById(R.id.tv_statusname_client_ticket);
            tvdepartmentid = itemView.findViewById(R.id.tv_iddepartment_client_ticket);
            tvclientid = itemView.findViewById(R.id.tv_idclient_client_ticket);
            tvuserid = itemView.findViewById(R.id.tv_iduser_client_ticket);
            tvassetid = itemView.findViewById(R.id.tv_idasset_client_ticket);
            tvprojectid = itemView.findViewById(R.id.tv_idproject_client_ticket);
            tvtimestamp = itemView.findViewById(R.id.tv_timestamp_client_ticket);
            tvnotes = itemView.findViewById(R.id.tv_notes_client_ticket);
            tvccs = itemView.findViewById(R.id.tv_ccs_client_ticket);
            tvtimespent = itemView.findViewById(R.id.tv_timespent_client_ticket);
            btnEdit = itemView.findViewById(R.id.btn_edit_ticket_client);
            btnHapus = itemView.findViewById(R.id.btn_delete_ticket_client);
        }
    }


}
