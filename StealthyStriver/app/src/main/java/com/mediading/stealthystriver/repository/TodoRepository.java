package com.mediading.stealthystriver.repository;


import android.os.AsyncTask;
import android.util.Log;

import com.mediading.stealthystriver.StealthyStriverApplication;
import com.mediading.stealthystriver.db.dao.TodoDAO;
import com.mediading.stealthystriver.db.entity.Todo;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

public class TodoRepository {
    private final String TAG = this.getClass().getName();
    private TodoDAO todoDao;
    private LiveData<List<Todo>> allTodos;


    @Inject
    TodoRepository() {
        Log.i(TAG,"TodoRepo init ");
        todoDao = StealthyStriverApplication.getLocalDB().todoDAO();
        allTodos = getAllTodos();
    }

    public void insert(Todo todo){
        Log.i(TAG,"insert "+ todo.toString());
        new AsyncInsertTodo(this.todoDao).execute(todo);
    }

    public void update(Todo todo){
        new AsyncUpdateTodo(this.todoDao).execute(todo);
    }

    public void delete (Todo todo){
        new AsyncDeleteTodo(this.todoDao).execute(todo);
    }

    public void deleteAllTodos (){
        new AsyncDeleteAllTodo(this.todoDao).execute();
    }

    public LiveData<List<Todo>> getAllTodos(){
        return this.allTodos;
    }

    private static class AsyncInsertTodo extends AsyncTask<Todo,Void,Void>{

        private TodoDAO todoDao;

        AsyncInsertTodo(TodoDAO todoDao){
            this.todoDao = todoDao;
        }

        @Override
        protected Void doInBackground(Todo... todos) {
            todoDao.insert(todos[0]);
            return null;
        }
    }

    private static class AsyncUpdateTodo extends AsyncTask<Todo,Void,Void>{

        private TodoDAO todoDao;

        AsyncUpdateTodo(TodoDAO todoDao){
            this.todoDao = todoDao;
        }

        @Override
        protected Void doInBackground(Todo... todos) {
            todoDao.updateTodoItem(todos[0]);
            return null;
        }
    }

    private static class AsyncDeleteTodo extends AsyncTask<Todo,Void,Void>{

        private TodoDAO todoDao;

        AsyncDeleteTodo(TodoDAO todoDao){
            this.todoDao = todoDao;
        }

        @Override
        protected Void doInBackground(Todo... todos) {
            todoDao.deleteItem(todos[0]);
            return null;
        }
    }

    private static class AsyncDeleteAllTodo extends AsyncTask<Void,Void,Void>{

        private TodoDAO todoDao;

        AsyncDeleteAllTodo(TodoDAO todoDao){
            this.todoDao = todoDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            todoDao.deleteAllItem();
            return null;
        }
    }
}
