package com.example.barmanagement;

import static com.example.barmanagement.utils.FirestoreFields.AGUA;
import static com.example.barmanagement.utils.FirestoreFields.TINTO_VERANO;
import static com.example.barmanagement.utils.FirestoreFields.ZUMO_NARANJA;
import static com.example.barmanagement.utils.FirestoreFields.ZUMO_PIÑA;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barmanagement.adapters.DrinksAdapter;
import com.example.barmanagement.firestorecontroller.FirestoreController;
import com.example.barmanagement.models.Category;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


public class MoreDrinksFragment extends Fragment implements NavigationBarView.OnItemSelectedListener{
    private FirebaseFirestore db;
    DrinksAdapter adapter;
    public static final String CATEGORIAS = "CATEGORIAS";
    ImageView arrow;
    FloatingActionButton btnCheck;

    FirestoreController firestoreController;
    public MoreDrinksFragment() { }

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
        return inflater.inflate(R.layout.fragment_more_drinks, container, false);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        firestoreController = new FirestoreController(this.db);
        db = FirebaseFirestore.getInstance();

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.listaMas);
        BottomNavigationView btnNav = (BottomNavigationView) view.findViewById(R.id.bottomNavigationViewDrinks);
        btnNav.setItemIconTintList(null);
        btnCheck = (FloatingActionButton) view.findViewById(R.id.btnCheck);
        arrow = (ImageView) view.findViewById(R.id.imbArrowBack);

        Query query = db.collection(CATEGORIAS).document("bebidas").collection("mas_bebidas");
        FirestoreRecyclerOptions<Category> options = new FirestoreRecyclerOptions.Builder<Category>()
                .setQuery(query, Category.class).build();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());

        adapter = new DrinksAdapter(options, getContext());
        adapter.notifyDataSetChanged();

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        btnNav.setOnItemSelectedListener(this);

        setOnClickListenerBack();
        setOnClickListenerCheck();
    }

    private void setOnClickListenerCheck() {
        btnCheck.setOnClickListener(view -> {
            List<HashMap<String, Object>> texto = adapter.getTexto();
            Object stock;
            for (int a =0; a<texto.size();a++) {
                HashMap<String, Object> data = (HashMap<String, Object>) texto.get(a);
                Set<String> key = data.keySet();
                Iterator<String> it = key.iterator();
                stock = data.get("stock");
                while (it.hasNext()) {
                    String keyData = (String)it.next();
                    Object num = data.get(keyData);
                    switch (keyData){
                        case AGUA:
                            if(num!=null){
                                if(!num.equals("")){
                                    firestoreController.actualizarMasBebidas(AGUA,"agua", num,stock);
                                }
                            }
                            break;
                        case TINTO_VERANO:
                            if(num!=null){
                                if(!num.equals("")){
                                    firestoreController.actualizarMasBebidas(TINTO_VERANO,"tinto_verano", num,stock);
                                }
                            }
                            break;
                        case ZUMO_NARANJA:
                            if(num!=null){
                                if(!num.equals("")){
                                    firestoreController.actualizarMasBebidas(ZUMO_NARANJA,"zumo_naranja", num,stock);
                                }
                            }
                            break;
                        case ZUMO_PIÑA:
                            if(num!=null){
                                if(!num.equals("")){
                                    firestoreController.actualizarMasBebidas(ZUMO_PIÑA,"zumo_piña", num,stock);
                                }
                            }
                            break;
                    }
                    it.remove();
                }

            }
        });
    }


        private void setOnClickListenerBack() {
            arrow.setOnClickListener(view -> {
                Navigation.findNavController(view).navigate(R.id.comandaFragment);
            });
        }

        @SuppressLint("NonConstantResourceId")
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.cerveza:
                    Navigation.findNavController(requireView()).navigate(R.id.beerFragment);
                    break;
                case R.id.cafes:
                    Navigation.findNavController(requireView()).navigate(R.id.coffeeFragment);
                    break;
                case R.id.refrescos:
                    Navigation.findNavController(requireView()).navigate(R.id.drinksFragment);
                    break;
            }
            return true;
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