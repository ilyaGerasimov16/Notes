package com.example.notes;

import java.io.Serializable;

public final class Note implements Serializable {

    private final String noteName;
    private final String description;
    private final String date;

    public Note(String noteName, String description, String date) {
        this.noteName = noteName;
        this.description = description;
        this.date = date;
    }


    public String getNoteName() {
        return noteName;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }
}
