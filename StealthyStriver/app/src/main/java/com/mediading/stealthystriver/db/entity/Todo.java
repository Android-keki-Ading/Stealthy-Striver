package com.mediading.stealthystriver.db.entity;

import com.mediading.stealthystriver.db.Converters;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.Date;

/**
 * Room allows you to return any Java-based object
 * from your queries as long as the set of result
 * columns can be mapped into the returned object
 */
@TypeConverters(Converters.class)
@Entity(tableName = "todo")
public class Todo {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "todo_id")
    private long todo_item_id;

    @ColumnInfo(name ="todo_title")
    private String todoTitle;

    @ColumnInfo(name = "todo_completed")
    private boolean completed;


    public long getTodo_item_id() {
        return todo_item_id;
    }


    public void setTodo_item_id(long todo_item_id) {
        this.todo_item_id = todo_item_id;
    }


    @ColumnInfo(name ="todo_description")
    private String todoDescription;

    //Cannot figure out how to save this field into database. You can consider adding a type converter for it
    @ColumnInfo(name ="todo_date")
    private Date todoDate;

    public Todo(String todoTitle, boolean completed, String todoDescription, Date todoDate) {
        this.todoTitle = todoTitle;
        this.completed = completed;
        this.todoDescription = todoDescription;
        this.todoDate = todoDate;
    }

    @Ignore
    public Todo(long todo_item_id, String todoTitle, String todoDescription, Date todoDate, boolean completed) {
        this.todo_item_id = todo_item_id;
        this.todoTitle = todoTitle;
        this.todoDescription = todoDescription;
        this.todoDate = todoDate;
        this.completed = completed;
    }

    public String getTodoTitle() {
        return todoTitle;
    }


    public void setTodoTitle(String todoTitle) {
        this.todoTitle = todoTitle;

    }

    public String getTodoDescription() {
        return todoDescription;
    }

    public void setTodoDescription(String todoDescription) {
        this.todoDescription = todoDescription;

    }

    public Date getTodoDate() {
        return todoDate;
    }

    public void setTodoDate(Date todoDate) {
        this.todoDate = todoDate;

    }

    public boolean isCompleted() {
        return completed;
    }

    public Todo setCompleted(boolean completed) {
        this.completed = completed;
        return this;
    }

    public String data2String(){
        return getTodoDate().toString();
    }
}