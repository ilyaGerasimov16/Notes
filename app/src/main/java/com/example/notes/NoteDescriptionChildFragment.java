package com.example.notes;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NoteDescriptionChildFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NoteDescriptionChildFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_INDEX_CHILD = "index";

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param note Parameter 1.
     * @return A new instance of fragment NoteDescriptionChildFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NoteDescriptionChildFragment newInstance(Note note) {
        NoteDescriptionChildFragment fragment = new NoteDescriptionChildFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_INDEX_CHILD, note);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_note_description_child, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle arguments = getArguments();
        if(arguments != null) {
            Note note = arguments.getParcelable(ARG_INDEX_CHILD);
            EditText editName = view.findViewById(R.id.Name);
            editName.setText(note.getNoteName());
        }

        view.findViewById(R.id.note_description_child_button_back_button)
                .setOnClickListener(v -> {
                    getParentFragmentManager().popBackStack();
                    requireActivity().getSupportFragmentManager().popBackStack();
                });
        Log.d("Fragment ChildNoteDescription", "Start");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("Fragment ChildNoteDescription", "Finish");
    }
}