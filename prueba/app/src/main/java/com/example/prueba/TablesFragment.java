package com.example.prueba;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prueba.adapters.ListTablesAdapter;
import com.example.prueba.models.Tables;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TablesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TablesFragment extends Fragment {

    TextView txtInterior;
    ListView listview_mesas;
    ListTablesAdapter adapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TablesFragment() {
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
    public static TablesFragment newInstance(String param1, String param2) {
        TablesFragment fragment = new TablesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tables, container, false);
        txtInterior = (TextView) view.findViewById(R.id.txtInterior);
        listview_mesas = (ListView) view.findViewById(R.id.listTables);

        adapter = new ListTablesAdapter( listaMesas(),getContext());
        listview_mesas.setAdapter(adapter);

        txtInterior.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                      Log.d("hola","hjp");
                    }
                }
        );
        // Inflate the layout for this fragment
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

}