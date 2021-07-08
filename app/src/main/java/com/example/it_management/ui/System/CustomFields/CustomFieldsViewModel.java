package com.example.it_management.ui.System.CustomFields;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CustomFieldsViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public CustomFieldsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Custom Fields fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
