package com.example.notes;

import java.io.Serializable;

public final class Note implements Serializable {

    private final String noteName;
    //private final String description;
    //private final String date;
    private final int index;

    public Note(int index, String noteName) {
        this.index = index;
        this.noteName = noteName;

    }

    public String getNoteName() {
        return noteName;
    }

    public int getIndex() {
        return index;
    }
}
