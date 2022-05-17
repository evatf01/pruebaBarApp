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
 * Use the {@link CoffeeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CoffeeFragment extends Fragment implements NavigationBarView.OnItemSelectedListener, DrinksAdapter.RecyclerViewClickListener {
    private FirebaseFirestore db;
    DrinksAdapter adapter;
    ImageView arrow;
    List<Category> cafes = new ArrayList<>();


    public CoffeeFragment() {}

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CoffeeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CoffeeFragment newInstance(String param1, String param2) {

        return new CoffeeFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_coffee, container, false);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.listaCafes);
        BottomNavigationView btnNav = (BottomNavigationView) view.findViewById(R.id.bottomNavigationViewDrinks);
        db = FirebaseFirestore.getInstance();
       // Query query = db.collection(CATEGORIAS).document("bebidas").collection("cafes");


        cafes = obtenerDatos();

        arrow = (ImageView) view.findViewById(R.id.imbArrowBack);
      /*  FirestoreRecyclerOptions<Category> options = new FirestoreRecyclerOptions.Builder<Category>()
                .setQuery(query, Category.class).build();

*/
        btnNav.setItemIconTintList(null);
        adapter = new DrinksAdapter( cafes,getContext(),this);
        adapter.notifyDataSetChanged();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        btnNav.setOnItemSelectedListener(this);

        setOnClickListenerBack();


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

    @SuppressLint("NotifyDataSetChanged")
    private List<Category> obtenerDatos()  {
        List<Category> lista_cafes = new ArrayList<>();
        CollectionReference refrescos =  db.collection(CATEGORIAS).document("bebidas").collection("cafes");
        refrescos.get().addOnCompleteListener(task -> {
            if(task.isSuccessful()  && task.isComplete()){
                for (QueryDocumentSnapshot document: task.getResult()){
                    Category category = document.toObject(Category.class);
                    lista_cafes.add(category);

                }
            }
            adapter.notifyDataSetChanged();
        });
        return lista_cafes;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.cerveza:
                Navigation.findNavController(requireView()).navigate(R.id.beerFragment);
                break;
            case R.id.refrescos:
                Navigation.findNavController(requireView()).navigate(R.id.drinksFragment);
                break;
            case R.id.more:
                Navigation.findNavController(requireView()).navigate(R.id.moreDrinksFragment);
                break;
        }
        return true;
    }

    @Override
    public void onClick(View view, int position) { }

    private void setOnClickListenerBack() {
        arrow.setOnClickListener(view -> {
            Navigation.findNavController(view).navigate(R.id.comandaFragment);
        });
    }
}