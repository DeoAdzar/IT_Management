package com.example.it_management.ui.Inventory.Licences;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.it_management.R;

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

        holder.tvtag.setText(lm.getTag());
        holder.tvcategory.setText(lm.getNama_category());
        holder.tvnama.setText(lm.getName());

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
        private TextView tvtag,tvcategory,tvnama,tvseats,tvclient,tvstatus;
        public HolderDataLicense(@NonNull View itemView) {
            super(itemView);
            tvtag = itemView.findViewById(R.id.tv_tag_license);
            tvcategory = itemView.findViewById(R.id.tv_category_license);
            tvnama = itemView.findViewById(R.id.tv_name_license);
            tvseats = itemView.findViewById(R.id.tv_seats_license);
            tvclient = itemView.findViewById(R.id.tv_client_license);
            tvstatus = itemView.findViewById(R.id.tv_statusname_license);
        }
    }
}
