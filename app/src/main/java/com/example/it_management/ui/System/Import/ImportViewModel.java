package com.example.it_management.ui.System.Import;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ImportViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public ImportViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Import fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
