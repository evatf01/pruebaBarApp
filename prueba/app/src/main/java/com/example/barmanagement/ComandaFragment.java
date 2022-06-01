package com.example.barmanagement;

import static com.example.barmanagement.utils.FirestoreFields.AQUARIUS_LIMON;
import static com.example.barmanagement.utils.FirestoreFields.AQUARIUS_NARANJA;
import static com.example.barmanagement.utils.FirestoreFields.BURGUER_POLLO;
import static com.example.barmanagement.utils.FirestoreFields.CATEGORIAS;
import static com.example.barmanagement.utils.FirestoreFields.COCA_COLA;
import static com.example.barmanagement.utils.FirestoreFields.COCA_COLA_ZERO;
import static com.example.barmanagement.utils.FirestoreFields.COMANDA;
import static com.example.barmanagement.utils.FirestoreFields.FANTA_LIMON;
import static com.example.barmanagement.utils.FirestoreFields.FANTA_NARANJA;
import static com.example.barmanagement.utils.FirestoreFields.SEVENUP;

import android.annotation.SuppressLint;
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
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bumptech.glide.signature.ObjectKey;
import com.example.barmanagement.adapters.ComandaAdapter;

import com.example.barmanagement.models.Comanda;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ComandaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ComandaFragment extends Fragment implements Serializable {
    private RecyclerView recyclerView;
    private ComandaAdapter adapter;
    private ImageButton btnAdd;
    public static String numero;
    private FirebaseFirestore db;
    private Button btnFactura;

    public ComandaFragment() {}
    public static ComandaFragment newInstance() {
        return new ComandaFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_comanda, container, false);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments()!= null) {
            numero = getArguments().getString("numero");
        }
        recyclerView = (RecyclerView) view.findViewById(R.id.listaComanda);
        btnAdd= (ImageButton) view.findViewById(R.id.btnAdd);
        btnFactura = (Button) view.findViewById(R.id.btnFactura);



        TextView txtNum = (TextView) view.findViewById(R.id.txtNumMesa);
        txtNum.setText(numero);

        db = FirebaseFirestore.getInstance();
        Query query = db.collection(COMANDA).document(numero).collection("orden");

        FirestoreRecyclerOptions<Comanda> options = new FirestoreRecyclerOptions.Builder<Comanda>()
                .setQuery(query, Comanda.class).build();

        adapter = new ComandaAdapter(options,getContext());

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        setOnClickListenerCategory();

        new ItemTouchHelper(itemTouchHelper).attachToRecyclerView(recyclerView);

        adapter.notifyDataSetChanged();
        obtenerDatos();
        generarFactura();

    }

    private void generarFactura() {
        btnFactura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.receiptFragment);
            }
        });
    }

    @SuppressLint("NotifyDataSetChanged")
    private void obtenerDatos() {
        List<Comanda> lista_refrescos = new ArrayList<>();
        CollectionReference refrescos =  db.collection(COMANDA).document(numero).collection("orden");

        refrescos.get().addOnCompleteListener(task -> {
            if(task.isSuccessful() && task.isComplete()){
                for (QueryDocumentSnapshot document: task.getResult()){
                    Comanda category = document.toObject(Comanda.class);
                    lista_refrescos.add(category);

                }
            }
            adapter.notifyDataSetChanged();
        }).addOnFailureListener(e -> Log.d("error", e.toString()));


    }

    private void setOnClickListenerCategory() {
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.categoriasFragment);
            }
        });
    }

    ItemTouchHelper.SimpleCallback itemTouchHelper = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @SuppressLint("NotifyDataSetChanged")
        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            adapter.deleteComanda(viewHolder.getBindingAdapterPosition());
            DocumentSnapshot nombre = adapter.getName(viewHolder.getBindingAdapterPosition());
            String nom = nombre.getString("nombre");
            String cantidad = nombre.getString("cantidad");
            actualizarStock(nom,cantidad);
        }
    };

    private void actualizarStock(String nombre, String cantidad) {
        if(nombre.equals(COCA_COLA)){
            actializarStockBebidas(cantidad,"CocaCola");
        }
        if(nombre.equals(COCA_COLA_ZERO)){
            actializarStockBebidas(cantidad,"CocaColaZero");
        }
          if(nombre.equals(AQUARIUS_LIMON)) {
              actializarStockBebidas(cantidad,"AquariusLimon");
          }
          if(nombre.equals(AQUARIUS_NARANJA)) {
              actializarStockBebidas(cantidad,"AquariusNaranja");

          }
          if(nombre.equals(SEVENUP)){
              actializarStockBebidas(cantidad,"SevenUp");

          }
        if(nombre.equals(FANTA_LIMON)){
            actializarStockBebidas(cantidad,"FantaLimon");

        }
        if(nombre.equals(FANTA_NARANJA)){
           actializarStockBebidas(cantidad,"FantaNaranja");
        }

    }
    private void actializarStockBebidas(String cantidad, String id){
        HashMap<String, Object> refresco = new HashMap<>();
        int stock_add= Integer.parseInt(cantidad);
        final int[] stock = {0};
        final boolean[] harta = {false};
        AtomicInteger stock_res= new AtomicInteger();
        DocumentReference ref = db.collection(CATEGORIAS).document("bebidas").collection("refrescos").document(id);
        ref.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                if(value.exists() && !harta[0]){
                    stock[0] = Integer.parseInt(value.get("stock").toString());
                    stock_res.set(stock[0] + stock_add);
                    refresco.put("stock", String.valueOf(stock_res.get()));
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    db.collection(CATEGORIAS).document("bebidas").collection("refrescos").document(id).update(refresco);
                    harta[0] = true;
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
}