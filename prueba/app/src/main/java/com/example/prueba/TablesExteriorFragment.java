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

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tables_exterior, container, false);
        txtInterior = (TextView) view.findViewById(R.id.txtInterior);
        setOnClickListenerZona();
        //setOnClickListenerCtegoria();
        // Inflate the layout for this fragment
        return view;
    }


    private void setOnClickListenerZona() {
        txtInterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment = new TablesInteriorFragment();
                requireActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.contenedorZonaExterior, fragment)
                        .addToBackStack(TablesExteriorFragment.class.getName())
                        .commit();
            }
        });
    }
}