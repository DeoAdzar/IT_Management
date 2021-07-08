package com.example.it_management.ui.Inventory.Assets.FragmentDetail.TicketsAssets;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.it_management.R;
import com.example.it_management.ui.Clients.FragmentDetail.ClientTickets.ClientsTicketsModel;

import java.util.List;

public class AssetTicketsAdapterData extends RecyclerView.Adapter<AssetTicketsAdapterData.HolderDataAssetTicket>{
    private Context ctx;
    private List<AssetTicketsModel> assetTicketsModelList ;

    public AssetTicketsAdapterData(Context ctx, List<AssetTicketsModel> assetTicketsModelList) {
        this.ctx = ctx;
        this.assetTicketsModelList = assetTicketsModelList;
    }

    @NonNull
    @Override
    public HolderDataAssetTicket onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_asset_tickets,parent,false);
        HolderDataAssetTicket holder = new HolderDataAssetTicket(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderDataAssetTicket holder, int position) {
        AssetTicketsModel ctm = assetTicketsModelList.get(position);
        holder.tvnama.setText(ctm.getNama());
        holder.tvsubject.setText(ctm.getSubject());
        holder.tvsubmitter.setText(ctm.getEmail());
        switch (ctm.getPriority()){
            case "Normal":
                holder.tvticket.setText(String.valueOf(ctm.getTicket()));
                holder.tvticket.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(ctx,R.drawable.normal_prio),null,null,null);
                break;
            case "Low":
                holder.tvticket.setText(String.valueOf(ctm.getTicket()));
                holder.tvticket.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(ctx,R.drawable.low_prio),null,null,null);
                break;
            case "High":
                holder.tvticket.setText(String.valueOf(ctm.getTicket()));
                holder.tvticket.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(ctx,R.drawable.high_prio),null,null,null);
                break;
        }
        switch (ctm.getStatus()){
            case "Closed":
                holder.tvstatusname.setText(ctm.getStatus());
                holder.tvstatusname.setBackground(ContextCompat.getDrawable(ctx,R.drawable.bg_status_done));
                holder.tvstatusname.setTextColor(ContextCompat.getColor(ctx,R.color.black));
                break;
            case "In Progress":
                holder.tvstatusname.setText(ctm.getStatus());
                holder.tvstatusname.setBackground(ContextCompat.getDrawable(ctx,R.drawable.bg_status_broken));
                break;
            case "Answered":
                holder.tvstatusname.setText(ctm.getStatus());
                holder.tvstatusname.setBackground(ContextCompat.getDrawable(ctx,R.drawable.bg_status_deployed));
                break;
            case "Open":
                holder.tvstatusname.setText(ctm.getStatus());
                holder.tvstatusname.setBackground(ContextCompat.getDrawable(ctx,R.drawable.bg_status_pending));
                break;
            case "Reopened":
                holder.tvstatusname.setText(ctm.getStatus());
                holder.tvstatusname.setBackground(ContextCompat.getDrawable(ctx,R.drawable.bg_status_reopened));
                break;
            default:
                holder.tvstatusname.setText(" - ");
                break;
        }
    }

    @Override
    public int getItemCount() {
        return assetTicketsModelList.size();
    }

    public class HolderDataAssetTicket extends RecyclerView.ViewHolder{
        private TextView tvnama,tvticket,tvsubject,tvsubmitter,tvstatusname;
        private ImageButton btnHapus,btnEdit;
        public HolderDataAssetTicket(@NonNull View itemView) {
            super(itemView);
            init(itemView);

        }
        private void init(@NonNull View itemView) {

            tvnama = itemView.findViewById(R.id.tv_assignedto_license_asset);
            tvticket = itemView.findViewById(R.id.tv_ticket_asset_ticket);
            tvsubject = itemView.findViewById(R.id.tv_subject_ticket_asset);
            tvsubmitter = itemView.findViewById(R.id.tv_submitter_license_asset);
            tvstatusname = itemView.findViewById(R.id.tv_statusname_asset_ticket);
            btnEdit = itemView.findViewById(R.id.btn_edit_ticket_asset);
            btnHapus = itemView.findViewById(R.id.btn_delete_ticket_asset);
        }
    }


}
