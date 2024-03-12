package com.example.myapplication;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.MutableLiveData;

public class SharedViewModel extends ViewModel {
    private final MutableLiveData<Integer> progressBarLimit = new MutableLiveData<>();
    private final MutableLiveData<Integer> currentProgressValue = new MutableLiveData<>();

    public void setProgressBarLimit(int limit) {
        progressBarLimit.setValue(limit);
    }

    public MutableLiveData<Integer> getProgressBarLimit() {
        return progressBarLimit;
    }

    public void setCurrentProgressValue(int value) {
        currentProgressValue.setValue(value);
    }

    public MutableLiveData<Integer> getCurrentProgressValue() {
        return currentProgressValue;
    }
}


