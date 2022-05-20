package com.example.barmanagement;

import static com.example.barmanagement.utils.FirestoreFields.COMANDA;

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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.barmanagement.adapters.ComandaAdapter;

import com.example.barmanagement.models.Category;
import com.example.barmanagement.models.Comanda;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.net.CookieManager;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ComandaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ComandaFragment extends Fragment {
    List<Comanda> listaOrdenes = new ArrayList<>();
    private RecyclerView recyclerView;
    private ComandaAdapter adapter;
    private EditText txtNumComensales;
    private ImageButton btnAdd;
    private ImageView btnArrow;
    public static String numero;
    private FirebaseFirestore db;


    public ComandaFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ComandaFragment newInstance(String param1, String param2) {

        return new ComandaFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //todo para que cuando salga el teclado para escribir, el layout no se mueva

        return inflater.inflate(R.layout.fragment_comanda, container, false);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        db = FirebaseFirestore.getInstance();
        if (getArguments()!= null) numero = getArguments().getString("numero");
        db = FirebaseFirestore.getInstance();
        recyclerView = (RecyclerView) view.findViewById(R.id.listaComanda);

        btnAdd= (ImageButton) view.findViewById(R.id.btnAdd);
        btnArrow = (ImageView) view.findViewById(R.id.imgArrow);
        TextView txtNum = (TextView) view.findViewById(R.id.txtNumMesa);
        txtNum.setText(numero);

        //listaOrdenes = obtenerDatos();


        Query query = db.collection(COMANDA).document(numero).collection("orden");

        FirestoreRecyclerOptions<Comanda> options = new FirestoreRecyclerOptions.Builder<Comanda>()
                .setQuery(query, Comanda.class).build();

        adapter = new ComandaAdapter(options,getContext());


        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        setOnClickListenerCategory();
        setOnClickListenerBack();

        new ItemTouchHelper(itemTouchHelper).attachToRecyclerView(recyclerView);

        adapter.notifyDataSetChanged();

    }

    @SuppressLint("NotifyDataSetChanged")
    private List<Comanda> obtenerDatos() {
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
        Log.d("refrescos", lista_refrescos.toString());
        return lista_refrescos;
    }

    private void setOnClickListenerBack() {
        btnArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.tablesInteriorFragment);
            }
        });
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

        }
    };

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