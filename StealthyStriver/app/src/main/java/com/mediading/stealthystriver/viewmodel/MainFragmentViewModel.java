package com.mediading.stealthystriver.viewmodel;


import android.util.Log;

import com.mediading.stealthystriver.db.entity.Todo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.MutableLiveData;
import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class MainFragmentViewModel extends BaseViewModel {
    public MutableLiveData<List<Todo>> getTodoList() {
        if(todoList==null){
            todoList = new MutableLiveData<>();
        }
        return todoList;
    }

    private MutableLiveData<List<Todo>> todoList;

    @Inject
    MainFragmentViewModel(){
        Log.i(TAG,"test");
        setFakeTodoList();
    }

    @Override
    protected void initDataStatus() {
        dataStatus.postValue(new DataStatus("主页",""));
    }

    public void setFakeTodoList(){
        List<Todo> todoList = new ArrayList<>();
        for (int i=0;i<30;i++){
            Todo todoItem = new Todo(i,"item"+i,"fake item",new Date(),false);
            todoList.add(todoItem);
        }
        this.todoList = new MutableLiveData<>();

        // 这里使用postValue会造成空指针异常
        this.todoList.setValue(todoList);
    }
}