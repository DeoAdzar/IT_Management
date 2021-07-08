package com.example.it_management.ui.Clients.FragmentDetail.ClientUser;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.it_management.R;

import java.util.List;

public class ClientsUserAdapterData extends RecyclerView.Adapter<ClientsUserAdapterData.HolderDataUserClient>{
    private Context ctx;
    private List<ClientsUserModel> clientsUserModelList;

    public ClientsUserAdapterData(Context ctx, List<ClientsUserModel> clientsUserModelList) {
        this.ctx = ctx;
        this.clientsUserModelList = clientsUserModelList;
    }

    @NonNull
    @Override
    public HolderDataUserClient onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_client_user,parent,false);
        HolderDataUserClient holder = new HolderDataUserClient(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderDataUserClient holder, int position) {
        ClientsUserModel cum = clientsUserModelList.get(position);

        holder.tvid.setText(String.valueOf(cum.getId()));
        holder.tvnama.setText(cum.getName());
        holder.tvemail.setText(cum.getEmail());
    }

    @Override
    public int getItemCount() {
        return clientsUserModelList.size();
    }

    public class HolderDataUserClient extends RecyclerView.ViewHolder{
        private TextView tvid,tvnama,tvemail;
        public HolderDataUserClient(@NonNull View itemView) {
            super(itemView);
            tvid = itemView.findViewById(R.id.tv_id_client_user);
            tvnama = itemView.findViewById(R.id.tv_nama_client_user);
            tvemail = itemView.findViewById(R.id.tv_email_client_user);
        }
    }
}
