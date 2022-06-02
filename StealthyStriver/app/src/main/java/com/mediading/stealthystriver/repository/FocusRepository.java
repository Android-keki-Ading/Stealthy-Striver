package com.mediading.stealthystriver.repository;


import android.util.Log;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class FocusRepository {
    private final String TAG = this.getClass().getName();

    @Inject
    FocusRepository(){
        Log.i(TAG, "FocusRepo Created");
    }

    private MutableLiveData<String> focusTime = new MutableLiveData<>();


    public MutableLiveData<String> getFocusTime(Integer progress) {
        int minutes = progress/60;
        int seconds = progress - minutes*60;
        String sec;

        if (seconds>=0 && seconds<10){
            sec = "0"+seconds;
        }else{
            sec = Integer.toString(seconds);
        }
        focusTime.setValue(minutes + ":" + sec);
        return focusTime;
    }
}
