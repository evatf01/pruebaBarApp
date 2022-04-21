package com.example.prueba;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.prueba.adapters.ListTablesAdapter;
import com.example.prueba.models.Tables;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TablesInteriorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TablesInteriorFragment extends Fragment {

    private TextView txtExterior;
    private ListView listview_mesas;
    private ListTablesAdapter adapter;
    private Fragment fragment;
    private static String ZONA = "interior";

    private final FragmentManager fm = getActivity().getSupportFragmentManager();
    private final FragmentTransaction ft = fm.beginTransaction();


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


    // TODO: Rename and change types of parameters


    public TablesInteriorFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TablesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TablesInteriorFragment newInstance(String param1, String param2) {
        TablesInteriorFragment fragment = new TablesInteriorFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tables_interior, container, false);
        txtExterior = (TextView) view.findViewById(R.id.txtExterior);
        listview_mesas = (ListView) view.findViewById(R.id.listTablesInterior);

        setOnClickListenerZona();
        setOnClickListenerText();

        adapter = new ListTablesAdapter( listaMesas(),getContext());
        listview_mesas.setAdapter(adapter);


        return view;
    }

    private void setOnClickListenerZona() {
        listview_mesas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                fragment = new CategoriasFragment();
                Bundle bundle = new Bundle();
                bundle.putString(ZONA, "interior" );
                fragment.setArguments(bundle);
                fm.beginTransaction().add(R.id.contenedorCategoria, fragment);
                ft.commit();
            }
        });
    }

    private void setOnClickListenerText() {
        listview_mesas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                fragment = new CategoriasFragment();

                fm.beginTransaction().add(R.id.contenedorZonaExterior, fragment);
                ft.commit();
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