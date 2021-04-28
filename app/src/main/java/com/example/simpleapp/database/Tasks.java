package com.example.simpleapp.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "tasks")
public class Tasks {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String content;
    private String dueDate;
    private boolean status;

    public Tasks(String content, String dueDate, boolean status) {
        this.content = content;
        this.dueDate = dueDate;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
