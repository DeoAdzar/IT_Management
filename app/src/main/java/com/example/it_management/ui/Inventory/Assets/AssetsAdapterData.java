package com.example.it_management.ui.Inventory.Assets;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.it_management.API.BaseApiService;
import com.example.it_management.API.UtilsApi;
import com.example.it_management.R;
import com.example.it_management.ui.Clients.FragmentDetail.ClientAssets.ClientsAssetAdapterData;
import com.example.it_management.ui.Clients.FragmentDetail.ClientAssets.ClientsAssetModel;
import com.example.it_management.ui.EditActivity.EditAsset;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AssetsAdapterData extends RecyclerView.Adapter<AssetsAdapterData.HolderDataAssets>{
    private Context ctx;
    private List<AssetsModel> assetsModelList;

    public AssetsAdapterData(Context ctx, List<AssetsModel> assetsModelList) {
        this.ctx = ctx;
        this.assetsModelList = assetsModelList;
    }

    @NonNull
    @Override
    public HolderDataAssets onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_asset, parent, false);
        AssetsAdapterData.HolderDataAssets holder = new AssetsAdapterData.HolderDataAssets(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderDataAssets holder, int position) {
        initHolder(holder,position);
    }

    @Override
    public int getItemCount() {
        return assetsModelList.size();
    }

    public class HolderDataAssets extends RecyclerView.ViewHolder{
        TextView tvid, tvcategoryid, tvadminid, tvclientid, tvuserid, tvmanufacturerid,
                tvmodelid, tvsupplierid, tvwarrantymonths, tvlocationid, tvpurchasedate,
                tvtag, tvname, tvserial, tvnotes, tvcustomfields, tvqrvalue,tvstatusid,tvidstatus,tvidcategory;
        ImageButton edit_btn,delete_btn;
        public HolderDataAssets(@NonNull View itemView) {
            super(itemView);
            init(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(ctx,DetailActivityAsset.class);
                    i.putExtra("idAssets",tvid.getText().toString());
                    i.putExtra("nameAssets",tvtag.getText().toString());
                    ctx.startActivity(i);
                }
            });
            edit_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(ctx, EditAsset.class);
                    i.putExtra("id",tvid.getText().toString());
                    i.putExtra("idCategory",tvidcategory.getText().toString());
                    i.putExtra("idAdmin",tvadminid.getText().toString());
                    i.putExtra("idClient",tvclientid.getText().toString());
                    i.putExtra("idUser",tvuserid.getText().toString());
                    i.putExtra("idManu",tvmanufacturerid.getText().toString());
                    i.putExtra("idModel",tvmodelid.getText().toString());
                    i.putExtra("idSupp",tvsupplierid.getText().toString());
                    i.putExtra("Warran",tvwarrantymonths.getText().toString());
                    i.putExtra("idLoc",tvlocationid.getText().toString());
                    i.putExtra("purchase",tvpurchasedate.getText().toString());
                    i.putExtra("tag",tvtag.getText().toString());
                    i.putExtra("name",tvname.getText().toString());
                    i.putExtra("serial",tvserial.getText().toString());
                    i.putExtra("notes",tvnotes.getText().toString());
                    i.putExtra("idStatus",tvidstatus.getText().toString());
                    ctx.startActivity(i);
                }
            });
        }
        private void init(@NonNull View itemView){
            edit_btn = itemView.findViewById(R.id.btn_edit_asset);
            tvid = itemView.findViewById(R.id.tv_id_asset);
            tvcategoryid = itemView.findViewById(R.id.tv_categoryid_asset);
            tvadminid = itemView.findViewById(R.id.tv_adminid_asset);
            tvclientid = itemView.findViewById(R.id.tv_clientid_asset);
            tvuserid = itemView.findViewById(R.id.tv_userid_asset);
            tvmanufacturerid = itemView.findViewById(R.id.tv_manufacturerid_asset);
            tvmodelid = itemView.findViewById(R.id.tv_modelid_asset);
            tvsupplierid =itemView.findViewById(R.id.tv_supplierid_asset);
            tvwarrantymonths = itemView.findViewById(R.id.tv_warranty_months_asset);
            tvlocationid = itemView.findViewById(R.id.tv_locationid_asset);
            tvpurchasedate = itemView.findViewById(R.id.tv_purchase_date_asset);
            tvtag = itemView.findViewById(R.id.tv_tag_asset);
            tvname = itemView.findViewById(R.id.tv_name_asset);
            tvserial = itemView.findViewById(R.id.tv_serial_asset);
            tvnotes =itemView.findViewById(R.id.tv_notes_asset);
            tvcustomfields =itemView.findViewById(R.id.tv_customfields_asset);
            tvqrvalue=itemView.findViewById(R.id.tv_qrvalue_asset);
            tvstatusid=itemView.findViewById(R.id.tv_statusid_asset);
            tvidstatus = itemView.findViewById(R.id.tv_id_status_asset);
            tvidcategory = itemView.findViewById(R.id.tv_id_category_asset);
        }
    }
    private void initHolder(@NotNull AssetsAdapterData.HolderDataAssets holder, int position){
        AssetsModel am = assetsModelList.get(position);
        holder.tvidcategory.setText(String.valueOf(am.getCategoryid()));
        holder.tvid.setText(String.valueOf(am.getId()));
        holder.tvadminid.setText(String.valueOf(am.getAdminid()));
        holder.tvcategoryid.setText(am.getCategorynama());
        holder.tvclientid.setText(String.valueOf(am.getClientid()));
        holder.tvuserid.setText(String.valueOf(am.getUserid()));
        holder.tvmanufacturerid.setText(String.valueOf(am.getManufacturerid()));
        holder.tvmodelid.setText(String.valueOf(am.getModelid()));
        holder.tvsupplierid.setText(String.valueOf(am.getSupplierid()));
        holder.tvwarrantymonths.setText(String.valueOf(am.getWarranty_months()));
        holder.tvlocationid.setText(String.valueOf(am.getLocationid()));
        holder.tvidstatus.setText(String.valueOf(am.getStatusid()));
        switch (am.getStatusid()){
            case 1:
                holder.tvstatusid.setText("Requested");
                holder.tvstatusid.setBackground(ContextCompat.getDrawable(ctx,R.drawable.bg_status_request));
                break;
            case 2:
                holder.tvstatusid.setText("Pending");
                holder.tvstatusid.setBackground(ContextCompat.getDrawable(ctx,R.drawable.bg_status_pending));
                break;
            case 3:
                holder.tvstatusid.setText("Deployed");
                holder.tvstatusid.setBackground(ContextCompat.getDrawable(ctx,R.drawable.bg_status_deployed));
                break;
            case 4:
                holder.tvstatusid.setText("Archived");
                holder.tvstatusid.setBackground(ContextCompat.getDrawable(ctx,R.drawable.bg_status_archived));
                break;
            case 5:
                holder.tvstatusid.setText("In Repair");
                holder.tvstatusid.setBackground(ContextCompat.getDrawable(ctx,R.drawable.bg_status_repair));
                break;
            case 6:
                holder.tvstatusid.setText("Broken");
                holder.tvstatusid.setBackground(ContextCompat.getDrawable(ctx,R.drawable.bg_status_broken));
                break;
            default:
                holder.tvstatusid.setText(" - ");
                break;
        }
        holder.tvpurchasedate.setText(am.getPurchase_date());
        holder.tvname.setText(am.getName());
        holder.tvtag.setText(am.getTag());
        holder.tvserial.setText(am.getSerial());
        holder.tvnotes.setText(am.getNotes());
        holder.tvcustomfields.setText(am.getCustomfields());
        holder.tvqrvalue.setText(am.getQrvalue());
    }
    
}
