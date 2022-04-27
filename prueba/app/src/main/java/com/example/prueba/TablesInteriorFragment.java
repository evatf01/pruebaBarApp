package com.example.prueba;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.prueba.adapters.ListTablesAdapter;
import com.example.prueba.models.Tables;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TablesInteriorFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class TablesInteriorFragment extends Fragment{

    private ListView listview_mesas;
    private ListTablesAdapter adapter;
    private Fragment fragmentInterior;
    private Fragment fragmentExterior;
    private static final String ZONA = "interior";

    public static TablesInteriorFragment newInstance(String param1, String param2) {

        return new TablesInteriorFragment();
    }

    public TablesInteriorFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tables_interior, container, false);
        fragmentExterior = new TablesExteriorFragment();
        fragmentInterior = new TablesInteriorFragment();
        listview_mesas = (ListView) view.findViewById(R.id.listTablesInterior);
        BottomNavigationView btnNavigation = (BottomNavigationView) view.findViewById(R.id.bottomNavigationView);
        //btnNavigation.setOnItemSelectedListener(this);
        adapter = new ListTablesAdapter( listaMesas(),getContext());
        listview_mesas.setAdapter(adapter);
        return view;
    }



    private ArrayList<Tables> listaMesas(){
        ArrayList<Tables> listaMesas = new ArrayList<>();
        listaMesas.add(new Tables(R.drawable.tables, "MESA 1"));
        listaMesas.add(new Tables(R.drawable.tables, "MESA 2"));
        listaMesas.add(new Tables(R.drawable.tables, "MESA 3"));
        listaMesas.add(new Tables(R.drawable.tables, "MESA 4"));
        listaMesas.add(new Tables(R.drawable.tables, "MESA 5"));
        listaMesas.add(new Tables(R.drawable.tables, "MESA 6"));

        return listaMesas;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        View txtExterior =(View)view.findViewById(R.id.exterior);
        txtExterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.tablesExteriorFragment);
            }
        });
    }
/*
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.exterior:



                break;
            case R.id.interior:

                requireActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.contenedorExterior,  fragmentInterior)
                        .remove(fragmentExterior)
                        .addToBackStack(TablesInteriorFragment.class.getName())
                        .commit();


                break;


        }
        return true;
    }*/
}