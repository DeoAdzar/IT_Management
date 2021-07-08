package com.example.it_management.ui.Clients;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.it_management.API.BaseApiService;
import com.example.it_management.API.UtilsApi;
import com.example.it_management.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClientsAdapterData extends RecyclerView.Adapter<ClientsAdapterData.HolderDataClient>{
    private Context ctx;
    private List<ClientsModel> listClient;
    private int idClients;
    private String id,Inama,Iasset_tag,Ilicense_tag,Inotes;

    public ClientsAdapterData(Context ctx, List<ClientsModel> listClient) {
        this.ctx = ctx;
        this.listClient = listClient;
    }

    @NonNull
    @Override
    public HolderDataClient onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_client,parent,false);
        HolderDataClient holder = new HolderDataClient(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderDataClient holder, int position) {
        ClientsModel cm = listClient.get(position);
        holder.tvId.setText(String.valueOf(cm.getId()));
        holder.tvNama.setText(cm.getName());
        holder.tvLicense_tag_prefix.setText(cm.getLicense_tag_prefix());
        holder.tvAsset_tag_prefix.setText(cm.getAsset_tag_prefix());
        holder.tvNotes.setText(cm.getNotes());



    }

    @Override
    public int getItemCount() {
        return listClient.size();
    }



    public class HolderDataClient extends RecyclerView.ViewHolder{
        TextView tvno,tvId,tvNama,tvAsset_tag_prefix,tvLicense_tag_prefix, tvNotes;
        ImageButton btnHapus,btnEdit;
        public HolderDataClient(@NonNull View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tv_idClient);
            tvNama = itemView.findViewById(R.id.tv_namaClient);
            tvAsset_tag_prefix = itemView.findViewById(R.id.tv_asset_tag_prefix);
            tvLicense_tag_prefix = itemView.findViewById(R.id.tv_license_tag_prefix);
            tvNotes = itemView.findViewById(R.id.tv_notes);
            btnHapus = itemView.findViewById(R.id.btn_delete_client);
            btnEdit = itemView.findViewById(R.id.btn_edit_client);
            btnEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    id = tvId.getText().toString();
                    Inama = tvNama.getText().toString();
                    Iasset_tag = tvAsset_tag_prefix.getText().toString();
                    Ilicense_tag = tvLicense_tag_prefix.getText().toString();
                    Inotes = tvNotes.getText().toString();

                    Intent i = new Intent(ctx,EditClientsActivity.class);
                    i.putExtra("id", id);
                    i.putExtra("name", Inama);
                    i.putExtra("asset_tag", Iasset_tag);
                    i.putExtra("license_tag", Ilicense_tag);
                    i.putExtra("notes", Inotes);
                    ctx.startActivity(i);
                }
            });
            btnHapus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    idClients = Integer.parseInt(tvId.getText().toString());
                    Inama = tvNama.getText().toString();
                    konfirmasi(Inama);
                    notifyDataSetChanged();
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    id = tvId.getText().toString();
                    idClients = Integer.parseInt(id);
                    Inama = tvNama.getText().toString();
                    Intent i = new Intent(ctx,DetailActivityClients.class);
                    i.putExtra("id", id);
                    i.putExtra("name", Inama);
                    i.putExtra("notes",tvNotes.getText().toString());
                    i.putExtra("asset_tag", tvAsset_tag_prefix.getText().toString());
                    i.putExtra("license_tag", tvLicense_tag_prefix.getText().toString());
                    ctx.startActivity(i);

                }
            });
        }
    }

    private void konfirmasi(String nama){
        final AlertDialog.Builder alert = new AlertDialog.Builder(ctx);
        alert.setTitle("Delete");
        alert.setIcon(R.drawable.ic_delete);
        alert.setMessage("Apakah anda yakin ingin menghapus data "+nama+" ?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteClients();
                        Toast.makeText(ctx, nama+" has been removed, please refresh page", Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                        ClientsFragment.cf.refresh();
                    }
                })
                .setNegativeButton("No", null);
        alert.show();
    }
    private void deleteClients(){
        BaseApiService mApiService = UtilsApi.getApiService();
        Call<ClientsResponseModel> deleteClient = mApiService.basDeleteClients(idClients);
        deleteClient.enqueue(new Callback<ClientsResponseModel>() {
            @Override
            public void onResponse(Call<ClientsResponseModel> call, Response<ClientsResponseModel> response) {

            }

            @Override
            public void onFailure(Call<ClientsResponseModel> call, Throwable t) {
                Toast.makeText(ctx, "Gagal menghubungi server | response : "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
