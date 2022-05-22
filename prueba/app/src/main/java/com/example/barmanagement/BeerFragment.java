package com.example.barmanagement;

import static com.example.barmanagement.ComandaFragment.numero;
import static com.example.barmanagement.DrinksFragment.CATEGORIAS;
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
import android.widget.ImageView;

import com.example.barmanagement.adapters.DrinksAdapter;
import com.example.barmanagement.models.Category;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BeerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BeerFragment extends Fragment  implements NavigationBarView.OnItemSelectedListener{

    private FirebaseFirestore db;
    DrinksAdapter adapter;
    ImageView arrow;
    List<Category> cervezas = new ArrayList<>();
    FloatingActionButton btnCheck;
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
        btnCheck = (FloatingActionButton) view.findViewById(R.id.btnCheck);
        //cervezas = obtenerDatos();

        Query query = db.collection(CATEGORIAS).document("bebidas").collection("cervezas");


        arrow = (ImageView) view.findViewById(R.id.imbArrowBack);
       FirestoreRecyclerOptions<Category> options = new FirestoreRecyclerOptions.Builder<Category>()
                .setQuery(query, Category.class).build();


        adapter = new DrinksAdapter(options,getContext());
        adapter.notifyDataSetChanged();

        btnNav.setItemIconTintList(null);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        setOnClickListenerBack();
        setOnClickListenerCheck();
        btnNav.setOnItemSelectedListener(this);

    }


    private void setOnClickListenerCheck() {
        btnCheck.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                List<HashMap<String, Object>> texto = adapter.getTexto();
                for (int a =0; a<texto.size();a++)
                {
                    HashMap<String, Object> data = (HashMap<String, Object>) texto.get(a);
                    Set<String> key = data.keySet();
                    Iterator<String> it = key.iterator();
                    while (it.hasNext()) {
                        String keyData = (String)it.next();
                        Object num = data.get(keyData);
                        //getCantidadBebidas(keyData, num);
                        System.out.println("Key: "+keyData +" & Data: "+num);
                        switch (keyData) {
                            case CERVEZA_1925:
                                if (num != null) {
                                    if(!num.equals("")){
                                        HashMap<String, Object> refresco = new HashMap<>();
                                        refresco.put("nombre", CERVEZA_1925);
                                        refresco.put("cantidad", num);
                                        db.collection(COMANDA).document(numero).collection("orden").document(CERVEZA_1925).set(refresco);
                                    }
                                }
                                break;
                            case ALHAMBRA:
                                if (num != null) {
                                    if(!num.equals("")){
                                        HashMap<String, Object> refresco = new HashMap<>();
                                        refresco.put("nombre", ALHAMBRA);
                                        refresco.put("cantidad", num);
                                        db.collection(COMANDA).document(numero).collection("orden").document(ALHAMBRA).set(refresco);
                                    }
                                }
                                break;
                            case ESTRELLA_GAL:
                                if (num != null) {
                                    if(!num.equals("")){
                                        HashMap<String, Object> refresco = new HashMap<>();
                                        refresco.put("nombre", ESTRELLA_GAL);
                                        refresco.put("cantidad", num);
                                        db.collection(COMANDA).document(numero).collection("orden").document(ESTRELLA_GAL).set(refresco);
                                    }
                                }
                                break;
                            case SALITOS:
                                if (num != null) {
                                    if(!num.equals("")){
                                        HashMap<String, Object> refresco = new HashMap<>();
                                   /* List<Integer> total = new ArrayList<>();
                                    //total.add(Integer.parseInt(num.toString()));
                                    HashMap<String, Object> refresco = new HashMap<>();
                                    int resultado = 0;
                                    for (int numero : total) {
                                        resultado += numero;
                                    }*/
                                        refresco.put("nombre", SALITOS);
                                        refresco.put("cantidad", num);
                                        db.collection(COMANDA).document(numero).collection("orden").document(SALITOS).set(refresco);
                                    }
                                }
                                break;
                            case SAN_MIGUEL:
                                if (num != null) {
                                    if(!num.equals("")){
                                        HashMap<String, Object> refresco = new HashMap<>();
                                        refresco.put("nombre", SAN_MIGUEL);
                                        refresco.put("cantidad", num);
                                        db.collection(COMANDA).document(numero).collection("orden").document(SAN_MIGUEL).set(refresco);
                                    }
                                }
                                break;
                        }
                        it.remove();
                    }

                }
            }
        });
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

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.cafes:
                Navigation.findNavController(requireView()).navigate(R.id.coffeeFragment);
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
}