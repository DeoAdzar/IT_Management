package com.example.it_management.ui.System.PredefinedReplies;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PredefiniedRepliesViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public PredefiniedRepliesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Predifinied Replies fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
