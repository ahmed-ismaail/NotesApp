package com.example.noteapp;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class NoteRepository {
    private MutableLiveData<List<Note>> noteListMutableLiveData = new MutableLiveData<>();
    private NoteDatabase noteDatabase;

    private static NoteRepository noteRepository;
    public static NoteRepository getInstance() {
        if (noteRepository != null) {
            noteRepository = new NoteRepository();
        }
        return noteRepository;
    }

    public void setNoteDatabase(Application application){
        this.noteDatabase = NoteDatabase.getInstance(application);
    }

    public MutableLiveData<List<Note>> retrieveAllNotes() {
        return noteListMutableLiveData;
    }

    @SuppressLint("CheckResult")
    private void getAllNotes(){
        noteDatabase.noteDao()
                .retrieveAllNotes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Note>>() {
                    @Override
                    public void accept(List<Note> notes){
                         noteListMutableLiveData.setValue(notes);
                    }
                });
    }

    public void insertNote(Note note) {
        noteDatabase.noteDao()
                .insertNote(note)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    public void deleteNote(Note note){
        noteDatabase.noteDao()
                .deleteNote(note)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    public void updateNote(Note note){
        noteDatabase.noteDao()
                .deleteNote(note)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }
    public void deleteAllNotes(){
        noteDatabase.noteDao()
                .deleteAllNotes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }
}
