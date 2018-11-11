package com.qacg.travelapp.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qacg.travelapp.R;

/**
 * Fragmento que muestra la lista de lugares.
 */
public class PlacesFragment extends Fragment {

    private static PlacesFragment instance;

    public static PlacesFragment getInstance() {
        if (instance == null) {
            instance = new PlacesFragment();
        }
        return instance;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_places, container, false);
    }

}
