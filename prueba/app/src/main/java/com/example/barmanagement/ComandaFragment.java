package com.example.barmanagement;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.barmanagement.adapters.ComandaAdapter;
import com.example.barmanagement.models.Category;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ComandaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ComandaFragment extends Fragment {
    ArrayList<Category> listaOrdenes = new ArrayList<>();
    private RecyclerView recyclerView;
    private ComandaAdapter adapter;
    private EditText txtNumComensales;
    private ImageButton btnAdd;
    private ImageView btnArrow;


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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        txtNumComensales  = (EditText) view.findViewById(R.id.txtNumComensales);
        btnAdd= (ImageButton) view.findViewById(R.id.btnAdd);
        btnArrow = (ImageView) view.findViewById(R.id.imgArrow);

        setOnClickListenerCategory();
        setOnClickListenerBack();
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
}