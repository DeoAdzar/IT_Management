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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.it_management.API.BaseApiService;
import com.example.it_management.API.UtilsApi;
import com.example.it_management.R;
import com.example.it_management.SpinnerTypeAdapter;
import com.example.it_management.SpinnerTypeItem;
import com.example.it_management.ui.AddActivity.AddIssues;
import com.example.it_management.ui.Clients.ClientsModel;
import com.example.it_management.ui.Clients.ClientsResponseModel;
import com.example.it_management.ui.Inventory.Assets.AssetsModel;
import com.example.it_management.ui.Inventory.Assets.AssetsResponseModel;
import com.example.it_management.ui.People.Staff.StaffModel;
import com.example.it_management.ui.People.Staff.StaffResponseModel;
import com.example.it_management.ui.Projects.ProjectsModel;
import com.example.it_management.ui.Projects.ProjectsResponseModel;

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

public class EditIssues extends AppCompatActivity {
    public AutoCompleteTextView client,asset,project,admin;
    EditText name,description,dueDate;
    TextView idClient,idAsset,idProject,idAdmin,typename;
    Button btn_edit_issues,btn_back;
    Spinner status,priority,type;
    private AlertDialog progressDialog;
    final Calendar calendar = Calendar.getInstance();
    List<ClientsModel> clientModel = new ArrayList<>();
    List<StaffModel> adminModel = new ArrayList<>();
    List<AssetsModel> assetsModel = new ArrayList<>();
    List<ProjectsModel> projectsModel= new ArrayList<>();
    ArrayList<SpinnerTypeItem> typeList;
    SpinnerTypeAdapter adapter;
    BaseApiService mApiService = UtilsApi.getApiService();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_issues);
        init();
        setData();
        getData();
        onAction();
    }

    private void setData() {
        setClientName();
        setAssetsName();
        setAdminName();
        setProjectName();
        name.setText(getIntent().getStringExtra("name"));
        description.setText(getIntent().getStringExtra("des"));
        dueDate.setText(getIntent().getStringExtra("duedate"));
        idClient.setText(getIntent().getStringExtra("idClient"));
        idAsset.setText(getIntent().getStringExtra("idAssets"));
        idProject.setText(getIntent().getStringExtra("idProject"));
        idAdmin.setText(getIntent().getStringExtra("idAdmin"));
        typename.setText(getIntent().getStringExtra("type"));


    }

    private void setProjectName() {
        String id;
        id = getIntent().getStringExtra("idProject");
        Call<ResponseBody> setClient = mApiService.basProjectsGetName(Integer.parseInt(id));
        setClient.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        JSONObject jsonResult = new JSONObject(response.body().string());
                        if (jsonResult.getString("error").equals("false")) {
                            String name = jsonResult.getJSONObject("client").getString("name");
                            admin.setText(name);
                        } else {
                            String error_msg = jsonResult.getString("error_msg");
                            Log.d("Msg",error_msg+" User");
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(EditIssues.this, "GAGAL", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    private void setAdminName() {
        String id;
        id = getIntent().getStringExtra("idAdmin");
        Call<ResponseBody> setClient = mApiService.basAkunGetName(Integer.parseInt(id));
        setClient.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        JSONObject jsonResult = new JSONObject(response.body().string());
                        if (jsonResult.getString("error").equals("false")) {
                            String name = jsonResult.getJSONObject("client").getString("name");
                            admin.setText(name);
                        } else {
                            String error_msg = jsonResult.getString("error_msg");
                            Log.d("Msg",error_msg+" User");
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(EditIssues.this, "GAGAL", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }


    private void setAssetsName() {
        String id;
        id = getIntent().getStringExtra("idAssets");
        Call<ResponseBody> setClient = mApiService.basAssetsGetName(Integer.parseInt(id));
        setClient.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        JSONObject jsonResult = new JSONObject(response.body().string());
                        if (jsonResult.getString("error").equals("false")) {
                            String name = jsonResult.getJSONObject("client").getString("name");
                            asset.setText(name);
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
                    Toast.makeText(EditIssues.this, "GAGAL", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(EditIssues.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
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
                    Toast.makeText(EditIssues.this, "GAGAL", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(EditIssues.this, t.getMessage(), Toast.LENGTH_SHORT).show();

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
                                    Toast.makeText(EditIssues.this, error_msg, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Toast.makeText(EditIssues.this, "GAGAL", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(EditIssues.this, "Gagal mengambil data "+t.getMessage(), Toast.LENGTH_SHORT).show();

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
                                    Toast.makeText(EditIssues.this, error_msg, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Toast.makeText(EditIssues.this, "GAGAL", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(EditIssues.this, "Gagal mengambil data "+t.getMessage(), Toast.LENGTH_SHORT).show();

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
                                    Toast.makeText(EditIssues.this, error_msg, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Toast.makeText(EditIssues.this, "GAGAL", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(EditIssues.this, "Gagal mengambil data "+t.getMessage(), Toast.LENGTH_SHORT).show();

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
                                    Toast.makeText(EditIssues.this, error_msg, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Toast.makeText(EditIssues.this, "GAGAL", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(EditIssues.this, "Gagal mengambil data "+t.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
        btn_edit_issues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateIssues();
            }
        });
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void updateIssues() {
        progressDialog.show();
        int id,idCl,idAs,idPro,idAdm;
        String nama,des,tipe,prio,stts,date;
        id = Integer.parseInt(getIntent().getStringExtra("idIssues"));
        idCl = Integer.parseInt(idClient.getText().toString());
        idAs = Integer.parseInt(idAsset.getText().toString());
        idPro = Integer.parseInt(idProject.getText().toString());
        idAdm = Integer.parseInt(idAdmin.getText().toString());
        nama = name.getText().toString();
        des = description.getText().toString();
        tipe = typename.getText().toString();
        prio = priority.getSelectedItem().toString();
        stts = status.getSelectedItem().toString();
        date = dueDate.getText().toString();
        Call<ResponseBody> input = mApiService.basUpdateIssues(id,idCl,idAdm,idAs,idPro,tipe,prio,stts,date,des,nama);
        input.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                progressDialog.dismiss();
                Toast.makeText(EditIssues.this, "Berhasil Input", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
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
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(EditIssues.this,
                            android.R.layout.simple_spinner_item, listAdmin);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    project.setThreshold(1);
                    project.setAdapter(adapter);
                } else {
                    Toast.makeText(EditIssues.this, "Gagal mengambil data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ProjectsResponseModel> call, Throwable t) {
                Toast.makeText(EditIssues.this, "Gagal Menghubungkan Server pesan : "+t, Toast.LENGTH_SHORT).show();
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
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(EditIssues.this,
                            android.R.layout.simple_spinner_item, listAdmin);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    asset.setThreshold(1);
                    asset.setAdapter(adapter);
                } else {
                    Toast.makeText(EditIssues.this, "Gagal mengambil data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AssetsResponseModel> call, Throwable t) {
                Toast.makeText(EditIssues.this, "Gagal Menghubungkan Server pesan : "+t, Toast.LENGTH_SHORT).show();
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
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(EditIssues.this,
                            android.R.layout.simple_spinner_item, listAdmin);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    admin.setThreshold(1);
                    admin.setAdapter(adapter);
                } else {
                    Toast.makeText(EditIssues.this, "Gagal mengambil data", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<StaffResponseModel> call, Throwable t) {
                Toast.makeText(EditIssues.this, "Gagal mengambil data "+t.getMessage(), Toast.LENGTH_SHORT).show();

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
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(EditIssues.this,
                            android.R.layout.simple_spinner_item, listClient);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    client.setThreshold(1);
                    client.setAdapter(adapter);
                } else {
                    Toast.makeText(EditIssues.this, "Gagal mengambil data", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ClientsResponseModel> call, Throwable t) {
                Toast.makeText(EditIssues.this, "Gagal mengambil data "+t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
    private void init() {
        initType();
        String valStatus,valPrio,valType;
        valStatus = getIntent().getStringExtra("status");
        valPrio = getIntent().getStringExtra("prio");
        valType = getIntent().getStringExtra("valtype");
        progressDialog = new SpotsDialog(this, R.style.Custom);
        name = findViewById(R.id.et_edit_asset_name);
        type = findViewById(R.id.spin_edit_issues_type);
        typename = findViewById(R.id.et_edit_assets_typename);
        adapter = new SpinnerTypeAdapter(this,typeList);
        type.setAdapter(adapter);
       /*if (valType!=null){
           int image = 0;
           switch (valType){
               case "Task":
                   image = R.drawable.task;
                   break;
               case "Maintenance":
                   image = R.drawable.maintenance;
                   break;
               case "Bug":
                   image = R.drawable.bug;
                   break;
               case "Improvement":
                   image = R.drawable.improvement;
                   break;
               case "New Feature":
                   image = R.drawable.newfeature;
                   break;
               case "Story":
                   image = R.drawable.story;
                   break;
           }
           SpinnerTypeItem set = new SpinnerTypeItem(valType,image);
           int TypeSelect = adapter.getPosition(set);
           type.setSelection(TypeSelect);
       }*/
        type.setSelection(Integer.parseInt(valType));
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
        client = findViewById(R.id.et_edit_issues_client);
        idClient = findViewById(R.id.et_edit_issues_clientid);
        asset = findViewById(R.id.et_edit_issues_assets);
        idAsset = findViewById(R.id.et_edit_issues_assetsid);
        project = findViewById(R.id.et_edit_issues_project);
        idProject = findViewById(R.id.et_edit_issues_projectid);
        admin = findViewById(R.id.et_edit_issues_assign);
        idAdmin = findViewById(R.id.et_edit_issues_assignid );
        description = findViewById(R.id.et_edit_issues_description);
        dueDate = findViewById(R.id.et_edit_issues_due);
        btn_edit_issues = findViewById(R.id.btn_edit_issues);
        btn_back = findViewById(R.id.btn_back_editIssues);
        status = findViewById(R.id.spin_edit_issues_status);
        priority = findViewById(R.id.spin_edit_issues_priority);
        ArrayAdapter<CharSequence> StatusAdapter = ArrayAdapter.createFromResource(this,R.array.ListStatusIssues,R.layout.custom_spinner);
        StatusAdapter.setDropDownViewResource(R.layout.custom_spinner_dropdown);
        status.setAdapter(StatusAdapter);
        if (valStatus != null){
            int spinnerPosition = StatusAdapter.getPosition(valStatus);
            status.post(new Runnable() {
                @Override
                public void run() {
                    status.setSelection(spinnerPosition);
                }
            });
        }
        ArrayAdapter<CharSequence> PriorityAdapter = ArrayAdapter.createFromResource(this,R.array.ListPriority,R.layout.custom_spinner);
        PriorityAdapter.setDropDownViewResource(R.layout.custom_spinner_dropdown);
        priority.setAdapter(PriorityAdapter);
        if (valPrio != null){
            int spinnerPosition = PriorityAdapter.getPosition(valPrio);
            priority.post(new Runnable() {
                @Override
                public void run() {
                    priority.setSelection(spinnerPosition);
                }
            });
        }
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
                new DatePickerDialog(EditIssues.this, date1, calendar
                        .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
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
    private void updateLabel() {
        String myFormat = "yyyy-MM-dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.ENGLISH);

        dueDate.setText(sdf.format(calendar.getTime()));
    }
}