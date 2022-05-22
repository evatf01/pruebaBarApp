package com.example.barmanagement;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AdminMenuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AdminMenuFragment extends Fragment {


    public AdminMenuFragment() { }

    // TODO: Rename and change types and number of parameters
    public static AdminMenuFragment newInstance(String param1, String param2) {
        return new AdminMenuFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_admin_menu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView  txtStock = (TextView) view.findViewById(R.id.txtStock);
        TextView  txtEmpleados = (TextView) view.findViewById(R.id.txtEmpleados);

        setOnClickListenerStock(view);
        setOnClickListenerEmpleados(view);

    }

    private void setOnClickListenerEmpleados(View view) {
    }

    private void setOnClickListenerStock(View view) {
        Navigation.findNavController(view).navigate(R.id.stockFragment);

    }
}