package com.qacg.travelapp.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.qacg.travelapp.R;
import com.qacg.travelapp.models.User;
import com.qacg.travelapp.presents.LoginPresenter;
import com.qacg.travelapp.utils.Constants;
import com.qacg.travelapp.utils.SharedPreferenceUtils;
import com.qacg.travelapp.views.ILoginView;

public class LoginActivity extends AppCompatActivity implements ILoginView {

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
        SharedPreferenceUtils.getInstance(this).setValue(Constants.SPKEYS.USERNAME, user.getUserName());
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    public void userNotFound() {
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
    }

    @Override
    public void emptyFields() {
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
    }
}
