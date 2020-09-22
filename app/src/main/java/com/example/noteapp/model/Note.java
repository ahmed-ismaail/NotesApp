package com.example.noteapp.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "note_table")
//Note is serializable because it implements Serializable
public class Note implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String title;

    private String description;

    private String timeStamp;

    public Note(String title, String description, String timeStamp) {
        this.title = title;
        this.description = description;
        this.timeStamp = timeStamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
}
