package com.example.prueba;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class BebidasFragment extends Fragment {



    public BebidasFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static BebidasFragment newInstance(String param1, String param2) {

        return new BebidasFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bebidas, container, false);
    }
}