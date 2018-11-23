package com.qacg.travelapp.fragments;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
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
    private SwipeRefreshLayout swipeRefreshLayout;

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
        swipeRefreshLayout = view.findViewById(R.id.swiperefresh);
        swipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        presenter.loadPlaces();
                    }
                }
        );
        places.setLayoutManager(new LinearLayoutManager(getContext()));
        places.setHasFixedSize(true);
        adapter= new PlaceAdapter(PlacesFragment.this.getContext());
        places.setAdapter(adapter);
        presenter = new PlacesPresenter(this);

        swipeRefreshLayout.setRefreshing(true);
        presenter.loadPlaces();
    }

    @Override
    public void placesFound(List<Place> places) {
        adapter.addList(places);
        if (swipeRefreshLayout != null && swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
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

    @Override
    public void connectionUnavailable() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity().getApplicationContext(), R.style.LightDialogTheme);
        builder.setTitle(R.string.app_name);
        builder.setMessage(R.string.conection_unavailable);
        builder.setPositiveButton(R.string.accept_bnt, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
