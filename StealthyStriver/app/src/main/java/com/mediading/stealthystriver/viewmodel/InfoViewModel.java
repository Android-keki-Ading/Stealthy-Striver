package com.mediading.stealthystriver.viewmodel;

import android.util.Log;

import com.mediading.stealthystriver.db.entity.User;
import com.mediading.stealthystriver.model.InfoResponse;
import com.mediading.stealthystriver.model.UserInfo;
import com.mediading.stealthystriver.repository.UserRepository;

import javax.inject.Inject;

import androidx.lifecycle.MutableLiveData;
import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class InfoViewModel extends BaseViewModel{
    private MutableLiveData<InfoResponse> infoResponse;
    private UserRepository userRepository;
    private final String github = "github.com/ldqsss";
    private final String contactEmail = "734200940@qq.com";

    @Inject
    InfoViewModel(UserRepository userRepository) {
        Log.i(TAG,"InfoViewModel() Init");
        this.userRepository = userRepository;
        this.infoResponse = userRepository.getInfoResponse();
    }

    public void setInfoResponse(MutableLiveData<InfoResponse> infoResponse) {
        this.infoResponse = infoResponse;
    }

    public String getContactEmail() {
        return contactEmail;
    }
    public String getGithub() {
        return github;
    }

    public MutableLiveData<InfoResponse> getInfoResponse() {
        return infoResponse;
    }

    public void updateUserInfo(){
        Log.i(TAG,"setUserInfo() --> "+ infoResponse.getValue().toString());
        if(// 对于初始化的infoResponse不做修改
            infoResponse.getValue().getNickName().equals("")
            &&infoResponse.getValue().getSign().equals("")
            &&infoResponse.getValue().getSex()==(byte)0)
            return;
        this.userRepository.updateUserInfo();
    }


    @Override
    protected void initDataStatus() {
    }

}
