package com.example.it_management.ui.Inventory.Credentials;

import android.content.Context;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.it_management.R;

import java.util.List;

public class CredentialsAdapterData extends RecyclerView.Adapter<CredentialsAdapterData.HolderDataCredential> {
    private Context ctx;
    private List<CredentialsModel> credentialsModelList;

    public CredentialsAdapterData(Context ctx, List<CredentialsModel> credentialsModelList) {
        this.ctx = ctx;
        this.credentialsModelList = credentialsModelList;
    }

    @NonNull
    @Override
    public HolderDataCredential onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_credentials,parent,false);
        HolderDataCredential holder = new HolderDataCredential(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderDataCredential holder, int position) {
        CredentialsModel cr = credentialsModelList.get(position);
        holder.tvid.setText(String.valueOf(cr.getId()));
        holder.tvtype.setText(cr.getType());
        holder.tvusername.setText(cr.getUsername());
        holder.tvpassword.setText(cr.getPassword());
        holder.tvasset.setText(cr.getNama_assets());
        holder.tvclient.setText(cr.getNama_client());
    }

    @Override
    public int getItemCount() {
        return credentialsModelList.size();
    }

    public class HolderDataCredential extends RecyclerView.ViewHolder{
        private TextView tvid,tvclient,tvasset,tvtype,tvusername,tvpassword;
        public HolderDataCredential(@NonNull View itemView) {
            super(itemView);
            tvid = itemView.findViewById(R.id.tv_id_credential);
            tvclient = itemView.findViewById(R.id.tv_client_credential);
            tvasset = itemView.findViewById(R.id.tv_asset_credential);
            tvtype = itemView.findViewById(R.id.tv_type_credential);
            tvusername = itemView.findViewById(R.id.tv_username_credential);
            tvpassword = itemView.findViewById(R.id.tv_password_credential);
        }
    }
}
