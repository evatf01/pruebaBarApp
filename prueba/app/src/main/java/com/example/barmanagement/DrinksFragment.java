package com.example.barmanagement;



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
import android.widget.EditText;
import android.widget.ImageView;

import com.example.barmanagement.adapters.DrinksAdapter;
import com.example.barmanagement.models.Category;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DrinksFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DrinksFragment extends Fragment implements NavigationBarView.OnItemSelectedListener, DrinksAdapter.RecyclerViewClickListener{
    private FirebaseFirestore db;
    DrinksAdapter adapter;
    List<Category> refrescos = new ArrayList<>();
    public static final String CATEGORIAS = "CATEGORIAS";
    ImageView arrow;



    public DrinksFragment() { }


    public static DrinksFragment newInstance(String param1, String param2) {

        return new DrinksFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_refrescos, container, false);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        db =  FirebaseFirestore.getInstance();


        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.listaRefrescos);
        BottomNavigationView btnNav = (BottomNavigationView) view.findViewById(R.id.bottomNavigationViewDrinks);

        arrow = (ImageView) view.findViewById(R.id.imbArrowBack);
        //Query query = db.collection(CATEGORIAS).document("bebidas").collection("refrescos");

       /* FirestoreRecyclerOptions<Category> options = new FirestoreRecyclerOptions.Builder<Category>()
                .setQuery(query, Category.class).build();*/

        //Log.d("query", options.getSnapshots().toString());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());

        refrescos = obtenerDatos();

        adapter = new DrinksAdapter(refrescos,getContext(),this);
        adapter.notifyDataSetChanged();

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        btnNav.setOnItemSelectedListener(this);

        getCantidadBebidas();

        setOnClickListenerBack();


    }

    private void getCantidadBebidas() {

        Map<String, Object> comanda = new HashMap<>();


        db.collection(CATEGORIAS).document("bebidas").collection("refrescos").document("comandas").set(comanda);
    }

    @SuppressLint("NotifyDataSetChanged")
    private List<Category> obtenerDatos() {
        List<Category> lista_refrescos = new ArrayList<>();
        CollectionReference refrescos =  db.collection(CATEGORIAS).document("bebidas").collection("refrescos");

        refrescos.get().addOnCompleteListener(task -> {
            if(task.isSuccessful() && task.isComplete()){
                for (QueryDocumentSnapshot document: task.getResult()){
                    Category category = document.toObject(Category.class);
                    lista_refrescos.add(category);

                }
            }


            adapter.notifyDataSetChanged();
        });

        //Thread.sleep(200);
        Log.d("refrescos", lista_refrescos.toString());


        return lista_refrescos;
    }

    private void setOnClickListenerBack() {
        arrow.setOnClickListener(view -> {
                Navigation.findNavController(view).navigate(R.id.comandaFragment);
        });
    }


  /*  @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }*/

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.cerveza:
                Navigation.findNavController(requireView()).navigate(R.id.beerFragment);
                break;
            case R.id.cafes:
                Navigation.findNavController(requireView()).navigate(R.id.coffeeFragment);

        }
       return true;
    }

    @Override
    public void onClick(View view, int position) {

    }
}