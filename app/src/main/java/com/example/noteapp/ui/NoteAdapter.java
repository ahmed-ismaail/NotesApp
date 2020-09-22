package com.example.noteapp.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.noteapp.model.Note;
import com.example.noteapp.R;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {
    List<Note> notes = new ArrayList<>();
    OnItemClickListener onItemClickListener;

    public NoteAdapter(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_item, parent, false);
        return new NoteViewHolder(view, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        holder.titleTextView.setText(notes.get(position).getTitle());

        String day = notes.get(position).getTimeStamp().substring(0,3);
        String month = notes.get(position).getTimeStamp().substring(4,7);
        String year = notes.get(position).getTimeStamp().substring(7);
        holder.noteDateTextView.setText(String.format("%s\n%s\n%s", day, month, year));
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
        notifyDataSetChanged();
    }

    public Note getNoteAt(int position){
        return notes.get(position);
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView titleTextView, noteDateTextView;
        OnItemClickListener onItemClickListener;

        public NoteViewHolder(@NonNull View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.title_textView);
            noteDateTextView = itemView.findViewById(R.id.note_date_textView);
            this.onItemClickListener = onItemClickListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
             onItemClickListener.onClickListener(notes.get(getAdapterPosition()));
        }
    }

    public interface OnItemClickListener{
        void onClickListener(Note note);
    }
}
