package com.example.it_management.ui.Inventory.Licences;

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


public class LicensesAdapterData extends RecyclerView.Adapter<LicensesAdapterData.HolderDataLicense>{
    private Context ctx;
    private List<LicencesModel> licencesModelList ;

    public LicensesAdapterData(Context ctx, List<LicencesModel> licencesModelList) {
        this.ctx = ctx;
        this.licencesModelList = licencesModelList;
    }

    @NonNull
    @Override
    public HolderDataLicense onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_licenses,parent,false);
        HolderDataLicense holder = new HolderDataLicense(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderDataLicense holder, int position) {
        LicencesModel lm = licencesModelList.get(position);
        holder.tvid.setText(lm.getId());
        holder.tvidclient.setText(lm.getClientid());
        holder.tvidcategory.setText(lm.getCategoryid());
        holder.tvidsupp.setText(lm.getSupplierid());
        holder.tvserial.setText(lm.getSerial());
        holder.tvnotes.setText(lm.getNotes());
        holder.tvtag.setText(lm.getTag());
        holder.tvcategory.setText(lm.getNama_category());
        holder.tvnama.setText(lm.getName());
        holder.tvidstatus.setText(lm.getStatusid());
        if (lm.getSeats().equals("")){
            holder.tvseats.setText("N/A");
            holder.tvseats.setTextColor(ContextCompat.getColor(ctx,R.color.none));
        }else{
            holder.tvseats.setText(lm.getSeats());
        }
        if (lm.getNama_client() == null){
            holder.tvclient.setText("N/A");
            holder.tvclient.setTextColor(ContextCompat.getColor(ctx,R.color.none));
        }else{
            holder.tvclient.setText(lm.getNama_client());
        }
        switch (lm.getNama_status()){
            case "Requested":
                holder.tvstatus.setText(lm.getNama_status());
                holder.tvstatus.setBackground(ContextCompat.getDrawable(ctx,R.drawable.bg_status_request));
                break;
            case "Pending":
                holder.tvstatus.setText(lm.getNama_status());
                holder.tvstatus.setBackground(ContextCompat.getDrawable(ctx,R.drawable.bg_status_pending));
                break;
            case "Deployed":
                holder.tvstatus.setText(lm.getNama_status());
                holder.tvstatus.setBackground(ContextCompat.getDrawable(ctx,R.drawable.bg_status_deployed));
                break;
            case "Archived":
                holder.tvstatus.setText(lm.getNama_status());
                holder.tvstatus.setBackground(ContextCompat.getDrawable(ctx,R.drawable.bg_status_archived));
                break;
            case "In Repair":
                holder.tvstatus.setText(lm.getNama_status());
                holder.tvstatus.setBackground(ContextCompat.getDrawable(ctx,R.drawable.bg_status_repair));
                break;
            case "Broken":
                holder.tvstatus.setText(lm.getNama_status());
                holder.tvstatus.setBackground(ContextCompat.getDrawable(ctx,R.drawable.bg_status_broken));
                break;
            default:
                holder.tvstatus.setText(" - ");
                break;
        }
    }

    @Override
    public int getItemCount() {
        return licencesModelList.size();
    }

    public class HolderDataLicense extends RecyclerView.ViewHolder{
        private TextView tvtag,tvcategory,tvnama,tvseats,tvclient,tvstatus,tvidstatus,tvid,tvidclient,tvidcategory,tvidsupp,tvserial,tvnotes;
        ImageButton btnEdit,btnDelete;
        public HolderDataLicense(@NonNull View itemView) {
            super(itemView);
            tvid = itemView.findViewById(R.id.tv_id_license);
            tvtag = itemView.findViewById(R.id.tv_tag_license);
            tvcategory = itemView.findViewById(R.id.tv_category_license);
            tvnama = itemView.findViewById(R.id.tv_name_license);
            tvseats = itemView.findViewById(R.id.tv_seats_license);
            tvclient = itemView.findViewById(R.id.tv_client_license);
            tvstatus = itemView.findViewById(R.id.tv_statusname_license);
            tvidstatus =itemView.findViewById(R.id.tv_idstatus_license);
            tvidcategory = itemView.findViewById(R.id.tv_categoryid_license);
            tvidclient = itemView.findViewById(R.id.tv_idclient_license);
            tvidsupp = itemView.findViewById(R.id.tv_supplierid_license);
            tvnotes = itemView.findViewById(R.id.tv_notes_license);
            tvserial =  itemView.findViewById(R.id.tv_serial_license);
            btnEdit = itemView.findViewById(R.id.btn_edit_license);
            btnDelete = itemView.findViewById(R.id.btn_delete_license);
            btnEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(ctx, EditLicense.class);
                    i.putExtra("idLicense",tvid.getText().toString());
                    i.putExtra("idClient",tvidclient.getText().toString());
                    i.putExtra("idCategory",tvidcategory.getText().toString());
                    i.putExtra("idSupp",tvidsupp.getText().toString());
                    i.putExtra("idStatus",tvidstatus.getText().toString());
                    i.putExtra("tag",tvtag.getText().toString());
                    i.putExtra("nama",tvnama.getText().toString());
                    i.putExtra("seat",tvseats.getText().toString());
                    i.putExtra("serial",tvserial.getText().toString());
                    i.putExtra("note",tvnotes.getText().toString());
                    ctx.startActivity(i);
                }
            });
        }
    }
}
