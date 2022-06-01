package com.example.barmanagement;

import static com.example.barmanagement.utils.FirestoreFields.EMPLOYE;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barmanagement.adapters.EmployeeAdapter;
import com.example.barmanagement.models.User;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.pranavpandey.android.dynamic.toasts.DynamicToast;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class EmpleadosFragment extends Fragment implements EmployeeAdapter.RecyclerViewClickListener{
    private FirebaseFirestore db;
    private EmployeeAdapter adapter;
    private List<User> users = new ArrayList<>();
    private EditText txtNombre;
    private Button btnBuscar;




    public EmpleadosFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static EmpleadosFragment newInstance() {
        return new EmpleadosFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_empleados, container, false);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewEmpleado);
        txtNombre = (EditText) view.findViewById(R.id.txtNombreUser);
        btnBuscar = (Button) view.findViewById(R.id.btnBuscar);
        db = FirebaseFirestore.getInstance();

        users = obtenerEmpleados();

        adapter = new EmployeeAdapter(users,getContext(),this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        adapter.notifyDataSetChanged();
        buscarEmpleado();
    }

    @SuppressLint("NotifyDataSetChanged")
    private void buscarEmpleado() {
            btnBuscar.setOnClickListener(view -> {
                String name = txtNombre.getText().toString();
                if(!name.isEmpty()){
                    DocumentReference ref =  db.collection(EMPLOYE).document(name);
                    ref.get().addOnCompleteListener(task -> {
                        if(task.isSuccessful() && task.isComplete()){
                            DocumentSnapshot doc = task.getResult();
                            if(doc.exists()){
                                User user = doc.toObject(User.class);
                                List<User> list = new ArrayList<>();
                                list.add(user);
                                Log.d("usuario", user.toString());
                                adapter.setEmpleado(list);
                                adapter.notifyDataSetChanged();
                            }else{
                                DynamicToast.makeError(requireContext(),"No se pudo encontrar este empleado", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Log.d("Error","Error: ",task.getException());
                        }
                    });
                }else{
                    adapter.setEmpleado(obtenerEmpleados());
                    //DynamicToast.makeWarning(requireContext(), "Escriba el nombre de un empleado", Toast.LENGTH_SHORT);
                }

            });

    }

    @SuppressLint("NotifyDataSetChanged")
    private List<User> obtenerEmpleados() {
        List<User> lista_empleados = new ArrayList<>();
        CollectionReference empleados =  db.collection(EMPLOYE);

        empleados.get().addOnCompleteListener(task -> {
            if(task.isSuccessful() && task.isComplete()){
                for (QueryDocumentSnapshot document: task.getResult()){
                    User user = document.toObject(User.class);
                    if(!user.getName().equals("administrador")){
                        lista_empleados.add(user);
                    }
                }
            }
            adapter.notifyDataSetChanged();
        });
        return lista_empleados;
    }


    @Override
    public void onClick(View view, int position) {
        Bundle bundle = new Bundle();
        String nombre = users.get(position).getName();
        Log.d("nombre empleados fragment ", nombre);
        bundle.putString("nombre", nombre);
        Navigation.findNavController(view).navigate(R.id.employeeDetailFragment, bundle);
    }
}