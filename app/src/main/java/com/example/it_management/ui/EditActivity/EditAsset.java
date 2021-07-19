package com.example.it_management.ui.EditActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.it_management.R;
import com.example.it_management.ui.Clients.ClientsModel;
import com.example.it_management.ui.Inventory.Attributes.AssetsCategories.AssetsCategoryModel;
import com.example.it_management.ui.Inventory.Attributes.AssetsModels.AssetsModelModel;
import com.example.it_management.ui.Inventory.Attributes.Locations.LocationsModel;
import com.example.it_management.ui.Inventory.Attributes.Manufacturers.ManufacturersModel;
import com.example.it_management.ui.Inventory.Attributes.Suppliers.SuppliersModel;
import com.example.it_management.ui.People.Staff.StaffModel;
import com.example.it_management.ui.People.Users.UsersModel;
import com.tobiasschuerg.prefixsuffix.PrefixSuffixEditText;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class EditAsset extends AppCompatActivity {
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_asset);
    }
}