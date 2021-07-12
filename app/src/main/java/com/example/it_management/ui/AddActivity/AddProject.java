package com.example.it_management.ui.AddActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.it_management.API.BaseApiService;
import com.example.it_management.API.UtilsApi;
import com.example.it_management.R;
import com.example.it_management.ui.Clients.ClientsModel;
import com.example.it_management.ui.Clients.ClientsResponseModel;
import com.example.it_management.ui.Inventory.Attributes.LicenseCategories.LicenseCategoryModel;
import com.example.it_management.ui.Inventory.Attributes.Suppliers.SuppliersModel;
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

public class AddProject extends AppCompatActivity {
    public AutoCompleteTextView client;
    EditText tag,name,notes,startDate,endDate;
    TextView idClient,progressValue;
    Button btn_add_project,btn_back;
    IndicatorSeekBar progressBar;
    private AlertDialog progressDialog;
    final Calendar myCalendar = Calendar.getInstance();
    List<ClientsModel> clientModel = new ArrayList<>();
    BaseApiService mApiService = UtilsApi.getApiService();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_project);
        init();
        getData();
        onAction();
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
                                    Toast.makeText(AddProject.this, error_msg, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Toast.makeText(AddProject.this, "GAGAL", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(AddProject.this, "Gagal mengambil data "+t.getMessage(), Toast.LENGTH_SHORT).show();

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
        btn_add_project.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertProject();
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
                new DatePickerDialog(AddProject.this, date1, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        endDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(AddProject.this, date2, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
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
    private void insertProject() {
        progressDialog.show();
        Integer idCl,progress;
        String tg,ProjectName,note,start,end;
        idCl = Integer.parseInt(idClient.getText().toString());
        tg = tag.getText().toString();
        ProjectName = name.getText().toString();
        note = notes.getText().toString();
        progress = Integer.valueOf(progressValue.getText().toString());
        start = startDate.getText().toString();
        end = endDate.getText().toString();
        Call<ResponseBody> input = mApiService.basInputProject(idCl,tg,ProjectName,note,progress,start,end);
        input.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                progressDialog.dismiss();
                finish();
                Toast.makeText(AddProject.this, "Berhasil Input", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(AddProject.this, "Gagal Input", Toast.LENGTH_SHORT).show();
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
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(AddProject.this,
                            android.R.layout.simple_spinner_item, listClient);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    client.setThreshold(1);
                    client.setAdapter(adapter);
                } else {
                    Toast.makeText(AddProject.this, "Gagal mengambil data", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ClientsResponseModel> call, Throwable t) {
                Toast.makeText(AddProject.this, "Gagal mengambil data "+t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void init() {
        tag = findViewById(R.id.et_add_project_tag);
        name = findViewById(R.id.et_add_project_name);
        notes = findViewById(R.id.et_add_project_notes);
        startDate = findViewById(R.id.et_add_project_start_date);
        endDate = findViewById(R.id.et_add_project_end_date);
        idClient = findViewById(R.id.et_add_project_clientid);
        client = findViewById(R.id.et_add_project_client);
        btn_add_project = findViewById(R.id.btn_add_project);
        btn_back = findViewById(R.id.btn_back_Addproject);
        progressBar = findViewById(R.id.sk_add_project_progress);
        progressDialog = new SpotsDialog(this, R.style.Custom);
        progressValue = findViewById(R.id.add_project_progress_value);
    }
}