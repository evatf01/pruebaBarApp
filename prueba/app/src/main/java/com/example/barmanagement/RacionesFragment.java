package com.example.barmanagement;

import static com.example.barmanagement.ComandaFragment.numero;
import static com.example.barmanagement.TapasFragment.CATEGORIAS;
import static com.example.barmanagement.utils.FirestoreFields.*;

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
import android.widget.ImageView;

import com.example.barmanagement.adapters.FoodAdapter;
import com.example.barmanagement.adapters.RacionesAdapter;
import com.example.barmanagement.models.Category;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RacionesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RacionesFragment extends Fragment {
    FirebaseFirestore db;
    RacionesAdapter adapter;
    public static final String CATEGORIAS = "CATEGORIAS";
    ImageView arrow;
    FloatingActionButton btnCheck;

    public RacionesFragment() { }

    public static RacionesFragment newInstance(String param1, String param2) {

        return new RacionesFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_raciones, container, false);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.listaRaciones);
        btnCheck = (FloatingActionButton) view.findViewById(R.id.btnCheck);
        db = FirebaseFirestore.getInstance();
        Query query = db.collection(CATEGORIAS).document("raciones_category").collection("raciones");

        arrow = (ImageView) view.findViewById(R.id.imbArrowBack);

        FirestoreRecyclerOptions<Category> options = new FirestoreRecyclerOptions.Builder<Category>()
                .setQuery(query, Category.class).build();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());

        adapter = new RacionesAdapter( options,getContext());
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
                            case BACALAO:
                                if(num!=null){
                                    if(!num.equals("")){
                                        HashMap<String,Object> tapas = new HashMap<>();
                                        tapas.put("nombre", BACALAO);
                                        tapas.put("cantidad",num);
                                        db.collection(COMANDA).document(numero).collection("orden").document(BACALAO).set(tapas);
                                    }
                                }
                                break;
                            case LOMO_COMPLETO:
                                if(num!=null){
                                    if(!num.equals("")){
                                        HashMap<String,Object> refresco = new HashMap<>();
                                        refresco.put("nombre", LOMO_COMPLETO);
                                        refresco.put("cantidad",num);
                                        db.collection(COMANDA).document(numero).collection("orden").document(LOMO_COMPLETO).set(refresco);
                                    }
                                }
                                break;
                            case LOMO_NORMAL:
                                if(num!=null){
                                    if(!num.equals("")){
                                        HashMap<String,Object> refresco = new HashMap<>();
                                        refresco.put("nombre", LOMO_NORMAL);
                                        refresco.put("cantidad",num);
                                        db.collection(COMANDA).document(numero).collection("orden").document(LOMO_NORMAL).set(refresco);
                                    }
                                }
                                break;
                            case CALAMARES:
                                if(num!=null){
                                    if(!num.equals("")){
                                        HashMap<String,Object> refresco = new HashMap<>();
                                        refresco.put("nombre", CALAMARES);
                                        refresco.put("cantidad",num);
                                        db.collection(COMANDA).document(numero).collection("orden").document(CALAMARES).set(refresco);
                                    }
                                }
                                break;
                            case CARNE_SALSA:
                                if(num!=null){
                                    if(!num.equals("")){
                                        HashMap<String,Object> refresco = new HashMap<>();
                                        refresco.put("nombre", CARNE_SALSA);
                                        refresco.put("cantidad",num);
                                        db.collection(COMANDA).document(numero).collection("orden").document(CARNE_SALSA).set(refresco);
                                    }
                                }
                                break;
                            case GAMBAS_PILPIL:
                                if(num!=null){
                                    if(!num.equals("")){
                                        HashMap<String,Object> refresco = new HashMap<>();
                                        refresco.put("nombre", GAMBAS_PILPIL);
                                        refresco.put("cantidad",num);
                                        db.collection(COMANDA).document(numero).collection("orden").document(GAMBAS_PILPIL).set(refresco);
                                    }
                                }
                                break;
                            case BURGUER_POLLO:
                                if(num!=null){
                                    if(!num.equals("")){
                                        HashMap<String,Object> refresco = new HashMap<>();
                                        refresco.put("nombre", BURGUER_POLLO);
                                        refresco.put("cantidad",num);
                                        db.collection(COMANDA).document(numero).collection("orden").document(BURGUER_POLLO).set(refresco);
                                    }
                                }
                                break;
                            case BURGUER_TERNERA:
                                if(num!=null){
                                    if(!num.equals("")){
                                        HashMap<String,Object> refresco = new HashMap<>();
                                        refresco.put("nombre", BURGUER_TERNERA);
                                        refresco.put("cantidad",num);
                                        db.collection(COMANDA).document(numero).collection("orden").document(BURGUER_TERNERA).set(refresco);
                                    }
                                }
                                break;
                            case LOMO_AJOS:
                                if(num!=null){
                                    if(!num.equals("")){
                                        HashMap<String,Object> refresco = new HashMap<>();
                                        refresco.put("nombre", LOMO_AJOS);
                                        refresco.put("cantidad",num);
                                        db.collection(COMANDA).document(numero).collection("orden").document(LOMO_AJOS).set(refresco);
                                    }
                                }
                                break;
                            case PAELLA:
                                if(num!=null){
                                    if(!num.equals("")){
                                        HashMap<String,Object> refresco = new HashMap<>();
                                        refresco.put("nombre", PAELLA);
                                        refresco.put("cantidad",num);
                                        db.collection(COMANDA).document(numero).collection("orden").document(PAELLA).set(refresco);
                                    }
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


    private void setOnClickListenerBack() {
        arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.comandaFragment);
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
}