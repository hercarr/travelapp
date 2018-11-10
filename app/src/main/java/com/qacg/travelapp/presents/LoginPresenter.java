package com.qacg.travelapp.presents;

import com.qacg.travelapp.models.User;
import com.qacg.travelapp.views.ILoginView;

public class LoginPresenter {

    private ILoginView view;

    public LoginPresenter(ILoginView view) {
        this.view = view;
    }

    public void validateUser(String username, String password) {
        if (username.length() == 0 || password.length() == 0) {
            view.emptyFields();
        } else if (username.equals("android") && password.equals("apps")) {
            User user = new User();
            user.setUserName(username);
            user.setPassword(password);
            view.userFound(user);
        } else {
            view.userNotFound();
        }
    }
}
