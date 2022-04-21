package com.example.prueba;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LogInFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LogInFragment extends Fragment {
    private Fragment fragment;

    private final FragmentManager fm = getActivity().getSupportFragmentManager();
    private final FragmentTransaction ft = fm.beginTransaction();


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
        TextView txtRegistro = (TextView) view.findViewById(R.id.txtRegistrar);

        txtRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment = new CreateAccountFragment();
                fm.beginTransaction().add(R.id.contenedorZonaInterior, fragment);
                ft.commit();
            }
        });
        return  view;
    }
}