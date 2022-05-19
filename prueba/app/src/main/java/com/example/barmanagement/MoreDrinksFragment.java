package com.example.barmanagement;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.annotation.SuppressLint;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import com.example.barmanagement.adapters.DrinksAdapter;
import com.example.barmanagement.models.Category;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import java.util.ArrayList;
import java.util.List;



/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DrinksFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MoreDrinksFragment extends Fragment implements NavigationBarView.OnItemSelectedListener, DrinksAdapter.RecyclerViewClickListener {
    private FirebaseFirestore db;
    DrinksAdapter adapter;
    List<Category> refrescos = new ArrayList<>();
    public static final String CATEGORIAS = "CATEGORIAS";
    ImageView arrow;
    FloatingActionButton btnCheck;
    List<String> escrito;

    public MoreDrinksFragment() { }

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
        return inflater.inflate(R.layout.fragment_refrescos, container, false);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        db = FirebaseFirestore.getInstance();

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.listaRefrescos);
        BottomNavigationView btnNav = (BottomNavigationView) view.findViewById(R.id.bottomNavigationViewDrinks);
        btnNav.setItemIconTintList(null);
        btnCheck = (FloatingActionButton) view.findViewById(R.id.btnCheck);
        arrow = (ImageView) view.findViewById(R.id.imbArrowBack);
        escrito = new ArrayList<>();
        //Query query = db.collection(CATEGORIAS).document("bebidas").collection("refrescos");

            //Log.d("query", options.getSnapshots().toString());
            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());

            refrescos = obtenerDatos();

            adapter = new DrinksAdapter(refrescos, getContext(),this);
            adapter.notifyDataSetChanged();

            recyclerView.setLayoutManager(layoutManager);

            recyclerView.setHasFixedSize(true);
            recyclerView.setAdapter(adapter);
            recyclerView.setItemViewCacheSize(refrescos.size());

            btnNav.setOnItemSelectedListener(this);

            // getCantidadBebidas();

            setOnClickListenerBack();
            setOnClickListenerCheck();
        }


        private void setOnClickListenerCheck() {
            btnCheck.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //escrito = adapter.getTexto();

                    Log.d("escrito", escrito.toString());
                }
            });
        }



        @SuppressLint("NotifyDataSetChanged")
        private List<Category> obtenerDatos() {
            List<Category> lista_refrescos = new ArrayList<>();
            CollectionReference refrescos =  db.collection(CATEGORIAS).document("bebidas").collection("mas_bebidas");

            refrescos.get().addOnCompleteListener(task -> {
                if(task.isSuccessful() && task.isComplete()){
                    for (QueryDocumentSnapshot document: task.getResult()){
                        Category category = document.toObject(Category.class);
                        lista_refrescos.add(category);

                    }
                }


                adapter.notifyDataSetChanged();
            });

            return lista_refrescos;
        }

        private void setOnClickListenerBack() {
            arrow.setOnClickListener(view -> {
                Navigation.findNavController(view).navigate(R.id.comandaFragment);
            });
        }


        @SuppressLint("NonConstantResourceId")
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.cerveza:
                    Navigation.findNavController(requireView()).navigate(R.id.beerFragment);
                    break;
                case R.id.cafes:
                    Navigation.findNavController(requireView()).navigate(R.id.coffeeFragment);
                    break;
                case R.id.refrescos:
                    Navigation.findNavController(requireView()).navigate(R.id.drinksFragment);
                    break;

            }
            return true;
        }


    @Override
    public void onClick(View view, int position) {

    }
}