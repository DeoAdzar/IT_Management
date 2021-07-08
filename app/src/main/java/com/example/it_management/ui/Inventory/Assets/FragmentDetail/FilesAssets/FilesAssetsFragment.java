package com.example.it_management.ui.Inventory.Assets.FragmentDetail.FilesAssets;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.it_management.API.BaseApiService;
import com.example.it_management.API.UtilsApi;
import com.example.it_management.R;
import com.example.it_management.ui.Clients.FragmentDetail.ClientFiles.ClientsFilesAdapterData;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FilesAssetsFragment extends Fragment {
    private RecyclerView rvAssetFiles;
    private RecyclerView.Adapter adAssetsFiles;
    private RecyclerView.LayoutManager lmAssetsFiles;
    private TextView nodata;
    private List<AssetsFilesModel> assetsFilesModelList = new ArrayList<>();
    private int id;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View v = inflater.inflate(R.layout.fragment_assets_files, container, false);
        rvAssetFiles = v.findViewById(R.id.rv_assets_files);
        lmAssetsFiles = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        rvAssetFiles.setLayoutManager(lmAssetsFiles);
        id = Integer.parseInt(getArguments().getString("idAssets"));
        nodata = v.findViewById(R.id.no_data_files_asset);
        nodata.setVisibility(View.GONE);
        tampilDataFiles();
       return v;
    }

    private void tampilDataFiles() {
        BaseApiService mApiService = UtilsApi.getApiService();
        Call<AssetsFilesResponseModel> tampil = mApiService.basGetFilesAssets(id);
        tampil.enqueue(new Callback<AssetsFilesResponseModel>() {
            @Override
            public void onResponse(Call<AssetsFilesResponseModel> call, Response<AssetsFilesResponseModel> response) {
                assetsFilesModelList = response.body().getData();
                if (assetsFilesModelList.isEmpty()){
                    rvAssetFiles.setVisibility(View.GONE);
                    nodata.setVisibility(View.VISIBLE);
                }else {
                    adAssetsFiles = new AssetsFilesAdapterData(getContext(), assetsFilesModelList);
                    rvAssetFiles.setAdapter(adAssetsFiles);
                    adAssetsFiles.notifyDataSetChanged();

                    rvAssetFiles.setVisibility(View.VISIBLE);
                    nodata.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<AssetsFilesResponseModel> call, Throwable t) {
                Toast.makeText(getActivity(), "Gagal Menghubungkan Server pesan : "+t, Toast.LENGTH_SHORT).show();
            }
        });
    }
}