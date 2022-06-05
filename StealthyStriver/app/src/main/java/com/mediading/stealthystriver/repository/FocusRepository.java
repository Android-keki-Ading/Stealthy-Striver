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
    private MutableLiveData<Integer> focusProgress = new MutableLiveData<>();
    private MutableLiveData<Integer> progressLeft = new MutableLiveData<>();
    private MutableLiveData<Integer> progressTotal = new MutableLiveData<>();
    private MutableLiveData<String> focusTotal = new MutableLiveData<>();
    private final MVUtils mvUtils;
    private MutableLiveData<String> focusTime = new MutableLiveData<>();
    private boolean flag = true;
    @Inject
    FocusRepository() {
        Log.i(TAG, "FocusRepo Created");
        MVUtilsEntryPoint entryPoint = EntryPointAccessors.fromApplication(getContext(), MVUtilsEntryPoint.class);
        mvUtils = entryPoint.getMVUtils();
        this.focusTime.setValue("25:00");
        progressLeft.setValue(mvUtils.getInt(Constant.CURRENT_FOCUS));
            progressTotal.setValue(mvUtils.getInt(Constant.TOTAL_FOCUS));
    }

    public void setFocusProgress(Integer progress) {
        this.focusProgress.setValue(progress);
    }

    public MutableLiveData<String> reflashFocusTime(Integer progress) {
        if(flag){
            // 只记录初始的progress有多长
            setFocusProgress(progress);
            flag=false;
        }
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

    public MutableLiveData<Integer> getProgressTotal() {
        Log.i(TAG,"getProgressTotal");
        return progressTotal;
    }

    public MutableLiveData<Integer> getFocusProgress() {
        Log.i(TAG, "getFocusProgress : "+ focusProgress.getValue());
        return focusProgress;
    }

    public void saveFocusTime() {
        List<String> currentFocusTime = Arrays.asList(this.focusTime.getValue().split(":"));
        String minute = currentFocusTime.get(0);
        String sec = currentFocusTime.get(1);
        // 转换为秒
        Integer time = Integer.parseInt(minute) * 60 + Integer.parseInt(sec);
        // 存入缓存
        mvUtils.put(Constant.CURRENT_FOCUS, time);

        Integer focusedTime = getFocusProgress().getValue()-time;
        mvUtils.put(Constant.TOTAL_FOCUS, focusedTime+getProgressTotal().getValue());
        Log.i(TAG, focusedTime + ",focusedTime");
        Log.i(TAG,  "TOTAL_FOCUS "+mvUtils.getInt(Constant.TOTAL_FOCUS));
    }

    public MutableLiveData<String> getFocusTotal(){
        Integer secs = getProgressTotal().getValue();
        int minutes = secs / 60;
        Log.i(TAG,""+secs);
        this.focusTotal.setValue(minutes+"分钟");
        return focusTotal;
    }
}
