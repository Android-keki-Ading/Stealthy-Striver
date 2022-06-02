package com.mediading.stealthystriver.db;

import android.content.Context;

import com.mediading.stealthystriver.db.dao.TodoDAO;
import com.mediading.stealthystriver.db.dao.UserDAO;
import com.mediading.stealthystriver.db.entity.Todo;
import com.mediading.stealthystriver.db.entity.User;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/**
 * ERROR record
 * <p>
 * Schema export directory is not provided to the annotation processor so we cannot export the schema.
 * You can either provide `room.schemaLocation` annotation processor argument OR set exportSchema to false.
 */
@Database(entities = {
        User.class,
        Todo.class
}, version = 1, exportSchema = false)
public abstract class LocalDataBase extends RoomDatabase {
    private static final String DB_NAME = "StealthyStriverLocalDB";
    private static LocalDataBase dbInstance;

    public static LocalDataBase getDBInstance(Context context) {
        if (dbInstance == null) {
            synchronized (LocalDataBase.class) {
                if (dbInstance == null) {
                    dbInstance = Room
                            .databaseBuilder(
                                    context.getApplicationContext(),
                                    LocalDataBase.class,
                                    DB_NAME)
                            .build();
                }
            }
        }
        return dbInstance;
    }

    public abstract UserDAO userDAO();

    public abstract TodoDAO todoDAO();
}
