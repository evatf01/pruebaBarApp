package com.example.barmanagement;


import static com.example.barmanagement.utils.FirestoreFields.AQUARIUS_LIMON;
import static com.example.barmanagement.utils.FirestoreFields.AQUARIUS_NARANJA;
import static com.example.barmanagement.utils.FirestoreFields.CATEGORIAS;
import static com.example.barmanagement.utils.FirestoreFields.COCA_COLA;
import static com.example.barmanagement.utils.FirestoreFields.COCA_COLA_ZERO;
import static com.example.barmanagement.utils.FirestoreFields.FANTA_LIMON;
import static com.example.barmanagement.utils.FirestoreFields.FANTA_NARANJA;
import static com.example.barmanagement.utils.FirestoreFields.SEVENUP;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barmanagement.adapters.DrinksAdapter;
import com.example.barmanagement.firestorecontroller.FirestoreController;
import com.example.barmanagement.models.Category;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.pranavpandey.android.dynamic.toasts.DynamicToast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class DrinksFragment extends Fragment implements NavigationBarView.OnItemSelectedListener, Serializable{
    private FirebaseFirestore db;
    private DrinksAdapter adapter;
    private ImageView arrow;
    private FloatingActionButton btnCheck;
    private static List<Map<String,Object>> refrescos = new ArrayList<>();
    private RecyclerView recyclerView;
    private FirestoreController firestoreController;


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
        firestoreController = new FirestoreController(this.db);
        db = FirebaseFirestore.getInstance();

        recyclerView = (RecyclerView) view.findViewById(R.id.listaRefrescos);
        BottomNavigationView btnNav = (BottomNavigationView) view.findViewById(R.id.bottomNavigationViewDrinks);
        btnNav.setItemIconTintList(null);
        btnCheck = (FloatingActionButton)view.findViewById(R.id.btnCheck);
        arrow = (ImageView) view.findViewById(R.id.imbArrowBack);

        Query query = db.collection(CATEGORIAS).document("bebidas").collection("refrescos");
        FirestoreRecyclerOptions<Category> options = new FirestoreRecyclerOptions.Builder<Category>()
                .setQuery(query, Category.class).build();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());


        adapter = new DrinksAdapter(options,getContext());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        btnNav.setOnItemSelectedListener(this);

        setOnClickListenerBack();
        setOnClickListenerCheck();

    }

    private void setOnClickListenerCheck() {

        btnCheck.setOnClickListener(view -> {
            Object stock;
            Object precio;
            List<HashMap<String, Object>> texto = adapter.getTexto();

            for (int a =0; a<texto.size();a++) {
                HashMap<String, Object> data = (HashMap<String, Object>) texto.get(a);
                HashMap<String, Object> bebidas = new HashMap<>();
                Set<String> key = data.keySet();
                Iterator<String> it = key.iterator();
                stock= data.get("stock") ;
                precio = data.get("precio");
                while (it.hasNext()) {
                    String keyData = (String) it.next();
                    Object num = data.get(keyData);
                    switch (keyData) {
                        case COCA_COLA:
                            if (num != null) {
                                if (!num.equals("")) {
                                    firestoreController.actualizarRefresco(COCA_COLA, "CocaCola", num,stock);
                                    bebidas.put("nombre", COCA_COLA);
                                    bebidas.put("cantidad", String.valueOf(num));
                                    bebidas.put("precio", String.valueOf(precio));
                                    refrescos.add(bebidas);

                                }

                            }
                            break;
                        case COCA_COLA_ZERO:
                            if (num != null) {
                                if (!num.equals("")) {
                                    firestoreController.actualizarRefresco(COCA_COLA_ZERO, "CocaColaZero",num,stock);
                                    bebidas.put("nombre", COCA_COLA_ZERO);
                                    bebidas.put("cantidad", String.valueOf(num));
                                    bebidas.put("precio", String.valueOf(precio));
                                    refrescos.add(bebidas);

                                }
                            }
                            break;
                        case AQUARIUS_LIMON:
                            if (num != null) {
                                if (!num.equals("")) {
                                    firestoreController.actualizarRefresco(AQUARIUS_LIMON, "AquariusLimon", num,stock);
                                    bebidas.put("nombre", AQUARIUS_LIMON);
                                    bebidas.put("cantidad", String.valueOf(num));
                                    bebidas.put("precio", String.valueOf(precio));
                                    refrescos.add(bebidas);

                                }
                            }
                            break;
                        case AQUARIUS_NARANJA:
                            if (num != null) {
                                if (!num.equals("")) {
                                    firestoreController.actualizarRefresco(AQUARIUS_NARANJA,"AquariusNaranja",num,stock);
                                    bebidas.put("nombre", AQUARIUS_NARANJA);
                                    bebidas.put("cantidad", String.valueOf(num));
                                    bebidas.put("precio", String.valueOf(precio));
                                    refrescos.add(bebidas);

                                }
                            }
                            break;
                        case SEVENUP:
                            if (num != null) {
                                if (!num.equals("")) {
                                    firestoreController.actualizarRefresco(SEVENUP,"SevenUp",num,stock);
                                    bebidas.put("nombre", SEVENUP);
                                    bebidas.put("cantidad", String.valueOf(num));
                                    bebidas.put("precio", String.valueOf(precio));
                                    refrescos.add(bebidas);

                                }

                            }
                            break;
                        case FANTA_LIMON:
                            if (num != null) {
                                if (!num.equals("")) {
                                    firestoreController.actualizarRefresco(FANTA_LIMON,"FantaLimon",num,stock);
                                    bebidas.put("nombre", FANTA_LIMON);
                                    bebidas.put("cantidad", String.valueOf(num));
                                    bebidas.put("precio", String.valueOf(precio));
                                    refrescos.add(bebidas);
                                }
                            }
                            break;
                        case FANTA_NARANJA:
                            if (num != null) {
                                if (!num.equals("")) {
                                    firestoreController.actualizarRefresco(FANTA_NARANJA, "FantaNaranja", num, stock);
                                    bebidas.put("nombre", FANTA_NARANJA);
                                    bebidas.put("cantidad", String.valueOf(num));
                                    bebidas.put("precio", String.valueOf(precio));
                                    refrescos.add(bebidas);

                                }
                            }
                            break;
                    }
                    it.remove();
                }
            }

            Log.d("map", refrescos.toString());
            DynamicToast.makeSuccess(requireContext(), "Añadido", Toast.LENGTH_SHORT).show();
        });
    }


    public static List<Map<String, Object>> getRefrescos(){
        return refrescos;
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