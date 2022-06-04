package com.mediading.stealthystriver.viewmodel;

import android.util.Log;

import com.mediading.stealthystriver.db.entity.User;
import com.mediading.stealthystriver.model.UserInfo;
import com.mediading.stealthystriver.repository.UserRepository;

import javax.inject.Inject;

import androidx.lifecycle.MutableLiveData;
import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class InfoViewModel extends BaseViewModel{
    private MutableLiveData<UserInfo> userInfo = new MutableLiveData<>();
    private UserRepository userRepository;

    public MutableLiveData<UserInfo> getUserInfo() {
        if(userInfo.getValue()==null){
            Log.i(TAG,"getUserInfo( 获得 userInfo )");
            userInfo.setValue(userRepository.getUserInfo());
        }
        Log.i(TAG,"getUserInfo()");
        return userInfo;
    }
    @Inject
    InfoViewModel(UserRepository userRepository) {
        Log.i(TAG,"InfoViewModel() Init");
        this.userRepository = userRepository;
    }

    public void setUserInfo(UserInfo info){
        Log.i(TAG,"setUserInfo() --> "+ info.toString());
        userInfo.setValue(info);
        // todo: 保存个人信息到本地、后台
    }


    @Override
    protected void initDataStatus() {
    }

}
