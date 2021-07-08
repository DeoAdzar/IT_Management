package com.example.it_management.ui.Clients.FragmentDetail.ClientCredential;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.it_management.R;

import java.util.List;

public class   ClientCredentialAdapterData extends RecyclerView.Adapter<ClientCredentialAdapterData.HolderClientsAdapterDataCredential>{
    private Context ctx;
    private List<ClientCredentialModel> credentialModelList ;

    public ClientCredentialAdapterData(Context ctx, List<ClientCredentialModel> credentialModelList) {
        this.ctx = ctx;
        this.credentialModelList = credentialModelList;
    }

    @NonNull
    @Override
    public HolderClientsAdapterDataCredential onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_client_credentials, parent, false);
        ClientCredentialAdapterData.HolderClientsAdapterDataCredential holder = new HolderClientsAdapterDataCredential(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderClientsAdapterDataCredential holder, int position) {
        ClientCredentialModel ccm = credentialModelList.get(position);

        holder.tvid.setText(String.valueOf(ccm.getId()));
        holder.tvidasset.setText(String.valueOf(ccm.getAssetId()));
        holder.tvidClient.setText(String.valueOf(ccm.getClientId()));
        holder.tvtype.setText(ccm.getType());
        holder.tvusername.setText(ccm.getUsername());
        holder.tvpassword.setText(ccm.getPassword());
    }

    @Override
    public int getItemCount() {
        return credentialModelList.size();
    }

    public class HolderClientsAdapterDataCredential extends RecyclerView.ViewHolder{
        TextView tvid,tvidClient,tvasset,tvidasset,tvtype,tvusername,tvpassword;
        public HolderClientsAdapterDataCredential(@NonNull View itemView) {
            super(itemView);
            init(itemView);
        }
        private void init(@NonNull View itemView){
            tvid = itemView.findViewById(R.id.tv_credentialId);
            tvidClient = itemView.findViewById(R.id.tv_id_clientcredential);
            tvidasset = itemView.findViewById(R.id.tv_assetid_credential);
            tvasset = itemView.findViewById(R.id.tv_credential_asset);
            tvtype = itemView.findViewById(R.id.tv_credential_type);
            tvusername = itemView.findViewById(R.id.tv_credential_username);
            tvpassword = itemView.findViewById(R.id.tv_credential_password);
        }
    }

}
