package com.qacg.travelapp.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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
    private View mainView;

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
        mainView = view;
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
    }

    @Override
    public void placesNotFound() {
        adapter.addList(null);
    }

    @Override
    public void connectionUnavailable() {
        Toast.makeText(getActivity(), getResources().getString(R.string.conection_unavailable),
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void showNoInternetConnectionMsg() {
        Toast.makeText(getActivity(), getResources().getString(R.string.not_connected_message),
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void hideSwipe() {
        if (swipeRefreshLayout != null && swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
    }
}
