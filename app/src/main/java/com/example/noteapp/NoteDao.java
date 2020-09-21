package com.example.noteapp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.Completable;

@Dao
public interface NoteDao {

    @Insert
    Completable insertNote(Note note);

    @Delete
    Completable deleteNote(Note note);

    @Update
    Completable updateNote(Note note);

    @Query("Delete From note_table")
    Completable deleteAllNotes();

    @Query("Select * From note_table")
    LiveData<List<Note>> retrieveAllNotes();
}
