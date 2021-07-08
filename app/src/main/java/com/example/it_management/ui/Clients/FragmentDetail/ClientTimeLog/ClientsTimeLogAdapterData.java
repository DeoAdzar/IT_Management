package com.example.it_management.ui.Clients.FragmentDetail.ClientTimeLog;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.it_management.R;

import java.util.List;


public class ClientsTimeLogAdapterData extends RecyclerView.Adapter<ClientsTimeLogAdapterData.HolderDataClientTimeLog>{
    private Context ctx;
    private List<ClientsTimeLogModel> clientsTimeLogModelList;

    public ClientsTimeLogAdapterData(Context ctx, List<ClientsTimeLogModel> clientsTimeLogModelList) {
        this.ctx = ctx;
        this.clientsTimeLogModelList = clientsTimeLogModelList;
    }

    @NonNull
    @Override
    public HolderDataClientTimeLog onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_client_timelog,parent,false);
        HolderDataClientTimeLog holder = new HolderDataClientTimeLog(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderDataClientTimeLog holder, int position) {
        ClientsTimeLogModel tlm = clientsTimeLogModelList.get(position);
        holder.tvid.setText(String.valueOf(tlm.getId()));
        holder.tvstaffid.setText(String.valueOf(tlm.getStaffid()));
        holder.tvprojectid.setText(String.valueOf(tlm.getProjectid()));
        holder.tvidclient.setText(String.valueOf(tlm.getClientid()));
        holder.tvasset.setText(tlm.getNama());
        holder.tvdate.setText(tlm.getDate());
        holder.tvtime.setText(tlm.getStart()+ " --> "+tlm.getEnd());
        holder.tvdescription.setText(Html.fromHtml(tlm.getDescription()));
    }

    @Override
    public int getItemCount() {
        return clientsTimeLogModelList.size();
    }

    public class HolderDataClientTimeLog extends RecyclerView.ViewHolder{
        private TextView tvid,tvdate,tvtime,tvdescription,tvidclient,tvprojectid,tvstaffid,tvasset;
        public HolderDataClientTimeLog(@NonNull View itemView) {
            super(itemView);
            tvid = itemView.findViewById(R.id.tv_id_timelog_client);
            tvdate = itemView.findViewById(R.id.tv_date_timelog_client);
            tvtime = itemView.findViewById(R.id.tv_time_timelog_client);
            tvasset = itemView.findViewById(R.id.tv_asset_timelog_client);
            tvdescription = itemView.findViewById(R.id.tv_description_timelog_client);
            tvidclient = itemView.findViewById(R.id.tv_idclient_client_timelog);
            tvprojectid = itemView.findViewById(R.id.tv_projectid_client_timelog);
            tvstaffid = itemView.findViewById(R.id.tv_staffid_client_timelog);
        }
    }
}
