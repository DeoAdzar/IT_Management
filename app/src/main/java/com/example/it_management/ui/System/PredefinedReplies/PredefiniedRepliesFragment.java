package com.example.it_management.ui.System.PredefinedReplies;

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


public class PredefiniedRepliesFragment extends Fragment {
    private PredefiniedRepliesViewModel predefiniedRepliesViewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        predefiniedRepliesViewModel =
                ViewModelProviders.of(this).get(PredefiniedRepliesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_predefinied_replies, container, false);
        final TextView textView = root.findViewById(R.id.text_predefinied_replies);
        predefiniedRepliesViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}