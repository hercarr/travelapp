package com.qacg.travelapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.qacg.travelapp.fragments.PlacesFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (false) {
            startActivity(new Intent(this, WelcomeActivity.class));
            finish();
        } else {
            loadFragment(R.id.mainFragment,PlacesFragment.getInstance());
        }
    }

    private void loadFragment(int idReplace,Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(idReplace, fragment)
                .commit();
    }
}
