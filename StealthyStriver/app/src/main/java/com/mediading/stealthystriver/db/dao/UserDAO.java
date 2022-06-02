package com.mediading.stealthystriver.db.dao;

import com.mediading.stealthystriver.db.entity.User;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface UserDAO {
    @Insert
    void insertUser(User user);

    @Delete
    void delUser(User user);

    @Update
    void updateUser(User user);

//    @Query("SELECT * FROM user")
//    MutableLiveData<List<User>> getLocalUsers();
    /**
     * 不能使用 MutableLiveData<User>
     * @param id
     * @return
     */
    @Query("SELECT * FROM user WHERE id = :id")
    LiveData<User> getUserByID(int id);

    @Query("DELETE FROM user")
    void delAll();

    @Query("SELECT * FROM user WHERE name = :name")
    LiveData<User> getUserByName(String name);



}
