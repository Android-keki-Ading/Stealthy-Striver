package com.mediading.stealthystriver.viewmodel;

import com.mediading.stealthystriver.db.entity.Todo;
import com.mediading.stealthystriver.model.UserTodo;
import com.mediading.stealthystriver.repository.TodoRepository;

import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class TodoViewModel extends BaseViewModel{
    private MutableLiveData<UserTodo> userTodo;
    private TodoRepository todoRepository;
    private LiveData<List<Todo>> allTodos;


    @Inject
    TodoViewModel(TodoRepository todoRepository) {
        super();
        this.todoRepository = todoRepository;
    }

    public void insert(Todo todo){
        todoRepository.insert(todo);
    }

    public void update(Todo todo){
        todoRepository.update(todo);
    }

    public void delete(Todo todo){
        todoRepository.delete(todo);
    }

    public void deleteAllTodo(){
        todoRepository.deleteAllTodos();
    }

    public LiveData<List<Todo>> getAllTodos(){
        return  this.allTodos;
    }
    @Override
    protected void initDataStatus() {
        dataStatus.postValue(new DataStatus("开始选择啦!",""));
    }
}
