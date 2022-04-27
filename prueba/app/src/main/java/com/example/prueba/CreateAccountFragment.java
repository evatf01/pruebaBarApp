package com.example.prueba;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CreateAccountFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreateAccountFragment extends Fragment {
    AppCompatButton btnCreateAccount;
    ImageView arrow;
    private Fragment fragment;

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

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_account, container, false);
        btnCreateAccount = (AppCompatButton) view.findViewById(R.id.btnCreate);
        arrow = (ImageView) view.findViewById(R.id.imbArrowBack);
        setOnClickListenerCreate();
        setOnClickListenerBack();
        return view;
    }

    private void setOnClickListenerBack() {
        arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment = new LogInFragment();
                requireActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.createAccountFragment, fragment)
                        .addToBackStack(CreateAccountFragment.class.getName())
                        .commit();
            }
        });
    }

    private void setOnClickListenerCreate() {
        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment = new TablesInteriorFragment();
                requireActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.createAccountFragment, fragment)
                        .addToBackStack(LogInFragment.class.getName())
                        .commit();
            }
        });
    }
}