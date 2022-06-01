package com.example.barmanagement;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ReceiptFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ReceiptFragment extends Fragment {
    private TextView txtNombre, txtCant, txtPrecio;
    ScrollView txtScroll;

    public ReceiptFragment() {
    }


    // TODO: Rename and change types and number of parameters
    public static ReceiptFragment newInstance() {
        return new ReceiptFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_receipt, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        txtNombre = (TextView) view.findViewById(R.id.txtNombre);
        txtScroll = (ScrollView) view.findViewById(R.id.scroll);
        //txtCant = (TextView) view.findViewById(R.id.txtCant);
        //txtPrecio = (TextView) view.findViewById(R.id.txtPrecio);
        List<Map<String, Object>> listaOrdenes = DrinksFragment.getRefrescos();
        //txtNombre.setText(listaOrdenes.toString());
        for (int i = 0; i < listaOrdenes.size(); i++) {
            HashMap<String, Object> data = (HashMap<String, Object>) listaOrdenes.get(i);
            Set<String> key = data.keySet();
            Iterator<String> it = key.iterator();

            while (it.hasNext()) {
                String keyData = (String) it.next();
                Object num = data.get(keyData);
                Log.d("key ", keyData);
                Log.d("num ", String.valueOf(num));
                txtNombre.setText(String.format("%s %s", keyData, num));

                GsonBuilder gsonMapBuilder = new GsonBuilder();

                Gson gsonObject = gsonMapBuilder.create();

                String JSONObject = gsonObject.toJson(data);

                Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
                String prettyJson = prettyGson.toJson(data);
                Log.d("json", prettyJson);


                txtNombre.setText(prettyJson);
                txtScroll.addView(txtNombre);

            }
        }

    }

}