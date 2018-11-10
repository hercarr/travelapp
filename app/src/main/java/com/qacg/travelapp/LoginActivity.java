package com.qacg.travelapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.qacg.travelapp.models.User;
import com.qacg.travelapp.presents.LoginPresenter;
import com.qacg.travelapp.views.ILoginView;

public class LoginActivity extends AppCompatActivity implements ILoginView {

    private static final String TAG = LoginActivity.class.getName();

    private LoginPresenter presenter;

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
        presenter = new LoginPresenter(this);
        txtUsername = findViewById(R.id.txtUsername);
        txtPassword = findViewById(R.id.txtPassword);

        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.validateUser(txtUsername.getText().toString(), txtPassword.getText().toString());
            }
        });
    }

    @Override
    public void userFound(User user) {
        Log.d(TAG, "userFound: Welcome"+user.getUserName());
    }

    @Override
    public void userNotFound() {
        Log.d(TAG, "userNotFound");
    }

    @Override
    public void emptyFields() {
        Log.d(TAG, "emptyFields: ");
    }
}
