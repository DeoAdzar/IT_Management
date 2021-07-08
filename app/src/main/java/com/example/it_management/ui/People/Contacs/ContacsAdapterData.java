package com.example.it_management.ui.People.Contacs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.it_management.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ContacsAdapterData extends RecyclerView.Adapter<ContacsAdapterData.HolderDataContacs>{
    private Context ctx;
    private List<ContacsModel> contacsModelList;

    public ContacsAdapterData(Context ctx, List<ContacsModel> contacsModelList) {
        this.ctx = ctx;
        this.contacsModelList = contacsModelList;
    }

    @NonNull
    @Override
    public HolderDataContacs onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_contacts,parent,false);
        HolderDataContacs holder = new HolderDataContacs(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull HolderDataContacs holder, int position) {
        ContacsModel cm = contacsModelList.get(position);
        holder.tvid.setText(String.valueOf(cm.getId()));
        holder.tvnama.setText(cm.getName());
        holder.tvemail.setText(cm.getEmail());
        holder.tvnohp.setText(cm.getPhone());
    }

    @Override
    public int getItemCount() {
        return contacsModelList.size();
    }

    public class HolderDataContacs extends RecyclerView.ViewHolder{
        private TextView tvid,tvnama,tvemail,tvnohp;
        public HolderDataContacs(@NonNull @NotNull View itemView) {
            super(itemView);
            tvid = itemView.findViewById(R.id.tv_id_contacts);
            tvnama = itemView.findViewById(R.id.tv_nama_contacts);
            tvemail = itemView.findViewById(R.id.tv_email_contacts);
            tvnohp = itemView.findViewById(R.id.tv_nohp_contacts);
        }
    }
}
