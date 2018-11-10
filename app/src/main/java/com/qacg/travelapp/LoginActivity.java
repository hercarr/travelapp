package com.qacg.travelapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = LoginActivity.class.getName();

    private TextView txtUsername;
    private TextView txtPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    private void init() {
        txtUsername = findViewById(R.id.txtUsername);
        txtPassword = findViewById(R.id.txtPassword);

        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateUser();
            }
        });
    }

    private void validateUser() {
        String username = txtUsername.getText().toString();
        String password = txtPassword.getText().toString();

        if (username.length() == 0 || password.length() == 0) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.LightDialogTheme);
            builder.setTitle(R.string.app_name);
            builder.setMessage(R.string.empty_credentials_msg);
            builder.setPositiveButton(R.string.accept_bnt, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.dismiss();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
            return;
        }

        if (username.equals("android") && password.equals("apps")) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        } else {
            final AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.LightDialogTheme);
            builder.setTitle(R.string.app_name);
            builder.setMessage(R.string.not_user_found_msg);
            builder.setPositiveButton(R.string.accept_bnt, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.dismiss();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
            return;
        }
    }

}
