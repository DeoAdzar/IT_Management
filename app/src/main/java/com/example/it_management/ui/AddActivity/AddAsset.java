package com.example.it_management.ui.AddActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
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
import com.example.it_management.HomeActivity;
import com.example.it_management.MainActivity;
import com.example.it_management.R;
import com.example.it_management.ui.Clients.ClientsAdapterData;
import com.example.it_management.ui.Clients.ClientsModel;
import com.example.it_management.ui.Clients.ClientsResponseModel;
import com.example.it_management.ui.Inventory.Attributes.AssetsCategories.AssetsCategoryModel;
import com.example.it_management.ui.Inventory.Attributes.AssetsCategories.AssetsCategoryResponseModel;
import com.example.it_management.ui.Inventory.Attributes.AssetsModels.AssetsModelModel;
import com.example.it_management.ui.Inventory.Attributes.AssetsModels.AssetsModelResponseModel;
import com.example.it_management.ui.Inventory.Attributes.Locations.LocationsModel;
import com.example.it_management.ui.Inventory.Attributes.Locations.LocationsResponseModel;
import com.example.it_management.ui.Inventory.Attributes.Manufacturers.ManufacturersModel;
import com.example.it_management.ui.Inventory.Attributes.Manufacturers.ManufacturersResponseModel;
import com.example.it_management.ui.Inventory.Attributes.Suppliers.SuppliersModel;
import com.example.it_management.ui.Inventory.Attributes.Suppliers.SuppliersResponseModel;
import com.example.it_management.ui.People.Staff.StaffModel;
import com.example.it_management.ui.People.Staff.StaffResponseModel;
import com.example.it_management.ui.People.Users.UsersModel;
import com.example.it_management.ui.People.Users.UsersResponseModel;
import com.tobiasschuerg.prefixsuffix.PrefixSuffixEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import dmax.dialog.SpotsDialog;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddAsset extends AppCompatActivity {
    public AutoCompleteTextView client,category,supplier,location,user,admin,manufacturer,model;
    EditText tag,name,purchase_date,serial,notes;
    TextView idClient,idCategory,idSupplier,idLocation,idUser,idAdmin,idManufacturer,idmodel;
    PrefixSuffixEditText warranty;
    Button btn_add_asset,btn_back;
    Spinner status;
    private AlertDialog progressDialog;
    String namaStatus;
    final Calendar calendar = Calendar.getInstance();
    List<ClientsModel> clientModel = new ArrayList<>();
    List<AssetsCategoryModel> categorymodel = new ArrayList<>();
    List<SuppliersModel> supplierModel = new ArrayList<>();
    List<LocationsModel> locationModel = new ArrayList<>();
    List<UsersModel> userModel = new ArrayList<>();
    List<StaffModel> adminModel = new ArrayList<>();
    List<ManufacturersModel> manufacturerModel = new ArrayList<>();
    List<AssetsModelModel> modelModel = new ArrayList<>();

    BaseApiService mApiService = UtilsApi.getApiService();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_asset);
        init();
        getData();
        onAction();
    }
    private void init() {
        client = findViewById(R.id.et_add_assets_client);
        idClient = findViewById(R.id.et_add_assets_clientid);
        category = findViewById(R.id.et_add_assets_category);
        idCategory = findViewById(R.id.et_add_assets_categoryid);
        supplier = findViewById(R.id.et_add_assets_supplier);
        idSupplier = findViewById(R.id.et_add_assets_supplierid);
        location = findViewById(R.id.et_add_assets_location);
        idLocation = findViewById(R.id.et_add_assets_locationid);
        user = findViewById(R.id.et_add_assets_user);
        idUser = findViewById(R.id.et_add_assets_userid);
        admin = findViewById(R.id.et_add_assets_admin);
        idAdmin = findViewById(R.id.et_add_assets_adminid);
        manufacturer = findViewById(R.id.et_add_assets_manufacturer);
        idManufacturer = findViewById(R.id.et_add_assets_manufacturerid);
        model = findViewById(R.id.et_add_assets_model);
        idmodel = findViewById(R.id.et_add_assets_modelid);
        tag = findViewById(R.id.et_add_asset_tag);
        name = findViewById(R.id.et_add_asset_name);
        purchase_date = findViewById(R.id.et_add_asset_purchase_date);
        warranty = findViewById(R.id.et_add_asset_warranty);
        serial = findViewById(R.id.et_add_asset_serial);
        notes = findViewById(R.id.et_add_asset_notes);
        status = findViewById(R.id.spin_add_asset_status);
        btn_add_asset = findViewById(R.id.btn_add_asset);
        btn_back = findViewById(R.id.btn_back_AddAssets);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.ListStatus,R.layout.custom_spinner);
        adapter.setDropDownViewResource(R.layout.custom_spinner_dropdown);
        status.setAdapter(adapter);
        progressDialog = new SpotsDialog(this, R.style.Custom);

    }
    private void getData() {
        getClient();
        getCategory();
        getSupplier();
        getLocation();
        getUser();
        getAdmin();
        getManufacturer();
        getModel();
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
                                    Toast.makeText(AddAsset.this, error_msg, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Toast.makeText(AddAsset.this, "GAGAL", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(AddAsset.this, "Gagal mengambil data "+t.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
        category.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                category.showDropDown();
            }
        });
        category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                category.showDropDown();
            }
        });
        category.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BaseApiService mApiService = UtilsApi.getApiService();
                Call<ResponseBody> getId = mApiService.basCategoryGetId(category.getText().toString());
                getId.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            try {
                                JSONObject jsonResult = new JSONObject(response.body().string());
                                if (jsonResult.getString("error").equals("false")) {
                                    String Id = jsonResult.getJSONObject("client").getString("id");
                                    idCategory.setText(Id);
                                } else {
                                    String error_msg = jsonResult.getString("error_msg");
                                    Toast.makeText(AddAsset.this, error_msg, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Toast.makeText(AddAsset.this, "GAGAL", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(AddAsset.this, "Gagal mengambil data "+t.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
        supplier.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                supplier.showDropDown();
            }
        });
        supplier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                supplier.showDropDown();
            }
        });
        supplier.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BaseApiService mApiService = UtilsApi.getApiService();
                Call<ResponseBody> getId = mApiService.basSupplierGetId(supplier.getText().toString());
                getId.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            try {
                                JSONObject jsonResult = new JSONObject(response.body().string());
                                if (jsonResult.getString("error").equals("false")) {
                                    String Id = jsonResult.getJSONObject("client").getString("id");
                                    idSupplier.setText(Id);
                                } else {
                                    String error_msg = jsonResult.getString("error_msg");
                                    Toast.makeText(AddAsset.this, error_msg, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Toast.makeText(AddAsset.this, "GAGAL", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(AddAsset.this, "Gagal mengambil data "+t.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
        location.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                location.showDropDown();
            }
        });
        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                location.showDropDown();
            }
        });
        location.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BaseApiService mApiService = UtilsApi.getApiService();
                Call<ResponseBody> getId = mApiService.basLocationGetId(location.getText().toString());
                getId.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            try {
                                JSONObject jsonResult = new JSONObject(response.body().string());
                                if (jsonResult.getString("error").equals("false")) {
                                    String Id = jsonResult.getJSONObject("client").getString("id");
                                    idLocation.setText(Id);
                                } else {
                                    String error_msg = jsonResult.getString("error_msg");
                                    Toast.makeText(AddAsset.this, error_msg, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Toast.makeText(AddAsset.this, "GAGAL", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(AddAsset.this, "Gagal mengambil data "+t.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
        user.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                user.showDropDown();
            }
        });
        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.showDropDown();
            }
        });
        user.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BaseApiService mApiService = UtilsApi.getApiService();
                Call<ResponseBody> getId = mApiService.basAkunGetId(user.getText().toString());
                getId.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            try {
                                JSONObject jsonResult = new JSONObject(response.body().string());
                                if (jsonResult.getString("error").equals("false")) {
                                    String Id = jsonResult.getJSONObject("client").getString("id");
                                    idUser.setText(Id);
                                } else {
                                    String error_msg = jsonResult.getString("error_msg");
                                    Toast.makeText(AddAsset.this, error_msg, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Toast.makeText(AddAsset.this, "GAGAL", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(AddAsset.this, "Gagal mengambil data "+t.getMessage(), Toast.LENGTH_SHORT).show();

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
                                    Toast.makeText(AddAsset.this, error_msg, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Toast.makeText(AddAsset.this, "GAGAL", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(AddAsset.this, "Gagal mengambil data "+t.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
        manufacturer.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                manufacturer.showDropDown();
            }
        });
        manufacturer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manufacturer.showDropDown();
            }
        });
        manufacturer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BaseApiService mApiService = UtilsApi.getApiService();
                Call<ResponseBody> getId = mApiService.basManufacturerGetId(manufacturer.getText().toString());
                getId.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            try {
                                JSONObject jsonResult = new JSONObject(response.body().string());
                                if (jsonResult.getString("error").equals("false")) {
                                    String Id = jsonResult.getJSONObject("client").getString("id");
                                    idManufacturer.setText(Id);
                                } else {
                                    String error_msg = jsonResult.getString("error_msg");
                                    Toast.makeText(AddAsset.this, error_msg, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Toast.makeText(AddAsset.this, "GAGAL", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(AddAsset.this, "Gagal mengambil data "+t.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
        model.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                model.showDropDown();
            }
        });
        model.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.showDropDown();
            }
        });
        model.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BaseApiService mApiService = UtilsApi.getApiService();
                Call<ResponseBody> getId = mApiService.basModelGetId(model.getText().toString());
                getId.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            try {
                                JSONObject jsonResult = new JSONObject(response.body().string());
                                if (jsonResult.getString("error").equals("false")) {
                                    String Id = jsonResult.getJSONObject("client").getString("id");
                                    idmodel.setText(Id);
                                } else {
                                    String error_msg = jsonResult.getString("error_msg");
                                    Toast.makeText(AddAsset.this, error_msg, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Toast.makeText(AddAsset.this, "GAGAL", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(AddAsset.this, "Gagal mengambil data "+t.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
        warranty.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    warranty.setSuffix(" months");
                }else if (warranty.equals("")){
                    warranty.setSuffix("");
                }else{
                    warranty.setSuffix("");
                }
            }
        });
        btn_add_asset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertAsset();
            }
        });
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR,year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };
        purchase_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(AddAsset.this, date, calendar
                        .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        ArrayAdapter<CharSequence> adapterStatus =ArrayAdapter.createFromResource(AddAsset.this,R.array.ListStatus,R.layout.custom_spinner);
        adapterStatus.setDropDownViewResource(R.layout.custom_spinner_dropdown);
        status.setAdapter(adapterStatus);
        status.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                namaStatus = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void insertAsset() {
        progressDialog.show();
        Integer idCl,idCa,idSup,idLoc,idUs,idAdm,idMan,idmod,warrant,st;
        String purchase,tg,assetName,srl,note;
        long stts= status.getSelectedItemId();
        idCl = Integer.parseInt(idClient.getText().toString());
        idCa = Integer.parseInt(idCategory.getText().toString());
        idSup = Integer.parseInt(idSupplier.getText().toString());
        idLoc = Integer.parseInt(idLocation.getText().toString());
        idUs = Integer.parseInt(idUser.getText().toString());
        idAdm = Integer.parseInt(idAdmin.getText().toString());
        idMan = Integer.parseInt(idManufacturer.getText().toString());
        idmod = Integer.parseInt(idmodel.getText().toString());
        purchase = purchase_date.getText().toString();
        warrant = Integer.parseInt(warranty.getText().toString());
        st = (int) stts;
        tg = tag.getText().toString();
        assetName = name.getText().toString();
        srl = serial.getText().toString();
        note = notes.getText().toString();
        BaseApiService mApiService = UtilsApi.getApiService();
        Call<ResponseBody> input = mApiService.basInputAssets(idCa,idAdm,idCl,idUs,idMan,idmod,idSup,st,purchase,warrant,tg,assetName,srl,note,idLoc);
        input.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                progressDialog.dismiss();
                Toast.makeText(AddAsset.this, "Berhasil Input Data", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(AddAsset.this, "Gagal Response ke database : "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getModel() {
        Call<AssetsModelResponseModel> getassetmodel = mApiService.basGetAssetsModel();
        getassetmodel.enqueue(new Callback<AssetsModelResponseModel>() {
            @Override
            public void onResponse(Call<AssetsModelResponseModel> call, Response<AssetsModelResponseModel> response) {
                if (response.isSuccessful()) {
                    modelModel = response.body().getData();
                    List<String> listModel = new ArrayList<String>();

                    for (int i = 0; i < modelModel.size(); i++){
                        listModel.add(modelModel.get(i).getName());
                    }
                    // Set hasil result json ke dalam adapter spinner
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(AddAsset.this,
                            android.R.layout.simple_spinner_item, listModel);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    model.setThreshold(1);
                    model.setAdapter(adapter);
                } else {
                    Toast.makeText(AddAsset.this, "Gagal mengambil data", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<AssetsModelResponseModel> call, Throwable t) {
                Toast.makeText(AddAsset.this, "Gagal mengambil data "+t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void getManufacturer() {
        Call<ManufacturersResponseModel> getmanufacturer = mApiService.basGetManufacturers();
        getmanufacturer.enqueue(new Callback<ManufacturersResponseModel>() {
            @Override
            public void onResponse(Call<ManufacturersResponseModel> call, Response<ManufacturersResponseModel> response) {
                if (response.isSuccessful()) {
                    manufacturerModel = response.body().getData();
                    List<String> listManufacturer = new ArrayList<String>();
                    for (int i = 0; i < manufacturerModel.size(); i++){
                        listManufacturer.add(manufacturerModel.get(i).getName());
                    }
                    // Set hasil result json ke dalam adapter spinner
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(AddAsset.this,
                            android.R.layout.simple_spinner_item, listManufacturer);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    manufacturer.setThreshold(1);
                    manufacturer.setAdapter(adapter);
                } else {
                    Toast.makeText(AddAsset.this, "Gagal mengambil data", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ManufacturersResponseModel> call, Throwable t) {
                Toast.makeText(AddAsset.this, "Gagal mengambil data "+t.getMessage(), Toast.LENGTH_SHORT).show();

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
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(AddAsset.this,
                            android.R.layout.simple_spinner_item, listAdmin);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    admin.setThreshold(1);
                    admin.setAdapter(adapter);
                } else {
                    Toast.makeText(AddAsset.this, "Gagal mengambil data", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<StaffResponseModel> call, Throwable t) {
                Toast.makeText(AddAsset.this, "Gagal mengambil data "+t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void getUser() {
        Call<UsersResponseModel> getuser = mApiService.basGetUser();
        getuser.enqueue(new Callback<UsersResponseModel>() {
            @Override
            public void onResponse(Call<UsersResponseModel> call, Response<UsersResponseModel> response) {
                if (response.isSuccessful()) {
                    userModel = response.body().getData();
                    List<String> listUser = new ArrayList<String>();
                    for (int i = 0; i < userModel.size(); i++){
                        listUser.add(userModel.get(i).getName());
                    }
                    // Set hasil result json ke dalam adapter spinner
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(AddAsset.this,
                            android.R.layout.simple_spinner_item, listUser);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    user.setThreshold(1);
                    user.setAdapter(adapter);
                } else {
                    Toast.makeText(AddAsset.this, "Gagal mengambil data", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<UsersResponseModel> call, Throwable t) {
                Toast.makeText(AddAsset.this, "Gagal mengambil data "+t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void getLocation() {
        Call<LocationsResponseModel> getlocation = mApiService.basGetLocations();
        getlocation.enqueue(new Callback<LocationsResponseModel>() {
            @Override
            public void onResponse(Call<LocationsResponseModel> call, Response<LocationsResponseModel> response) {
                if (response.isSuccessful()) {
                    locationModel = response.body().getData();
                    List<String> listLocation = new ArrayList<String>();
                    for (int i = 0; i < locationModel.size(); i++){
                        listLocation.add(locationModel.get(i).getName());

                    }
                    // Set hasil result json ke dalam adapter spinner
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(AddAsset.this,
                            android.R.layout.simple_spinner_item, listLocation);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    location.setThreshold(1);
                    location.setAdapter(adapter);
                } else {
                    Toast.makeText(AddAsset.this, "Gagal mengambil data", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<LocationsResponseModel> call, Throwable t) {
                Toast.makeText(AddAsset.this, "Gagal mengambil data "+t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void getSupplier() {
        Call<SuppliersResponseModel> getsupply = mApiService.basGetSuppliers();
        getsupply.enqueue(new Callback<SuppliersResponseModel>() {
            @Override
            public void onResponse(Call<SuppliersResponseModel> call, Response<SuppliersResponseModel> response) {
                if (response.isSuccessful()) {
                    supplierModel = response.body().getData();
                    List<String> listSupply = new ArrayList<String>();
                    for (int i = 0; i < supplierModel.size(); i++){
                        listSupply.add(supplierModel.get(i).getName());
                    }
                    // Set hasil result json ke dalam adapter spinner
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(AddAsset.this,
                            android.R.layout.simple_spinner_item, listSupply);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    supplier.setThreshold(1);
                    supplier.setAdapter(adapter);
                } else {
                    Toast.makeText(AddAsset.this, "Gagal mengambil data", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<SuppliersResponseModel> call, Throwable t) {
                Toast.makeText(AddAsset.this, "Gagal mengambil data "+t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void getCategory() {
        Call<AssetsCategoryResponseModel> getcategory = mApiService.basGetAssetsCategory();
        getcategory.enqueue(new Callback<AssetsCategoryResponseModel>() {
            @Override
            public void onResponse(Call<AssetsCategoryResponseModel> call, Response<AssetsCategoryResponseModel> response) {
                if (response.isSuccessful()) {
                    categorymodel = response.body().getData();
                    List<String> listCategory = new ArrayList<String>();
                    for (int i = 0; i < categorymodel.size(); i++){
                        listCategory.add(categorymodel.get(i).getName());
                    }
                    // Set hasil result json ke dalam adapter spinner
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(AddAsset.this,
                            android.R.layout.simple_spinner_item, listCategory);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    category.setThreshold(1);
                    category.setAdapter(adapter);
                } else {
                    Toast.makeText(AddAsset.this, "Gagal mengambil data", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<AssetsCategoryResponseModel> call, Throwable t) {
                Toast.makeText(AddAsset.this, "Gagal mengambil data "+t.getMessage(), Toast.LENGTH_SHORT).show();

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
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(AddAsset.this,
                            android.R.layout.simple_spinner_item, listClient);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    client.setThreshold(1);
                    client.setAdapter(adapter);
                } else {
                    Toast.makeText(AddAsset.this, "Gagal mengambil data", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ClientsResponseModel> call, Throwable t) {
                Toast.makeText(AddAsset.this, "Gagal mengambil data "+t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }




    private void updateLabel() {
        String myFormat = "yyyy-MM-dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.ENGLISH);

        purchase_date.setText(sdf.format(calendar.getTime()));
    }
}