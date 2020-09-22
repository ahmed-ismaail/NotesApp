package com.example.noteapp.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import androidx.lifecycle.ViewModelProvider;

import com.example.noteapp.model.Note;
import com.example.noteapp.utils.NotesDate;
import com.example.noteapp.R;
import com.example.noteapp.ui.viewmodels.CreateUpdateNoteViewModel;

public class NoteActivity extends AppCompatActivity {

    EditText titleEditText, notesDescriptionEditText;
    private CreateUpdateNoteViewModel createUpdateNoteViewModel;
    private Note note;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        setTitle("Notes page");

        note = (Note) getIntent().getSerializableExtra("noteObject");

        titleEditText = findViewById(R.id.title_editText);
        notesDescriptionEditText = findViewById(R.id.noteDescription_editText);

        if (note != null) {
            titleEditText.setText(note.getTitle());
            notesDescriptionEditText.setText(note.getDescription());
        }

        createUpdateNoteViewModel = new ViewModelProvider(this)
                .get(CreateUpdateNoteViewModel.class);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item_save, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.saveNote) {
            saveNote();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void saveNote() {
        String title = titleEditText.getText().toString();
        String noteDescription = notesDescriptionEditText.getText().toString();
        String timestamp = NotesDate.getCurrentTimeStamp();

        if (title.isEmpty() || noteDescription.isEmpty()) {
            emptyFieldsAlertDialog().show();
        } else if (note != null) {//update
            setNote();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else {//add
            createUpdateNoteViewModel.insertNote(new Note(title, noteDescription, timestamp));
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

    public void setNote() {
        String title = titleEditText.getText().toString();
        String noteDescription = notesDescriptionEditText.getText().toString();
        note.setTitle(title);
        note.setDescription(noteDescription);
        createUpdateNoteViewModel.updateNote(note);
    }

    public Dialog emptyFieldsAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("you can't leave title or notes description empty!")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
        return builder.create();
    }

    public Dialog unsavedChangesAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Do you want to save changes?")
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        saveNote();
                    }
                })
                .setNegativeButton("Discard", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        NavUtils.navigateUpFromSameTask(NoteActivity.this);
                    }
                })
                .setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
        return builder.create();
    }

    @Override
    public void onBackPressed() {
        String title = titleEditText.getText().toString();
        String noteDescription = notesDescriptionEditText.getText().toString();
        if (title.isEmpty() || noteDescription.isEmpty()) {
            emptyFieldsAlertDialog().show();
        } else {
            unsavedChangesAlertDialog().show();
        }
    }
}
