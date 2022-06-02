package com.mediading.stealthystriver.viewmodel;
import android.util.Log;

import com.mediading.stealthystriver.repository.FocusRepository;

import javax.inject.Inject;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class RankFragmentViewModel extends BaseViewModel {
    private MutableLiveData<Integer> focusProgress = new MutableLiveData<>();
    private MutableLiveData<Integer> focusMax = new MutableLiveData<>();
    private FocusRepository focusRepository;

    public final int defaultMax = 3600;
    public final int defaultFocusProgress = 1500;
    private LiveData<String> focusTime = Transformations.switchMap(focusProgress, new Function<Integer, LiveData<String>>() {
        @Override
        public LiveData<String> apply(Integer input) {
            LiveData<String> tmp1 = focusRepository.getFocusTime(input);
            LiveData<String> tmp2 = focusRepository.getFocusTime(input);
            String test = tmp1.hashCode()==tmp2.hashCode()?"true":"false";
            Log.i(TAG,"....................."+test);
            Log.i(TAG,"....................."+focusRepository.getFocusTime(input).getValue());
            return focusRepository.getFocusTime(input);
        }
    });


    public void setFocusProgress(Integer focusProgress) {
        this.focusProgress.setValue(focusProgress);
    }

    public MutableLiveData<Integer> getFocusMax() {
        return focusMax;
    }

    public void setFocusMax(int focusMax) {
        this.focusMax.setValue(focusMax);
    }

    @Override
    protected void initDataStatus() {
        dataStatus.postValue(new DataStatus("专注~",""));
    }

    @Inject
    RankFragmentViewModel(FocusRepository focusRepository){
        super();
        this.focusRepository = focusRepository;
        this.focusProgress.setValue(defaultFocusProgress);
        this.focusMax.setValue(defaultMax);
        Log.i(TAG,"INIT -->"+this.focusProgress.getValue()+" focus time:"+this.focusTime.getValue());
    }

    public LiveData<String> getFocusTime() {
        return focusTime;
    }

    public MutableLiveData<Integer> getFocusProgress() {
        return focusProgress;
    }

}