package com.example.barmanagement;


import static com.example.barmanagement.ComandaFragment.numero;
import static com.example.barmanagement.utils.FirestoreFields.AQUARIUS_LIMON;
import static com.example.barmanagement.utils.FirestoreFields.AQUARIUS_NARANJA;
import static com.example.barmanagement.utils.FirestoreFields.COCA_COLA;
import static com.example.barmanagement.utils.FirestoreFields.COCA_COLA_ZERO;
import static com.example.barmanagement.utils.FirestoreFields.COMANDA;
import static com.example.barmanagement.utils.FirestoreFields.FANTA_LIMON;
import static com.example.barmanagement.utils.FirestoreFields.FANTA_NARANJA;
import static com.example.barmanagement.utils.FirestoreFields.SEVENUP;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

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
import com.pranavpandey.android.dynamic.toasts.DynamicToast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DrinksFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DrinksFragment extends Fragment implements NavigationBarView.OnItemSelectedListener{
    private FirebaseFirestore db;
    DrinksAdapter adapter;
    List<Category> refrescos = new ArrayList<>();
    public static final String CATEGORIAS = "CATEGORIAS";
    ImageView arrow;
    FloatingActionButton btnCheck;
    List<ContentValues> escrito;
    RecyclerView recyclerView;

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
        return inflater.inflate(R.layout.fragment_refrescos, container, false);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        db =  FirebaseFirestore.getInstance();

        recyclerView = (RecyclerView) view.findViewById(R.id.listaRefrescos);
        BottomNavigationView btnNav = (BottomNavigationView) view.findViewById(R.id.bottomNavigationViewDrinks);
        btnNav.setItemIconTintList(null);
        btnCheck = (FloatingActionButton)view.findViewById(R.id.btnCheck);
        arrow = (ImageView) view.findViewById(R.id.imbArrowBack);

        escrito = new ArrayList<>();
        Query query = db.collection(CATEGORIAS).document("bebidas").collection("refrescos");
        FirestoreRecyclerOptions<Category> options = new FirestoreRecyclerOptions.Builder<Category>()
                .setQuery(query, Category.class).build();
        Log.d("query", options.getSnapshots().toString());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());

        //refrescos = obtenerDatos();

        adapter = new DrinksAdapter(options,getContext());


        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        recyclerView.setItemViewCacheSize(refrescos.size());

        btnNav.setOnItemSelectedListener(this);

        setOnClickListenerBack();
        setOnClickListenerCheck();




    }

    private void setOnClickListenerCheck() {
        btnCheck.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
               List<HashMap<String, Object>> texto = adapter.getTexto();
                for (int a =0; a<texto.size();a++) {
                    HashMap<String, Object> data = (HashMap<String, Object>) texto.get(a);
                    Set<String> key = data.keySet();
                    Iterator<String> it = key.iterator();
                    while (it.hasNext()) {
                        String keyData = (String) it.next();
                        Object num = data.get(keyData);
                        //getCantidadBebidas(keyData, num);
                        System.out.println("Key: " + keyData + " & Data: " + num);
                        switch (keyData) {
                            case COCA_COLA:
                                if (num != null) {
                                    if (!num.equals("")) {
                                        HashMap<String, Object> refresco = new HashMap<>();
                                        refresco.put("nombre", COCA_COLA);
                                        refresco.put("cantidad", num);
                                        db.collection(COMANDA).document(numero).collection("orden").document(COCA_COLA).set(refresco);
                                    }

                                }
                                break;
                            case COCA_COLA_ZERO:
                                if (num != null) {
                                    if (!num.equals("")) {
                                        HashMap<String, Object> refresco = new HashMap<>();
                                        refresco.put("nombre", COCA_COLA_ZERO);
                                        refresco.put("cantidad", num);
                                        db.collection(COMANDA).document(numero).collection("orden").document(COCA_COLA_ZERO).set(refresco);
                                    }
                                }
                                break;
                            case AQUARIUS_LIMON:
                                if (num != null) {
                                    if (!num.equals("")) {
                                        HashMap<String, Object> refresco = new HashMap<>();
                                   /* List<Integer> total = new ArrayList<>();
                                    //total.add(Integer.parseInt(num.toString()));

                                    int resultado=0;
                                    for (int numero : total){
                                        resultado += numero;
                                    }*/
                                        refresco.put("nombre", AQUARIUS_LIMON);
                                        refresco.put("cantidad", num);
                                        db.collection(COMANDA).document(numero).collection("orden").document(AQUARIUS_LIMON).set(refresco);
                                    }
                                }
                                break;
                            case AQUARIUS_NARANJA:
                                if (num != null) {
                                    if (!num.equals("")) {
                                        HashMap<String, Object> refresco = new HashMap<>();
                                        refresco.put("nombre", AQUARIUS_NARANJA);
                                        refresco.put("cantidad", num);
                                        db.collection(COMANDA).document(numero).collection("orden").document(AQUARIUS_NARANJA).set(refresco);
                                    }

                                }
                                break;
                            case SEVENUP:
                                if (num != null) {
                                    if (!num.equals("")) {
                                        HashMap<String, Object> refresco = new HashMap<>();
                                        refresco.put("nombre", SEVENUP);
                                        refresco.put("cantidad", num);
                                        db.collection(COMANDA).document(numero).collection("orden").document(SEVENUP).set(refresco);
                                    }

                                }
                                break;
                            case FANTA_LIMON:
                                if (num != null) {
                                    if (!num.equals("")) {
                                        HashMap<String, Object> refresco = new HashMap<>();
                                        refresco.put("nombre", FANTA_LIMON);
                                        refresco.put("cantidad", num);
                                        db.collection(COMANDA).document(numero).collection("orden").document(FANTA_LIMON).set(refresco);
                                    }

                                }
                                break;
                            case FANTA_NARANJA:
                                if (num != null) {
                                    if (!num.equals("")) {
                                        HashMap<String, Object> refresco = new HashMap<>();
                                        refresco.put("nombre", FANTA_NARANJA);
                                        refresco.put("cantidad", num);
                                        db.collection(COMANDA).document(numero).collection("orden").document(FANTA_NARANJA).set(refresco);
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
            case R.id.more:
                Navigation.findNavController(requireView()).navigate(R.id.moreDrinksFragment);
        }
        return true;
    }


}