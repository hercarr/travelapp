package com.qacg.travelapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.qacg.travelapp.R;
import com.qacg.travelapp.fragments.PlacesFragment;
import com.qacg.travelapp.utils.Constants;
import com.qacg.travelapp.utils.SharedPreferenceUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        validateSession();
        /* the data is deleted in order to execute all flows when the app runs  */
        SharedPreferenceUtils.getInstance(this).clear();
    }

    private void validateSession() {
        String username = SharedPreferenceUtils.getInstance(this).getStringValue(Constants.SPKEYS.USERNAME, null);
        if (username == null) {
            startActivity(new Intent(this, WelcomeActivity.class));
            finish();
        } else {
            loadFragment(R.id.mainFragment,PlacesFragment.getInstance());
        }
    }

    private void loadFragment(int idReplace, Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(idReplace, fragment)
                .commit();
    }

}