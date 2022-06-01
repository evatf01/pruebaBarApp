package com.example.barmanagement;

import static com.example.barmanagement.utils.FirestoreFields.DNI;
import static com.example.barmanagement.utils.FirestoreFields.EMAIL;
import static com.example.barmanagement.utils.FirestoreFields.EMPLOYE;
import static com.example.barmanagement.utils.FirestoreFields.NAME;
import static com.example.barmanagement.utils.FirestoreFields.PHONE;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.pranavpandey.android.dynamic.toasts.DynamicToast;

import java.util.HashMap;
import java.util.Map;


public class EmployeeDetailFragment extends Fragment {
    String nombre="";
    EditText txtNombre, txtDni, txtEmail, txtPhone;
    ImageView arrow;
    FirebaseFirestore db;
    Button btnUpdate;

    public EmployeeDetailFragment() {}

    public static EmployeeDetailFragment newInstance() {
        return new EmployeeDetailFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_employee_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        db = FirebaseFirestore.getInstance();
        btnUpdate = (Button) view.findViewById(R.id.btnUpdate);
        if(getArguments()!=null) nombre = getArguments().getString("nombre");

        Log.d("nombre empleados detail fragment ", nombre);

        txtNombre = (EditText) view.findViewById(R.id.txtNombre);
        txtDni = (EditText) view.findViewById(R.id.txtDni);
        txtEmail = (EditText) view.findViewById(R.id.txtEmail);
        txtPhone = (EditText) view.findViewById(R.id.txtPhone);
        arrow = (ImageView) view.findViewById(R.id.imgArrow);

        setOnClickListenerBack();
        obtenerEmpleadoDetails();
        updateEmpleado();
    }

    private void updateEmpleado() {
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = txtNombre.getText().toString();
                String dni = txtDni.getText().toString();
                String email = txtEmail.getText().toString();
                String phone = txtPhone.getText().toString();
                Map<String, Object> user = new HashMap<>();
                if(!name.isEmpty() || !dni.isEmpty() || !email.isEmpty() || !phone.isEmpty()){
                    System.out.print("holaaaa");
                    user.put(NAME, name);
                    user.put(DNI, dni);
                    user.put(EMAIL, email);
                    user.put(PHONE, phone);
                    db.collection(EMPLOYE).document(nombre).update(user);
                    db.collection(EMPLOYE).document(name);
                    DynamicToast.makeSuccess(requireContext(), "Actualizado", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setOnClickListenerBack() {
        arrow.setOnClickListener(view -> Navigation.findNavController(view).navigate(R.id.empleadosFragment));
    }

    private void obtenerEmpleadoDetails() {
        DocumentReference userRef = db.collection(EMPLOYE).document(nombre);
        userRef.get().addOnCompleteListener(task -> {
            if(task.isSuccessful() && task.isComplete()){
                DocumentSnapshot doc = task.getResult();
                if(doc.exists()){

                    String nombre = doc.getString(NAME);
                    String dni = doc.getString(DNI);
                    String email = doc.getString(EMAIL);
                    String phone = doc.getString(PHONE);
                    txtNombre.setText(nombre);
                    txtDni.setText(dni);
                    txtEmail.setText(email);
                    txtPhone.setText(phone);
                }else{
                    DynamicToast.makeError(requireContext(),"Usuario no encontrado", Toast.LENGTH_SHORT).show();
                }
            }else{
                Log.d("Error","Error: ",task.getException());
            }
        });

    }
}
