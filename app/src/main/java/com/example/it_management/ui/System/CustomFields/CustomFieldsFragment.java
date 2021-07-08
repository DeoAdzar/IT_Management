package com.example.it_management.ui.System.CustomFields;

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


public class CustomFieldsFragment extends Fragment {
    private CustomFieldsViewModel customFieldsViewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        customFieldsViewModel =
                ViewModelProviders.of(this).get(CustomFieldsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_custom_fields, container, false);
        final TextView textView = root.findViewById(R.id.text_custom_fields);
        customFieldsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}