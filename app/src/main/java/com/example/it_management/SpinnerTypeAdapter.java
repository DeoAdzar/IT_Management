package com.example.it_management;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import java.util.ArrayList;

public class SpinnerTypeAdapter extends ArrayAdapter<SpinnerTypeItem> {
    public SpinnerTypeAdapter(Context ctx, ArrayList<SpinnerTypeItem> typeList){
        super(ctx,0,typeList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable  View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }
    private View initView(int position,View convertView,ViewGroup parent){
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.custom_dropdown_spinner_2,parent,false
            );
        }
        ImageView image = convertView.findViewById(R.id.image_dropdown);
        TextView name = convertView.findViewById(R.id.name_dropdown);

        SpinnerTypeItem currentItem = getItem(position);

        if (currentItem != null) {
            image.setImageResource(currentItem.getmImage());
            name.setText(currentItem.getmName());
        }
        return convertView;
    }

}
