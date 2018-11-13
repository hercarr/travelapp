package com.qacg.travelapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.qacg.travelapp.utils.Constants;
import com.qacg.travelapp.utils.SharedPreferenceUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        validateSession();
        SharedPreferenceUtils.getInstance(this).clear();
    }

    private void validateSession() {
        String username = SharedPreferenceUtils.getInstance(this).getStringValue(Constants.SPKEYS.USERNAME, null);
        if (username == null) {
            startActivity(new Intent(this, WelcomeActivity.class));
            finish();
        }
    }

}
