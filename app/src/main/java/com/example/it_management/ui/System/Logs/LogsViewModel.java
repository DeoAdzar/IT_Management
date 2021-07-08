package com.example.it_management.ui.System.Logs;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LogsViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public LogsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Logs fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
