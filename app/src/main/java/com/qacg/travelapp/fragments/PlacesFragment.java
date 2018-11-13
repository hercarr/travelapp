package com.qacg.travelapp.fragments;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qacg.travelapp.R;
import com.qacg.travelapp.adapters.PlaceAdapter;
import com.qacg.travelapp.models.Place;
import com.qacg.travelapp.presents.PlacesPresenter;
import com.qacg.travelapp.views.IPlacesView;

import java.util.List;

/**
 * Fragmento que muestra la lista de lugares.
 */
public class PlacesFragment extends Fragment implements IPlacesView {

    private RecyclerView places;
    private PlaceAdapter adapter;
    private PlacesPresenter presenter;

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
        places.setHasFixedSize(true);
        adapter= new PlaceAdapter();
        places.setAdapter(adapter);
        presenter = new PlacesPresenter(this);
        presenter.loadPlaces();
    }

    @Override
    public void placesFound(List<Place> places) {
        adapter.addList(places);
    }

    @Override
    public void placesNotFound() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.LightDialogTheme);
        builder.setTitle(R.string.app_name);
        builder.setMessage(R.string.not_places_found_msg);
        builder.setPositiveButton(R.string.accept_bnt, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
