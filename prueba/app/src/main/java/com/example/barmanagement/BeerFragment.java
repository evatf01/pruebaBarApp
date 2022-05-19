package com.example.barmanagement;

import static com.example.barmanagement.DrinksFragment.CATEGORIAS;

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
import android.widget.ImageView;

import com.example.barmanagement.adapters.DrinksAdapter;
import com.example.barmanagement.models.Category;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BeerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BeerFragment extends Fragment  implements NavigationBarView.OnItemSelectedListener, DrinksAdapter.RecyclerViewClickListener {

    private FirebaseFirestore db;
    DrinksAdapter adapter;
    ImageView arrow;
    List<Category> cervezas = new ArrayList<>();
    public BeerFragment() {
        // Required empty public constructor
    }

    public static BeerFragment newInstance() {

        return new BeerFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_beer, container, false);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.listCerveza);
        BottomNavigationView btnNav = (BottomNavigationView) view.findViewById(R.id.bottomNavigationViewDrinks);
        db =  FirebaseFirestore.getInstance();
        btnNav.setItemIconTintList(null);

        cervezas = obtenerDatos();

        //Query query = db.collection(CATEGORIAS).document("bebidas").collection("cervezas");


        arrow = (ImageView) view.findViewById(R.id.imbArrowBack);
       /* FirestoreRecyclerOptions<Category> options = new FirestoreRecyclerOptions.Builder<Category>()
                .setQuery(query, Category.class).build();

        */
        adapter = new DrinksAdapter(cervezas,getContext(),this);
        adapter.notifyDataSetChanged();

        btnNav.setItemIconTintList(null);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        setOnClickListenerBack();
        btnNav.setOnItemSelectedListener(this);

    }

    private void setOnClickListenerBack() {
        arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.comandaFragment);
            }
        });
    }

    @SuppressLint("NotifyDataSetChanged")
    private List<Category> obtenerDatos() {
        List<Category> lista_cervza = new ArrayList<>();
        CollectionReference cerveza =  db.collection(CATEGORIAS).document("bebidas").collection("cervezas");

        cerveza.get().addOnCompleteListener(task -> {
            if(task.isSuccessful()  && task.isComplete()){
                for (QueryDocumentSnapshot document: task.getResult()){
                    Category category = document.toObject(Category.class);
                    lista_cervza.add(category);

                }
            }
            adapter.notifyDataSetChanged();
        });


        return lista_cervza;
    }

 /*   @Override
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
            case R.id.cafes:
                item.setCheckable(true);
                Navigation.findNavController(requireView()).navigate(R.id.coffeeFragment);
                break;
            case R.id.refrescos:
                item.setCheckable(true);
                Navigation.findNavController(requireView()).navigate(R.id.drinksFragment);
                break;
            case R.id.more:
                Navigation.findNavController(requireView()).navigate(R.id.moreDrinksFragment);
                break;
        }
        return true;
    }

    @Override
    public void onClick(View view, int position) {

    }
}