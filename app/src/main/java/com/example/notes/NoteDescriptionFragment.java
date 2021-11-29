package com.example.notes;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NoteDescriptionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NoteDescriptionFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    static final String ARG_PARAM1 = "index";


    // TODO: Rename and change types of parameters
    private Note note;

    public NoteDescriptionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param note Parameter 1.
     * @return A new instance of fragment NoteDescriptionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NoteDescriptionFragment newInstance(Note note) {
        NoteDescriptionFragment fragment = new NoteDescriptionFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PARAM1, note);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            note = (Note) getArguments().getParcelable(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_note_description, container, false);
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (note == null) {
            return;
        }

        getChildFragmentManager().beginTransaction()
                .replace(R.id.note_description_child_container, NoteDescriptionChildFragment.newInstance(note))
                .addToBackStack("")
                .commit();

        Button buttonBack = view.findViewById(R.id.note_description_button_back);
        buttonBack.setOnClickListener(v -> {
            requireActivity().getSupportFragmentManager().popBackStack();
        });

        view.findViewById(R.id.note_description_button_remove).setOnClickListener(v -> {
            FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
            List<Fragment> fragments = fragmentManager.getFragments();
            for (Fragment fragment: fragments) {
                if (fragment instanceof NoteDescriptionFragment && fragment.isVisible()) {
                    fragmentManager.beginTransaction().remove(fragment).commit();
                }
            }
        });

        Log.d("Fragment NoteDescription", "Start");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("Fragment NoteDescription", "Finish");
    }
}