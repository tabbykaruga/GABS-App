package com.example.authentication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsFragement extends Fragment implements OnMapReadyCallback {


     private GoogleMap map;


    SupportMapFragment mapFragment;
    ArrayList<LatLng> arrayList=new ArrayList<LatLng>();
    LatLng Nairobi = new LatLng(1.2921, 36.8219);
    LatLng TakatakaSolutions = new LatLng(-1.271846, 36.796290);
    LatLng FlashServices = new LatLng(-1.284002, 36.820649);
    LatLng SmartCity = new LatLng(-1.283979, 36.822175);
    LatLng EcoUrbanWasteMgmLTD = new LatLng(-1.284766, 36.821397);
    LatLng BioBinsServices = new LatLng(-1.287730, 36.827534);
    LatLng JymtichCareServices=new LatLng(-1.2969554499123148, 36.80146835832448);
    LatLng zoataka =new LatLng(-1.318444317822455, 36.87332262720601);
    LatLng nesco =new LatLng(-1.2840499272723442, 36.82060372945227);
    LatLng garbagecom=new LatLng(-1.2987516545467173, 36.83804462727388);
    LatLng bins=new LatLng(-1.3152703035387445, 36.874122845002034);

    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_maps, container, false);

        mapFragment=(SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        arrayList.add(Nairobi);
        arrayList.add(TakatakaSolutions);
        arrayList.add(FlashServices);
        arrayList.add(SmartCity);
        arrayList.add(JymtichCareServices);
        arrayList.add(EcoUrbanWasteMgmLTD);
        arrayList.add(BioBinsServices);
        arrayList.add(zoataka);
        arrayList.add(nesco);
        arrayList.add(garbagecom);
        arrayList.add(bins);
        return view;

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        map=googleMap;

        for (int i=0;i<arrayList.size();i++){
            map.moveCamera(CameraUpdateFactory.newLatLng(arrayList.get(i)));

            map.addMarker(new MarkerOptions().position(arrayList.get(i)).title("Marker"));
            map.animateCamera(CameraUpdateFactory.zoomTo(16.0f));



        }

    }
}
