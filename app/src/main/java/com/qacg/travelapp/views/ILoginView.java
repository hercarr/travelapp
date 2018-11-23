package com.qacg.travelapp.views;

import com.qacg.travelapp.models.User;

/**
 * Vista que define el comportamiento de la vista.
 */
public interface ILoginView {
    void userFound(User user);

    void userNotFound();

    void emptyFields();

    void connectionUnavailable();
}
