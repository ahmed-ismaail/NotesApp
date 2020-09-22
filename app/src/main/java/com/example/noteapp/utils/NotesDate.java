package com.example.noteapp.utils;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NotesDate {
    public static String getCurrentTimeStamp() {
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM yyyy");

        return dateFormat.format(new Date());//return date as String
    }
}
