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

import com.example.barmanagement.adapters.ListCategoriesAdapter;
import com.example.barmanagement.models.CategoryTypes;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CategoriasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CategoriasFragment extends Fragment implements ListCategoriesAdapter.RecyclerViewClickListener {
    ArrayList<CategoryTypes> listaCategorias = new ArrayList<>();
    private final static String BEBIDAS = "BEBIDAS";
    private final static String TAPAS = "TAPAS";
    private final static String RACIONES = "RACIONES";
    private final static String POSTRES = "POSTRES";


    private static String CATEGORIA = "categoria";


    public CategoriasFragment() {
    }


    public static CategoriasFragment newInstance(String param1, String param2) {

        return new CategoriasFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_categorias, container, false);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listaCategorias = listaCategorias();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerCategories);
        ListCategoriesAdapter adapter = new ListCategoriesAdapter(listaCategorias, getContext(),  this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        adapter.notifyDataSetChanged();
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);

    }

    private ArrayList<CategoryTypes> listaCategorias() {
        ArrayList<CategoryTypes> lista = new ArrayList<>();
        lista.add(new CategoryTypes(R.drawable.bebidas, BEBIDAS));
        lista.add(new CategoryTypes(R.drawable.tapas, TAPAS));
        lista.add(new CategoryTypes(R.drawable.raciones, RACIONES));
        lista.add(new CategoryTypes(R.drawable.postre, POSTRES));

        return lista;
    }

    @Override
    public void onClick(View view, int position) {
        if(listaCategorias.get(position).getName().equals(BEBIDAS)) Navigation.findNavController(view).navigate(R.id.drinksFragment);
        if(listaCategorias.get(position).getName().equals(TAPAS)) Navigation.findNavController(view).navigate(R.id.tapasFragment);
        if(listaCategorias.get(position).getName().equals(RACIONES)) Navigation.findNavController(view).navigate(R.id.racionesFragment);
        if(listaCategorias.get(position).getName().equals(POSTRES)) Navigation.findNavController(view).navigate(R.id.dessertsFragment);

    }
}