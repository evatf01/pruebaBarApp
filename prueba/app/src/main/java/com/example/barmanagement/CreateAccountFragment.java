package com.example.barmanagement;

import static com.example.barmanagement.utils.FirestoreFields.*;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.barmanagement.controllers.SqliteController;
import com.example.barmanagement.firestorecontroller.FirestoreController;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.pranavpandey.android.dynamic.toasts.DynamicToast;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CreateAccountFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreateAccountFragment extends Fragment {
    private AppCompatButton btnCreateAccount;
    private ImageView arrow;
    private EditText txtName, txtPassword, txtDni, txtEmail, txtPhone;
    private FirebaseFirestore db;
    private SqliteController sqliteController;
    private FirestoreController firestoreController;
    public CreateAccountFragment() { }


    // TODO: Rename and change types and number of parameters
    public static CreateAccountFragment newInstance(String param1, String param2) {

        return new CreateAccountFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_create_account, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        firestoreController = new FirestoreController(this.db);
        sqliteController = new SqliteController();
        db =  FirebaseFirestore.getInstance();
        btnCreateAccount = (AppCompatButton) view.findViewById(R.id.btnCreate);
        arrow = (ImageView) view.findViewById(R.id.imgArrow);
        txtName = (EditText) view.findViewById(R.id.txtNombre);
        txtPassword = (EditText) view.findViewById(R.id.txtPassword);
        txtDni = (EditText) view.findViewById(R.id.txtDni);
        txtEmail = (EditText) view.findViewById(R.id.txtEmail);
        txtPhone = (EditText) view.findViewById(R.id.txtPhone);



        setOnClickListenerCreate();
        setOnClickListenerBack();
/*
       */
    }

    private void setOnClickListenerBack() {
        arrow.setOnClickListener(view -> {
             Navigation.findNavController(view).navigate(R.id.logInFragment2);
        });
    }

    private void setOnClickListenerCreate() {
        btnCreateAccount.setOnClickListener(view -> {
            if (txtName.getText().toString().isEmpty() || txtPassword.getText().toString().isEmpty() || txtDni.getText().toString().isEmpty() || txtEmail.getText().toString().isEmpty()
                    || txtPhone.getText().toString().isEmpty()) {
                DynamicToast.makeWarning(requireContext(), "Rellene los campos", Toast.LENGTH_SHORT).show();
            } else {


                if(firestoreController.isOnline(requireContext())) {
                    createUser(view);
                }

            }

        });
    }

    private void createUser(View view) {
            DocumentReference userRef = db.collection(EMPLOYE).document(txtName.getText().toString());
            userRef.get().addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        DocumentSnapshot doc = task.getResult();
                        if(doc.exists()){
                            DynamicToast.makeError(requireContext(),"Nombre de usuario ya existe", Toast.LENGTH_SHORT).show();
                        }else{
                            String name = txtName.getText().toString();
                            String password = txtPassword.getText().toString();
                            String dni = txtDni.getText().toString();
                            String email = txtEmail.getText().toString();
                            String phone = txtPhone.getText().toString();

                            sqliteController.createUserSqlite(getContext(), name, password, dni, email, phone);
                            Map<String, Object> user = new HashMap<>();
                            user.put(NAME, name);
                            user.put(PASSWORD, password);
                            user.put(DNI, dni);
                            user.put(EMAIL, email);
                            user.put(PHONE,phone);
                            db.collection(EMPLOYE).document(txtName.getText().toString()).set(user);
                            Navigation.findNavController(view).navigate(R.id.tablesInteriorFragment);
                            DynamicToast.makeSuccess(requireContext(), "Cuenta creada", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Log.d("Error","Error: ",task.getException());
                    }

            });

        }
}