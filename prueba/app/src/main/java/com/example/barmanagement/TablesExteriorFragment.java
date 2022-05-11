package com.example.barmanagement;

import static com.example.barmanagement.utils.FirestoreFields.MESAS;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.barmanagement.adapters.ListTablesAdapter;
import com.example.barmanagement.models.Tables;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TablesExteriorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TablesExteriorFragment extends Fragment  {
    ArrayList<Tables> listaMesas = new ArrayList<>();
    private static final String ZONA = "EXTERIOR";
    private View txtInterior;
    private FirebaseFirestore db;
    private ListTablesAdapter adapter;


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


        return inflater.inflate(R.layout.fragment_tables_exterior, container, false);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        txtInterior = (View) view.findViewById(R.id.interior);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.listTablesExterior);
        //listaMesas = listaMesas();
        db =  FirebaseFirestore.getInstance();
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
                Navigation.findNavController(view).navigate(R.id.comandaFragment);
            }
        });
        setOnClickListenerComanda();
        setOnClickListenerTablesInterior();
       // crearMesasFS();

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

  /*  private void crearMesasFS() {
        Map<String,Object> tables = new HashMap<>();
        tables.put("eMesa1", new Tables("MESA 1","EXTERIOR","","","https://cdn-icons-png.flaticon.com/512/47/47638.png"));
        tables.put("eMesa2", new Tables("MESA 2","EXTERIOR","","","https://cdn-icons-png.flaticon.com/512/47/47638.png"));
        tables.put("eMesa3", new Tables("MESA 3","EXTERIOR","","","https://cdn-icons-png.flaticon.com/512/47/47638.png"));
        tables.put("eMesa4", new Tables("MESA 4","EXTERIOR","","","https://cdn-icons-png.flaticon.com/512/47/47638.png"));
        tables.put("eMesa5", new Tables("MESA 5","EXTERIOR","","","https://cdn-icons-png.flaticon.com/512/47/47638.png"));
        tables.put("eMesa6", new Tables("MESA 6","EXTERIOR","","","https://cdn-icons-png.flaticon.com/512/47/47638.png"));
        tables.put("eMesa7", new Tables("MESA 7","EXTERIOR","","","https://cdn-icons-png.flaticon.com/512/47/47638.png"));
        tables.put("eMesa8", new Tables("MESA 8","EXTERIOR","","","https://cdn-icons-png.flaticon.com/512/47/47638.png"));
        tables.put("eMesa9", new Tables("MESA 9","EXTERIOR","","","https://cdn-icons-png.flaticon.com/512/47/47638.png"));
        tables.put("eMesa10", new Tables("MESA 10","EXTERIOR","","","https://cdn-icons-png.flaticon.com/512/47/47638.png"));


        db.collection(MESAS).document(ZONA).set(tables).addOnSuccessListener(new OnSuccessListener<Void>() {
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

    }*/



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
}