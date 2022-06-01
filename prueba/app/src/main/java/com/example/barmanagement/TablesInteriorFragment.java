package com.example.barmanagement;

import static com.example.barmanagement.utils.FirestoreFields.EMPLOYE;
import static com.example.barmanagement.utils.FirestoreFields.PASSWORD;
import static com.firebase.ui.auth.AuthUI.getApplicationContext;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.icu.text.RelativeDateTimeFormatter;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.barmanagement.adapters.ListTablesAdapter;
import com.example.barmanagement.controllers.SqliteController;
import com.example.barmanagement.models.Tables;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.pranavpandey.android.dynamic.toasts.DynamicToast;


public class TablesInteriorFragment extends Fragment {
    private View txtExterior;
    private FirebaseFirestore db;
    private ListTablesAdapter adapter;
    private ImageView admin;
    private SqliteController sqliteController;

    private static final String ZONA = "INTERIOR";

    public static TablesInteriorFragment newInstance(String param1, String param2) { return new TablesInteriorFragment(); }

    public TablesInteriorFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tables_interior, container, false);
    }


    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        db =  FirebaseFirestore.getInstance();
        sqliteController = new SqliteController();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.listTablesInterior);
        admin = (ImageView) view.findViewById(R.id.admin);

        Query query = db.collection(ZONA);
        FirestoreRecyclerOptions<Tables> options = new FirestoreRecyclerOptions.Builder<Tables>()
                .setQuery(query, Tables.class).build();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());

        adapter = new ListTablesAdapter( options,getContext());
        adapter.notifyDataSetChanged();

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

         txtExterior = (View)view.findViewById(R.id.exterior);
         setOnClickListenerComanda(view);
         setOnClickListenerExterior();
         setOnClickListenerAdmin();
         sqliteController.createTables(requireContext());
    }

    private void setOnClickListenerComanda(View view) {
        adapter.setOnClickListener(new ListTablesAdapter.OnTablesListener() {
            @Override
            public void onTableClick(DocumentSnapshot documentSnapshot, int position) {
                Bundle bundle = new Bundle();
                String num =  documentSnapshot.getString("num");
                bundle.putString("numero", num);
                Navigation.findNavController(view).navigate(R.id.comandaFragment, bundle);
            }
        });
    }

    private void setOnClickListenerAdmin() {
        admin.setOnClickListener(view -> {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(requireContext());
            alertDialog.setTitle("Administrador");
            alertDialog.setMessage("Escriba el codigo");

            final EditText input = new EditText(requireContext());
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT);
            input.setLayoutParams(lp);
            alertDialog.setView(input);
            alertDialog.setPositiveButton("OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            DocumentReference userRef = db.collection(EMPLOYE).document("admin");
                            userRef.get().addOnCompleteListener(task -> {
                                if(task.isSuccessful()){
                                    DocumentSnapshot doc = task.getResult();
                                    if(doc.exists()){
                                        String codigo = doc.getString("codigo");
                                        assert codigo != null;
                                        if(codigo.equals(input.getText().toString())){
                                            Navigation.findNavController(requireView()).navigate(R.id.adminMenuFragment);
                                            DynamicToast.makeSuccess(requireContext(),"Codigo correcto", Toast.LENGTH_SHORT).show();
                                        }
                                        else DynamicToast.makeError(requireContext(),"Codigo incorrecto", Toast.LENGTH_SHORT).show();
                                    }
                                }else{
                                    Log.d("Error","Error: ",task.getException());
                                }
                            });

                            String password = input.getText().toString();

                        }
                    });

            alertDialog.setNegativeButton("CANCELAR",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

            alertDialog.show();

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


    private void setOnClickListenerExterior() {
        txtExterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.tablesExteriorFragment);
            }
        });
    }
}