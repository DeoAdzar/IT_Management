package com.example.it_management.ui.People.Users;

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

public class UsersAdapterData extends RecyclerView.Adapter<UsersAdapterData.HolderDataUser>{
    private Context ctx;
    private List<UsersModel> usersModelList;

    public UsersAdapterData(Context ctx, List<UsersModel> usersModelList) {
        this.ctx = ctx;
        this.usersModelList = usersModelList;
    }

    @NonNull
    @Override
    public HolderDataUser onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(ctx).inflate(R.layout.card_item_user,parent,false);
        HolderDataUser holder = new HolderDataUser(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderDataUser holder, int position) {
        UsersModel um = usersModelList.get(position);
        holder.id.setText(String.valueOf(um.getId()));
        holder.client.setText(um.getNamaClient());
        holder.nama.setText(um.getName());
        holder.email.setText(um.getEmail());
        holder.role.setText(um.getNamaRole());
    }

    @Override
    public int getItemCount() {
        return usersModelList.size();
    }

    public class HolderDataUser extends RecyclerView.ViewHolder{
        private TextView id,client,nama,role,email;
        public HolderDataUser(@NonNull @NotNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.tv_id_user);
            client = itemView.findViewById(R.id.tv_client_user);
            nama = itemView.findViewById(R.id.tv_nama_user);
            role = itemView.findViewById(R.id.tv_role_user);
            email = itemView.findViewById(R.id.tv_email_user);
        }
    }
}
