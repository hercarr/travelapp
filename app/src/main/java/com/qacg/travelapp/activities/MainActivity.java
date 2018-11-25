package com.qacg.travelapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.item_close_session) {
            SharedPreferenceUtils.getInstance(this).clear();
            startActivity(new Intent(MainActivity.this, WelcomeActivity.class));
            finish();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
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