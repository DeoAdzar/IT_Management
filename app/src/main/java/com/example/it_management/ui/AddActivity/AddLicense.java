package com.example.it_management.ui.AddActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.it_management.API.BaseApiService;
import com.example.it_management.API.UtilsApi;
import com.example.it_management.R;
import com.example.it_management.ui.Clients.ClientsModel;
import com.example.it_management.ui.Clients.ClientsResponseModel;
import com.example.it_management.ui.Inventory.Attributes.AssetsCategories.AssetsCategoryModel;
import com.example.it_management.ui.Inventory.Attributes.AssetsCategories.AssetsCategoryResponseModel;
import com.example.it_management.ui.Inventory.Attributes.LicenseCategories.LicenseCategoryModel;
import com.example.it_management.ui.Inventory.Attributes.LicenseCategories.LicenseCategoryResponseModel;
import com.example.it_management.ui.Inventory.Attributes.Suppliers.SuppliersModel;
import com.example.it_management.ui.Inventory.Attributes.Suppliers.SuppliersResponseModel;
import com.tobiasschuerg.prefixsuffix.PrefixSuffixEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dmax.dialog.SpotsDialog;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddLicense extends AppCompatActivity {
    public AutoCompleteTextView client,category,supplier;
    EditText tag,name,serial,seat,notes;
    TextView idClient,idCategory,idSupplier;
    Button btn_add_license,btn_back;
    Spinner status;
    private AlertDialog progressDialog;
    String namaStatus;
    List<ClientsModel> clientModel = new ArrayList<>();
    List<LicenseCategoryModel> categorymodel = new ArrayList<>();
    List<SuppliersModel> supplierModel = new ArrayList<>();
    BaseApiService mApiService = UtilsApi.getApiService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_license);
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
                                    Toast.makeText(AddLicense.this, error_msg, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Toast.makeText(AddLicense.this, "GAGAL", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(AddLicense.this, "Gagal mengambil data "+t.getMessage(), Toast.LENGTH_SHORT).show();

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
                Call<ResponseBody> getId = mApiService.basLicenseCategoryGetId(category.getText().toString());
                getId.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            try {
                                JSONObject jsonResult = new JSONObject(response.body().string());
                                if (jsonResult.getString("error").equals("false")) {
                                    String Id = jsonResult.getJSONObject("license").getString("id");
                                    idCategory.setText(Id);
                                } else {
                                    String error_msg = jsonResult.getString("error_msg");
                                    Toast.makeText(AddLicense.this, error_msg, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Toast.makeText(AddLicense.this, "GAGAL", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(AddLicense.this, "Gagal mengambil data "+t.getMessage(), Toast.LENGTH_SHORT).show();

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
                                    Toast.makeText(AddLicense.this, error_msg, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Toast.makeText(AddLicense.this, "GAGAL", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(AddLicense.this, "Gagal mengambil data "+t.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
        btn_add_license.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertLicense();
            }
        });
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ArrayAdapter<CharSequence> adapterStatus =ArrayAdapter.createFromResource(AddLicense.this,R.array.ListStatus,R.layout.custom_spinner);
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

    private void insertLicense() {
        progressDialog.show();
        Integer idCl,idCa,idSup,st;
        String seatt,tg,LicenseName,srl,note;
        long stts= status.getSelectedItemId();
        idCl = Integer.parseInt(idClient.getText().toString());
        idCa = Integer.parseInt(idCategory.getText().toString());
        idSup = Integer.parseInt(idSupplier.getText().toString());
        st = (int) stts;
        tg = tag.getText().toString();
        LicenseName = name.getText().toString();
        seatt = seat.getText().toString();
        srl = serial.getText().toString();
        note = notes.getText().toString();
        BaseApiService mApiService = UtilsApi.getApiService();
        Call<ResponseBody> input = mApiService.basInputLicense(idCa,idCl,idSup,st,tg,LicenseName,seatt,srl,note);
        input.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                progressDialog.dismiss();
                Toast.makeText(AddLicense.this, "Berhasil input", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(AddLicense.this, "Gagal input", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void init() {
        client = findViewById(R.id.et_add_licenses_client);
        idClient = findViewById(R.id.et_add_licenses_clientid);
        category = findViewById(R.id.et_add_licenses_category);
        idCategory = findViewById(R.id.et_add_licenses_categoryid);
        supplier = findViewById(R.id.et_add_licenses_supplier);
        idSupplier = findViewById(R.id.et_add_licenses_supplierid);
        tag = findViewById(R.id.et_add_license_tag);
        name = findViewById(R.id.et_add_license_name);
        serial = findViewById(R.id.et_add_license_serial);
        seat = findViewById(R.id.et_add_license_seat);
        notes = findViewById(R.id.et_add_license_notes);
        status = findViewById(R.id.spin_add_license_status);
        btn_add_license = findViewById(R.id.btn_add_license);
        btn_back = findViewById(R.id.btn_back_Addlicenses);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.ListStatus,R.layout.custom_spinner);
        adapter.setDropDownViewResource(R.layout.custom_spinner_dropdown);
        status.setAdapter(adapter);
        progressDialog = new SpotsDialog(this, R.style.Custom);

    }

    private void getData() {
        getSupplier();
        getClient();
        getCategory();
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
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(AddLicense.this,
                            android.R.layout.simple_spinner_item, listSupply);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    supplier.setThreshold(1);
                    supplier.setAdapter(adapter);
                } else {
                    Toast.makeText(AddLicense.this, "Gagal mengambil data", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<SuppliersResponseModel> call, Throwable t) {
                Toast.makeText(AddLicense.this, "Gagal mengambil data "+t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void getCategory() {
        Call<LicenseCategoryResponseModel> getcategory = mApiService.basGetLicenseCategory();
        getcategory.enqueue(new Callback<LicenseCategoryResponseModel>() {
            @Override
            public void onResponse(Call<LicenseCategoryResponseModel> call, Response<LicenseCategoryResponseModel> response) {
                if (response.isSuccessful()) {
                    categorymodel = response.body().getData();
                    List<String> listCategory = new ArrayList<String>();
                    for (int i = 0; i < categorymodel.size(); i++){
                        listCategory.add(categorymodel.get(i).getName());
                    }
                    // Set hasil result json ke dalam adapter spinner
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(AddLicense.this,
                            android.R.layout.simple_spinner_item, listCategory);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    category.setThreshold(1);
                    category.setAdapter(adapter);
                } else {
                    Toast.makeText(AddLicense.this, "Gagal mengambil data", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<LicenseCategoryResponseModel> call, Throwable t) {
                Toast.makeText(AddLicense.this, "Gagal mengambil data "+t.getMessage(), Toast.LENGTH_SHORT).show();

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
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(AddLicense.this,
                            android.R.layout.simple_spinner_item, listClient);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    client.setThreshold(1);
                    client.setAdapter(adapter);
                } else {
                    Toast.makeText(AddLicense.this, "Gagal mengambil data", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ClientsResponseModel> call, Throwable t) {
                Toast.makeText(AddLicense.this, "Gagal mengambil data "+t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}