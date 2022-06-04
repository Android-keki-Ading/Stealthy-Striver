package com.mediading.stealthystriver.ui.adapter;

import android.annotation.SuppressLint;
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

    private OnItemClickListener listener;

    LiveData<List<Todo>> todoList;

    public TodoAdapter(LiveData<List<Todo>> todoList) {
        this.todoList = todoList;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setTodoList(LiveData<List<Todo>> todoList_){
        todoList = todoList_;
        notifyDataSetChanged();
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
        if(todoList.getValue()!=null)
            return todoList.getValue().size();
        return 0;
    }


    class TodoViewHolder extends RecyclerView.ViewHolder {
        TodoItemBinding todoItemBinding;

        public TodoViewHolder(TodoItemBinding dataBinding) {
            super(dataBinding.getRoot());
            todoItemBinding = dataBinding;
            todoItemBinding.cvTodo.setOnClickListener(e->{
                int position = getBindingAdapterPosition();
                if(listener!=null && position!=RecyclerView.NO_POSITION){
                    listener.onItemClick(todoList.getValue().get(position));
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Todo todo);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }
}
