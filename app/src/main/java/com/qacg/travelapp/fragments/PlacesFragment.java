package com.qacg.travelapp.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qacg.travelapp.R;

/**
 * Fragmento que muestra la lista de lugares.
 */
public class PlacesFragment extends Fragment {

    private RecyclerView places;

    public static PlacesFragment getInstance() {
        return new PlacesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_places, container, false);
        init(view);
        return view;
    }

    private void init(View view){
        places= view.findViewById(R.id.recyclerViewPlaces);
        places.setLayoutManager(new LinearLayoutManager(getContext()));
    }

}
