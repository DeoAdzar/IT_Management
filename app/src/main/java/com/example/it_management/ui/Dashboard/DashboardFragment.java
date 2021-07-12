package com.example.it_management.ui.Dashboard;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.it_management.API.BaseApiService;
import com.example.it_management.API.UtilsApi;
import com.example.it_management.HomeActivity;
import com.example.it_management.R;
import com.example.it_management.ui.Clients.ClientsAdapterData;
import com.example.it_management.ui.Clients.ClientsFragment;
import com.example.it_management.ui.Clients.ClientsModel;
import com.example.it_management.ui.Inventory.Assets.AssetsFragment;
import com.example.it_management.ui.Inventory.Licences.LicencesFragment;
import com.example.it_management.ui.Projects.ProjectsFragment;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieEntry;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardFragment extends Fragment {
    private CardView cvCountClient,cvCountAssets,cvCountLicense,cvCountProject;
    private TextView tvCountClient,tvCountAssets,tvCountLicense,tvCountProject;
    PieChart chart;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        cvCountClient = root.findViewById(R.id.cv_dash_clients);
        cvCountAssets = root.findViewById(R.id.cv_assets);
        cvCountLicense = root.findViewById(R.id.cv_licences);
        cvCountProject = root.findViewById(R.id.cv_project);
        chart = root.findViewById(R.id.pieChart);

        getCountClient();
        getCountAssets();
        getCountLicense();
        getCountProject();
        cvCountClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                fm.beginTransaction().replace(R.id.nav_host_fragment, new ClientsFragment()).commit();
            }
        });
        cvCountAssets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                fm.beginTransaction().replace(R.id.nav_host_fragment, new AssetsFragment()).commit();
            }
        });
        cvCountProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                fm.beginTransaction().replace(R.id.nav_host_fragment, new ProjectsFragment()).commit();
            }
        });
        cvCountLicense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                fm.beginTransaction().replace(R.id.nav_host_fragment, new LicencesFragment()).commit();
            }
        });
        tvCountClient = root.findViewById(R.id.count_client);
        tvCountAssets = root.findViewById(R.id.count_Assets);
        tvCountLicense = root.findViewById(R.id.count_licenses);
        tvCountProject = root.findViewById(R.id.count_project);
        setChart();
        return root;
    }

    private void setChart() {
        chart.setUsePercentValues(true);
        chart.getDescription().setEnabled(true);
        chart.setExtraOffsets(5,10,5,5);
        chart.setDragDecelerationFrictionCoef(0.95f);
        chart.setDrawHoleEnabled(true);
        chart.setHoleColor(Color.WHITE);
        chart.setTransparentCircleRadius(61f);
        ArrayList<PieEntry> entry = new ArrayList<>();
    }

    private void getCountProject() {
        BaseApiService mApiService = UtilsApi.getApiService();
        Call<ResponseBody> get = mApiService.basCountProject();
        get.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        JSONObject jsonResult = new JSONObject(response.body().string());
                        if (jsonResult.getString("error").equals("false")) {
                            String count = jsonResult.getJSONObject("count").getString("projects");
                            tvCountProject.setText(count);
                        } else {
                            String error_msg = jsonResult.getString("error_msg");
                            Toast.makeText(getContext(), error_msg, Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {

                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(getContext(), "GAGAL", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(getContext(), "Error : "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getCountLicense() {
        BaseApiService mApiService = UtilsApi.getApiService();
        Call<ResponseBody> get = mApiService.basCountLicense();
        get.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        JSONObject jsonResult = new JSONObject(response.body().string());
                        if (jsonResult.getString("error").equals("false")) {
                            String count = jsonResult.getJSONObject("count").getString("licenses");
                            tvCountLicense.setText(count);
                        } else {
                            String error_msg = jsonResult.getString("error_msg");
                            Toast.makeText(getContext(), error_msg, Toast.LENGTH_SHORT).show();

                        }
                    } catch (JSONException e) {

                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(getContext(), "GAGAL", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(getContext(), "Error : "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getCountAssets() {
        BaseApiService mApiService = UtilsApi.getApiService();
        Call<ResponseBody> get = mApiService.basCountAssets();
        get.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        JSONObject jsonResult = new JSONObject(response.body().string());
                        if (jsonResult.getString("error").equals("false")) {
                            String count = jsonResult.getJSONObject("count").getString("assets");
                            tvCountAssets.setText(count);
                        } else {
                            String error_msg = jsonResult.getString("error_msg");
                            Toast.makeText(getContext(), error_msg, Toast.LENGTH_SHORT).show();

                        }
                    } catch (JSONException e) {

                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(getContext(), "GAGAL", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(getContext(), "Error : "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getCountClient() {
        BaseApiService mApiService = UtilsApi.getApiService();
        Call<ResponseBody> get = mApiService.basCountClient();
        get.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        JSONObject jsonResult = new JSONObject(response.body().string());
                        if (jsonResult.getString("error").equals("false")) {
                            String cClient = jsonResult.getJSONObject("count").getString("client");
                            tvCountClient.setText(cClient);
                        } else {
                            String error_msg = jsonResult.getString("error_msg");
                            Toast.makeText(getContext(), error_msg, Toast.LENGTH_SHORT).show();

                        }
                    } catch (JSONException e) {

                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(getContext(), "GAGAL", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(getContext(), "Error : "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}