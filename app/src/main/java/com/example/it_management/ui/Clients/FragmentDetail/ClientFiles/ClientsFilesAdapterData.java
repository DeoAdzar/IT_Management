package com.example.it_management.ui.Clients.FragmentDetail.ClientFiles;

import android.Manifest;
import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.it_management.API.BaseApiService;
import com.example.it_management.API.UtilsApi;
import com.example.it_management.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.motion.utils.Oscillator.TAG;

public class ClientsFilesAdapterData extends RecyclerView.Adapter<ClientsFilesAdapterData.HolderDataClientsFiles>{
    private Context ctx;
    private List<ClientsFilesModel> clientsFilesModelList;

    public ClientsFilesAdapterData(Context ctx, List<ClientsFilesModel> clientsFilesModelList) {
        this.ctx = ctx;
        this.clientsFilesModelList = clientsFilesModelList;
    }

    @NonNull
    @Override
    public HolderDataClientsFiles onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_client_files,parent,false);
        HolderDataClientsFiles holder = new HolderDataClientsFiles(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderDataClientsFiles holder, int position) {
        ClientsFilesModel cfm = clientsFilesModelList.get(position);
        long a = getItemId(position);

        holder.tvid.setText(String.valueOf(cfm.getId()));
        holder.tvfile.setText(cfm.getFile());
        holder.tvname.setText(cfm.getName());
        holder.tvassetid.setText(String.valueOf(cfm.getAssetid()));
        holder.tvclientid.setText(String.valueOf(cfm.getClientid()));
        holder.tvprojectid.setText(String.valueOf(cfm.getProjectid()));
        holder.tvticketreplyid.setText(String.valueOf(cfm.getTicketreplyid()));
    }

    @Override
    public int getItemCount() {
        return clientsFilesModelList.size();
    }

    @Override
    public long getItemId(int position) {
        return clientsFilesModelList.get(position).getId();
    }

    public class HolderDataClientsFiles extends RecyclerView.ViewHolder{
        private TextView tvid,tvclientid,tvprojectid,tvassetid,tvticketreplyid,tvname,tvfile;
        public HolderDataClientsFiles(@NonNull View itemView) {
            super(itemView);

            tvid = itemView.findViewById(R.id.tv_id_client_files);
            tvclientid = itemView.findViewById(R.id.tv_client_id_client_files);
            tvprojectid = itemView.findViewById(R.id.tv_project_id_client_files);
            tvassetid = itemView.findViewById(R.id.tv_asset_id_client_files);
            tvticketreplyid = itemView.findViewById(R.id.tv_ticketreply_id_client_files);
            tvname = itemView.findViewById(R.id.tv_name_client_files);
            tvfile = itemView.findViewById(R.id.tv_file_client_files);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String file = tvfile.getText().toString();
                    String id = tvid.getText().toString();
//                    download(file);
//                    DownloadManager downloadmanager = (DownloadManager)ctx.getSystemService(Context.DOWNLOAD_SERVICE);
//                    Uri uri = Uri.parse(UtilsApi.FILE_URL+id);
//
//                    DownloadManager.Request request = new DownloadManager.Request(uri);
//                    request.setTitle(file);
//                    request.setDescription("Downloading");
//                    request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
//                    request.setVisibleInDownloadsUi(true);
//                    request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,file);
//
//                    downloadmanager.enqueue(request);
//                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//                    StrictMode.setThreadPolicy(policy);
                }
            });
        }

//        public void download(String file){
//            BaseApiService mApiService = UtilsApi.getApiService();
//            Call<ResponseBody> download = mApiService.downloadFiles(file);
//            download.enqueue(new Callback<ResponseBody>() {
//                @Override
//                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                    if (response.isSuccessful()){
//                        Log.d(TAG, "server contacted and has file");
//
//                        boolean writtenToDisk = writeResponseBodyToDisk(response.body());
//
//                        Log.d(TAG, "file download was a success? " + writtenToDisk);
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<ResponseBody> call, Throwable t) {
//                    Toast.makeText(ctx, "cannot connected", Toast.LENGTH_SHORT).show();
//                }
//            });
//        }

        private boolean writeResponseBodyToDisk(ResponseBody body) {
            try {
                File futureStudioIconFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),tvfile.getText().toString());

                InputStream inputStream = null;
                OutputStream outputStream = null;

                try {
                    byte[] fileReader = new byte[4096];

                    long fileSize = body.contentLength();
                    long fileSizeDownloaded = 0;

                    inputStream = body.byteStream();
                    outputStream = new FileOutputStream(futureStudioIconFile);

                    while (true) {
                        int read = inputStream.read(fileReader);

                        if (read == -1) {
                            break;
                        }

                        outputStream.write(fileReader, 0, read);

                        fileSizeDownloaded += read;

                        Log.d(TAG, "file download: " + fileSizeDownloaded + " of " + fileSize);
                    }

                    outputStream.flush();

                    return true;
                } catch (IOException e) {
                    return false;
                } finally {
                    if (inputStream != null) {
                        inputStream.close();
                    }

                    if (outputStream != null) {
                        outputStream.close();
                    }
                }
            }catch (IOException e){
                return false;
            }
        }
    }

}
