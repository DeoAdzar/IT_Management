package com.example.it_management.ui.AddActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.it_management.API.BaseApiService;
import com.example.it_management.API.UtilsApi;
import com.example.it_management.R;
import com.example.it_management.SpinnerTypeAdapter;
import com.example.it_management.SpinnerTypeItem;
import com.example.it_management.ui.Clients.ClientsModel;
import com.example.it_management.ui.People.Staff.StaffModel;
import com.google.gson.TypeAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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
    ArrayList<SpinnerTypeItem> typeList;
    SpinnerTypeAdapter adapter ;

    BaseApiService mApiService = UtilsApi.getApiService();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_issues);
        init();
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