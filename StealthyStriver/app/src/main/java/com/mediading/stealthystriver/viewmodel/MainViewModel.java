package com.mediading.stealthystriver.viewmodel;

public class MainViewModel extends BaseViewModel{

    @Override
    protected void initDataStatus() {
        dataStatus.postValue(new DataStatus("悄悄地学习,玩的不要",""));
    }
}
