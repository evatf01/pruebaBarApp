package com.example.prueba;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
    ArrayList<Tables> listaMesas = new ArrayList<>();
    private RecyclerView recyclerView;
    private ListTablesAdapter adapter;
    private ListTablesAdapter.RecyclerViewClickListenerTables listener;
    private static final String ZONA = "EXTERIOR";
    private View txtInterior;


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

        recyclerView = (RecyclerView) view.findViewById(R.id.listTablesExterior);
        listaMesas = listaMesas();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        adapter = new ListTablesAdapter( listaMesas,getContext(), listener);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        txtInterior = (View) view.findViewById(R.id.interior);
        setOnClickListenerComanda();
        setOnClickListenerTablesInterior();

    }

    private void setOnClickListenerTablesInterior() {
        txtInterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.tablesInteriorFragment);
            }
        });
    }

    private void setOnClickListenerComanda() {
        listener = new ListTablesAdapter.RecyclerViewClickListenerTables() {
            @Override
            public void onClick(View view, int position) {

                Tables table = listaMesas.get(position);
                Log.d("mesa click", table.toString());
                Navigation.findNavController(view).navigate(R.id.comandaFragment);
            }
        };
    }



    private ArrayList<Tables> listaMesas(){
        ArrayList<Tables> listaMesas = new ArrayList<>();
        listaMesas.add(new Tables(R.drawable.mesas, "MESA 1"));
        listaMesas.add(new Tables(R.drawable.mesas, "MESA 2"));
        listaMesas.add(new Tables(R.drawable.mesas, "MESA 3"));
        listaMesas.add(new Tables(R.drawable.mesas, "MESA 4"));
        listaMesas.add(new Tables(R.drawable.mesas, "MESA 5"));
        listaMesas.add(new Tables(R.drawable.mesas, "MESA 6"));
        listaMesas.add(new Tables(R.drawable.mesas, "MESA 7"));
        listaMesas.add(new Tables(R.drawable.mesas, "MESA 8"));

        return listaMesas;

    }


}