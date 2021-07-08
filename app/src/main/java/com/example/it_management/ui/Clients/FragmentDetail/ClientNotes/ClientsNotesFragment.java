package com.example.it_management.ui.Clients.FragmentDetail.ClientNotes;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.it_management.R;

public class ClientsNotesFragment extends Fragment {
    EditText etNotes;
    TextView id,name,asset_tag,license_tag;
    Button btnSave,btncancel;
    private String note;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =inflater.inflate(R.layout.fragment_clients_notes, container, false);
        id = v.findViewById(R.id.clientid_note);
        name = v.findViewById(R.id.clientname_note);
        asset_tag = v.findViewById(R.id.tvasset_tag_prefix_client_note);
        license_tag = v.findViewById(R.id.tvlicense_tag_prefix_client_note);
        etNotes =  v.findViewById(R.id.et_notes_client);
        btnSave = v.findViewById(R.id.btn_save_notes_client);
        btncancel = v.findViewById(R.id.btn_cancel_notes_client);
        id.setText(getArguments().getString("idClient"));
        name.setText(getArguments().getString("nama"));
        asset_tag.setText(getArguments().getString("asset_tag"));
        license_tag.setText(getArguments().getString("license_tag"));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            etNotes.setText(Html.fromHtml(getArguments().getString("notes"), Html.FROM_HTML_MODE_COMPACT));
        } else {
            etNotes.setText(Html.fromHtml(getArguments().getString("notes")));
        }
        etNotes.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                btnSave.setVisibility(View.VISIBLE);
                btncancel.setVisibility(View.VISIBLE);
            }
        });
        etNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnSave.setVisibility(View.VISIBLE);
                btncancel.setVisibility(View.VISIBLE);
            }
        });
        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog dialog = new AlertDialog.Builder(getActivity())
                        .setTitle("Warning !")
                        .setMessage("Apakah anda ingin menghapus perubahan ?")
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                btnSave.setVisibility(View.GONE);
                                btncancel.setVisibility(View.GONE);
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                    etNotes.setText(Html.fromHtml(getArguments().getString("notes"), Html.FROM_HTML_MODE_COMPACT));
                                } else {
                                    etNotes.setText(Html.fromHtml(getArguments().getString("notes")));
                                }
                            }
                        }).setNegativeButton("Tidak",null)
                        .show();
            }
        });
        return v;

    }


}