package com.example.notes;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;


public class NotesFragment extends Fragment {



    ArrayList<Note> noteArrayList = new ArrayList<>();

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
        Log.d("Fragment Notes", "Start");
    }


    private void initList(View view) {
        LinearLayout layoutView = (LinearLayout) view;

        noteArrayList.add(new Note());
        noteArrayList.add(new Note());
        noteArrayList.add(new Note());

        for (int i = 0; i < noteArrayList.size(); i++) {
            String noteName = noteArrayList.get(i).getNoteName();
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

        Button buttonNew = view.findViewById(R.id.button_create_new_note);
        buttonNew.setOnClickListener(v -> {
            noteArrayList.add(new Note());
            System.out.println(noteArrayList.size());
        });

    }


    private void showPortNote (Note note) {
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.fragment_container, NoteDescriptionFragment.newInstance(note))
                .addToBackStack("")
                .commit();
    }
}