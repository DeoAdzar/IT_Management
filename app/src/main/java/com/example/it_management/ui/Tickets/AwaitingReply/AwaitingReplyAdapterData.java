package com.example.it_management.ui.Tickets.AwaitingReply;

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

public class AwaitingReplyAdapterData extends RecyclerView.Adapter<AwaitingReplyAdapterData.HolderDataAwaitingReply>{
    private Context ctx;
    private List<AwaitingReplyModel> awaitingReplyModelList ;

    public AwaitingReplyAdapterData(Context ctx, List<AwaitingReplyModel> awaitingReplyModelList) {
        this.ctx = ctx;
        this.awaitingReplyModelList = awaitingReplyModelList;
    }

    @NonNull
    @Override
    public HolderDataAwaitingReply onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_awaiting_reply,parent,false);
        HolderDataAwaitingReply holder = new HolderDataAwaitingReply(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderDataAwaitingReply holder, int position) {
        AwaitingReplyModel arm  = awaitingReplyModelList.get(position);
        holder.tvnama.setText(arm.getNama());
        holder.tvsubject.setText(arm.getSubject());
        holder.tvsubmitter.setText(arm.getEmail());
        switch (arm.getPriority()){
            case "Normal":
                holder.tvticket.setText(String.valueOf(arm.getTicket()));
                holder.tvticket.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(ctx,R.drawable.normal_prio),null,null,null);
                break;
            case "Low":
                holder.tvticket.setText(String.valueOf(arm.getTicket()));
                holder.tvticket.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(ctx,R.drawable.low_prio),null,null,null);
                break;
            case "High":
                holder.tvticket.setText(String.valueOf(arm.getTicket()));
                holder.tvticket.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(ctx,R.drawable.high_prio),null,null,null);
                break;
        }
        switch (arm.getStatus()){
            case "Closed":
                holder.tvstatusname.setText(arm.getStatus());
                holder.tvstatusname.setBackground(ContextCompat.getDrawable(ctx,R.drawable.bg_status_done));
                holder.tvstatusname.setTextColor(ContextCompat.getColor(ctx,R.color.black));
                break;
            case "In Progress":
                holder.tvstatusname.setText(arm.getStatus());
                holder.tvstatusname.setBackground(ContextCompat.getDrawable(ctx,R.drawable.bg_status_broken));
                break;
            case "Answered":
                holder.tvstatusname.setText(arm.getStatus());
                holder.tvstatusname.setBackground(ContextCompat.getDrawable(ctx,R.drawable.bg_status_deployed));
                break;
            case "Open":
                holder.tvstatusname.setText(arm.getStatus());
                holder.tvstatusname.setBackground(ContextCompat.getDrawable(ctx,R.drawable.bg_status_pending));
                break;
            case "Reopened":
                holder.tvstatusname.setText(arm.getStatus());
                holder.tvstatusname.setBackground(ContextCompat.getDrawable(ctx,R.drawable.bg_status_reopened));
                break;
            default:
                holder.tvstatusname.setText(" - ");
                break;
        }
    }

    @Override
    public int getItemCount() {
        return awaitingReplyModelList.size();
    }

    public class HolderDataAwaitingReply extends RecyclerView.ViewHolder{
        private TextView tvnama,tvticket,tvsubject,tvsubmitter,tvstatusname;
        private ImageButton btnHapus,btnEdit;
        public HolderDataAwaitingReply(@NonNull View itemView) {
            super(itemView);
            init(itemView);

        }
        private void init(@NonNull View itemView) {
            tvnama = itemView.findViewById(R.id.tv_assignedto_awaiting_reply);
            tvticket = itemView.findViewById(R.id.tv_ticket_awaiting_reply);
            tvsubject = itemView.findViewById(R.id.tv_subject_awaiting_reply);
            tvsubmitter = itemView.findViewById(R.id.tv_submitter_awaiting_reply);
            tvstatusname = itemView.findViewById(R.id.tv_statusname_awaiting_reply);
            btnEdit = itemView.findViewById(R.id.btn_edit_awaiting_reply);
            btnHapus = itemView.findViewById(R.id.btn_delete_awaiting_reply);
        }
    }


}
