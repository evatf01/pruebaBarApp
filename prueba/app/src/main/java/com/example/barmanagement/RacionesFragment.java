package com.example.barmanagement;

import static com.example.barmanagement.TapasFragment.CATEGORIAS;

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
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

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
        db = FirebaseFirestore.getInstance();
        Query query = db.collection(CATEGORIAS).document("tapas_category").collection("raciones");

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