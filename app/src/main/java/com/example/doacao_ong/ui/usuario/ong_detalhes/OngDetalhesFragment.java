package com.example.doacao_ong.ui.usuario.ong_detalhes;

import androidx.core.app.ActivityCompat;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doacao_ong.R;
import com.example.doacao_ong.config.ConfiguracaoFirebase;
import com.example.doacao_ong.model.Doacao;
import com.example.doacao_ong.model.Ong;
import com.example.doacao_ong.model.Usuario;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.maps.DirectionsApi;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DirectionsLeg;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DirectionsRoute;
import com.google.maps.model.DirectionsStep;
import com.google.maps.model.EncodedPolyline;

import java.util.ArrayList;
import java.util.List;

public class OngDetalhesFragment extends Fragment {

    private MutableLiveData<Ong> ongLiveData;
    private MapView mapView;
    private GoogleMap googleMap;

    private Button searchButton;
    private EditText inputSearch;
    private TextView labelCausa;
    private TextView labelMissao;
    private TextView labelDescricao;


    public static OngDetalhesFragment newInstance() {
        return new OngDetalhesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.ong_detalhes_fragment, container, false);

        mapView = rootView.findViewById(R.id.ong_detalhes_map_view);
        mapView.onCreate(savedInstanceState);

        ongLiveData = new MutableLiveData<>();
        ongLiveData.setValue(new Ong());

        labelCausa = rootView.findViewById(R.id.ong_detalhes_causa_msg);
        labelMissao = rootView.findViewById(R.id.ong_detalhes_missao_msg);
        labelDescricao = rootView.findViewById(R.id.ong_detalhes_descricao_msg);

        inputSearch = rootView.findViewById(R.id.ong_detalhes_input_search);
        searchButton = rootView.findViewById(R.id.ong_detalhes_button_search);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!inputSearch.getText().toString().isEmpty()) {
                    getONG(inputSearch.getText().toString());
                } else {
                    Toast.makeText(getActivity(), "Informe o nome da ONG", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mapView.onResume();

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                googleMap = mMap;

                if (ActivityCompat.checkSelfPermission(getActivity(),
                        Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                        ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION)
                                != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION,
                    }, PackageManager.PERMISSION_GRANTED);
                }
                googleMap.setMyLocationEnabled(true);

                ongLiveData.observe(getViewLifecycleOwner(), new Observer<Ong>() {
                    @Override
                    public void onChanged(Ong ong) {
                        if (ong != null) {
                            labelCausa.setText(ong.getCausa());
                            labelMissao.setText(ong.getMissao());
                            labelDescricao.setText(ong.getDescricao());

                            LatLng ongCoords = new LatLng(ong.getLatitude(), ong.getLongitude());
//                            LatLng sidney = new LatLng(-34, 151);
                            googleMap.addMarker(new MarkerOptions().position(ongCoords).title(ong.getNome()));
//                        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

                            // For zooming automatically to the location of the marker
                            CameraPosition cameraPosition = new CameraPosition.Builder().target(ongCoords).zoom(6).build();
                            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                        }
                    }
                });
            }
        });

        return rootView;
    }

    private void getONG(String nomeONG) {
        DatabaseReference database = ConfiguracaoFirebase.getFirebaseDatabase();
        database.child("ongs").orderByChild("nome").equalTo(nomeONG)
                .get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Ong ong = snapshot.getValue(Ong.class);
                    if (ong.getNome().equals(nomeONG)) {
                        Toast.makeText(getActivity(), "ONG encontrada!", Toast.LENGTH_SHORT).show();
                        ongLiveData.setValue(ong);
                    }
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity(), "ONG não encontrada! Verifique o nome e tente novamente", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }
}