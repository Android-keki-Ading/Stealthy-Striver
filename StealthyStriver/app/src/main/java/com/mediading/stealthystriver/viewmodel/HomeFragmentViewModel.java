package com.mediading.stealthystriver.viewmodel;

import com.mediading.stealthystriver.model.UserInfo;
import com.mediading.stealthystriver.repository.FocusRepository;
import com.mediading.stealthystriver.repository.UserRepository;

import javax.inject.Inject;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import dagger.hilt.android.AndroidEntryPoint;
import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class HomeFragmentViewModel extends BaseViewModel {
    private FocusRepository focusRepository;
    private MutableLiveData<String> currentVersion;
    private final String github = "github.com/ldqsss";
    private final String contactEmail = "734200940@qq.com";
    private MutableLiveData<Integer> focusProgressTotal;

    private MutableLiveData<String> focusTotal;

    private MutableLiveData<UserInfo> userInfo;


    @Inject
    HomeFragmentViewModel(FocusRepository focusRepository){
        super();
        this.focusRepository = focusRepository;
        this.focusProgressTotal=focusRepository.getProgressTotal();
        this.focusTotal = focusRepository.getFocusTotal();
    }




    public FocusRepository getFocusRepository() {
        return focusRepository;
    }

    public MutableLiveData<String> getCurrentVersion() {
        return currentVersion;
    }

    public String getGithub() {
        return github;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public MutableLiveData<Integer> getFocusProgressTotal() {
        return focusProgressTotal;
    }

    public MutableLiveData<String> getFocusTotal() {
        return focusTotal;
    }

    public MutableLiveData<UserInfo> getUserInfo() {
        return userInfo;
    }

    @Override
    protected void initDataStatus() {
    }

}