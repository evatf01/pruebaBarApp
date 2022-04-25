package com.example.prueba;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

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
    ListCategoriesAdapter adapter;
    private ListView listaCategorias;
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

        View view = inflater.inflate(R.layout.fragment_categorias, container, false);
        listaCategorias = (ListView) view.findViewById(R.id.listaCategorias);
        adapter = new ListCategoriesAdapter(listaCategorias(), getContext());

        listaCategorias.setAdapter(adapter);
        listaCategorias.setClickable(true);
        setOnClickListener();

        return view;
    }



    private void setOnClickListener() {
        listaCategorias.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Category category = adapter.getItem(i);
                fragment = new BebidasFragment();
                Bundle bundle = new Bundle();
                bundle.putString(CATEGORIA, category.getNombre());
                fragment.setArguments(bundle);

                requireActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.contenedorCategoria, fragment)
                        .addToBackStack(CategoriasFragment.class.getName())
                        .commit();
            }
        });
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