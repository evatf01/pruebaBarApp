package com.example.prueba;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TablesExteriorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TablesExteriorFragment extends Fragment {
    private Fragment fragment;
    private FragmentManager fm =  getActivity().getSupportFragmentManager();
    private final FragmentTransaction ft = fm.beginTransaction();
    TextView txtInterior;


    public TablesExteriorFragment() {
        // Required empty public constructor
    }


    public static TablesExteriorFragment newInstance(String param1, String param2) {

        return new TablesExteriorFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setOnClickListenerZona();
        setOnClickListenerCtegoria();
    }

    private void setOnClickListenerCtegoria() {

    }

    private void setOnClickListenerZona() {
        txtInterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment = new CreateAccountFragment();
                fm.beginTransaction().add(R.id.contenedorZonaInterior, fragment);
                ft.commit();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tables_exterior, container, false);
    }
}