package com.example.noteapp.model;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class NoteRepository {
    public LiveData<List<Note>> noteList;
    private NoteDao noteDao;

    public NoteRepository(Application application) {
        NoteDatabase noteDatabase = NoteDatabase.getInstance(application);
        noteDao = noteDatabase.noteDao();
        noteList = noteDao.retrieveAllNotes();
    }

    public LiveData<List<Note>> getAllNotes() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                noteList = noteDao.retrieveAllNotes();
            }
        }).start();
        return noteList;
    }

    public void insertNote(Note note) {
        noteDao.insertNote(note)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    public void deleteNote(Note note) {
        noteDao.deleteNote(note)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    public void updateNote(Note note) {
        noteDao.updateNote(note)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    public void deleteAllNotes() {
        noteDao.deleteAllNotes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }
}
