package com.example.barmanagement;

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
import android.widget.AbsListView;
import android.widget.ImageView;

import com.example.barmanagement.adapters.DrinksAdapter;
import com.example.barmanagement.adapters.ListCategoriesAdapter;
import com.example.barmanagement.adapters.ListTablesAdapter;
import com.example.barmanagement.models.Category;
import com.example.barmanagement.models.Tables;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DrinksFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DrinksFragment extends Fragment {
    private FirebaseFirestore db;
    DrinksAdapter adapter;
    private static final String CATEGORIAS = "CATEGORIAS";
    ImageView arrow;


    public DrinksFragment() {
        // Required empty public constructor
    }


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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_refrescos, container, false);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        db =  FirebaseFirestore.getInstance();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.listaRefrescos);
        arrow = (ImageView) view.findViewById(R.id.imbArrowBack);
        Query query = db.collection(CATEGORIAS).document("bebidas").collection("refrescos");
        Log.d("query", query.toString());
        FirestoreRecyclerOptions<Category> options = new FirestoreRecyclerOptions.Builder<Category>()
                .setQuery(query, Category.class).build();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());

        adapter = new DrinksAdapter( options,getContext());
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