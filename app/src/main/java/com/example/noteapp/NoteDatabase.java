package com.example.noteapp;

import android.app.Application;
import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Note.class}, version = 1)
public abstract class NoteDatabase extends RoomDatabase {

    private static NoteDatabase instance;

    public abstract NoteDao noteDao();

    //synchronized means that only one thread at a time can access this method
    public static synchronized NoteDatabase getInstance(Application application) {
        if (instance != null) {
            instance = Room.databaseBuilder(application,
                    NoteDatabase.class, "note_database")
                    .fallbackToDestructiveMigration()//this delete all tables and create them again when database version change
                    .build();
        }
        return instance;
    }
}
