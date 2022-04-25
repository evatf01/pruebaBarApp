package com.example.prueba;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LogInFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LogInFragment extends Fragment {
    private Fragment fragment;
    private  Button signIn;
    private TextView txtRegistro;

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
        View view = inflater.inflate(R.layout.fragment_log_in, container, false);
        signIn = (Button) view.findViewById(R.id.btnSignIn);
        txtRegistro = (TextView) view.findViewById(R.id.txtRegistrar);

        ButtonSetOncLickListener();
        RegisterSetOnClickListener();

        return  view;
    }

    //AL PULSAR EL TEXTO DE REGISTRATE, LANZA EL FRAGMENT PARA CREAR TU CUENTA
    private void RegisterSetOnClickListener() {
        txtRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment = new CreateAccountFragment();
                requireActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.logInFragment, fragment)
                        .addToBackStack(LogInFragment.class.getName())
                        .commit();

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
                fragment = new TablesInteriorFragment();
                requireActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.logInFragment, fragment)
                        .addToBackStack(LogInFragment.class.getName())
                        .commit();
            }
        });
    }
}