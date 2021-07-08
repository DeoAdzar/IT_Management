package com.example.it_management.ui.System.ApiKeys;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.it_management.R;


public class ApiKeysFragment extends Fragment {
    private ApiKeysViewModel apiKeysViewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        apiKeysViewModel =
                ViewModelProviders.of(this).get(ApiKeysViewModel.class);
        View root = inflater.inflate(R.layout.fragment_api_keys, container, false);
        final TextView textView = root.findViewById(R.id.text_api_key);
        apiKeysViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}