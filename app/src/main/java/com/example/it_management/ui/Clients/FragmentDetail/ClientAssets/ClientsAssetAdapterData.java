package com.example.it_management.ui.Clients.FragmentDetail.ClientAssets;

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
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.it_management.API.BaseApiService;
import com.example.it_management.API.UtilsApi;
import com.example.it_management.R;
import com.example.it_management.ui.Clients.ClientsFragment;
import com.example.it_management.ui.EditActivity.EditAsset;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ClientsAssetAdapterData extends RecyclerView.Adapter<ClientsAssetAdapterData.HolderClientsAdapterDataAssets> {
    private Context ctx;
    private List<ClientsAssetModel> clientsAssetModelList;

    public ClientsAssetAdapterData(Context ctx, List<ClientsAssetModel> clientsAssetModelList) {
        this.ctx = ctx;
        this.clientsAssetModelList = clientsAssetModelList;
    }

    @NonNull
    @Override
    public HolderClientsAdapterDataAssets onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_client_assets, parent, false);
        HolderClientsAdapterDataAssets holder = new HolderClientsAdapterDataAssets(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderClientsAdapterDataAssets holder, int position) {
        initHolder(holder,position);

    }

    @Override
    public int getItemCount() {
        return clientsAssetModelList.size();
    }

    public class HolderClientsAdapterDataAssets extends RecyclerView.ViewHolder {
        TextView tvid, tvcategoryid, tvadminid, tvclientid, tvuserid, tvmanufacturerid,
                tvmodelid, tvsupplierid, tvwarrantymonths, tvlocationid, tvpurchasedate,
                tvtag, tvname, tvserial, tvnotes, tvcustomfields, tvqrvalue,tvstatusid,tvidstatus;
        ImageButton btnEditAssetClient,btnHapusAssetClient;

        public HolderClientsAdapterDataAssets(@NonNull View itemView) {
            super(itemView);
            init(itemView);
            btnEditAssetClient.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(ctx, EditAsset.class);
                    i.putExtra("id",tvid.getText().toString());
                    i.putExtra("idCategory",tvcategoryid.getText().toString());
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
            btnHapusAssetClient.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int id = Integer.parseInt(tvid.getText().toString());
                    konfirmasi(tvname.getText().toString(),id);

                }
            });
        }
        private void konfirmasi(String nama,int id){
            final AlertDialog.Builder alert = new AlertDialog.Builder(ctx);
            alert.setTitle("Delete");
            alert.setIcon(R.drawable.ic_delete);
            alert.setMessage("Apakah anda yakin ingin menghapus data "+nama+" ?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            deleteAssets(nama,id);
                            dialog.dismiss();

                        }
                    })
                    .setNegativeButton("No", null);
            alert.show();
        }
        private void deleteAssets(String nama,int id){
            BaseApiService mApiService = UtilsApi.getApiService();
            Call<ResponseBody> delete = mApiService.basDeleteAssets(id);
            delete.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    Toast.makeText(ctx, nama+" has been removed, please refresh page", Toast.LENGTH_LONG).show();
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Toast.makeText(ctx, "Gagal menghubungi server | response : "+t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
        private void init(@NonNull View itemView){
            btnEditAssetClient = itemView.findViewById(R.id.btn_edit_asset_client);
            btnHapusAssetClient = itemView.findViewById(R.id.btn_delete_asset_client);
            tvid = itemView.findViewById(R.id.tv_id_assets_client);
            tvcategoryid = itemView.findViewById(R.id.tv_categoryid);
            tvadminid = itemView.findViewById(R.id.tv_adminid);
            tvclientid = itemView.findViewById(R.id.tv_id_Client_Asset);
            tvuserid = itemView.findViewById(R.id.tv_userid);
            tvmanufacturerid = itemView.findViewById(R.id.tv_manufacturerid);
            tvmodelid = itemView.findViewById(R.id.tv_modelid);
            tvsupplierid =itemView.findViewById(R.id.tv_supplierid);
            tvwarrantymonths = itemView.findViewById(R.id.tv_warranty_months);
            tvlocationid = itemView.findViewById(R.id.tv_locationid);
            tvpurchasedate = itemView.findViewById(R.id.tv_purchase_date);
            tvtag = itemView.findViewById(R.id.tv_tag);
            tvname = itemView.findViewById(R.id.tv_name_asset_client);
            tvserial = itemView.findViewById(R.id.tv_serial);
            tvnotes =itemView.findViewById(R.id.tv_notes);;
            tvcustomfields =itemView.findViewById(R.id.tv_customfields);
            tvqrvalue=itemView.findViewById(R.id.tv_qrvalue);
            tvstatusid=itemView.findViewById(R.id.tv_statusid);
            tvidstatus = itemView.findViewById(R.id.tv_id_status_Asset);
        }

    }

    private void initHolder(@NotNull HolderClientsAdapterDataAssets holder, int position){
        ClientsAssetModel cam = clientsAssetModelList.get(position);
        holder.tvidstatus.setText(String.valueOf(cam.getStatusid()));
        holder.tvid.setText(String.valueOf(cam.getId()));
        holder.tvadminid.setText(String.valueOf(cam.getAdminid()));
        holder.tvcategoryid.setText(String.valueOf(cam.getCategoryid()));
        holder.tvclientid.setText(String.valueOf(cam.getClientid()));
        holder.tvuserid.setText(String.valueOf(cam.getUserid()));
        holder.tvmanufacturerid.setText(String.valueOf(cam.getManufacturerid()));
        holder.tvmodelid.setText(String.valueOf(cam.getModelid()));
        holder.tvsupplierid.setText(String.valueOf(cam.getSupplierid()));
        holder.tvwarrantymonths.setText(String.valueOf(cam.getWarranty_months()));
        holder.tvlocationid.setText(String.valueOf(cam.getLocationid()));
        switch (cam.getStatusid()){
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
        holder.tvpurchasedate.setText(cam.getPurchase_date());
        holder.tvname.setText(cam.getName());
        holder.tvtag.setText(cam.getTag());
        holder.tvserial.setText(cam.getSerial());
        holder.tvnotes.setText(cam.getNotes());
        holder.tvcustomfields.setText(cam.getCustomfields());
        holder.tvqrvalue.setText(cam.getQrvalue());

    }
}
