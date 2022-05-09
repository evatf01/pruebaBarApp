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
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class TablesInteriorFragment extends Fragment implements NavigationBarView.OnItemSelectedListener {
    View txtExterior;
    private FirebaseFirestore db;
    ListTablesAdapter adapter;


    private static final String ZONA = "INTERIOR";

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


    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        db =  FirebaseFirestore.getInstance();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.listTablesInterior);
        BottomNavigationView btnNav = (BottomNavigationView) view.findViewById(R.id.bottomNavigationView);
        btnNav.setOnItemSelectedListener(this);


        Query query = db.collection(ZONA);

        FirestoreRecyclerOptions<Tables> options = new FirestoreRecyclerOptions.Builder<Tables>()
                .setQuery(query, Tables.class).build();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());

        adapter = new ListTablesAdapter( options,getContext());
        adapter.notifyDataSetChanged();

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        adapter.setOnClickListener(new ListTablesAdapter.OnTablesListener() {
            @Override
            public void onTableClick(DocumentSnapshot documentSnapshot, int position) {
                Bundle bundle = new Bundle();
                String num =  documentSnapshot.getString("num");
                bundle.putString("numero", num);
                Log.d("numero", documentSnapshot.getString("num"));
                Navigation.findNavController(view).navigate(R.id.comandaFragment, bundle);
            }
        });


         txtExterior = (View)view.findViewById(R.id.exterior);

        setOnClickListenerExterior();
        //crearMesasFS();
        //obtenerDatos();

    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }

   /* private void crearMesasFS() {
        Map<String,Object> tables = new HashMap<>();
        tables.put("iMesa1", new Tables("MESA 1","INTERIOR","","","https://cdn-icons-png.flaticon.com/512/47/47638.png"));
        tables.put("iMesa2", new Tables("MESA 2","INTERIOR","","","https://cdn-icons-png.flaticon.com/512/47/47638.png"));
        tables.put("iMesa3", new Tables("MESA 3","INTERIOR","","","https://cdn-icons-png.flaticon.com/512/47/47638.png"));
        tables.put("iMesa4", new Tables("MESA 4","INTERIOR","","","https://cdn-icons-png.flaticon.com/512/47/47638.png"));
        tables.put("iMesa5", new Tables("MESA 5","INTERIOR","","","https://cdn-icons-png.flaticon.com/512/47/47638.png"));
        tables.put("iMesa6", new Tables("MESA 6","INTERIOR","","","https://cdn-icons-png.flaticon.com/512/47/47638.png"));
        tables.put("iMesa7", new Tables("MESA 7","INTERIOR","","","https://cdn-icons-png.flaticon.com/512/47/47638.png"));


        db.collection(ZONA).document("MESA"+num+1).set(tables).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

            }
        });
    }*/

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