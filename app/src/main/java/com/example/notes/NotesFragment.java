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

    private static final String CURRENT_Note = "CurrentNote";
    private Note currentNote = null;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        if (savedInstanceState != null) {
            currentNote = (Note) savedInstanceState.getParcelable(CURRENT_Note);
        }
        initList(view);

        if (Utils.isLandscape(getResources())) {
            showLandNote(currentNote);
        }

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
                currentNote = new Note(position, noteName);
                showNote(currentNote);
            });
        }
    }

    private void showNote(Note note) {
        if (Utils.isLandscape(getResources())) {
            showLandNote(note);
        } else {
            showPortNote(note);
        }
    }

    private void showLandNote(Note note) {
        NoteDescriptionFragment noteDescriptionFragment =
                NoteDescriptionFragment.newInstance(note);
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.noteDescriptionFragment_container, noteDescriptionFragment);
        transaction.commit();
    }

    private void showPortNote(Note note) {

        Activity activity = requireActivity();
        Intent intent = new Intent(activity, NoteDescriptionActivity.class);
        intent.putExtra(ARG_PARAM1, note);
        activity.startActivity(intent);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelable(CURRENT_Note, currentNote);
        super.onSaveInstanceState(outState);
    }
}