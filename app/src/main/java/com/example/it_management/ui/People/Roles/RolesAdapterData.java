package com.example.it_management.ui.People.Roles;

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

public class RolesAdapterData extends RecyclerView.Adapter<RolesAdapterData.HolderDataRoles>{
    private Context ctx;
    private List<RolesModel> rolesModelList;

    public RolesAdapterData(Context ctx, List<RolesModel> rolesModelList) {
        this.ctx = ctx;
        this.rolesModelList = rolesModelList;
    }

    @NonNull
    @Override
    public HolderDataRoles onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(ctx).inflate(R.layout.card_item_roles,parent,false);
        HolderDataRoles holder = new HolderDataRoles(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull HolderDataRoles holder, int position) {
        RolesModel rm = rolesModelList.get(position);
        holder.tvid.setText(String.valueOf(rm.getId()));
        holder.tvnama.setText(rm.getName());
        holder.tvtype.setText(rm.getType());
    }

    @Override
    public int getItemCount() {
        return rolesModelList.size();
    }

    public class HolderDataRoles extends RecyclerView.ViewHolder{
        private TextView tvid,tvnama,tvtype;
        public HolderDataRoles(@NonNull @NotNull View itemView) {
            super(itemView);
            tvid = itemView.findViewById(R.id.tv_id_roles);
            tvnama = itemView.findViewById(R.id.tv_nama_roles);
            tvtype = itemView.findViewById(R.id.tv_type_roles);
        }
    }
}
