package com.mediading.stealthystriver.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * 每个ViewModel得有模板代码 TAG...,
 * 干脆弄个继承BaseViewModel, 通过this获取实例子类viewModel的class
 * 从而设置TAG
 */
public abstract class BaseViewModel extends ViewModel {
    /**
     * 在继承关系中，不管父类还是子类
     * 这些类里面的this都代表了最终new出来时的那个类型的实例对象
     * 所以在父类中你可以中this获取到子类的信息
     */
    public String TAG;

    private void setTAG(){
        Log.i("BaseViewModel","--> init tag"+this.getClass().getSimpleName());
        TAG = this.getClass().getSimpleName();
    }

    public MutableLiveData<DataStatus> getDataStatus() {
        return dataStatus;
    }

    /**
     * 让各个ViewModel记录
     * 向view层提供数据时候的，记录提供数据的状态（情况） 比如在本地找不到用户输入的uname和passwd，
     * 让view层观察status的变化
     * （为了方便在viewModel层完成一些逻辑判断（比如checkUser, 用户的输入是否和本地库的user匹配）
     */
    public MutableLiveData<DataStatus> dataStatus = new MutableLiveData<>();

    /**
     * 初始化数据初始状态
     */
    protected abstract void initDataStatus();
    BaseViewModel(){
        setTAG();
        initDataStatus();
    }

    protected void updateRegisterStatusMsg(String msg){
        MutableLiveData<DataStatus> registerStatus =  getDataStatus();
        registerStatus.getValue().setMsg(msg);
        registerStatus.postValue(getDataStatus().getValue());
    }

    public class DataStatus{

        /**
         * 状态信息
         */
        private String msg;

        public DataStatus(String msg, String other) {
            this.msg = msg;
            this.other = other;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getOther() {
            return other;
        }

        public void setOther(String other) {
            this.other = other;
        }

        /**
         * 应该还有别的状态信息,想不到,姑且用other代替
         */
        private String other;
    }
}


