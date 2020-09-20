package com.example.noteapp;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class NotesViewModel extends AndroidViewModel {
    public NoteRepository repository;
    public MutableLiveData<List<Note>> noteListMutableLiveData = new MutableLiveData<>();

    public NotesViewModel(Application application) {
        super(application);
        repository = new NoteRepository(application);
    }

    public void getAllNotes(){
        noteListMutableLiveData = repository.retrieveAllNotes();
    }

    public void deleteNote(Note note){
        repository.deleteNote(note);
    }

    public void deleteAllNotes(){
        repository.deleteAllNotes();
    }
}
