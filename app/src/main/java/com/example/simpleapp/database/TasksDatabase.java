package com.example.simpleapp.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Tasks.class}, version = 1)
public abstract class TasksDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "tasks.db";
    private static TasksDatabase instance2;

    public static synchronized TasksDatabase getInstance(Context context) {
        if (instance2 == null) {
            instance2 = Room.databaseBuilder(context.getApplicationContext(), TasksDatabase.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        return instance2;
    }
    public abstract TasksDAO tasksDAO();
}
