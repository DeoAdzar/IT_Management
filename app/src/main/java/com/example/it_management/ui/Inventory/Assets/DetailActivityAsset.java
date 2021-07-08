package com.example.it_management.ui.Inventory.Assets;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.it_management.R;
import com.example.it_management.ui.Clients.DetailActivityClients;
import com.example.it_management.ui.Inventory.Assets.FragmentDetail.FilesAssets.FilesAssetsFragment;
import com.example.it_management.ui.Inventory.Assets.FragmentDetail.IssuesAssets.IssuesAssetsFragment;
import com.example.it_management.ui.Inventory.Assets.FragmentDetail.TicketsAssets.TicketsAssetsFragment;
import com.example.it_management.ui.Inventory.Assets.FragmentDetail.TimeLogAssets.TimeLogAssets;
import com.google.android.material.tabs.TabLayout;

import java.io.File;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class DetailActivityAsset extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Button btnBack;
    private TextView tvName,tvId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_asset);
        tabLayout = findViewById(R.id.tabLayoutAssets);
        viewPager = findViewById(R.id.viewPagerAssets);
        btnBack = findViewById(R.id.back_detail_assets);
        tvId = findViewById(R.id.id_assets_tab);
        tvName = findViewById(R.id.tv_assets_name);
        tvId.setText(getIntent().getStringExtra("idAssets"));
        tvName.setText(getIntent().getStringExtra("nameAssets"));
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        Bundle bundle = new Bundle();
        bundle.putString("idAssets",tvId.getText().toString());
        FilesAssetsFragment filesAssetsFragment = new FilesAssetsFragment();
        IssuesAssetsFragment issuesAssetsFragment = new IssuesAssetsFragment();
        TicketsAssetsFragment ticketsAssetsFragment = new TicketsAssetsFragment();
        TimeLogAssets timeLogAssets = new TimeLogAssets();
        filesAssetsFragment.setArguments(bundle);
        issuesAssetsFragment.setArguments(bundle);
        ticketsAssetsFragment.setArguments(bundle);
        timeLogAssets.setArguments(bundle);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(issuesAssetsFragment,"Issues");
        adapter.addFrag(ticketsAssetsFragment,"Tickets");
        adapter.addFrag(filesAssetsFragment,"Files");
        adapter.addFrag(timeLogAssets,"Time Log");
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
}