package com.example.prueba;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CreateAccountFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreateAccountFragment extends Fragment {
    Button btnCreateAccount;
    private Fragment fragment;
    private final FragmentManager fm = getActivity().getSupportFragmentManager();
    private final FragmentTransaction ft = fm.beginTransaction();




    public CreateAccountFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static CreateAccountFragment newInstance(String param1, String param2) {

        return new CreateAccountFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setOnClickListener();
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_account, container, false);
        btnCreateAccount = (Button) view.findViewById(R.id.btnCreate);
        return view;
    }

    private void setOnClickListener() {
        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment = new CreateAccountFragment();
                fm.beginTransaction().add(R.id.contenedorZonaInterior, fragment);
                ft.commit();
            }
        });
    }
}