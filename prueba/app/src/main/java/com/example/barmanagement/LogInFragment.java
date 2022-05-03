package com.example.barmanagement;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.FirebaseFirestore;
import com.pranavpandey.android.dynamic.toasts.DynamicToast;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LogInFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LogInFragment extends Fragment {
    private Fragment fragment;
    private  Button signIn;
    private TextView txtRegistro;
    private EditText txtUser, txtPassword;
    FirebaseFirestore db;

    //private  final FragmentManager fm =  LogInFragment.class.getSupportFragmentManager();
    //)  private final FragmentTransaction ft = fm.beginTransaction();


    public LogInFragment() {  }

    public static LogInFragment newInstance(String param1, String param2) {

        return new LogInFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_log_in, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        signIn = (Button) view.findViewById(R.id.btnSignIn);
        txtRegistro = (TextView) view.findViewById(R.id.txtRegistrar);
        txtUser = (EditText)view.findViewById(R.id.txtUser);
        txtPassword = (EditText)view.findViewById(R.id.txtPassword);
        FirebaseApp.initializeApp(requireContext());
        db =  FirebaseFirestore.getInstance();
        FirebaseApp.initializeApp(requireContext());


        ButtonSetOncLickListener();
        RegisterSetOnClickListener();
    }

    //AL PULSAR EL TEXTO DE REGISTRATE, LANZA EL FRAGMENT PARA CREAR TU CUENTA
    private void RegisterSetOnClickListener() {
        txtRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.createAccountFragment2);

               /* fm.beginTransaction().add(R.id.contenedorZonaInterior, fragment);
                ft.commit();*/
            }
        });
    }

    //DESDE EL BOTON DE LOGEARSE VOY AL FRAGMENT DONDE ESTÁN LAS MESAS QUE TIENE EL BAR
    private void ButtonSetOncLickListener() {

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtUser.getText().toString().isEmpty() || txtPassword.getText().toString().isEmpty()){
                    DynamicToast.makeWarning(requireContext(),"Rellene los campos", Toast.LENGTH_SHORT).show();
                }else{
                    Map<String, Object> user = new HashMap<>();
                    user.put("name", txtUser.getText().toString());
                    user.put("password", txtPassword.getText().toString());
                    db.collection("Users").document().set(user);
                    Navigation.findNavController(view).navigate(R.id.tablesInteriorFragment);
                }

            }
        });
    }
}