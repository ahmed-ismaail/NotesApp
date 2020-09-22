package com.example.noteapp.ui.viewmodels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.noteapp.model.Note;
import com.example.noteapp.model.NoteRepository;

import java.util.List;

public class NotesViewModel extends AndroidViewModel {
    public NoteRepository repository;

    public NotesViewModel(Application application) {
        super(application);
        repository = new NoteRepository(application);
    }

    public LiveData<List<Note>> getAllNotes(){
        return repository.getAllNotes();
    }

    public void deleteNote(Note note){
        repository.deleteNote(note);
    }

    public void deleteAllNotes(){
        repository.deleteAllNotes();
    }
}
