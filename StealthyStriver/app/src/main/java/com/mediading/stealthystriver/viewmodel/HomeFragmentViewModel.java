package com.mediading.stealthystriver.viewmodel;

import com.mediading.stealthystriver.model.InfoResponse;
import com.mediading.stealthystriver.model.UserInfo;
import com.mediading.stealthystriver.repository.FocusRepository;
import com.mediading.stealthystriver.repository.UserRepository;

import javax.inject.Inject;


import androidx.lifecycle.MutableLiveData;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class HomeFragmentViewModel extends BaseViewModel {
    private FocusRepository focusRepository;
    private UserRepository userRepository;
    private MutableLiveData<Integer> focusProgressTotal;
    private MutableLiveData<String> focusTotal;
    private MutableLiveData<InfoResponse> infoResponse;


    @Inject
    HomeFragmentViewModel(FocusRepository focusRepository, UserRepository userRepository){
        super();
        this.userRepository = userRepository;
        this.focusRepository = focusRepository;
        this.focusProgressTotal=focusRepository.getProgressTotal();
        this.focusTotal = focusRepository.getFocusTotal();
        this.infoResponse = userRepository.getInfoResponse();
    }

    public MutableLiveData<InfoResponse> getInfoResponse() {
        return infoResponse;
    }

    public void setInfoResponse(MutableLiveData<InfoResponse> infoResponse) {
        this.infoResponse = infoResponse;
    }

    public MutableLiveData<Integer> getFocusProgressTotal() {
        return focusProgressTotal;
    }

    public MutableLiveData<String> getFocusTotal() {
        return focusTotal;
    }

    @Override
    protected void initDataStatus() {
    }

}