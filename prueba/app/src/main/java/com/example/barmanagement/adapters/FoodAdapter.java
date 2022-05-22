package com.example.barmanagement.adapters;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.barmanagement.R;
import com.example.barmanagement.models.Category;
import com.example.barmanagement.models.Comanda;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder> {
    List<Category> categorias = new ArrayList<>();
    Context context;
    private final RecyclerViewClickListener listener; // interfaz que me he creado para poder hacer onClick en el recyclerView
    public List<HashMap<String, Object>>extra;


    public FoodAdapter(List<Category> categorias, Context context, RecyclerViewClickListener listener) {
        this.categorias = categorias;
        this.context = context;
        this.listener = listener;
        extra = new ArrayList<>();

    }



    public  interface RecyclerViewClickListener{
        void onClick(View view, int position);
    }
    public List<HashMap<String, Object>> getExtra() {
        return extra;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tapas_item,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        holder.txtFood.setText(categorias.get(position).getNombre());
        holder.txtExtra.setText(categorias.get(position).getCantidad());
        categorias.get(position).getPrecio();
    }

    @Override
    public int getItemCount() {
        return categorias.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        // componentes del RecyclerView

        TextView txtFood;
        EditText txtExtra;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtFood = (TextView) itemView.findViewById(R.id.txtFood);
            txtExtra = (EditText) itemView.findViewById(R.id.txtExtra);

            MyTextWatcher myTextWatcher = new MyTextWatcher(txtExtra,txtFood);
            txtExtra.addTextChangedListener(myTextWatcher);
            itemView.setOnClickListener(this);

        }
        // implementamos el metodo onClick, donde usaremos el metodo de la interfaz creada anteriormente
        @Override
        public void onClick(View v) {

            listener.onClick(v,getBindingAdapterPosition());
        }
    }
    public class MyTextWatcher implements TextWatcher {
        private EditText txtExtra;
        private TextView txtBebida;


        public MyTextWatcher(EditText txtExtra, TextView txtBebida) {
            this.txtExtra = txtExtra;
            this.txtBebida = txtBebida;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            String cantidad = String.valueOf(txtExtra.getText());
            HashMap<String,Object> map= new HashMap<>();
            map.put(txtBebida.getText().toString(),cantidad);

            extra.add(map);


        }
        @Override
        public void afterTextChanged(Editable s) { }
    }
}
