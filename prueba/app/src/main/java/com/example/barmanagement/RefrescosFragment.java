package com.example.barmanagement;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class RefrescosFragment extends Fragment {



    public RefrescosFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static RefrescosFragment newInstance(String param1, String param2) {

        return new RefrescosFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_refrescos, container, false);
        BottomNavigationView nav = (BottomNavigationView) view.findViewById(R.id.bottomNavigationView);
        nav.setItemIconTintList(null);
        // Inflate the layout for this fragment
        return view;
    }
}