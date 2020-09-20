package com.example.noteapp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public class CreateUpdateNoteViewModel extends AndroidViewModel {
    private NoteRepository repository;

    public CreateUpdateNoteViewModel(@NonNull Application application) {
        super(application);
        repository = new NoteRepository(application);
    }

    public void insertNote(Note note){
        repository.insertNote(note);
    }

    public void updateNote(Note note){
        repository.updateNote(note);
    }
}
