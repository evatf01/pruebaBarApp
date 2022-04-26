package com.example.prueba;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.prueba.adapters.ListTablesAdapter;
import com.example.prueba.models.Tables;

import java.util.ArrayList;


public class TablesInteriorFragment extends Fragment {

    private TextView txtExterior;
    private ListView listview_mesas;
    private ListTablesAdapter adapter;
    private Fragment fragment;
    private static String ZONA = "interior";



    public TablesInteriorFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static TablesInteriorFragment newInstance(String param1, String param2) {

        return new TablesInteriorFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tables_interior, container, false);
        txtExterior = (TextView) view.findViewById(R.id.exterior);
        listview_mesas = (ListView) view.findViewById(R.id.listTablesInterior);
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        setOnClickListenerZona();
        setOnClickListenerCategory();

        adapter = new ListTablesAdapter( listaMesas(),getContext());
        listview_mesas.setAdapter(adapter);


        return view;
    }


    private void setOnClickListenerZona() {
     txtExterior.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             fragment = new TablesExteriorFragment();
             requireActivity().getSupportFragmentManager().beginTransaction()
                     .replace(R.id.contenedorZonaInterior, fragment)
                     .addToBackStack(TablesInteriorFragment.class.getName())
                     .commit();
         }
     });
    }

    private void setOnClickListenerCategory() {
        listview_mesas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                fragment = new CategoriasFragment();
                requireActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.contenedorZonaInterior, fragment)
                        .addToBackStack(TablesInteriorFragment.class.getName())
                        .commit();
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

        return listaMesas;

    }

}