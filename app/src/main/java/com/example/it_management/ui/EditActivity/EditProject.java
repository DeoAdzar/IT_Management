package com.example.it_management.ui.EditActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.it_management.API.BaseApiService;
import com.example.it_management.API.UtilsApi;
import com.example.it_management.R;
import com.example.it_management.ui.AddActivity.AddProject;
import com.example.it_management.ui.Clients.ClientsModel;
import com.example.it_management.ui.Clients.ClientsResponseModel;
import com.warkiz.widget.IndicatorSeekBar;
import com.warkiz.widget.OnSeekChangeListener;
import com.warkiz.widget.SeekParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import dmax.dialog.SpotsDialog;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProject extends AppCompatActivity {
    public AutoCompleteTextView client;
    EditText tag,name,notes,startDate,endDate;
    TextView idClient,progressValue;
    Button btn_edit_project,btn_back;
    IndicatorSeekBar progressBar;
    private AlertDialog progressDialog;
    final Calendar myCalendar = Calendar.getInstance();
    List<ClientsModel> clientModel = new ArrayList<>();
    BaseApiService mApiService = UtilsApi.getApiService();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_project);
        init();
        setData();
        getData();
        onAction();
    }

    private void setData() {
        setClientName();
        float val = Float.parseFloat(getIntent().getStringExtra("progress"));
        tag.setText(getIntent().getStringExtra("tag"));
        name.setText(getIntent().getStringExtra("nama"));
        notes.setText(getIntent().getStringExtra("notes"));
        startDate.setText(getIntent().getStringExtra("startdate"));
        endDate.setText(getIntent().getStringExtra("duedate"));
        idClient.setText(getIntent().getStringExtra("idClient"));
        progressValue.setText(getIntent().getStringExtra("progress"));
        progressBar.setProgress(val);
    }
    private void setClientName() {
        String idCl;
        idCl = getIntent().getStringExtra("idClient");
        Call<ResponseBody> setClient = mApiService.basClientGetName(Integer.parseInt(idCl));
        setClient.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        JSONObject jsonResult = new JSONObject(response.body().string());
                        if (jsonResult.getString("error").equals("false")) {
                            String name = jsonResult.getJSONObject("client").getString("name");
                            client.setText(name);
                        } else {
                            String error_msg = jsonResult.getString("error_msg");
                            Log.d("Msg",error_msg+" Client");
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(EditProject.this, "GAGAL", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(EditProject.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void onAction() {
        client.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                client.showDropDown();
            }
        });
        client.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                client.showDropDown();
            }
        });
        client.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BaseApiService mApiService = UtilsApi.getApiService();
                Call<ResponseBody> getId = mApiService.basClientGetId(client.getText().toString());
                getId.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            try {
                                JSONObject jsonResult = new JSONObject(response.body().string());
                                if (jsonResult.getString("error").equals("false")) {
                                    String Id = jsonResult.getJSONObject("client").getString("id");
                                    idClient.setText(Id);
                                } else {
                                    String error_msg = jsonResult.getString("error_msg");
                                    Toast.makeText(EditProject.this, error_msg, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Toast.makeText(EditProject.this, "GAGAL", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(EditProject.this, "Gagal mengambil data "+t.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
        progressBar.setOnSeekChangeListener(new OnSeekChangeListener() {

            @Override
            public void onSeeking(SeekParams seekParams) {
                progressValue.setText(String.valueOf(seekParams.progress));

            }

            @Override
            public void onStartTrackingTouch(IndicatorSeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(IndicatorSeekBar seekBar) {

            }
        });
        btn_edit_project.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateProject();
            }
        });
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        DatePickerDialog.OnDateSetListener date1 = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel1();
            }

        };
        DatePickerDialog.OnDateSetListener date2 = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel2();
            }

        };
        startDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(EditProject.this, date1, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        endDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(EditProject    .this, date2, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    private void updateProject() {
        progressDialog.show();
        Integer idCl,progress,idProject;
        String tg,ProjectName,note,start,end;
        idCl = Integer.parseInt(idClient.getText().toString());
        tg = tag.getText().toString();
        ProjectName = name.getText().toString();
        note = notes.getText().toString();
        progress = Integer.valueOf(progressValue.getText().toString());
        start = startDate.getText().toString();
        end = endDate.getText().toString();
        idProject = Integer.valueOf(getIntent().getStringExtra("idProject"));
        Call<ResponseBody> input = mApiService.basUpdateProject(idProject,idCl,tg,ProjectName,note,progress,start,end);
        input.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                progressDialog.dismiss();
                finish();
                Toast.makeText(EditProject.this, "Berhasil Input", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(EditProject.this, "Gagal Input", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getData() {
        getClient();

    }
    private void getClient() {
        Call<ClientsResponseModel> getclient = mApiService.basClientData();
        getclient.enqueue(new Callback<ClientsResponseModel>() {
            @Override
            public void onResponse(Call<ClientsResponseModel> call, Response<ClientsResponseModel> response) {
                if (response.isSuccessful()) {
                    clientModel = response.body().getdata();
                    List<String> listClient = new ArrayList<String>();
                    for (int i = 0; i < clientModel.size(); i++){
                        listClient.add(clientModel.get(i).getName());
                    }
                    // Set hasil result json ke dalam adapter spinner
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(EditProject.this,
                            android.R.layout.simple_spinner_item, listClient);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    client.setThreshold(1);
                    client.setAdapter(adapter);
                } else {
                    Toast.makeText(EditProject.this, "Gagal mengambil data", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ClientsResponseModel> call, Throwable t) {
                Toast.makeText(EditProject.this, "Gagal mengambil data "+t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
    private void init() {
        tag = findViewById(R.id.et_edit_project_tag);
        name = findViewById(R.id.et_edit_project_name);
        notes = findViewById(R.id.et_edit_project_notes);
        startDate = findViewById(R.id.et_edit_project_start_date);
        endDate = findViewById(R.id.et_edit_project_end_date);
        idClient = findViewById(R.id.et_edit_project_clientid);
        client = findViewById(R.id.et_edit_project_client);
        btn_edit_project = findViewById(R.id.btn_edit_project);
        btn_back = findViewById(R.id.btn_back_editproject);
        progressBar = findViewById(R.id.sk_edit_project_progress);
        progressDialog = new SpotsDialog(this, R.style.Custom);
        progressValue = findViewById(R.id.edit_project_progress_value);
    }
    private void updateLabel1() {
        String myFormat = "yyyy-MM-dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.ENGLISH);

        startDate.setText(sdf.format(myCalendar.getTime()));
    }
    private void updateLabel2() {
        String myFormat = "yyyy-MM-dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.ENGLISH);

        endDate.setText(sdf.format(myCalendar.getTime()));
    }
}