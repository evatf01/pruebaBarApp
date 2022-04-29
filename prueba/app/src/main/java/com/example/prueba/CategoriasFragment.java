package com.example.prueba;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.prueba.adapters.ListCategoriesAdapter;
import com.example.prueba.models.Category;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CategoriasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CategoriasFragment extends Fragment {
    ArrayList<Category> listaCategorias = new ArrayList<>();
    private RecyclerView recyclerView;
    private ListCategoriesAdapter adapter;
    private ListCategoriesAdapter.RecyclerViewClickListener listener;
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
        listaCategorias = listaCategorias();
        View view = inflater.inflate(R.layout.fragment_categorias, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerCategories);
        adapter = new ListCategoriesAdapter(listaCategorias, getContext(), listener);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        setOnClickListener();

        return view;
    }



    private void setOnClickListener() {
        listener= new ListCategoriesAdapter.RecyclerViewClickListener() { //todo hago referencia a la interfaz
            @Override
            /**
             * Lanzo la actividad con la lista de canciones de ese album que clicko, pasandole como dato
             * el nombre del album y el artista  para hacer la peticion a la API
             **/
            public void onClick(View view, int position) {
                String nombre = listaCategorias.get(position).getNombre();
                if (nombre.equals(BEBIDAS)){
                    Navigation.findNavController(view).navigate(R.id.refrescosFragment);
                }


            }
        };
    }

    private ArrayList<Category> listaCategorias(){
        ArrayList<Category> lista = new ArrayList<>();
        lista.add(new Category(R.drawable.bebidas,"BEBIDAS"));
        lista.add(new Category(R.drawable.tapas,"TAPAS"));
        lista.add(new Category(R.drawable.raciones,"RACIONES"));
        lista.add(new Category(R.drawable.postre,"POSTRES"));

        return lista;
    }
}