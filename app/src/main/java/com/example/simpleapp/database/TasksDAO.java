package com.example.simpleapp.database;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
@Dao
public interface TasksDAO {
    @Insert
    void insertTasks(Tasks tasks);
    @Query("SELECT * FROM tasks")
    List<Tasks> getListTasks();
    @Update
    void Update(Tasks tasks);
}