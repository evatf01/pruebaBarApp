package com.example.prueba;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.prueba.adapters.ListTablesAdapter;
import com.example.prueba.models.Tables;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TablesExteriorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TablesExteriorFragment extends Fragment  {
    private Fragment fragment;
    private ListView lista_mesas;
    ListTablesAdapter adapter;
    TextView txtInterior;
    private static final String ZONA = "EXTERIOR";


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
        TextView txtExterior = (TextView) view.findViewById(R.id.txtExterior);
        lista_mesas = (ListView) view.findViewById(R.id.listTablesExterior);

        adapter = new ListTablesAdapter( listaMesas(),getContext());
        lista_mesas.setAdapter(adapter);

        //setOnClickListenerCtegoria();
        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        View txtInterior = (View) view.findViewById(R.id.interior);
        txtInterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.tablesInteriorFragment);
            }
        });
    }

    private ArrayList<Tables> listaMesas(){
        ArrayList<Tables> listaMesas = new ArrayList<>();
        listaMesas.add(new Tables(R.drawable.tables, "MESA 1"));
        listaMesas.add(new Tables(R.drawable.tables, "MESA 2"));
        listaMesas.add(new Tables(R.drawable.tables, "MESA 3"));
        listaMesas.add(new Tables(R.drawable.tables, "MESA 4"));
        listaMesas.add(new Tables(R.drawable.tables, "MESA 5"));
        listaMesas.add(new Tables(R.drawable.tables, "MESA 6"));
        listaMesas.add(new Tables(R.drawable.tables, "MESA 7"));
        listaMesas.add(new Tables(R.drawable.tables, "MESA 8"));

        return listaMesas;

    }


}