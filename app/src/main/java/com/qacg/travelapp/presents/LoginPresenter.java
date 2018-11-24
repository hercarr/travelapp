package com.qacg.travelapp.presents;

import com.qacg.travelapp.api.ResourceGenerator;
import com.qacg.travelapp.models.User;
import com.qacg.travelapp.views.ILoginView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter {

    private ILoginView view;

    public LoginPresenter(ILoginView view) {
        this.view = view;
    }

    public void validateUser(final String username, String password) {
        view.showLoader();
        if (username.length() == 0 || password.length() == 0) {
            view.hideLoader();
            view.emptyFields();
        } else {
            Call<User> call = ResourceGenerator.getTravelResource().authenticateUser(new User(username, password));
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    view.hideLoader();
                    if (response.isSuccessful()) {
                        view.userFound(response.body());
                    } else {
                        controlErrors(response.code());
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    view.hideLoader();
                    if (!call.isCanceled()) {
                        view.connectionUnavailable();
                    }
                }
            });
        }
    }

    private void controlErrors(int code) {
        if (code == 401) {
            view.userNotFound();
        } else {
            view.connectionUnavailable();
        }
    }
}
