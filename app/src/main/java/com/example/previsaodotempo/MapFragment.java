package com.example.previsaodotempo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap gMap;
    private TextView tvCidadeConsultada;

    // Coordenadas de Toledo, PR
    private LatLng cidadeCoordenadas = new LatLng(-24.715, -53.743);
    private String nomeCidade = "Toledo/PR";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_map, container, false);

        tvCidadeConsultada = view.findViewById(R.id.tv_cidade_consultada);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map_container);

        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
    }


    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        gMap = googleMap;


        updateMapLocation();
    }

    private void updateMapLocation() {
        if (gMap == null) return;

        gMap.clear();

        gMap.addMarker(new MarkerOptions().position(cidadeCoordenadas).title(nomeCidade));

        gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(cidadeCoordenadas, 12f));

        tvCidadeConsultada.setText("Cidade consultada: " + nomeCidade);
    }

    public void updateCity(String newCityName, LatLng newCoords) {
        this.nomeCidade = newCityName;
        this.cidadeCoordenadas = newCoords;

        if (gMap != null) {
            updateMapLocation();
        }
    }
}