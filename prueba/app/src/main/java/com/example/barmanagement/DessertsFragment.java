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

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

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
 * Use the {@link DessertsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DessertsFragment extends Fragment {
    FloatingActionButton btnCheck;
    FirebaseFirestore db;
    ImageView arrow;
    RacionesAdapter adapter;


    public DessertsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DessertsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DessertsFragment newInstance(String param1, String param2) {

        return new DessertsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_desserts, container, false);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        db = FirebaseFirestore.getInstance();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.listaPostres);
        btnCheck = (FloatingActionButton) view.findViewById(R.id.btnCheck);
        Query query = db.collection(CATEGORIAS).document("postres_category").collection("postres");

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
                            case ENSALADA_FRUTAS:
                                if (num != null) {
                                    if (!num.equals("")) {
                                        HashMap<String, Object> refresco = new HashMap<>();
                                        refresco.put("nombre", COCA_COLA);
                                        refresco.put("cantidad", num);
                                        db.collection(COMANDA).document(numero).collection("orden").document(ENSALADA_FRUTAS).set(refresco);
                                    }

                                }
                                break;
                            case FLAN:
                                if (num != null) {
                                    if (!num.equals("")) {
                                        HashMap<String, Object> refresco = new HashMap<>();
                                        refresco.put("nombre", FLAN);
                                        refresco.put("cantidad", num);
                                        db.collection(COMANDA).document(numero).collection("orden").document(FLAN).set(refresco);
                                    }
                                }
                                break;
                            case HELADO_NATACHOC:
                                if (num != null) {
                                    if (!num.equals("")) {
                                        HashMap<String, Object> refresco = new HashMap<>();
                                   /* List<Integer> total = new ArrayList<>();
                                    //total.add(Integer.parseInt(num.toString()));

                                    int resultado=0;
                                    for (int numero : total){
                                        resultado += numero;
                                    }*/
                                        refresco.put("nombre", HELADO_NATACHOC);
                                        refresco.put("cantidad", num);
                                        db.collection(COMANDA).document(numero).collection("orden").document(HELADO_NATACHOC).set(refresco);
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
                            case NATILLAS:
                                if (num != null) {
                                    if (!num.equals("")) {
                                        HashMap<String, Object> refresco = new HashMap<>();
                                        refresco.put("nombre", NATILLAS);
                                        refresco.put("cantidad", num);
                                        db.collection(COMANDA).document(numero).collection("orden").document(NATILLAS).set(refresco);
                                    }

                                }
                                break;
                            case TARTA_QUESO:
                                if (num != null) {
                                    if (!num.equals("")) {
                                        HashMap<String, Object> refresco = new HashMap<>();
                                        refresco.put("nombre", TARTA_QUESO);
                                        refresco.put("cantidad", num);
                                        db.collection(COMANDA).document(numero).collection("orden").document(TARTA_QUESO).set(refresco);
                                    }

                                }
                                break;
                            case TARTA_ZANAHORIA:
                                if (num != null) {
                                    if (!num.equals("")) {
                                        HashMap<String, Object> refresco = new HashMap<>();
                                        refresco.put("nombre", TARTA_ZANAHORIA);
                                        refresco.put("cantidad", num);
                                        db.collection(COMANDA).document(numero).collection("orden").document(TARTA_ZANAHORIA).set(refresco);
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
        arrow.setOnClickListener(view -> {
            Navigation.findNavController(view).navigate(R.id.comandaFragment);
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