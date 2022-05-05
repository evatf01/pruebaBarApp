package com.example.barmanagement;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.barmanagement.adapters.ListTablesAdapter;
import com.example.barmanagement.models.Tables;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TablesExteriorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TablesExteriorFragment extends Fragment implements ListTablesAdapter.OnTablesListener {
    ArrayList<Tables> listaMesas = new ArrayList<>();
    private RecyclerView recyclerView;
    private ListTablesAdapter adapter;
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
        //listaMesas = listaMesas();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        adapter = new ListTablesAdapter( listaMesas,getContext(), this);
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

    }



   /* private ArrayList<Tables> listaMesas(){
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

    }*/


    @Override
    public void onTableClick(View view, int position) {

        Tables table = listaMesas.get(position);
        Log.d("mesa exterior", table.toString());
        Navigation.findNavController(view).navigate(R.id.comandaFragment);

    }
}