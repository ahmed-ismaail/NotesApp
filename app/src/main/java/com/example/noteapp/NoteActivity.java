package com.example.noteapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class NoteActivity extends AppCompatActivity {

   EditText titleEditText,notesDescriptionEditText;
   private CreateUpdateNoteViewModel createUpdateNoteViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        titleEditText = findViewById(R.id.title_editText);
        notesDescriptionEditText = findViewById(R.id.noteDescription_editText);

        createUpdateNoteViewModel = new ViewModelProvider(this)
                .get(CreateUpdateNoteViewModel.class);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.save_menu_item,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.saveNote){
            saveNote();
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void saveNote(){
        String title = titleEditText.getText().toString();
        String noteDescription = notesDescriptionEditText.getText().toString();

        if(title.isEmpty() || noteDescription.isEmpty()){
            Toast.makeText(this,"your notes is empty",Toast.LENGTH_SHORT).show();
            return;
        }

        createUpdateNoteViewModel.insertNote(new Note(title,noteDescription));
    }
}
