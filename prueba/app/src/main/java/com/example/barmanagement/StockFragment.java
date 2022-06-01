package com.example.barmanagement;



import static com.example.barmanagement.utils.FirestoreFields.CATEGORIAS;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.barmanagement.adapters.StockAdapter;
import com.example.barmanagement.models.Category;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StockFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StockFragment extends Fragment {

    FirebaseFirestore db;
    Spinner familias;
    StockAdapter stockAdapter;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    List<Category> listaCategorias = new ArrayList<>();
    String nombre_familia = "";
    private static String REFRESCOS = "Refrescos";
    private static String CERVEZAS = "Cervezas";
    private static String CAFES = "Cafes";
    private static String MAS_BEBIDAS = "Más bebidas";


    public StockFragment() {  }

    public static StockFragment newInstance(String param1, String param2) {
        return new StockFragment();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_stock, container, false);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        db = FirebaseFirestore.getInstance();
        familias = (Spinner) view.findViewById(R.id.spinner);
        recyclerView = (RecyclerView) view.findViewById(R.id.listaStock);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(requireContext(), R.array.familia, android.R.layout.simple_spinner_item);
        familias.setAdapter(adapter);
        familias.setSelection(0);
        listaCategorias = obtenerRefrescos();
        spinnerSetOnItemClick();

        stockAdapter = new StockAdapter(listaCategorias, getContext());
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setAdapter(stockAdapter);
        recyclerView.setLayoutManager(layoutManager);
        stockAdapter.notifyDataSetChanged();
    }


    private void spinnerSetOnItemClick() {
        familias.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                nombre_familia = adapterView.getSelectedItem().toString();
                if (nombre_familia.equals(REFRESCOS)){
                    String nombre_coleccion = "refrescos";
                    List<Category> listaCategorias = obtenerDatos(nombre_coleccion);
                    stockAdapter.setCategories(listaCategorias);
                }
                if (nombre_familia.equals(CERVEZAS)){
                    String nombre_coleccion = "cervezas";
                    List<Category> listaCategorias = obtenerDatos(nombre_coleccion);
                    stockAdapter.setCategories(listaCategorias);
                }
                if (nombre_familia.equals(CAFES)){
                    String nombre_coleccion = "cafes";
                    List<Category> listaCategorias = obtenerDatos(nombre_coleccion);
                    stockAdapter.setCategories(listaCategorias);

                }
                if (nombre_familia.equals(MAS_BEBIDAS)){
                    String nombre_coleccion = "mas_bebidas";
                    List<Category> listaCategorias = obtenerDatos(nombre_coleccion);
                    stockAdapter.setCategories(listaCategorias);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


    @SuppressLint("NotifyDataSetChanged")
    private List<Category> obtenerRefrescos() {
        List<Category> lista_refrescos = new ArrayList<>();
        CollectionReference refrescos =  db.collection(CATEGORIAS).document("bebidas").collection("refrescos");

        refrescos.get().addOnCompleteListener(task -> {
            if(task.isSuccessful() && task.isComplete()){
                for (QueryDocumentSnapshot document: task.getResult()){
                    Category category = document.toObject(Category.class);
                    lista_refrescos.add(category);
                }
            }
            stockAdapter.notifyDataSetChanged();
        });
        return lista_refrescos;
    }

    @SuppressLint("NotifyDataSetChanged")
    private List<Category> obtenerDatos(String nombre_coleccion) {
        List<Category> lista_refrescos = new ArrayList<>();
        CollectionReference refrescos =  db.collection(CATEGORIAS).document("bebidas").collection(nombre_coleccion);

        refrescos.get().addOnCompleteListener(task -> {
            if(task.isSuccessful() && task.isComplete()){
                for (QueryDocumentSnapshot document: task.getResult()){
                    Category category = document.toObject(Category.class);
                    lista_refrescos.add(category);
                }
            }
            stockAdapter.notifyDataSetChanged();
        });
        return lista_refrescos;
    }
}