package com.example.it_management.ui.Tickets.ActiveTickets;

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
import com.example.it_management.ui.Tickets.ActiveTickets.ActiveTicketsModel;

import java.util.List;

public class ActiveTicketsAdapterData extends RecyclerView.Adapter<ActiveTicketsAdapterData.HolderDataActiveTickets>{
    private Context ctx;
    private List<ActiveTicketsModel> ActiveTicketsModelList ;

    public ActiveTicketsAdapterData(Context ctx, List<ActiveTicketsModel> ActiveTicketsModelList) {
        this.ctx = ctx;
        this.ActiveTicketsModelList = ActiveTicketsModelList;
    }

    @NonNull
    @Override
    public HolderDataActiveTickets onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_active_tickets,parent,false);
        HolderDataActiveTickets holder = new HolderDataActiveTickets(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderDataActiveTickets holder, int position) {
        ActiveTicketsModel atm  = ActiveTicketsModelList.get(position);
        holder.tvnama.setText(atm.getNama());
        holder.tvsubject.setText(atm.getSubject());
        holder.tvsubmitter.setText(atm.getEmail());
        switch (atm.getPriority()){
            case "Normal":
                holder.tvticket.setText(String.valueOf(atm.getTicket()));
                holder.tvticket.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(ctx,R.drawable.normal_prio),null,null,null);
                break;
            case "Low":
                holder.tvticket.setText(String.valueOf(atm.getTicket()));
                holder.tvticket.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(ctx,R.drawable.low_prio),null,null,null);
                break;
            case "High":
                holder.tvticket.setText(String.valueOf(atm.getTicket()));
                holder.tvticket.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(ctx,R.drawable.high_prio),null,null,null);
                break;
        }
        switch (atm.getStatus()){
            case "Closed":
                holder.tvstatusname.setText(atm.getStatus());
                holder.tvstatusname.setBackground(ContextCompat.getDrawable(ctx,R.drawable.bg_status_done));
                holder.tvstatusname.setTextColor(ContextCompat.getColor(ctx,R.color.black));
                break;
            case "In Progress":
                holder.tvstatusname.setText(atm.getStatus());
                holder.tvstatusname.setBackground(ContextCompat.getDrawable(ctx,R.drawable.bg_status_broken));
                break;
            case "Answered":
                holder.tvstatusname.setText(atm.getStatus());
                holder.tvstatusname.setBackground(ContextCompat.getDrawable(ctx,R.drawable.bg_status_deployed));
                break;
            case "Open":
                holder.tvstatusname.setText(atm.getStatus());
                holder.tvstatusname.setBackground(ContextCompat.getDrawable(ctx,R.drawable.bg_status_pending));
                break;
            case "Reopened":
                holder.tvstatusname.setText(atm.getStatus());
                holder.tvstatusname.setBackground(ContextCompat.getDrawable(ctx,R.drawable.bg_status_reopened));
                break;
            default:
                holder.tvstatusname.setText(" - ");
                break;
        }
    }

    @Override
    public int getItemCount() {
        return ActiveTicketsModelList.size();
    }

    public class HolderDataActiveTickets extends RecyclerView.ViewHolder{
        private TextView tvnama,tvticket,tvsubject,tvsubmitter,tvstatusname;
        private ImageButton btnHapus,btnEdit;
        public HolderDataActiveTickets(@NonNull View itemView) {
            super(itemView);
            init(itemView);

        }
        private void init(@NonNull View itemView) {
            tvnama = itemView.findViewById(R.id.tv_assignedto_active_tickets);
            tvticket = itemView.findViewById(R.id.tv_ticket_active_tickets);
            tvsubject = itemView.findViewById(R.id.tv_subject_active_tickets);
            tvsubmitter = itemView.findViewById(R.id.tv_submitter_active_tickets);
            tvstatusname = itemView.findViewById(R.id.tv_statusname_active_tickets);
            btnEdit = itemView.findViewById(R.id.btn_edit_active_tickets);
            btnHapus = itemView.findViewById(R.id.btn_delete_active_tickets);
        }
    }


}
