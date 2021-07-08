package com.example.it_management.ui.Inventory.Assets.FragmentDetail.FilesAssets;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.it_management.API.UtilsApi;
import com.example.it_management.R;
import com.example.it_management.ui.Clients.FragmentDetail.ClientFiles.ClientsFilesModel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import okhttp3.ResponseBody;

import static androidx.constraintlayout.motion.utils.Oscillator.TAG;

public class AssetsFilesAdapterData extends RecyclerView.Adapter<AssetsFilesAdapterData.HolderDataAssetFiles>{
    private Context ctx;
    private List<AssetsFilesModel> assetsFilesModelsList;

    public AssetsFilesAdapterData(Context ctx, List<AssetsFilesModel> assetsFilesModelsList) {
        this.ctx = ctx;
        this.assetsFilesModelsList = assetsFilesModelsList;
    }

    @NonNull
    @Override
    public HolderDataAssetFiles onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_asset_files,parent,false);
        HolderDataAssetFiles holder = new HolderDataAssetFiles(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderDataAssetFiles holder, int position) {
        AssetsFilesModel cfm = assetsFilesModelsList.get(position);
        holder.tvfile.setText(cfm.getFile());
        holder.tvname.setText(cfm.getName());

    }

    @Override
    public int getItemCount() {
        return assetsFilesModelsList.size();
    }

    @Override
    public long getItemId(int position) {
        return assetsFilesModelsList.get(position).getId();
    }

    public class HolderDataAssetFiles extends RecyclerView.ViewHolder{
        private TextView tvname,tvfile;
        public HolderDataAssetFiles(@NonNull View itemView) {
            super(itemView);
            tvname = itemView.findViewById(R.id.tv_name_asset_files);
            tvfile = itemView.findViewById(R.id.tv_file_asset_files);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String file = tvfile.getText().toString();
//                    download(file);
                    DownloadManager downloadmanager = (DownloadManager)ctx.getSystemService(Context.DOWNLOAD_SERVICE);
                    Uri uri = Uri.parse(UtilsApi.FILE_URL+file);

                    DownloadManager.Request request = new DownloadManager.Request(uri);
                    request.setTitle("My File");
                    request.setDescription("Downloading");
                    request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                    request.setVisibleInDownloadsUi(false);
                    request.setDestinationUri(Uri.parse("file://ITManagement/"+file));

                    downloadmanager.enqueue(request);
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

//        private boolean writeResponseBodyToDisk(ResponseBody body) {
//            try {
//                File futureStudioIconFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),tvfile.getText().toString());
//
//                InputStream inputStream = null;
//                OutputStream outputStream = null;
//
//                try {
//                    byte[] fileReader = new byte[4096];
//
//                    long fileSize = body.contentLength();
//                    long fileSizeDownloaded = 0;
//
//                    inputStream = body.byteStream();
//                    outputStream = new FileOutputStream(futureStudioIconFile);
//
//                    while (true) {
//                        int read = inputStream.read(fileReader);
//
//                        if (read == -1) {
//                            break;
//                        }
//
//                        outputStream.write(fileReader, 0, read);
//
//                        fileSizeDownloaded += read;
//
//                        Log.d(TAG, "file download: " + fileSizeDownloaded + " of " + fileSize);
//                    }
//
//                    outputStream.flush();
//
//                    return true;
//                } catch (IOException e) {
//                    return false;
//                } finally {
//                    if (inputStream != null) {
//                        inputStream.close();
//                    }
//
//                    if (outputStream != null) {
//                        outputStream.close();
//                    }
//                }
//            }catch (IOException e){
//                return false;
//            }
//        }
    }
}
