package com.qacg.travelapp;

import android.content.Intent;
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
            // TODO - mostrar una alerta de error
            Log.d(TAG, "Por favor, proporcione sus credenciales de acceso para iniciar sesión.");
            return;
        }

        if (username.equals("android") && password.equals("apps")) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        } else {
            // TODO - mostrar una alerta de error
            Log.d(TAG, "Las credenciales de accesos proporcionadas son incorrectas. Intente nuevamente.");
            return;
        }
    }

}
