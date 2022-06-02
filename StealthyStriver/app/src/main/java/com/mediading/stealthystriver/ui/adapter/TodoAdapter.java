package com.mediading.stealthystriver.ui.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.mediading.stealthystriver.R;
import com.mediading.stealthystriver.databinding.TodoItemBinding;
import com.mediading.stealthystriver.db.entity.Todo;
import com.mediading.stealthystriver.model.UserTodo;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TodoViewHolder> {
    LiveData<List<Todo>> todoList;

    public TodoAdapter(LiveData<List<Todo>> todoList) {
        this.todoList = todoList;
    }

    @NonNull
    @Override
    public TodoAdapter.TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TodoItemBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.todo_item,
                parent,
                false);
        return new TodoViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoAdapter.TodoViewHolder holder, int position) {
        Todo todoItem = todoList.getValue().get(position);
        holder.todoItemBinding.setTodoItem(todoItem);
    }

    @Override
    public int getItemCount() {
        return todoList.getValue().size();
    }


    class TodoViewHolder extends RecyclerView.ViewHolder {
        TodoItemBinding todoItemBinding;

        public TodoViewHolder(TodoItemBinding dataBinding) {
            super(dataBinding.getRoot());
            todoItemBinding = dataBinding;
        }
    }
}
