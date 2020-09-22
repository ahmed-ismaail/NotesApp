package com.example.noteapp.ui.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.noteapp.model.Note;
import com.example.noteapp.model.NoteRepository;

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
