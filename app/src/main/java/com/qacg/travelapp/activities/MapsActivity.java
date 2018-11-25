package com.qacg.travelapp.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.qacg.travelapp.R;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private static final String KEY_LATITUDE = "latitude";
    private static final String KEY_LONGITUDE = "longitude";
    private static final String KEY_NAME_PLACE = "name_places";

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    public static Intent getActivityIntent(Context context, double latitude, double longitude, String namePlaces) {
        return new Intent(context, MapsActivity.class)
                .putExtra(KEY_LATITUDE, latitude)
                .putExtra(KEY_LONGITUDE, longitude)
                .putExtra(KEY_NAME_PLACE, namePlaces);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng place = new LatLng(getIntent().getDoubleExtra(KEY_LATITUDE, -34),
                getIntent().getDoubleExtra(KEY_LONGITUDE, 151));
        mMap.addMarker(new MarkerOptions().position(place).title(getIntent().getStringExtra(KEY_NAME_PLACE)));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(place, 10));
    }
}
