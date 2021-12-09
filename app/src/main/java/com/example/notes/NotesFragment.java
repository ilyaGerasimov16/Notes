package com.example.notes;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
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
import android.widget.Toast;

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
        LayoutInflater ltInflater = getLayoutInflater();


        Button buttonNew = view.findViewById(R.id.button_create_new_note);
        buttonNew.setOnClickListener(v -> {
            Note newNote = new Note();
            noteArrayList.add(newNote);

            View item = ltInflater.inflate(R.layout.item, layoutView, false);
            TextView tvNewNoteName = item.findViewById(R.id.item_text_view);

            tvNewNoteName.setText(newNote.getNoteName());
            layoutView.addView(item);

            tvNewNoteName.setOnClickListener(view1 -> NotesFragment.this.showPortNote(newNote));
            showNewNoteToast();
        });

    }

    private void showNewNoteToast() {
        Toast.makeText(requireActivity(), getString(R.string.toast_text_on_create_note), Toast.LENGTH_SHORT).show();
    }


    private void showPortNote (Note note) {
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.fragment_container, NoteDescriptionFragment.newInstance(note))
                .addToBackStack("")
                .commit();
    }
}