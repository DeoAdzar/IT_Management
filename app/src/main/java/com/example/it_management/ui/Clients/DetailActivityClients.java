package com.example.it_management.ui.Clients;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.it_management.R;
import com.example.it_management.ui.Clients.FragmentDetail.ClientAssets.ClientsAssetsFragment;
import com.example.it_management.ui.Clients.FragmentDetail.ClientCredential.ClientsCredentialFragment;
import com.example.it_management.ui.Clients.FragmentDetail.ClientFiles.ClientsFilesFragment;
import com.example.it_management.ui.Clients.FragmentDetail.ClientIssues.ClientsIssuesFragment;
import com.example.it_management.ui.Clients.FragmentDetail.ClientLicense.ClientsLicenseFragment;
import com.example.it_management.ui.Clients.FragmentDetail.ClientNotes.ClientsNotesFragment;
import com.example.it_management.ui.Clients.FragmentDetail.ClientProjects.ClientsProjectsFragment;
import com.example.it_management.ui.Clients.FragmentDetail.ClientTickets.ClientsTicketsFragment;
import com.example.it_management.ui.Clients.FragmentDetail.ClientTimeLog.ClientsTimeLogFragment;
import com.example.it_management.ui.Clients.FragmentDetail.ClientUser.ClientsUserFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class DetailActivityClients extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Button btnBack;
    private String idClient;
    private Integer idTabClient;
    private TextView tvName,tvId,tvnotes,tvasset_tag_prefix, tvlicense_tag_prefix;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_clients);
        tabLayout = findViewById(R.id.tabLayoutClients);
        viewPager = findViewById(R.id.viewPagerClients);
        btnBack =findViewById(R.id.back_detail_client);
        tvName = findViewById(R.id.tv_ClientsName);
        tvId = findViewById(R.id.id_ClientsTab);
        tvnotes = findViewById(R.id.tv_ClientsNotes);
        tvasset_tag_prefix = findViewById(R.id.tvlicense_tag_prefix);
        tvlicense_tag_prefix= findViewById(R.id.tvlicense_tag_prefix);
        tvnotes.setText(getIntent().getStringExtra("notes"));
        tvId.setText(getIntent().getStringExtra("id"));
        tvName.setText(getIntent().getStringExtra("name"));
        tvasset_tag_prefix.setText(getIntent().getStringExtra("asset_tag"));
        tvlicense_tag_prefix.setText(getIntent().getStringExtra("license_tag"));
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);


//        tab();
//        fragmentAdapterClients =new FragmentAdapterClients(getSupportFragmentManager(),tabLayout.getTabCount());
//        viewPager.setAdapter(fragmentAdapterClients);
//        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                viewPager.setCurrentItem(tab.getPosition());
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//                viewPager.refreshDrawableState();
//            }
//        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setupViewPager(ViewPager viewPager) {
        Bundle bundle = new Bundle();
        bundle.putString("idClient",tvId.getText().toString());
        bundle.putString("nama",tvName.getText().toString());
        bundle.putString("notes",tvnotes.getText().toString());
        bundle.putString("asset_tag",tvasset_tag_prefix.getText().toString());
        bundle.putString("license_tag",tvlicense_tag_prefix.getText().toString());
        ClientsAssetsFragment clientsAssetsFragment = new ClientsAssetsFragment();
        ClientsCredentialFragment clientsCredentialFragment = new ClientsCredentialFragment();
        ClientsLicenseFragment clientsLicenseFragment = new ClientsLicenseFragment();
        ClientsProjectsFragment clientsProjectsFragment = new ClientsProjectsFragment();
        ClientsIssuesFragment clientsIssuesFragment = new ClientsIssuesFragment();
        ClientsTicketsFragment clientsTicketsFragment = new ClientsTicketsFragment();
        ClientsUserFragment clientsUserFragment = new ClientsUserFragment();
        ClientsFilesFragment clientsFilesFragment  = new ClientsFilesFragment();
        ClientsTimeLogFragment clientsTimeLogFragment = new ClientsTimeLogFragment();
        ClientsNotesFragment clientsNotesFragment = new ClientsNotesFragment();
        clientsAssetsFragment.setArguments(bundle);
        clientsCredentialFragment.setArguments(bundle);
        clientsLicenseFragment.setArguments(bundle);
        clientsProjectsFragment.setArguments(bundle);
        clientsIssuesFragment.setArguments(bundle);
        clientsTicketsFragment.setArguments(bundle);
        clientsUserFragment.setArguments(bundle);
        clientsFilesFragment.setArguments(bundle);
        clientsTimeLogFragment.setArguments(bundle);
        clientsNotesFragment.setArguments(bundle);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(clientsAssetsFragment, "Assets");
        adapter.addFrag(clientsLicenseFragment, "License");
        adapter.addFrag(clientsProjectsFragment, "Projects");
        adapter.addFrag(clientsIssuesFragment, "Issues");
        adapter.addFrag(clientsTicketsFragment, "Tickets");
        adapter.addFrag(clientsCredentialFragment, "Credential");
        adapter.addFrag(clientsUserFragment, "User");
        adapter.addFrag(clientsFilesFragment, "Files");
        adapter.addFrag(clientsTimeLogFragment, "Time Log");
        adapter.addFrag(clientsNotesFragment, "Notes");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

            @Override
            public CharSequence getPageTitle(int position) {
                return mFragmentTitleList.get(position);
            }
    }
//    private void tab(){
//        tabLayout.addTab(tabLayout.newTab().setText("Assets"));
//        tabLayout.addTab(tabLayout.newTab().setText("License"));
//        tabLayout.addTab(tabLayout.newTab().setText("Projects"));
//        tabLayout.addTab(tabLayout.newTab().setText("Issues"));
//        tabLayout.addTab(tabLayout.newTab().setText("Tickets"));
//        tabLayout.addTab(tabLayout.newTab().setText("Credential"));
//        tabLayout.addTab(tabLayout.newTab().setText("User"));
//        tabLayout.addTab(tabLayout.newTab().setText("Files"));
//        tabLayout.addTab(tabLayout.newTab().setText("Time Log"));
//        tabLayout.addTab(tabLayout.newTab().setText("Notes"));
//        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
//    }
}