package com.mediading.stealthystriver.repository;


import android.util.Log;

import com.mediading.stealthystriver.utils.Constant;
import com.mediading.stealthystriver.utils.MVUtils;
import com.mediading.stealthystriver.utils.MVUtilsEntryPoint;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.MutableLiveData;
import dagger.hilt.android.EntryPointAccessors;

import static com.mediading.stealthystriver.StealthyStriverApplication.getContext;

public class FocusRepository {
    private final String TAG = this.getClass().getName();

    private MutableLiveData<Integer> progressLeft = new MutableLiveData<>();
    /**
     * 在非
     */
    private final MVUtils mvUtils;
    private MutableLiveData<String> focusTime = new MutableLiveData<>();

    @Inject
    FocusRepository() {
        Log.i(TAG, "FocusRepo Created");
        MVUtilsEntryPoint entryPoint = EntryPointAccessors.fromApplication(getContext(), MVUtilsEntryPoint.class);
        mvUtils = entryPoint.getMVUtils();
        progressLeft.setValue(mvUtils.getInt(Constant.CURRENT_FOCUS));
    }

    public MutableLiveData<String> reflashFocusTime(Integer progress) {
        int minutes = progress / 60;
        int seconds = progress - minutes * 60;
        String sec;

        if (seconds >= 0 && seconds < 10) {
            sec = "0" + seconds;
        } else {
            sec = Integer.toString(seconds);
        }
        focusTime.setValue(minutes + ":" + sec);
        return focusTime;
    }

    public MutableLiveData<Integer> getProgressLeft() {
        Log.i(TAG,"getProgressLeft");
        return progressLeft;
    }


    public void saveFocusTime() {
        List<String> currentFocusTime = Arrays.asList(this.focusTime.getValue().split(":"));
        String minute = currentFocusTime.get(0);
        String sec = currentFocusTime.get(1);
        // 转换为秒
        Integer time = Integer.parseInt(minute) * 60 + Integer.parseInt(sec);
        // 存入缓存
        mvUtils.put(Constant.CURRENT_FOCUS, time);
        Log.i(TAG, time + "");
    }
}
