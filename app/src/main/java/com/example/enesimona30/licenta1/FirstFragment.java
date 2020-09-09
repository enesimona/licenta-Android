package com.example.enesimona30.licenta1;


import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.enesimona30.licenta1.utils.Restaurant;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


/**
 * A simple {@link Fragment} subclass.
 */
public class FirstFragment extends android.app.Fragment implements OnMapReadyCallback{

    GoogleMap mGoogleMap;
    MapView mMapview;
    View mView;
    public static Restaurant rest=new Restaurant();



    public FirstFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView=inflater.inflate(R.layout.fragment_first, container, false);
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mMapview=(MapView) mView.findViewById(R.id.fragment_map);
        if(mMapview!=null){
            mMapview.onCreate(null);
            mMapview.onResume();
            mMapview.getMapAsync(this);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(getActivity());
        mGoogleMap=googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.addMarker(new MarkerOptions().position(new LatLng(rest.getLatitudine(),
                rest.getLongitudine())).title(rest.getNumeRestaurant()).snippet(rest.getAdresaRestaurant()));
        CameraPosition pozitie=CameraPosition.builder().target(new LatLng(rest.getLatitudine(),
                rest.getLongitudine())).zoom(16).bearing(0).tilt(45).build();
        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(pozitie));
    }
}
