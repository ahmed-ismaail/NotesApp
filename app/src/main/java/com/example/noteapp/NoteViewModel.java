package com.example.noteapp;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class NoteViewModel extends ViewModel {
    private NoteRepository repository = NoteRepository.getInstance();
    private MutableLiveData<List<Note>> noteListMutableLiveData = new MutableLiveData<>();

    public void getAllNotes(){
        noteListMutableLiveData = repository.retrieveAllNotes();
    }

    public void insertNote(Note note){
        repository.insertNote(note);
    }

    public void updateNote(Note note){
        repository.updateNote(note);
    }

    public void deleteNote(Note note){
        repository.deleteNote(note);
    }

    public void deleteAllNotes(){
        repository.deleteAllNotes();
    }
}
