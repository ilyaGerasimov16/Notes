package com.example.notes;

import static com.example.notes.NoteDescriptionFragment.ARG_PARAM1;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
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
    private int currentPosition = 0;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_notes, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        if (savedInstanceState != null) {
            currentPosition = savedInstanceState.getInt(CURRENT_Note,0);
        }
        initList(view);

        if (isLandscape()) {
            showLandNote(currentPosition);
        }

    }

    private boolean isLandscape() {
        return getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_LANDSCAPE;
    }

    private void initList(View view) {
        LinearLayout layoutView = (LinearLayout) view;
        String[] notes = getResources().getStringArray(R.array.notes);

        for (int i = 0; i< notes.length; i++) {
            String note = notes[i];
            TextView tvNoteName = new TextView(getContext());
            tvNoteName.setText(note);
            tvNoteName.setTextSize(30);
            layoutView.addView(tvNoteName);
            final int position = i;
            tvNoteName.setOnClickListener(v -> {
                currentPosition = position;
                showNote(position);
            });
        }
    }

    private void showNote(int position) {
        if (isLandscape()) {
            showLandNote(position);
        } else {
            showPortNote(position);
        }
    }

    private void showLandNote(int position) {
        NoteDescriptionFragment noteDescriptionFragment =
                NoteDescriptionFragment.newInstance(position);
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.noteDescriptionFragment_container, noteDescriptionFragment);
        transaction.commit();
    }

    private void showPortNote(int position) {

        Activity activity = requireActivity();
        Intent intent = new Intent(activity, NoteDescriptionActivity.class);
        intent.putExtra(ARG_PARAM1, position);
        activity.startActivity(intent);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt(CURRENT_Note, currentPosition);
        super.onSaveInstanceState(outState);
    }
}