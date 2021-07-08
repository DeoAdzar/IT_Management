package com.example.it_management;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.app.AlertDialog;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class HomeActivity extends AppCompatActivity {
    private AppBarConfiguration mAppBarConfiguration;
    Button btnExit;
    boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        Intent intent = getIntent();
//        String extraName = intent.getStringExtra("result_name");
//        String extraEmail = intent.getStringExtra("email");
//        nama = (TextView) findViewById(R.id.namaAdmin);
//        nama.setText("fhazjmnrjyzgxydc");
        btnExit = findViewById(R.id.exit);

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder alert = new AlertDialog.Builder(HomeActivity.this);
                alert.setTitle("Logout");
                alert.setMessage("Are you sure want to logout?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .setNegativeButton("No", null);
                alert.show();
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        View HeaderView = navigationView.getHeaderView(0);
        TextView textNama = (TextView) HeaderView.findViewById(R.id.namaAdmin);
        /*ImageView avatar = (ImageView) HeaderView.findViewById(R.id.imageView);*/

        textNama.setText(getIntent().getStringExtra("result_name"));
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_project, R.id.nav_assets,
                R.id.nav_knowledge_base, R.id.nav_credentials, R.id.nav_licenses,
                R.id.nav_assets_categories, R.id.nav_assets_models, R.id.nav_license_categories,
                R.id.nav_locations, R.id.nav_manufacturers, R.id.nav_status_labels,
                R.id.nav_suppliers, R.id.nav_active_issues, R.id.nav_all_issues,
                R.id.nav_monitoring, R.id.nav_contacs, R.id.nav_roles, R.id.nav_staff,
                R.id.nav_users, R.id.nav_reports, R.id.nav_api_keys, R.id.nav_custom_fields,
                R.id.nav_import, R.id.nav_logs, R.id.nav_predefined_replies, R.id.nav_setting,
                R.id.nav_active_tickets, R.id.nav_all_tickets, R.id.nav_awaiting_reply)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.home, menu);
//        return true;
//    }

    @Override
    public void onBackPressed() {
        final AlertDialog.Builder alert = new AlertDialog.Builder(HomeActivity.this);

        alert.setTitle("Logout");
        alert.setMessage("Are you sure want to logout?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                })
                .setNegativeButton("No", null);
        alert.show();
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}