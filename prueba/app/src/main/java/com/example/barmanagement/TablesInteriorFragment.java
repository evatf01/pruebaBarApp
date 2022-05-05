package com.example.barmanagement;

import static com.example.barmanagement.utils.FirestoreFields.*;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.barmanagement.adapters.ListTablesAdapter;
import com.example.barmanagement.models.Tables;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class TablesInteriorFragment extends Fragment implements NavigationBarView.OnItemSelectedListener, ListTablesAdapter.OnTablesListener {
    View txtExterior;
    ArrayList<Tables> listaMesas = new ArrayList<>();
    private FirebaseFirestore db;

    private static final String ZONA = "interior";

    public static TablesInteriorFragment newInstance(String param1, String param2) { return new TablesInteriorFragment(); }

    public TablesInteriorFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_tables_interior, container, false);
    }




    /*private ArrayList<Tables> listaMesas(){

        ArrayList<Tables> listaMesas = new ArrayList<>();
        listaMesas.add(new Tables(R.drawable.mesas, "MESA 1"));
        listaMesas.add(new Tables(R.drawable.mesas, "MESA 2"));
        listaMesas.add(new Tables(R.drawable.mesas, "MESA 3"));
        listaMesas.add(new Tables(R.drawable.mesas, "MESA 4"));
        listaMesas.add(new Tables(R.drawable.mesas, "MESA 5"));
        listaMesas.add(new Tables(R.drawable.mesas, "MESA 6"));

        return listaMesas;

    }
*/
    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
       // listaMesas = listaMesas();
        db =  FirebaseFirestore.getInstance();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.listTablesInterior);
        BottomNavigationView btnNav = (BottomNavigationView) view.findViewById(R.id.bottomNavigationView);
        btnNav.setOnItemSelectedListener(this);
        Log.d("mesas", listaMesas.toString());
        ListTablesAdapter adapter = new ListTablesAdapter(listaMesas, getContext(), this);


        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());

        adapter.notifyDataSetChanged();
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);


         txtExterior = (View)view.findViewById(R.id.exterior);

        setOnClickListenerExterior();
        crearMesasFS();

    }

    private void crearMesasFS() {
        Map<String,Object> tables = new HashMap<>();
        tables.put("iMesa1", new Tables("MESA 1","INTERIOR","","","https://cdn-icons-png.flaticon.com/512/47/47638.png"));
        tables.put("iMesa2", new Tables("MESA 2","INTERIOR","","","https://cdn-icons-png.flaticon.com/512/47/47638.png"));
        tables.put("iMesa3", new Tables("MESA 3","INTERIOR","","","https://cdn-icons-png.flaticon.com/512/47/47638.png"));
        tables.put("iMesa4", new Tables("MESA 4","INTERIOR","","","https://cdn-icons-png.flaticon.com/512/47/47638.png"));
        tables.put("iMesa5", new Tables("MESA 5","INTERIOR","","","https://cdn-icons-png.flaticon.com/512/47/47638.png"));


        db.collection(MESAS).document().set(tables).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Log.d("bien", "añadido");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("mal","no añadido");
            }
        });

    }

    private void setOnClickListenerExterior() {
        txtExterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.tablesExteriorFragment);
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.exterior:
                Log.d("","");
        }
        return false;
    }

    @Override
    public void onTableClick(View view, int position) {

        Tables table = listaMesas.get(position);
        Log.d("mesa interior", table.toString());
        Navigation.findNavController(view).navigate(R.id.comandaFragment);
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