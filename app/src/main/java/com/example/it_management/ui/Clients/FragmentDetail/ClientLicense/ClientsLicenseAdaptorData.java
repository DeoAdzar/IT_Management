package com.example.it_management.ui.Clients.FragmentDetail.ClientLicense;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.it_management.R;
import com.example.it_management.ui.EditActivity.EditLicense;

import java.util.List;

public class ClientsLicenseAdaptorData extends RecyclerView.Adapter<ClientsLicenseAdaptorData.HolderDataClientsLicense>{
    private Context ctx;
    private List<ClientsLicenseModel> clientsLicenseModelList;

    public ClientsLicenseAdaptorData(Context ctx, List<ClientsLicenseModel> clientsLicenseModelList) {
        this.ctx = ctx;
        this.clientsLicenseModelList = clientsLicenseModelList;
    }

    @NonNull
    @Override
    public HolderDataClientsLicense onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_client_licenses,parent,false);
        HolderDataClientsLicense holder = new HolderDataClientsLicense(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderDataClientsLicense holder, int position) {
        ClientsLicenseModel clm = clientsLicenseModelList.get(position);

        holder.tvid.setText(String.valueOf(clm.getId()));
        holder.tvsupplierid.setText(String.valueOf(clm.getSupplierid()));
        holder.tvstatusid.setText(String.valueOf(clm.getStatusid()));
        holder.tvcategoryid.setText(String.valueOf(clm.getCategoryid()));
        holder.tvclientid.setText(String.valueOf(clm.getClientid()));
        holder.tvtag.setText(clm.getTag());
        holder.tvname.setText(clm.getName());
        holder.tvcategoryname.setText(clm.getNama());
        holder.tvseats.setText(clm.getSeats());
        holder.tvserial.setText(clm.getSerial());
        holder.tvcustomfields.setText(clm.getCustomfields());
        holder.tvqrvalues.setText(clm.getQrvalue());
        holder.tvnote.setText(clm.getNotes());
        switch (clm.getStatusid()){
            case 1:
                holder.tvstatusname.setText("Requested");
                holder.tvstatusname.setBackground(ContextCompat.getDrawable(ctx,R.drawable.bg_status_request));
                break;
            case 2:
                holder.tvstatusname.setText("Pending");
                holder.tvstatusname.setBackground(ContextCompat.getDrawable(ctx,R.drawable.bg_status_pending));
                break;
            case 3:
                holder.tvstatusname.setText("Deployed");
                holder.tvstatusname.setBackground(ContextCompat.getDrawable(ctx,R.drawable.bg_status_deployed));
                break;
            case 4:
                holder.tvstatusname.setText("Archived");
                holder.tvstatusname.setBackground(ContextCompat.getDrawable(ctx,R.drawable.bg_status_archived));
                break;
            case 5:
                holder.tvstatusname.setText("In Repair");
                holder.tvstatusname.setBackground(ContextCompat.getDrawable(ctx,R.drawable.bg_status_repair));
                break;
            case 6:
                holder.tvstatusname.setText("Broken");
                holder.tvstatusname.setBackground(ContextCompat.getDrawable(ctx,R.drawable.bg_status_broken));
                break;
            default:
                holder.tvstatusname.setText(" - ");
                break;
        }
    }

    @Override
    public int getItemCount() {
        return clientsLicenseModelList.size();
    }

    public class HolderDataClientsLicense extends RecyclerView.ViewHolder{
        TextView tvtag,tvname,tvcategoryname,tvid,tvclientid,tvnote,tvcategoryid,tvstatusid,tvsupplierid,tvseats
                ,tvserial,tvcustomfields,tvqrvalues,tvstatusname;
        ImageButton btnEdit,btnDelete;
        public HolderDataClientsLicense(@NonNull View itemView) {
            super(itemView);
            tvstatusname = itemView.findViewById(R.id.tv_statusname_client_license);
            tvtag = itemView.findViewById(R.id.tv_tag_license_client);
            tvname = itemView.findViewById(R.id.tv_name_license_client);
            tvcategoryname = itemView.findViewById(R.id.tv_category_license_client);
            tvid = itemView.findViewById(R.id.tv_id_client_license);
            tvclientid = itemView.findViewById(R.id.tv_idclient_client_license);
            tvnote = itemView.findViewById(R.id.tv_notes_client_license);
            tvcategoryid = itemView.findViewById(R.id.tv_categoryid_client_license);
            tvstatusid = itemView.findViewById(R.id.tv_idstatus_client_license);
            tvsupplierid = itemView.findViewById(R.id.tv_supplierid_client_license);
            tvseats = itemView.findViewById(R.id.tv_seats__client_license);
            tvserial = itemView.findViewById(R.id.tv_serial_client_license);
            tvcustomfields = itemView.findViewById(R.id.tv_customfields_client_license);
            tvqrvalues = itemView.findViewById(R.id.tv_qrvalue_client_license);
            btnEdit = itemView.findViewById(R.id.btn_edit_license_client);
            btnDelete = itemView.findViewById(R.id.btn_delete_license_client);
            btnEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(ctx, EditLicense.class);
                    i.putExtra("idLicense",tvid.getText().toString());
                    i.putExtra("idClient",tvclientid.getText().toString());
                    i.putExtra("idCategory",tvcategoryid.getText().toString());
                    i.putExtra("idSupp",tvsupplierid.getText().toString());
                    i.putExtra("idStatus",tvstatusid.getText().toString());
                    i.putExtra("tag",tvtag.getText().toString());
                    i.putExtra("nama",tvname.getText().toString());
                    i.putExtra("seat",tvseats.getText().toString());
                    i.putExtra("serial",tvserial.getText().toString());
                    i.putExtra("note",tvnote.getText().toString());
                    ctx.startActivity(i);
                }
            });
        }
    }
}
