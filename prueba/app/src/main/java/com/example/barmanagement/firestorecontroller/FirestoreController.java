package com.example.barmanagement.firestorecontroller;

import static com.example.barmanagement.ComandaFragment.numero;
import static com.example.barmanagement.utils.FirestoreFields.*;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class FirestoreController {
    FirebaseFirestore db;

    public FirestoreController(FirebaseFirestore db) {
        this.db = db;
    }

    public boolean isOnline(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected();
    }


    public void actualizarRefresco(String nombre, String id, Object num, Object stock) {
        db =  FirebaseFirestore.getInstance();
        int cantidad =  Integer.parseInt(num.toString());
        int stockTotal = Integer.parseInt(stock.toString()) - Integer.parseInt(num.toString());
        HashMap<String, Object> refresco = new HashMap<>();
        HashMap<String, Object> setStock = new HashMap<>();
        setStock.put("stock", String.valueOf(stockTotal));
        refresco.put("nombre", nombre);
        refresco.put("cantidad", String.valueOf(cantidad));
        db.collection(COMANDA).document(numero).collection("orden").document(nombre).set(refresco);
        db.collection(CATEGORIAS).document("bebidas").collection("refrescos").document(id).update(setStock);

    }

    public void actualizarCerveza(String nombre, String id, Object num, Object stock) {
        db =  FirebaseFirestore.getInstance();
        int cantidad =  Integer.parseInt(num.toString());
        int stockTotal = Integer.parseInt(stock.toString()) - Integer.parseInt(num.toString());
        HashMap<String, Object> refresco = new HashMap<>();
        HashMap<String, Object> setStock = new HashMap<>();
        setStock.put("stock", String.valueOf(stockTotal));
        refresco.put("nombre", nombre);
        refresco.put("cantidad", String.valueOf(cantidad));
        db.collection(COMANDA).document(numero).collection("orden").document(nombre).set(refresco);
        db.collection(CATEGORIAS).document("bebidas").collection("cervezas").document(id).update(setStock);

    }
    public void actualizarCafes(String nombre, String id, Object num, Object stock) {
        db =  FirebaseFirestore.getInstance();
        int cantidad =  Integer.parseInt(num.toString());
        int stockTotal = Integer.parseInt(stock.toString()) - Integer.parseInt(num.toString());
        HashMap<String, Object> refresco = new HashMap<>();
        HashMap<String, Object> setStock = new HashMap<>();
        setStock.put("stock", String.valueOf(stockTotal));
        refresco.put("nombre", nombre);
        refresco.put("cantidad", String.valueOf(cantidad));
        db.collection(COMANDA).document(numero).collection("orden").document(nombre).set(refresco);
        db.collection(CATEGORIAS).document("bebidas").collection("cafes").document(id).update(setStock);

    }

    public void actualizarMasBebidas(String nombre, String id, Object num, Object stock) {
        db =  FirebaseFirestore.getInstance();
        int cantidad =  Integer.parseInt(num.toString());
        int stockTotal = Integer.parseInt(stock.toString()) - Integer.parseInt(num.toString());
        HashMap<String, Object> refresco = new HashMap<>();
        HashMap<String, Object> setStock = new HashMap<>();
        setStock.put("stock", String.valueOf(stockTotal));
        refresco.put("nombre", nombre);
        refresco.put("cantidad", String.valueOf(cantidad));
        db.collection(COMANDA).document(numero).collection("orden").document(nombre).set(refresco);
        db.collection(CATEGORIAS).document("bebidas").collection("mas_bebidas").document(id).update(setStock);

    }

}
