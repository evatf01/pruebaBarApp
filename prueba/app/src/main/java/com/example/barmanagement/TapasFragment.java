package com.example.barmanagement;


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
import com.example.barmanagement.models.Category;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TapasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TapasFragment extends Fragment {

    private FirebaseFirestore db;
    FoodAdapter adapter;
    public static final String CATEGORIAS = "CATEGORIAS";
    ImageView arrow;

    public TapasFragment() {
        // Required empty public constructor
    }

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
        Query query = db.collection(CATEGORIAS).document("tapas_category").collection("tapas");


        arrow = (ImageView) view.findViewById(R.id.imbArrowBack);
        FirestoreRecyclerOptions<Category> options = new FirestoreRecyclerOptions.Builder<Category>()
                .setQuery(query, Category.class).build();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());

        adapter = new FoodAdapter( options,getContext());
        adapter.notifyDataSetChanged();

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);


        setOnClickListenerBack();

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