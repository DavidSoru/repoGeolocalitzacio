package com.example.geolocalitzacio;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

public class MapsFragment extends Fragment {

    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        @Override
        public void onMapReady(GoogleMap googleMap) {
            LatLng plCat = new LatLng(41.386351, 2.170033);
            googleMap.addMarker(new MarkerOptions().position(plCat).title("Marker in Plaça Catalunya"));
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(plCat, 15));

            LatLng tivoli = new LatLng(41.3878804, 2.1685202);
            googleMap.addMarker(new MarkerOptions().position(tivoli).title("Marker in Teatre Tívoli"));

            LatLng tr = new LatLng(41.38613367427904, 2.1690888282655116);
            googleMap.addMarker(new MarkerOptions().position(tr).title("Marker in CC El Triangle"));

            LatLng hotel = new LatLng(41.39069669564972, 2.172586630864967);
            googleMap.addMarker(new MarkerOptions().position(hotel).title("Marker in Hotel Negresco Princess"));

            FusedLocationProviderClient client = LocationServices.getFusedLocationProviderClient(getActivity());



            try {
                client.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        double MyLat = location.getLatitude();
                        double MyLong = location.getLongitude();
                        LatLng current = new LatLng(MyLat, MyLong);
                        googleMap.addMarker(new MarkerOptions().position(current).title("Ubicació actual"));
                    }
                });
            } catch (SecurityException e) {
                Toast.makeText(getActivity(), "GPS Unavailable", Toast.LENGTH_LONG).show();
            }



        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_maps, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }
}