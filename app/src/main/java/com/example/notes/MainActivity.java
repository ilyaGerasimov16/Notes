package com.example.notes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            NotesFragment notesFragment = new NotesFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, notesFragment)
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.first_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_about:
                getSupportFragmentManager().beginTransaction().addToBackStack("")
                        .replace(R.id.fragment_container, new AboutFragment()).commit();
                return true;
            case R.id.action_exit:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);

    }
}