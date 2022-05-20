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
 * Use the {@link CoffeeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CoffeeFragment extends Fragment implements NavigationBarView.OnItemSelectedListener, DrinksAdapter.RecyclerViewClickListener {
    private FirebaseFirestore db;
    DrinksAdapter adapter;
    ImageView arrow;
    List<Category> cafes = new ArrayList<>();
    FloatingActionButton btnCheck;


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
        btnNav.setItemIconTintList(null);
        btnCheck = (FloatingActionButton) view.findViewById(R.id.btnCheck);
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
        setOnClickListenerCheck();

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
                        switch (keyData){
                            case CAFE_CORTADO:
                                if(num!=null){
                                    HashMap<String,Object> refresco = new HashMap<>();
                                    refresco.put("nombre", CAFE_CORTADO);
                                    refresco.put("cantidad",num);
                                    db.collection(COMANDA).document(numero).collection("orden").document(CAFE_CORTADO).set(refresco);
                                }
                                break;
                            case CAFE_DESCAFEINADO:
                                if(num!=null){
                                    HashMap<String,Object> refresco = new HashMap<>();
                                    refresco.put("nombre", COCA_COLA_ZERO);
                                    refresco.put("cantidad",num);
                                    db.collection(COMANDA).document(numero).collection("orden").document(CAFE_DESCAFEINADO).set(refresco);
                                }
                                break;
                            case CAFE_LECHE:
                                if(num!=null){
                                    HashMap<String,Object> refresco = new HashMap<>();
                                   /* List<Integer> total = new ArrayList<>();
                                    //total.add(Integer.parseInt(num.toString()));

                                    int resultado=0;
                                    for (int numero : total){
                                        resultado += numero;
                                    }*/
                                    refresco.put("nombre", CAFE_LECHE);
                                    refresco.put("cantidad",num);
                                    db.collection(COMANDA).document(numero).collection("orden").document(CAFE_LECHE).set(refresco);
                                }
                                break;
                            case CAFE_SOLO:
                                if(num!=null){
                                    HashMap<String,Object> refresco = new HashMap<>();
                                    refresco.put("nombre", CAFE_SOLO);
                                    refresco.put("cantidad",num);
                                    db.collection(COMANDA).document(numero).collection("orden").document(CAFE_SOLO).set(refresco);
                                }
                                break;
                            case CAPUCCINO:
                                if(num!=null){
                                    HashMap<String,Object> refresco = new HashMap<>();
                                    refresco.put("nombre", CAPUCCINO);
                                    refresco.put("cantidad",num);
                                    db.collection(COMANDA).document(numero).collection("orden").document(CAPUCCINO).set(refresco);
                                }
                                break;
                            case MANCHADA:
                                if(num!=null){
                                    HashMap<String,Object> refresco = new HashMap<>();
                                    refresco.put("nombre", MANCHADA);
                                    refresco.put("cantidad",num);
                                    db.collection(COMANDA).document(numero).collection("orden").document(MANCHADA).set(refresco);
                                }
                                break;
                            case FANTA_NARANJA:
                                if(num!=null){
                                    HashMap<String,Object> refresco = new HashMap<>();
                                    refresco.put("nombre", FANTA_NARANJA);
                                    refresco.put("cantidad",num);
                                    db.collection(COMANDA).document(numero).collection("orden").document(FANTA_NARANJA).set(refresco);
                                }
                                break;

                        }
                        it.remove();
                    }

                }
            }
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