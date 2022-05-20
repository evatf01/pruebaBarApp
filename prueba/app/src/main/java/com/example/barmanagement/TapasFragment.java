package com.example.barmanagement;


import static com.example.barmanagement.ComandaFragment.numero;
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
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.barmanagement.adapters.FoodAdapter;
import com.example.barmanagement.models.Category;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
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
 * Use the {@link TapasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TapasFragment extends Fragment implements FoodAdapter.RecyclerViewClickListener{

    private FirebaseFirestore db;
    FoodAdapter adapter;
    public static final String CATEGORIAS = "CATEGORIAS";
    ImageView arrow;
    List<Category> tapas = new ArrayList<>();
    FloatingActionButton btnCheck;
    public TapasFragment() {  }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TapasFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TapasFragment newInstance(String param1, String param2) {

        return new TapasFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tapas, container, false);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.listaTapas);
        db = FirebaseFirestore.getInstance();
        btnCheck = (FloatingActionButton) view.findViewById(R.id.btnCheck);
      /*  Query query = db.collection(CATEGORIAS).document("tapas_category").collection("tapas");



        FirestoreRecyclerOptions<Category> options = new FirestoreRecyclerOptions.Builder<Category>()
                .setQuery(query, Category.class).build();*/

        arrow = (ImageView) view.findViewById(R.id.imbArrowBack);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        tapas = obtenerDatos();

        adapter = new FoodAdapter( tapas,getContext(),this);
        adapter.notifyDataSetChanged();


        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);


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

                        System.out.println("Key: "+keyData +" & Data: "+num);
                        switch (keyData){
                            case ALPUJARREÑO:
                                if(num!=null){
                                    HashMap<String,Object> tapas = new HashMap<>();
                                    tapas.put("nombre", ALPUJARREÑO);
                                    tapas.put("cantidad",num);
                                    db.collection(COMANDA).document(numero).collection("orden").document(ALPUJARREÑO).set(tapas);
                                }
                                break;
                            case ENSALADA_CASA:
                                if(num!=null){
                                    HashMap<String,Object> refresco = new HashMap<>();
                                    refresco.put("nombre", ENSALADA_CASA);
                                    refresco.put("cantidad",num);
                                    db.collection(COMANDA).document(numero).collection("orden").document(ENSALADA_CASA).set(refresco);
                                }
                                break;
                            case ENSALADA_CESAR:
                                if(num!=null){
                                    HashMap<String,Object> refresco = new HashMap<>();
                                    refresco.put("nombre", ENSALADA_CESAR);
                                    refresco.put("cantidad",num);
                                    db.collection(COMANDA).document(numero).collection("orden").document(ENSALADA_CESAR).set(refresco);
                                }
                                break;
                            case TAPA_CALAMARES:
                                if(num!=null){
                                    HashMap<String,Object> refresco = new HashMap<>();
                                    refresco.put("nombre", TAPA_CALAMARES);
                                    refresco.put("cantidad",num);
                                    db.collection(COMANDA).document(numero).collection("orden").document(TAPA_CALAMARES).set(refresco);
                                }
                                break;
                            case TAPA_CARNE_SALSA:
                                if(num!=null){
                                    HashMap<String,Object> refresco = new HashMap<>();
                                    refresco.put("nombre", TAPA_CARNE_SALSA);
                                    refresco.put("cantidad",num);
                                    db.collection(COMANDA).document(numero).collection("orden").document(TAPA_CARNE_SALSA).set(refresco);
                                }
                                break;
                            case FAJITA_POLLO:
                                if(num!=null){
                                    HashMap<String,Object> refresco = new HashMap<>();
                                    refresco.put("nombre", FAJITA_POLLO);
                                    refresco.put("cantidad",num);
                                    db.collection(COMANDA).document(numero).collection("orden").document(FAJITA_POLLO).set(refresco);
                                }
                                break;
                            case FAJITA_VERDURAS:
                                if(num!=null){
                                    HashMap<String,Object> refresco = new HashMap<>();
                                    refresco.put("nombre", FAJITA_VERDURAS);
                                    refresco.put("cantidad",num);
                                    db.collection(COMANDA).document(numero).collection("orden").document(FAJITA_VERDURAS).set(refresco);
                                }
                                break;
                            case LOMO_PIMIENTA:
                                if(num!=null){
                                    HashMap<String,Object> refresco = new HashMap<>();
                                    refresco.put("nombre", LOMO_PIMIENTA);
                                    refresco.put("cantidad",num);
                                    db.collection(COMANDA).document(numero).collection("orden").document(LOMO_PIMIENTA).set(refresco);
                                }
                                break;
                            case PATATAS_HUEVO:
                                if(num!=null){
                                    HashMap<String,Object> refresco = new HashMap<>();
                                    refresco.put("nombre", PATATAS_HUEVO);
                                    refresco.put("cantidad",num);
                                    db.collection(COMANDA).document(numero).collection("orden").document(PATATAS_HUEVO).set(refresco);
                                }
                                break;
                            case ENSALADILLA_RUSA:
                                if(num!=null){
                                    HashMap<String,Object> refresco = new HashMap<>();
                                    refresco.put("nombre", ENSALADILLA_RUSA);
                                    refresco.put("cantidad",num);
                                    db.collection(COMANDA).document(numero).collection("orden").document(ENSALADILLA_RUSA).set(refresco);
                                }
                                break;

                        }
                        it.remove(); // avoids a ConcurrentModificationException
                    }

                }
                //Log.d("texto", texto.toString());
            }
        });
    }


    @SuppressLint("NotifyDataSetChanged")
    private List<Category> obtenerDatos()  {
        List<Category> lista_tapas = new ArrayList<>();
        CollectionReference tapas =  db.collection(CATEGORIAS).document("tapas_category").collection("tapas");

        tapas.get().addOnCompleteListener(task -> {
            if(task.isSuccessful() && task.isComplete()){
                for (QueryDocumentSnapshot document: task.getResult()){
                    Category category = document.toObject(Category.class);
                    lista_tapas.add(category);

                }
            }


            adapter.notifyDataSetChanged();
        });

        return lista_tapas;
    }

    private void setOnClickListenerBack() {
        arrow.setOnClickListener(view -> {
                Navigation.findNavController(view).navigate(R.id.comandaFragment);
        });
    }

    @Override
    public void onClick(View view, int position) {

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

}