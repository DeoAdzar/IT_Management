package com.example.it_management.ui.System.Import;

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


public class ImportFragment extends Fragment {
    private ImportViewModel importViewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        importViewModel =
                ViewModelProviders.of(this).get(ImportViewModel.class);
        View root = inflater.inflate(R.layout.fragment_import, container, false);
        final TextView textView = root.findViewById(R.id.text_import);
        importViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}