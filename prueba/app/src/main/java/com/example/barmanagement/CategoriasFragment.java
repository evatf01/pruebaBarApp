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
import com.example.barmanagement.models.Category;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CategoriasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CategoriasFragment extends Fragment implements ListCategoriesAdapter.OnCategoryListener {
    ArrayList<Category> listaCategorias = new ArrayList<>();
    private final static String BEBIDAS = "BEBIDAS";

    private Fragment fragment;

    private static String CATEGORIA= "caegoria";


    public CategoriasFragment() {}


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
        ListCategoriesAdapter adapter = new ListCategoriesAdapter(listaCategorias, getContext(), this );
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        adapter.notifyDataSetChanged();
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);

    }

    private ArrayList<Category> listaCategorias(){
        ArrayList<Category> lista = new ArrayList<>();
        lista.add(new Category(R.drawable.bebidas,"BEBIDAS"));
        lista.add(new Category(R.drawable.tapas,"TAPAS"));
        lista.add(new Category(R.drawable.raciones,"RACIONES"));
        lista.add(new Category(R.drawable.postre,"POSTRES"));

        return lista;
    }

    @Override
    public void onClickListener(View view, int position) {
        Category category = listaCategorias.get(position);
        if(category.getNombre().equals("BEBIDAS")) Navigation.findNavController(view).navigate(R.id.refrescosFragment);

    }
}