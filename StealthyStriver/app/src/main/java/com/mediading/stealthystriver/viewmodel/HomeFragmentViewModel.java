package com.mediading.stealthystriver.viewmodel;

import com.mediading.stealthystriver.model.UserInfo;
import com.mediading.stealthystriver.repository.UserRepository;

import javax.inject.Inject;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import dagger.hilt.android.AndroidEntryPoint;
import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class HomeFragmentViewModel extends BaseViewModel {
    private final UserRepository userRepository;
    private MutableLiveData<String> currentVersion;
    private final String github = "github.com/ldqsss";
    private final String contactEmail = "734200940@qq.com";

    private MutableLiveData<UserInfo> userInfo;




    @Inject
    HomeFragmentViewModel(UserRepository userRepository){
        super();
        this.userRepository = userRepository;
    }



    @Override
    protected void initDataStatus() {
    }

}