package com.example.barmanagement;

import static com.example.barmanagement.utils.FirestoreFields.CAFE_CORTADO;
import static com.example.barmanagement.utils.FirestoreFields.CAFE_DESCAFEINADO;
import static com.example.barmanagement.utils.FirestoreFields.CAFE_LECHE;
import static com.example.barmanagement.utils.FirestoreFields.CAFE_SOLO;
import static com.example.barmanagement.utils.FirestoreFields.CAPUCCINO;
import static com.example.barmanagement.utils.FirestoreFields.CATEGORIAS;
import static com.example.barmanagement.utils.FirestoreFields.MANCHADA;

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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CoffeeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CoffeeFragment extends Fragment implements NavigationBarView.OnItemSelectedListener{
    private FirebaseFirestore db;
    DrinksAdapter adapter;
    ImageView arrow;
    FloatingActionButton btnCheck;
    FirestoreController firestoreController;


    public CoffeeFragment() {}

    public static CoffeeFragment newInstance() {
        return new CoffeeFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_coffee, container, false);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        firestoreController = new FirestoreController(this.db);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.listaCafes);
        BottomNavigationView btnNav = (BottomNavigationView) view.findViewById(R.id.bottomNavigationViewDrinks);
        db = FirebaseFirestore.getInstance();
        btnNav.setItemIconTintList(null);
        btnCheck = (FloatingActionButton) view.findViewById(R.id.btnCheck);

        Query query = db.collection(CATEGORIAS).document("bebidas").collection("cafes");
        arrow = (ImageView) view.findViewById(R.id.imbArrowBack);
       FirestoreRecyclerOptions<Category> options = new FirestoreRecyclerOptions.Builder<Category>()
                .setQuery(query, Category.class).build();

       adapter = new DrinksAdapter(options,getContext());
       adapter.notifyDataSetChanged();
       LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
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
            for (int a =0; a<texto.size();a++)
            {
                HashMap<String, Object> data = (HashMap<String, Object>) texto.get(a);
                Set<String> key = data.keySet();
                Iterator<String> it = key.iterator();
                stock = data.get("stock");
                while (it.hasNext()) {
                    String keyData = (String)it.next();
                    Object num = data.get(keyData);
                    switch (keyData){
                        case CAFE_CORTADO:
                            if(num!=null){
                                if(!num.equals("")){
                                    firestoreController.actualizarCafes(CAFE_CORTADO,"cafe_cortado", num,stock);
                                }
                            }
                            break;
                        case CAFE_DESCAFEINADO:
                            if(num!=null){
                                if(!num.equals("")){
                                    firestoreController.actualizarCafes(CAFE_DESCAFEINADO,"cafe_descafeinado", num,stock);
                                }
                            }
                            break;
                        case CAFE_LECHE:
                            if(num!=null){
                                if(!num.equals("")){
                                    firestoreController.actualizarCafes(CAFE_LECHE,"cafe_leche", num,stock);
                                }
                            }
                            break;
                        case CAFE_SOLO:
                            if(num!=null){
                                if(!num.equals("")){
                                    firestoreController.actualizarCafes(CAFE_SOLO,"cafe_solo", num,stock);
                                }
                            }
                            break;
                        case CAPUCCINO:
                            if(num!=null){
                                if(!num.equals("")){
                                    firestoreController.actualizarCafes(CAPUCCINO,"capuccino", num,stock);
                                }
                            }
                            break;
                        case MANCHADA:
                            if(num!=null){
                                if(!num.equals("")){
                                    firestoreController.actualizarCafes(MANCHADA,"machada", num,stock);
                                }
                            }
                            break;
                    }
                    it.remove();
                }

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

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.cerveza:
                Navigation.findNavController(requireView()).navigate(R.id.beerFragment);
                break;
            case R.id.refrescos:
                Navigation.findNavController(requireView()).navigate(R.id.drinksFragment);
                break;
            case R.id.more:
                Navigation.findNavController(requireView()).navigate(R.id.moreDrinksFragment);
                break;

        }
        return true;
    }

    private void setOnClickListenerBack() {
        arrow.setOnClickListener(view -> {
            Navigation.findNavController(view).navigate(R.id.comandaFragment);
        });
    }
}