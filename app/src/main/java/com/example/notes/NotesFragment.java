package com.example.notes;

import static com.example.notes.NoteDescriptionFragment.ARG_PARAM1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


public class NotesFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initList(view);

    }


    private void initList(View view) {
        LinearLayout layoutView = (LinearLayout) view;
        String[] notes = getResources().getStringArray(R.array.notes);

        for (int i = 0; i< notes.length; i++) {
            String noteName = notes[i];
            TextView tvNoteName = new TextView(getContext());
            tvNoteName.setText(noteName);
            tvNoteName.setTextSize(30);
            layoutView.addView(tvNoteName);
            final int position = i;
            tvNoteName.setOnClickListener(v -> {
                Note currentNote = new Note(position, noteName);
                showPortNote(currentNote);
            });
        }
    }

    private void showPortNote(Note note) {
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.fragment_container, NoteDescriptionFragment.newInstance(note)).commit();
    }
}