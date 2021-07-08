package com.example.it_management.ui.People.Staff;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.it_management.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class StaffAdapterData extends RecyclerView.Adapter<StaffAdapterData.HolderDataStaff>{
    private Context ctx;
    private List<StaffModel> staffModelList;

    public StaffAdapterData(Context ctx, List<StaffModel> staffModelList) {
        this.ctx = ctx;
        this.staffModelList = staffModelList;
    }

    @NonNull
    @Override
    public HolderDataStaff onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(ctx).inflate(R.layout.card_item_staff,parent,false);
        HolderDataStaff holder = new HolderDataStaff(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull HolderDataStaff holder, int position) {
        StaffModel sm = staffModelList.get(position);
        holder.id.setText(String.valueOf(sm.getId()));
        holder.nama.setText(sm.getName());
        holder.email.setText(sm.getEmail());
        holder.role.setText(sm.getNamaRole());
        holder.title.setText(sm.getTitle());
    }

    @Override
    public int getItemCount() {
        return staffModelList.size();
    }

    public class HolderDataStaff extends RecyclerView.ViewHolder{
        private TextView id,nama,email,title,role;
        private ImageButton edit,hapus;
        public HolderDataStaff(@NonNull @NotNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.tv_id_staff);
            nama = itemView.findViewById(R.id.tv_nama_staff);
            email = itemView.findViewById(R.id.tv_email_staff);
            title = itemView.findViewById(R.id.tv_title_staff);
            role = itemView.findViewById(R.id.tv_role_staff);
        }
    }
}
