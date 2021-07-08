package com.example.it_management.ui.System.ApiKeys;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ApiKeysViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public ApiKeysViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Api KEys fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
