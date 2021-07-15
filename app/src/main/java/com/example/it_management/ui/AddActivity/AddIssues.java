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
import com.example.it_management.SpinnerTypeAdapter;
import com.example.it_management.SpinnerTypeItem;
import com.example.it_management.ui.Clients.ClientsModel;
import com.example.it_management.ui.Clients.ClientsResponseModel;
import com.example.it_management.ui.Inventory.Assets.AssetsAdapterData;
import com.example.it_management.ui.Inventory.Assets.AssetsModel;
import com.example.it_management.ui.Inventory.Assets.AssetsResponseModel;
import com.example.it_management.ui.People.Staff.StaffModel;
import com.example.it_management.ui.People.Staff.StaffResponseModel;
import com.example.it_management.ui.Projects.ProjectsAdapterData;
import com.example.it_management.ui.Projects.ProjectsModel;
import com.example.it_management.ui.Projects.ProjectsResponseModel;
import com.google.gson.TypeAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddIssues extends AppCompatActivity {
    public AutoCompleteTextView client,asset,project,admin;
    EditText name,description,dueDate;
    TextView idClient,idAsset,idProject,idAdmin,typename;
    Button btn_add_issues,btn_back;
    Spinner status,priority,type;
    private AlertDialog progressDialog;
    final Calendar calendar = Calendar.getInstance();
    List<ClientsModel> clientModel = new ArrayList<>();
    List<StaffModel> adminModel = new ArrayList<>();
    List<AssetsModel> assetsModel = new ArrayList<>();
    List<ProjectsModel> projectsModel= new ArrayList<>();
    ArrayList<SpinnerTypeItem> typeList;
    SpinnerTypeAdapter adapter ;

    BaseApiService mApiService = UtilsApi.getApiService();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_issues);
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
                                    Toast.makeText(AddIssues.this, error_msg, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Toast.makeText(AddIssues.this, "GAGAL", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(AddIssues.this, "Gagal mengambil data "+t.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
        admin.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                admin.showDropDown();
            }
        });
        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                admin.showDropDown();
            }
        });
        admin.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BaseApiService mApiService = UtilsApi.getApiService();
                Call<ResponseBody> getId = mApiService.basAkunGetId(admin.getText().toString());
                getId.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            try {
                                JSONObject jsonResult = new JSONObject(response.body().string());
                                if (jsonResult.getString("error").equals("false")) {
                                    String Id = jsonResult.getJSONObject("client").getString("id");
                                    idAdmin.setText(Id);
                                } else {
                                    String error_msg = jsonResult.getString("error_msg");
                                    Toast.makeText(AddIssues.this, error_msg, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Toast.makeText(AddIssues.this, "GAGAL", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(AddIssues.this, "Gagal mengambil data "+t.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
        asset.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                asset.showDropDown();
            }
        });
        asset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                asset.showDropDown();
            }
        });
        asset.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BaseApiService mApiService = UtilsApi.getApiService();
                Call<ResponseBody> getId = mApiService.basAssetsGetId(asset.getText().toString());
                getId.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            try {
                                JSONObject jsonResult = new JSONObject(response.body().string());
                                if (jsonResult.getString("error").equals("false")) {
                                    String Id = jsonResult.getJSONObject("client").getString("id");
                                    idAsset.setText(Id);
                                } else {
                                    String error_msg = jsonResult.getString("error_msg");
                                    Toast.makeText(AddIssues.this, error_msg, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Toast.makeText(AddIssues.this, "GAGAL", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(AddIssues.this, "Gagal mengambil data "+t.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
        project.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                project.showDropDown();
            }
        });
        project.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                project.showDropDown();
            }
        });
        project.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BaseApiService mApiService = UtilsApi.getApiService();
                Call<ResponseBody> getId = mApiService.basProjectsGetId(project.getText().toString());
                getId.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            try {
                                JSONObject jsonResult = new JSONObject(response.body().string());
                                if (jsonResult.getString("error").equals("false")) {
                                    String Id = jsonResult.getJSONObject("client").getString("id");
                                    idProject.setText(Id);
                                } else {
                                    String error_msg = jsonResult.getString("error_msg");
                                    Toast.makeText(AddIssues.this, error_msg, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Toast.makeText(AddIssues.this, "GAGAL", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(AddIssues.this, "Gagal mengambil data "+t.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
        btn_add_issues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertIssues();
            }
        });
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void insertIssues() {
    }

    private void init() {
        initType();
        name = findViewById(R.id.et_add_asset_name);
        type = findViewById(R.id.spin_add_issues_type);
        typename = findViewById(R.id.et_add_assets_typename);
        adapter = new SpinnerTypeAdapter(this,typeList);
        type.setAdapter(adapter);
        type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SpinnerTypeItem clickedItem = (SpinnerTypeItem) parent.getItemAtPosition(position);
                String nameType = clickedItem.getmName();
                typename.setText(nameType);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        client = findViewById(R.id.et_add_issues_client);
        idClient = findViewById(R.id.et_add_issues_clientid);
        asset = findViewById(R.id.et_add_issues_assets);
        idAsset = findViewById(R.id.et_add_issues_assetsid);
        project = findViewById(R.id.et_add_issues_project);
        idProject = findViewById(R.id.et_add_issues_projectid);
        admin = findViewById(R.id.et_add_issues_assign);
        idAdmin = findViewById(R.id.et_add_issues_assignid );
        description = findViewById(R.id.et_add_issues_description);
        dueDate = findViewById(R.id.et_add_issues_due);
        btn_add_issues = findViewById(R.id.btn_add_issues);
        btn_back = findViewById(R.id.btn_back_AddIssues);
        status = findViewById(R.id.spin_add_issues_status);
        priority = findViewById(R.id.spin_add_issues_priority);
        ArrayAdapter<CharSequence> StatusAdapter = ArrayAdapter.createFromResource(this,R.array.ListStatusIssues,R.layout.custom_spinner);
        adapter.setDropDownViewResource(R.layout.custom_spinner_dropdown);
        status.setAdapter(StatusAdapter);
        ArrayAdapter<CharSequence> PriorityAdapter = ArrayAdapter.createFromResource(this,R.array.ListPriority,R.layout.custom_spinner);
        adapter.setDropDownViewResource(R.layout.custom_spinner_dropdown);
        priority.setAdapter(PriorityAdapter);
        DatePickerDialog.OnDateSetListener date1 = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };
        dueDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(AddIssues.this, date1, calendar
                        .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }
    private void updateLabel() {
        String myFormat = "yyyy-MM-dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.ENGLISH);

        dueDate.setText(sdf.format(calendar.getTime()));
    }

    private void getData() {
        getClient();
        getAsset();
        getProject();
        getAdmin();
    }

    private void getProject() {
        Call<ProjectsResponseModel> tampilData = mApiService.basGetProjects();
        tampilData.enqueue(new Callback<ProjectsResponseModel>() {
            @Override
            public void onResponse(Call<ProjectsResponseModel> call, Response<ProjectsResponseModel> response) {
                if (response.isSuccessful()) {
                    projectsModel = response.body().getData();
                    List<String> listAdmin = new ArrayList<String>();
                    for (int i = 0; i < projectsModel.size(); i++){
                        listAdmin.add(projectsModel.get(i).getName());
                    }
                    // Set hasil result json ke dalam adapter spinner
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(AddIssues.this,
                            android.R.layout.simple_spinner_item, listAdmin);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    project.setThreshold(1);
                    project.setAdapter(adapter);
                } else {
                    Toast.makeText(AddIssues.this, "Gagal mengambil data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ProjectsResponseModel> call, Throwable t) {
                Toast.makeText(AddIssues.this, "Gagal Menghubungkan Server pesan : "+t, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getAsset() {
        Call<AssetsResponseModel> tampilData = mApiService.basGetAssets();
        tampilData.enqueue(new Callback<AssetsResponseModel>() {
            @Override
            public void onResponse(Call<AssetsResponseModel> call, Response<AssetsResponseModel> response) {
                if (response.isSuccessful()) {
                    assetsModel = response.body().getData();
                    List<String> listAdmin = new ArrayList<String>();
                    for (int i = 0; i < assetsModel.size(); i++){
                        listAdmin.add(assetsModel.get(i).getName());
                    }
                    // Set hasil result json ke dalam adapter spinner
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(AddIssues.this,
                            android.R.layout.simple_spinner_item, listAdmin);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    asset.setThreshold(1);
                    asset.setAdapter(adapter);
                } else {
                    Toast.makeText(AddIssues.this, "Gagal mengambil data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AssetsResponseModel> call, Throwable t) {
                Toast.makeText(AddIssues.this, "Gagal Menghubungkan Server pesan : "+t, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getAdmin() {
        Call<StaffResponseModel> getadmin = mApiService.basGetStaff();
        getadmin.enqueue(new Callback<StaffResponseModel>() {
            @Override
            public void onResponse(Call<StaffResponseModel> call, Response<StaffResponseModel> response) {
                if (response.isSuccessful()) {
                    adminModel = response.body().getData();
                    List<String> listAdmin = new ArrayList<String>();
                    for (int i = 0; i < adminModel.size(); i++){
                        listAdmin.add(adminModel.get(i).getName());
                    }
                    // Set hasil result json ke dalam adapter spinner
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(AddIssues.this,
                            android.R.layout.simple_spinner_item, listAdmin);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    admin.setThreshold(1);
                    admin.setAdapter(adapter);
                } else {
                    Toast.makeText(AddIssues.this, "Gagal mengambil data", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<StaffResponseModel> call, Throwable t) {
                Toast.makeText(AddIssues.this, "Gagal mengambil data "+t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
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
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(AddIssues.this,
                            android.R.layout.simple_spinner_item, listClient);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    client.setThreshold(1);
                    client.setAdapter(adapter);
                } else {
                    Toast.makeText(AddIssues.this, "Gagal mengambil data", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ClientsResponseModel> call, Throwable t) {
                Toast.makeText(AddIssues.this, "Gagal mengambil data "+t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void initType() {
        typeList = new ArrayList<>();
        typeList.add(new SpinnerTypeItem("Task",R.drawable.task));
        typeList.add(new SpinnerTypeItem("Maintenance",R.drawable.maintenance));
        typeList.add(new SpinnerTypeItem("Bug",R.drawable.bug));
        typeList.add(new SpinnerTypeItem("Improvement",R.drawable.improvement));
        typeList.add(new SpinnerTypeItem("New Feature",R.drawable.newfeature));
        typeList.add(new SpinnerTypeItem("Story",R.drawable.story));
    }
}