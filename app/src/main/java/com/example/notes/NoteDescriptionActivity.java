package com.example.notes;

import static com.example.notes.NoteDescriptionFragment.ARG_PARAM1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class NoteDescriptionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_description2);

        if (Utils.isLandscape(getResources())) {
            finish();
            return;
        }

        if (savedInstanceState == null){
            Note note = (Note) getIntent().getExtras().getSerializable(ARG_PARAM1);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.note_description_fragment_container, NoteDescriptionFragment.newInstance(note))
                    .commit();
        }
    }
}