package com.mediading.stealthystriver.viewmodel;


import android.util.Log;

import com.mediading.stealthystriver.db.entity.Todo;
import com.mediading.stealthystriver.repository.TodoRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class MainFragmentViewModel extends BaseViewModel {
    private final TodoRepository todoRepository;

    public LiveData<List<Todo>> getTodoList() {
//        if(todoList==null){
//            todoList = new MutableLiveData<>();
//        }
        return todoList;
    }

    private LiveData<List<Todo>> todoList;

    @Inject
    MainFragmentViewModel(TodoRepository todoRepository){
        this.todoRepository = todoRepository;
        this.todoList = this.todoRepository.getAllTodos();
        Log.i(TAG,"test");
//        setTodoList();
    }

    @Override
    protected void initDataStatus() {
        dataStatus.postValue(new DataStatus("主页",""));
    }

    public void setFakeTodoList(){
        List<Todo> todoList = new ArrayList<>();
        for (int i=0;i<2;i++){
            Todo todoItem = new Todo(i,"item"+i,"fake item",new Date().toString(),false);
            todoList.add(todoItem);
        }
        MutableLiveData<List<Todo>> todos = new MutableLiveData<>();
        todos.setValue(todoList);
        this.todoList=todos;
    }

    public void setTodoList(){

        this.todoList = todoRepository.getAllTodos();
        if (todoList.getValue()==null){
            this.todoList = todoRepository.getAllTodos();
        }
        if(todoList.getValue()==null){
           setFakeTodoList();
        }
    }
}