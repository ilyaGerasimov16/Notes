package com.example.notes;

import android.os.Parcel;
import android.os.Parcelable;


public final class Note implements Parcelable {

    private final String noteName;
    //private final String description;
    //private final String date;
    private final int index;

    public Note(int index, String noteName) {
        this.index = index;
        this.noteName = noteName;

    }

    protected Note(Parcel in) {
        noteName = in.readString();
        index = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(noteName);
        dest.writeInt(index);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };

    public String getNoteName() {
        return noteName;
    }

    public int getIndex() {
        return index;
    }
}
