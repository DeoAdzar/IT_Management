package com.example.it_management.ui.AddActivity;

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
import com.example.it_management.ui.Inventory.Attributes.Suppliers.SuppliersModel;
import com.tobiasschuerg.prefixsuffix.PrefixSuffixEditText;

import java.util.ArrayList;
import java.util.List;

public class AddLicense extends AppCompatActivity {
    public AutoCompleteTextView client,category,supplier;
    EditText tag,name,serial,seat,notes;
    TextView idClient,idCategory,idSupplier;
    PrefixSuffixEditText warranty;
    Button btn_add_license,btn_back;
    Spinner status;
    private AlertDialog progressDialog;
    String namaStatus;
    List<ClientsModel> clientModel = new ArrayList<>();
    List<AssetsCategoryModel> categorymodel = new ArrayList<>();
    List<SuppliersModel> supplierModel = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_license);
    }
}